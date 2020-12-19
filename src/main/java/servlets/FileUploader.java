package servlets;

import java.io.File;
import java.io.FileWriter;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import logical.SyntaxChecker;

/**
 * Servlet implementation class FileUploader
 */
public class FileUploader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected static final Logger LOGGER = LogManager.getLogger(FileUploader.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * Evita que haya comunicacion con este servlet en un metodo get.
		 */
		try {
			response.sendError(405, "Esta URL solo deberia ser utilizada para postear archivos");
		} catch (Exception e) {
			LOGGER.error("Ha ocurrido un error en doGet()");
			LOGGER.error(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * Este servlet recibe un archivo del usuario. Este archivo es revisado con un
		 * REGEX y subido al servidor donde puede ser accedido por otros servlets.
		 */
		
		int statusCode = HttpServletResponse.SC_OK;
		try {
			this.procesar(request);
		} catch (FileUploadException e) {
			// En caso de que no se pueda recibir el archivo, se envia un codigo de error al
			// usuario.
			String error = "Se ha intentado parsear un archivo desde el objeto request, sin exito.";
			LOGGER.warn(error);
			LOGGER.warn(e);
			statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			
		} catch (IOException e) {
			String error = "No se ha podido acceder al archivo. Revise que efectivamente se haya seleccionado y subido un archivo.";
			LOGGER.warn(error);
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		
		try {
			response.setContentType("text/plain");
			response.setStatus(statusCode);
			switch (statusCode) {
			case 200:
				response.getWriter().append("Se ha subido el archivo de forma exitosa.");
				break;
			case 400:
				response.sendError(statusCode, "No se ha podido acceder al archivo subido.");
				break;
			case 500:
				response.sendError(statusCode, "Ha ocurrido un error al procesar el archivo.");
			}
		} catch (IOException e) {
			LOGGER.fatal("No se ha podido enviar una respuesta\n{}", e.getMessage());
		}
	}
	
	private void procesar(HttpServletRequest request) 
			throws FileUploadException, IOException {
		ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
		String path = this.getServletContext().getRealPath("/");;
		File f = new File(path + "target.txt");;
		
		List<FileItem> files = sfu.parseRequest(request);
		for (FileItem item : files) {
			try (FileWriter fw = new FileWriter(f, true)) {
				fw.append(item.getString());
			}
			SyntaxChecker.format(path);
		}
	}

}
