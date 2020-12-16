package servlets;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class FileCleaner
 */
public class FileCleaner extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected static final Logger LOGGER = LogManager.getLogger(FileCleaner.class.getName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileCleaner() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String path = this.getServletContext().getRealPath("/" + "target.txt");
			LOGGER.debug(path);
			FileUtils.write(new File(path), "", Charset.defaultCharset());
			LOGGER.debug("Archivo limpiado exitosamente.");
		} catch(IOException e) {
			LOGGER.fatal("No pudo limpiarse los contenidos de target.txt\n{}", e.getMessage());
		}
		response.setStatus(200);
		response.setContentType("text/plain");
		response.getWriter().append("Se ha vaciado la lista con exito");
	}

}
