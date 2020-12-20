package servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import logical.DAO;

/**
 * Servlet implementation class registroCamiones
 */
public class RegistroCamiones extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected static final Logger LOGGER = LogManager.getLogger(RegistroCamiones.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistroCamiones() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.sendError(405, "Esta URL no debe ser accedida con un metodo GET");
		} catch (IOException e) {
			LOGGER.error("No se pudo enviar una respuesta al usuario.\n{}", e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int statusCode = HttpServletResponse.SC_OK;
		
		DAO.limpiar();
		Enumeration<?> e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String id = (String) e.nextElement();
			try {
				int obj = Integer.parseInt(request.getParameter(id));
				for(int i = 0; i < obj; i++) {
					DAO.crear(this.getServletContext().getRealPath("/"), id);
				}
			} catch (NumberFormatException ex) {
				LOGGER.error("No se pudo parsear la ID desde el objeto request.\n{}", ex.getMessage());
				statusCode = HttpServletResponse.SC_BAD_REQUEST;
				break;
			}
		}
		
		try {
			response.setContentType("text/plain");
			if(statusCode == 200) {
				response.setStatus(statusCode);
				response.getWriter().append("Se ha agregado correctamente");
			}
			if(statusCode == 400) {
				response.sendError(statusCode, "Una o mas IDs recibidas eran incorrectas.");
			}
		} catch(IOException ioe) {
			LOGGER.fatal("No se ha podido enviar una respuesta al usuario.\n{}", ioe.getMessage());
			LOGGER.debug(ioe.getStackTrace());
		}
	}

}
