package logical;

import java.util.*;

public class Camino {
	protected Queue<Entidad> cola;
	
	// Para que el camino sea valido debe iniciar en un centro de venta
	public Camino(Entidad inicio) {
		this.cola = new LinkedList<>();
		if(inicio.getTipo() == 'C') {
			cola.offer(inicio);
		}
	}
	
	public Camino(List<Entidad> lista) {
		if(((LinkedList<Entidad>) lista).peekFirst().getTipo() == 'C') {
			cola = (LinkedList<Entidad>)lista;
			return;
		}
		cola = new LinkedList<>();
	}

	public Queue<Entidad> getCamino() {
		return cola;
	}
	
	public void agregarEntidad(Entidad e) {
		if(!cola.contains(e)) {
			cola.offer(e);
		}
	}
	
	public void eliminarEntidad(int identificador) {
		for(Entidad e : cola) {
			if(e.getIdentificador() == identificador) {
				cola.remove(e);
			}
		}
	}
	
	
	public double distanciaTotal() {
		LinkedList<Entidad> lista = (LinkedList<Entidad>) cola;
		
		// Obtiene el primer valor de la lista
		Entidad anterior = new Entidad();
		
		double distancia = 0;
		for(Entidad e : lista) {
			distancia += anterior.distancia(e);
			anterior = e;
		}
		
		// Agrega la distancia al origen
		distancia += anterior.distancia(new Entidad());
		
		return distancia;
	}
	
}
