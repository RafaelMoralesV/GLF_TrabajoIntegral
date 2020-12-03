package servlets;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class Enlistador
 */
public class Enlistador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected static final Logger LOGGER = LogManager.getLogger(HelloWorld.class.getName());  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Enlistador() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String path = this.getServletContext().getRealPath("/");
			File f = new File(path + "target.txt");
			List<logical.Entidad> lista = logical.Parser.parseFile(f);
			for(logical.Entidad e : lista) {
				response.getWriter().append(e.toString()).append("\n\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error("No se pudo abrir el archivo.");
			LOGGER.error(e);
			
			try {
				response.sendError(409, "No se ha podido encontrar la lista de puntos. Es posible que esta no haya sido subida.");
			}catch(Exception exp) {
				LOGGER.error("No se ha podido establecer una respuesta.");
				LOGGER.error(exp);
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			response.sendError(405, "Esta URL solo deberia ser utilizada para obtener la lista de puntos.");
		} catch(Exception e) {
			LOGGER.error("Ha ocurrido un error en doGet()");
			LOGGER.error(e);
		}
	}

}
