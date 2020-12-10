package logical;

import java.util.*;

public class Camino {
	protected Queue<Entidad> cola;
	
	/*
	 * CONSTRUCTORES
	 */
	public Camino(Entidad inicio) {
		this.cola = new LinkedList<>();
		if(inicio.getTipo() == 'C') {
			cola.offer(inicio);
		}
	}
	public Camino(List<Entidad> lista) {
		// Verifica que el primer elemento es un centro de venta
		if(((LinkedList<Entidad>) lista).peekFirst().getTipo() == 'C') {
			cola = (LinkedList<Entidad>)lista;
			return;
		}
		cola = new LinkedList<>();
	}

	/* 
	 * GETTER
	 */
	public Queue<Entidad> getCamino() {
		return cola;
	}
	
	/*
	 * METODOS LOGICOS
	 */
	public void agregarEntidad(Entidad e) {
		LinkedList<Entidad> mejorCamino = new LinkedList<>();
		double distancia = Double.MAX_VALUE;
		for(int i = 0; i < cola.size(); i++) {
			LinkedList<Entidad> aux = (LinkedList<Entidad>)cola;
			aux.add(i, e);
			if(Camino.distanciaTotal((List<Entidad>)aux) < distancia) {
				mejorCamino = aux;
				distancia = Camino.distanciaTotal((List<Entidad>)aux);
			}
		}
		cola = mejorCamino;
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
	public static double distanciaTotal(List<Entidad> l) {
		Entidad anterior = new Entidad();
		double dist = 0;
		for(Entidad e : l) {
			dist += anterior.distancia(e);
			anterior = e;
		}
		dist += anterior.distancia(new Entidad());		
		return dist;
	}
}
