package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.logging.*;

/**
 * Servlet implementation class HelloWorld
 */
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(HelloWorld.class.getName());

	
    public HelloWorld() {
    	super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
    	LOGGER.info("Logger name: " + LOGGER.getName());
    	LOGGER.warning("Puede generar IOException");
    	try {
    		response.getWriter().append("Served at: ").append(request.getContextPath());
    	}
    	catch (IOException e) {
    		// Hay que logear esto
    		LOGGER.log(Level.SEVERE, "Ocurrio una Excepcion: ", e);
    	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.warning("Llamando a doGet()");
    	try {
			doGet(request, response);
		}
		catch (IOException|ServletException e) {
    		// Hay que logear esto
    		LOGGER.log(Level.SEVERE, "Ocurrio una Excepcion: ", e);
    	}
	}

}
