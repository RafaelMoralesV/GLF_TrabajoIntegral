package logical;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DAO {
	static final List<Camino> lista = new ArrayList<>();
	protected static final Logger LOGGER = LogManager.getLogger(DAO.class.getName());
	
	private static List<Entidad> cargar(String path){
		List<Entidad> l = new ArrayList<>();
		try {
			l = Parser.parseFile(path + "target.txt");
		}
		catch(java.io.IOException e) {
			LOGGER.fatal("No pudo cargarse el archivo target.txt con la lista de entidades.\n{}", e.getMessage());
		}
		return l;
	}
	
	private static Entidad iniciar(String path, String id) 
			throws NumberFormatException{
		List<Entidad> l = cargar(path);
		for(Entidad e : l) {
			if(e.getIdentificador() == Integer.parseInt(id)) {
				return e;
			}
		}
		return new Entidad();
	}
	
	public static void crear(String path, String id) 
			throws NumberFormatException, IllegalStateException{
		List<Entidad> l = cargar(path);
		for(Entidad e : l) {
			if(e.getTipo() == 'C' && e.getIdentificador() == Integer.parseInt(id)) {
				lista.add(new Camino(e));
			}
		}
	}
	
	public static boolean agregar(String path, String idCD, String idPV, int producto) {
		
		/*
		 * Funcion booleana que agregar un punto de venta hacia el mejor camino disponible
		 * mediante fuerza bruta.
		 * Retorna verdadero en caso de que se haya hecho el cambio, y falso en caso contrario.
		 */
		
		Entidad inicio 	= iniciar(path, idCD);
		PuntoDeVenta pdv 			= new PuntoDeVenta(iniciar(path, idPV), producto);
		
		boolean cambiable = false;
		double mejorDist = Double.MAX_VALUE;
		Camino cambiado = null;
		for(Camino c : lista) {
			// Busca todos los caminos donde es posible agregar el punto de venta
			// esto se refiere a que el punto de venta este vinculado a ese centro de distribucion
			// y sea posible atribuirle este punto de venta a ese camino.
			if(c.getInicio().equals(inicio) && c.esAgregable(pdv)) {
				Camino aux = c.simularCamino(pdv);
				if(aux.distanciaTotal() < mejorDist) {
					cambiable = true;
					mejorDist = aux.distanciaTotal();
					cambiado = c;
				}
			}
		}
		if(cambiable) {
			cambiado.agregarEntidad(pdv);
			return cambiable;
		}
		return false;
	}
}
