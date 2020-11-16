package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Servlet implementation class HelloWorld
 */
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected static final Logger LOGGER = LogManager.getLogger(HelloWorld.class.getName());
	
    public HelloWorld(){
    	super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
    	try {
    		response.getWriter().append("Served at: ").append(request.getContextPath());
    	}
    	catch (IOException e) {
    		LOGGER.error("Ha ocurrido un error: ", e);
    	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			doGet(request, response);
		}
		catch (IOException|ServletException e) {
			LOGGER.error("Ha ocurrido un error al llamar doGet(): ", e);
    	}
	}

}
