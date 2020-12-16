package servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
	private final int ARBITARY_SIZE = 1048;
	private static final long serialVersionUID = 1L;
	protected static final Logger LOGGER = LogManager.getLogger(FileCleaner.class.getName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileCleaner() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
        response.setHeader("Content-disposition", "attachment; filename=dump.txt");

        
        try(InputStream in = this.getServletContext().getResourceAsStream("dump.txt");
          OutputStream out = response.getOutputStream()) {

            byte[] buffer = new byte[ARBITARY_SIZE];
        
            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
            FileUtils.write(new File(this.getServletContext().getRealPath("/" + "dump.txt")), "", Charset.defaultCharset());
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			FileUtils.write(new File(this.getServletContext().getRealPath("/" + "target.txt")), "", Charset.defaultCharset());
			LOGGER.debug("Archivo limpiado exitosamente.");
		} catch(IOException e) {
			LOGGER.fatal("No pudo limpiarse los contenidos de target.txt\n{}", e.getMessage());
		}
		response.setStatus(200);
		response.setContentType("text/plain");
		response.getWriter().append("Se ha vaciado la lista con exito");
	}

}
