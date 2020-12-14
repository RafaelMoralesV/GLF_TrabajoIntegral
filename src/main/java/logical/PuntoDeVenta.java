package logical;

public class PuntoDeVenta extends Entidad {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + neededProduct;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PuntoDeVenta other = (PuntoDeVenta) obj;
		return neededProduct == other.neededProduct;
	}
	private int neededProduct;
	public PuntoDeVenta(int producto, int id, char tipo, int posX, int posY) {
		super(posX, posY, tipo, id);
		this.setNeededProduct(producto);
	}
	
	public PuntoDeVenta() {
		super();
		neededProduct = 0;
	}
	
	public void setNeededProduct(int cant){
		if (cant < 0 || cant > 1000) {
			throw new IllegalArgumentException("El producto necesitado no puede ser negativo o mayor a 1000");
		}
		else {
			this.neededProduct = cant;
		}
	}
	public int getNeededProduct() {
		return neededProduct;
	}
}
