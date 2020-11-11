/**
 * 
 */
package logical;

import java.lang.Math;

/**
 * @author condondeazucar
 *
 */
public class Entidad {
	private int posicion_X;
	private int posicion_Y;
	private char tipo;
	private int identificador;
	
	// CONSTRUCTORES
	public Entidad(int posicion_X, int posicion_Y, char tipo, int identificador) {
		super();
		this.posicion_X = posicion_X;
		this.posicion_Y = posicion_Y;
		this.setTipo(tipo);
		this.identificador = identificador;
	}
	
	public Entidad() {
		super();
		this.posicion_X = 0;
		this.posicion_Y = 0;
		this.setTipo('\0');
		this.identificador = -1;
	}
	
	public Entidad(char tipo, int identificador) {
		super();
		this.posicion_X = 0;
		this.posicion_Y = 0;
		this.setTipo(tipo);
		this.identificador = identificador;
	}

	/*
	 * GETTERS Y SETTERS
	 */
	
	// posicion en X
	public int getPosicion_X() {
		return posicion_X;
	}

	public void setPosicion_X(int posicion_X) {
		this.posicion_X = posicion_X;
	}

	// posicion en Y
	public int getPosicion_Y() {
		return posicion_Y;
	}

	public void setPosicion_Y(int posicion_Y) {
		this.posicion_Y = posicion_Y;
	}

	// Tipo de entidad
	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		if(tipo == 'P' || tipo == 'C') {
			this.tipo = tipo;
		}
		else {
			this.tipo = '\0';
		}
	}

	// Identificador de la entidad
	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	
	// Distancia entre dos entidades utilizando teorema de pitagoras simple
	public double distancia(Entidad other) {
		int otherX = other.getPosicion_X();
		int otherY = other.getPosicion_Y();
		int thisX = this.posicion_X;
		int thisY = this.posicion_Y;
		return Math.sqrt(Math.pow(thisX - otherX, 2) + Math.pow(thisY - otherY, 2));
	}
}
