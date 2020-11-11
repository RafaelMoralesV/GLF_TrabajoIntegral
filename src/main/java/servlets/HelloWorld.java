package servlets;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWorld
 */
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    public HelloWorld() {
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
    	catch (UnknownHostException uhex) {
    		// Hay que logear esto
    		return;
    	}
    	catch (IOException ioe) {
    		// Hay que logear esto
    		return;
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
		catch (IOException ioe) {
    		// Hay que logear esto
    		return;
    	}
		catch (ServletException se) {
    		// Hay que logear esto
    		return;
    	}
		
	}

}
