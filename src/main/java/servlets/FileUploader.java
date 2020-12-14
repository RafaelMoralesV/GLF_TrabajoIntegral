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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Evita que haya comunicacion con este servlet en un metodo get.
		 */
		try{
			response.sendError(405, "Esta URL solo deberia ser utilizada para postear archivos");
		} catch(Exception e) {
			LOGGER.error("Ha ocurrido un error en doGet()");
			LOGGER.error(e);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Este servlet recibe un archivo del usuario.
		 * Este archivo es revisado con un REGEX y subido al servidor donde puede ser accedido por otros
		 * servlets.
		 */
		// TODO: Verificar IDs y eliminar las que se repitan
		ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
		String path = null;
		File f = null;
		try {
			// Parsear lista de archivos recibidos
			// Solo se espera 1, pero se agrega toda la informacion obtenida a 'target.txt'
			List<FileItem> files = sfu.parseRequest(request);
			for(FileItem item : files) {
				path = this.getServletContext().getRealPath("/");
				f = new File(path + "target.txt");
				FileWriter fw = new FileWriter(f, true);
				fw.append(item.getString());
				fw.close();
				SyntaxChecker.format(path);
			}
		} catch (FileUploadException e) {
			// En caso de que no se pueda recibir el archivo, se envia un codigo de error al usuario.
			String error = "Se ha intentado parsear un archivo desde el objeto request, sin exito.";
			LOGGER.warn(error);
			LOGGER.warn(e);
			try {
				response.sendError(500, error);
			} catch (Exception exc) {
				LOGGER.error("No se ha podido enviar el codigo de error");
				LOGGER.error(exc);
			}
		} catch (Exception e) {
			String error = "No se ha podido acceder al archivo. Revise que efectivamente se haya seleccionado y subido un archivo.";
			LOGGER.warn(error);
			LOGGER.warn(path);
			if(f != null) {
				LOGGER.warn(f.getName());
			}
			try {
				response.sendError(400, error);
			} catch (Exception exc) {
				LOGGER.error("No se ha podido enviar el codigo de error");
				LOGGER.error(exc);
			}
		}
		// Se envia al usuario a la pagina de inicio
		// TODO este redirect debe mandar a una pagina con mas sentido.
		response.sendRedirect(this.getServletContext().getContextPath());
	}

}
