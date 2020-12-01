package servlets;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.apache.logging.log4j.Logger;

import logical.SyntaxChecker;

import org.apache.logging.log4j.LogManager;

/**
 * Servlet implementation class HelloWorld
 */

public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected static final Logger LOGGER = LogManager.getLogger(HelloWorld.class.getName());
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(405, "Esta URL solo deberia ser utilizada para postear archivos");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
		String path = null;
		File f = null;
		try {
			// Parsear lista de archivos recibidos
			// Solo se espera 1, pero se guardan todos los necesarios
			List<FileItem> files = sfu.parseRequest(request);
			for(FileItem item : files) {
				path = this.getServletContext().getRealPath("/");
				f = new File(path + item.getName());
				item.write(f);
				SyntaxChecker.Validar(f);
			}
		} catch (FileUploadException e) {
			String error = "Se ha intentado parsear un archivo desde el objeto request, sin exito.";
			LOGGER.warn(error);
			LOGGER.warn(e);
			response.sendError(500, error);
		} catch (Exception e) {
			String error = "No se ha podido acceder al archivo. Revise que efectivamente se haya seleccionado y subido un archivo.";
			LOGGER.warn(error);
			LOGGER.warn(path);
			LOGGER.warn(f.getName());
			response.sendError(400, error);
		}
	}

}
