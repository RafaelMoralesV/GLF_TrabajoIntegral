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
	
    public HelloWorld(){
    	super();
    	
    	LogManager.getLogManager().reset();
    	LOGGER.setLevel(Level.ALL);
    	
    	ConsoleHandler ch = new ConsoleHandler();
    	ch.setLevel(Level.SEVERE);
    	LOGGER.addHandler(ch);
    	
    	try {
    		// GLF_TIG3_Servlet ==
    		// Grafos y Lenguajes Formales
    		// Trabajo Integral Grupo 3
        	FileHandler fh = new FileHandler("GLF_TIG3_Servlet.log", true);
        	fh.setFormatter(new SimpleFormatter());
        	fh.setLevel(Level.FINE);
        	LOGGER.addHandler(fh);
    	}
    	catch(Exception e) {
    		LOGGER.log(Level.SEVERE, "No pudo inicializarse el Log en archivo", e);
    	}
    	
    	LOGGER.severe("Inicializado servlet: " + LOGGER.getName());
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
