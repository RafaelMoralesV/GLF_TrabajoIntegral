package logical;

/*
 * @author condondeazucar
 */
public class Entidad {
	
	/*
	 * VARIABLES
	 */
	private int posicionX;
	private int posicionY;
	private char tipo;
	private int identificador;

	
	/*
	 * CONSTRUCTORES
	 */
	public Entidad(int posicionX, int posicionY, char tipo, int identificador) {
		super();
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.setTipo(tipo);
		this.identificador = identificador;
	}
	public Entidad() {
		super();
		this.posicionX = 0;
		this.posicionY = 0;
		this.setTipo('\0');
		this.identificador = -1;
	}
	public Entidad(char tipo, int identificador) {
		super();
		this.posicionX = 0;
		this.posicionY = 0;
		this.setTipo(tipo);
		this.identificador = identificador;
	}

	
	/*
	 * GETTERS Y SETTERS
	 */
	// posicion en X
	public int getPosicionX() {
		return posicionX;
	}
	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	// posicion en Y
	public int getPosicionY() {
		return posicionY;
	}
	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
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
	
	/*
	 * METODOS LOGICOS 
	 */
	// Distancia entre dos entidades utilizando teorema de pitagoras simple
	public double distancia(Entidad other) {
		int otherX = other.getPosicionX();
		int otherY = other.getPosicionY();
		int thisX = this.posicionX;
		int thisY = this.posicionY;
		return Math.sqrt(Math.pow((double)thisX - otherX, 2) + Math.pow((double)thisY - otherY, 2));
	}



	
	/*
	 * Overrides, Utils y otros. 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + identificador;
		result = prime * result + posicionX;
		result = prime * result + posicionY;
		result = prime * result + tipo;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entidad other = (Entidad) obj;
		if (identificador != other.identificador)
			return false;
		if (posicionX != other.posicionX)
			return false;
		if (posicionY != other.posicionY)
			return false;
		return tipo == other.tipo;
	}
	@Override
	public String toString() {
		// Sobrecarga metodo toString()
		return "Entidad en (" + posicionX + ", " + posicionY + "): tipo = " + tipo + ", identificador = "
				+ identificador;
	}
}
