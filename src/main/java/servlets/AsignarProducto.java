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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAO.vaciarCaminos();
		Enumeration<?> e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String idSel = (String) e.nextElement();
			String idCD = request.getParameter(idSel);
			String idProd = (String) e.nextElement();
			String resProd = request.getParameter(idProd);
			
			String[] idPDV = idSel.split("-");
			DAO.agregar(this.getServletContext().getRealPath("/"), idCD, idPDV[1], Integer.parseInt(resProd));
		}
		response.setContentType("Text/plain");
		response.setStatus(200);
		response.getWriter().append("Puntos y cantidad de productos asignados correctamente");
	}

}
