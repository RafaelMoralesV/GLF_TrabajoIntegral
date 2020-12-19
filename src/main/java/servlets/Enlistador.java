package servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class Enlistador
 */
public class Enlistador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected static final Logger LOGGER = LogManager.getLogger(Enlistador.class.getName());

	public Enlistador() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * Este servlet actualmente retorna una version string de todas las entidades
		 * que son cargables desde el archivo target.txt
		 */
		
		int statusCode = HttpServletResponse.SC_OK; // SC_OK = 200;
		List<logical.Entidad> lista = new ArrayList<>();
		try {
			String path = this.getServletContext().getRealPath("/");
			File f = new File(path + "target.txt");
			lista = logical.Parser.parseFile(f);
		} catch(IOException e) {
			LOGGER.error("No se pudo abrir el archivo.");
			LOGGER.error(e);
			statusCode = HttpServletResponse.SC_CONFLICT;
		}
		
		if(statusCode == 409) {
			try {
				response.sendError(statusCode, "No se ha podido leer el archivo de entidades. Quizas este no ha sido subido aun.");
			} catch (IOException e) {
				LOGGER.fatal("No se ha podido enviar el codigo de error 409 al usuario.\n{}", e.getMessage());
				LOGGER.debug(e.getStackTrace());
			}
			return;
		}

			// Preparando respuesta JSON
		response.setContentType("application/json");
		JSONArray res = new JSONArray();
		for (logical.Entidad e : lista) {
			JSONObject json = new JSONObject();
			try {
				json.put("Tipo", String.valueOf(e.getTipo()));
				json.put("ID", e.getIdentificador());
				json.put("posX", e.getPosicionX());
				json.put("posY", e.getPosicionY());
				res.put(json);
			} catch (JSONException ex) {
				LOGGER.error("Ha ocurrido un error al parsear el archivo JSON");
				LOGGER.debug(ex.getStackTrace());
				statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				break;
			}
		}
		if (statusCode == 500) {
			try {
				response.sendError(statusCode, "Ha ocurrido un error al formular la respuesta JSON.");
			} catch(IOException e) {
				LOGGER.fatal("No se ha podido enviar el codigo de error 500 al usuario.\n{}", e.getMessage());
				LOGGER.debug(e.getStackTrace());
			}
		}
		
		if(statusCode == 200) {
			try {
				response.setContentType("application/json");
				response.setStatus(statusCode);
				response.getWriter().append(res.toString());
			} catch (IOException e1) {
				LOGGER.fatal("No se ha podido enviar la respuesta JSON al usuario.\n{}", e1.getMessage());
				LOGGER.debug(e1.getStackTrace());
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * Intenta enviar un status de error en caso de que se intente acceder al metodo
		 * post de este servlet.
		 */

		try {
			response.sendError(405, "Esta URL solo deberia ser utilizada para obtener la lista de puntos.");
		} catch (IOException e) {
			LOGGER.error("No se ha podido enviar el codigo de error 405 al usuario");
			LOGGER.error(e);
		}
	}

}
