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
	private static final int ARBITRARYSIZE = 1048;
	private static final long serialVersionUID = 1L;
	protected static final Logger LOGGER = LogManager.getLogger(FileCleaner.class.getName());

    public FileCleaner() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * Este metodo enviar un archivo dump.txt como respuesta, cuyo contenido es
		 * el de todas las lineas que fueron rechazadas al momento de escribirse el archivo
		 * target.txt que contiene la lista de entidades.
		 */
		
		response.setContentType("text/plain");
        response.setHeader("Content-disposition", "attachment; filename=dump.txt");

        
        try(InputStream in = this.getServletContext().getResourceAsStream("dump.txt");
          OutputStream out = response.getOutputStream()) {

            byte[] buffer = new byte[ARBITRARYSIZE];
        
            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
            String path = this.getServletContext().getRealPath("/");
            FileUtils.write(new File(path + "dump.txt"), "", Charset.defaultCharset());
        } catch (IOException ioe) {
        	LOGGER.error("Ha ocurrido un error al intentar enviar el archivo dump.txt");
        	LOGGER.debug(ioe.getStackTrace());
        }
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String path = this.getServletContext().getRealPath("/");
			FileUtils.write(new File(path + "target.txt"), "", Charset.defaultCharset());
			LOGGER.debug("Archivo limpiado exitosamente.");
		} catch(IOException e) {
			LOGGER.fatal("No pudo limpiarse los contenidos de target.txt\n{}", e.getMessage());
		}
		response.setStatus(200);
		response.setContentType("text/plain");
		try {
		response.getWriter().append("Se ha vaciado la lista con exito");
		} catch (IOException ioe) {
			LOGGER.fatal("No se pudo enviar una respuesta al usuario.");
		}
	}

}
