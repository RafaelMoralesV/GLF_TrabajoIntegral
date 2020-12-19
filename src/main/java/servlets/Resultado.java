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
		 * Este servlet retorna una respuesta HTML de contenido application/json
		 * con el resultado del algoritmo de fuerza bruta que genera caminos,
		 * y contiene toda la informacion necesaria para deducir el orden y
		 * toda la otra informacion relacionadas a estos.
		 * 
		 * Estructura del Json
		 * [ camino, camino, camino ]
		 * donde camino es un objeto de la siguiente forma
		 * { id: { id de la entidad }, list: [ e1, e2, ..., en ] }
		 * donde id es la id de un centro de
		 * distribucion, y lista contiene una lista de objetos en la
		 * en la siguiente estructura:
		 * { id: { id de la entidad }, producto: { producto necesitado por la entidad } }
		 * que corresponde a la informacion almacenada en la clase logical.PuntoDeVenta.java
		 */
		
		List<Camino> resultado = DAO.getLista();
		JSONArray caminos = new JSONArray();
		
		try {
			for(int i = 0; i < resultado.size(); i++) {
				// Ingresa cada camino como lista
				Camino c = resultado.get(i);
				JSONObject camino = new JSONObject();
				JSONArray ids = new JSONArray();
				LinkedList<PuntoDeVenta> l = (LinkedList<PuntoDeVenta>) c.getCamino();
				for(int j = 0; j < l.size(); j++) {
					// Ingresa la informacion de cada punto de venta
					// en el objeto relacionado al centro de distribucion
					// en la propiedad 'list'
					PuntoDeVenta pdv = l.get(j);
					JSONObject id = new JSONObject();
					id.put("id", pdv.getIdentificador());
					id.put("producto", pdv.getNeededProduct());
					ids.put(id);
				}
				// Agrega la informacion descrita antes al objeto JSON
				camino.put("id", c.getInicio().getIdentificador());
				camino.put("list", ids);
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
