package servlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import logical.Camino;
import logical.DAO;
import logical.PuntoDeVenta;

/**
 * Servlet implementation class Resultado
 */
public class Resultado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected static final Logger LOGGER = LogManager.getLogger(Resultado.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Este servlet retorna una respuesta HTML de contenido text/Json
		 * con el resultado del algoritmo de fuerza bruta que genera caminos,
		 * y contiene toda la informacion necesaria para deducir el orden y
		 * toda la otra informacion relacionadas a estos.
		 * 
		 * Estructura del Json
		 * [ camino, camino, camino ]
		 * donde camino es un objeto de la siguiente forma
		 * EntidadInicio: [ id1, id2, id3, ..., idn]
		 * donde entidadInicio es la id de un centro de
		 * distribucion, y el resto de ids un objeto en la siguiente
		 * estructura:
		 * { id: productoQueNecesita }, que corresponde a la informacion
		 * almacenada en la clase logical.PuntoDeVenta.java
		 */
		
		List<Camino> resultado = DAO.getLista();
		JSONArray caminos = new JSONArray();
		
		try {
			for(int i = 0; i < resultado.size(); i++) {
				Camino c = resultado.get(i);
				JSONObject camino = new JSONObject();
				JSONArray ids = new JSONArray();
				LinkedList<PuntoDeVenta> l = (LinkedList<PuntoDeVenta>) c.getCamino();
				for(int j = 0; j < l.size(); j++) {
					PuntoDeVenta pdv = l.get(j);
					JSONObject id = new JSONObject();
					id.put(String.valueOf(pdv.getIdentificador()),
							pdv.getNeededProduct());
					ids.put(id);
				}
				camino.put(String.valueOf(c.getInicio().getIdentificador()), ids);
				caminos.put(camino);
			}
		} catch(JSONException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			LOGGER.error("Ha ocurrido un error al escribir el documento JSON.\n{}", e.getMessage());
		}
		response.setContentType("application/json");
		response.getWriter().append(caminos.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
