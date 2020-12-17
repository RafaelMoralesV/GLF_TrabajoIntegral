package logical;

import java.util.*;

public class Camino {
	protected Entidad inicio;
	protected LinkedList<PuntoDeVenta> cola = new LinkedList<>();

	protected int cargaActual = 0;

	/*
	 * CONSTRUCTORES
	 */
	public Camino(Entidad inicio) {
		this.inicio = inicio;
	}

	/*
	 * GETTER
	 */
	public Queue<PuntoDeVenta> getCamino() {
		return cola;
	}

	public Entidad getInicio() {
		return inicio;
	}

	public void setInicio(Entidad inicio) {
		this.inicio = inicio;
	}

	public int getCargaActual() {
		return cargaActual;
	}

	public void setCargaActual(int cargaActual) {
		this.cargaActual = cargaActual;
	}
	
	protected void setCola(LinkedList<PuntoDeVenta> col) {
		this.cola = col;
	}

	/*
	 * METODOS LOGICOS
	 */
	public LinkedList<PuntoDeVenta> simular(PuntoDeVenta e) {
		
		/*
		 * Esta funcion consigue un mejor camino mediante fuerza bruta
		 * y retorna esa lista ordenada sin modificar el camino actual.
		 */
		
		LinkedList<PuntoDeVenta> estadoInicial = this.cola;

		LinkedList<PuntoDeVenta> mejorCamino = this.cola;
		double mejorDistancia = Double.MAX_VALUE;
		
		if(estadoInicial.size() == 0) {
			mejorCamino.add(e);
			return mejorCamino;
		}

		for (int i = 0; i < estadoInicial.size(); i++) {
			// Coloca el nuevo punto de venta en cada indice y prueba
			// si es que esta configuracion es la mejor que se ha encontrado.
			this.cola.add(i, e);
			double aux = this.distanciaTotal();
			if (aux < mejorDistancia) {
				mejorDistancia = aux;
				mejorCamino = this.cola;
			}
			// Reinicia el camino a un estado inicial.
			this.cola = estadoInicial;
		}
		
		return mejorCamino;
	}

	public Camino simularCamino(PuntoDeVenta e) {
		LinkedList<PuntoDeVenta> mejor = this.simular(e);
		Camino ret = new Camino(this.inicio);
		ret.setCola(mejor);
		return ret;
	}
	public void agregarEntidad(PuntoDeVenta e) {
		this.cola = this.simular(e);
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

		for (Entidad e : cola) {
			dist += anterior.distancia(e);
			anterior = e;
		}

		dist += anterior.distancia(new Entidad());

		return dist;
	}
}
