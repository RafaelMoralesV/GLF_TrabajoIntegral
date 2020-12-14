package logical;

import java.util.*;

public class Camino {
	protected CentroDistribucion inicio;
	protected LinkedList<PuntoDeVenta> cola;
	
	protected int cargaActual = 0;
	
	/*
	 * CONSTRUCTORES
	 */
	public Camino(CentroDistribucion inicio) {
		this.inicio = inicio;
	}

	/* 
	 * GETTER
	 */
	public Queue<PuntoDeVenta> getCamino() {
		return cola;
	}
	
	/*
	 * METODOS LOGICOS
	 */
	public void agregarEntidad(PuntoDeVenta e) {
		LinkedList<PuntoDeVenta> estadoInicial = this.cola;
		
		LinkedList<PuntoDeVenta> mejorCamino = this.cola;
		double mejorDistancia = Double.MAX_VALUE;
		
		for(int i = 0; i < estadoInicial.size(); i++) {
			this.cola = estadoInicial;
			this.cola.add(i, e);
			double aux = this.distanciaTotal();
			if(aux < mejorDistancia) {
				mejorDistancia = aux;
				mejorCamino = this.cola;
			}
		}
		this.cola = mejorCamino;
		this.cargaActual += e.getNeededProduct();
	}
	public boolean esAgregable(PuntoDeVenta e) {
		return this.cargaActual + e.getNeededProduct() <= 1000;
	}

	public double distanciaTotal() {
		double dist = 0;
		
		Entidad anterior = new Entidad();
		
		dist += anterior.distancia(inicio);
		anterior = inicio;
		
		for(Entidad e : cola) {
			dist += anterior.distancia(e);
			anterior = e;
		}
		
		dist += anterior.distancia(new Entidad());
		
		return dist;
	}
}
