package servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO postear desde un archivo
		response.getWriter().append("10");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Agregar lo que se postee a un archivo
		Enumeration<?> e = request.getParameterNames();
		while(e.hasMoreElements()) {
			String id = (String) e.nextElement();
			int obj = Integer.parseInt(request.getParameter(id));
			LOGGER.info("(ID): {}\t(VALUE): {}", id, obj);
		}
		response.sendError(501, "Aun no implementado");
	}

}
