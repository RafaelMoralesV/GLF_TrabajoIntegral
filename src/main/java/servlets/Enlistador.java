package servlets;

import java.io.File;
import java.io.IOException;
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
		try {
			String path = this.getServletContext().getRealPath("/");
			File f = new File(path + "target.txt");
			List<logical.Entidad> lista = logical.Parser.parseFile(f);

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
					try {
						response.sendError(500, "No se ha podido generar una respuesta.");
					} catch (IOException exc) {
						LOGGER.error("No se ha podido enviar un codigo de error al usuario.\n{}", exc.getMessage());
					}
				}
			}
			try {
			response.getWriter().append(res.toString());
			} catch(IOException e) {
				LOGGER.error("No se pudo enviar la respuesta JSON al usuario.\n{}", e.getMessage());
			}
		} catch (IOException e) {
			LOGGER.error("No se pudo abrir el archivo.");
			LOGGER.error(e);

			try {
				response.sendError(409,
						"No se ha podido encontrar la lista de puntos. Es posible que esta no haya sido subida.");
			} catch (Exception exp) {
				LOGGER.error("No se ha podido establecer una respuesta.");
				LOGGER.error(exp);
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
		} catch (Exception e) {
			LOGGER.error("Ha ocurrido un error en doGet()");
			LOGGER.error(e);
		}
	}

}
