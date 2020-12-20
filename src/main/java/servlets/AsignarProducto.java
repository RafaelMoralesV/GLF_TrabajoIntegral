package servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import logical.DAO;

public class AsignarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected static final Logger LOGGER = LogManager.getLogger(AsignarProducto.class.getName());

	
	private void procesar(Enumeration<?> e, HttpServletRequest request) {
		String idSel = (String) e.nextElement();
		String idCD = request.getParameter(idSel);
		String idProd = (String) e.nextElement();
		String resProd = request.getParameter(idProd);
		
		String[] idPDV = idSel.split("-");
		int producto = Integer.parseInt(resProd);
		if(producto < 0 || producto > 1000) {
			throw new IllegalStateException("La cantidad de producto ingresada es invalida.");
		}
		if(producto == 0) {
			// se asume en este caso que este punto de venta no requiere visita.
			return;
		}
		if(!DAO.agregar(this.getServletContext().getRealPath("/"), idCD, idPDV[1], producto)) {
			throw new InsertionException("No se pudo insertar la entidad en ningun camino.");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int statusCode = HttpServletResponse.SC_OK;
		
		DAO.vaciarCaminos();
		Enumeration<?> e = request.getParameterNames();
		while (e.hasMoreElements()) {
			try {
				this.procesar(e, request);
			} catch (NullPointerException npe) {
				LOGGER.error("Se ha intentado agregar un punto de venta a un centro que no posee camiones hoy.");
				statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				break;
			} catch (InsertionException ie) {
				LOGGER.error("No se ha podido asignar una entidad a ningun camino. Esto puede ocurrir cuando no existen caminos disponibles donde agregar esta entidad");
				statusCode = HttpServletResponse.SC_CONFLICT;
				break;
			} catch(NumberFormatException nfe) {
				LOGGER.error("El producto necesario no es un valor entero, y ha producido un error.");
				statusCode =  HttpServletResponse.SC_BAD_REQUEST;
				break;
			} catch(IllegalStateException ise) {
				LOGGER.error("El producto necesario es una cantidad invalida o imposible y no puede ser utilizado.");
				statusCode = HttpServletResponse.SC_BAD_REQUEST;
				break;
			}
		}
		
		try {
			response.setContentType("Text/plain");
			switch(statusCode) {
			case 200:
				response.setStatus(statusCode);
				response.getWriter().append("Puntos y cantidad de productos asignados correctamente");
				break;
			case 400:
				response.sendError(statusCode, "Uno o mas puntos de venta tiene valores malformados en el campo de producto necesario");
				break;
			case 409:
				response.setStatus(statusCode);
				response.getWriter().append("Se ha intentado vincular un punto de venta a un centro de distribucion"
						+ " que no posee camiones. Por favor revise el formulario e intentelo de nuevo.");
				break;
			case 500:
				response.sendError(statusCode, "Ha ocurrido un error al procesar el resultado");
				break;
			default:
				statusCode = 418;
				response.sendError(statusCode, "Ha ocurrido un error inesperado.");
			}
		} catch (IOException ioe) {
			LOGGER.fatal("No se ha podido enviar una respuesta al usuario.\n{}", ioe.getMessage());
			LOGGER.debug(ioe.getStackTrace());
		}
	}
	
	private class InsertionException extends RuntimeException{
		private static final long serialVersionUID = 1L;

		public InsertionException(String message) {
			super(message);
		}
	}

}
