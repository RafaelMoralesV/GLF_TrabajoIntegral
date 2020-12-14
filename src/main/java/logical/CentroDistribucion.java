package logical;

import java.util.List;

public class CentroDistribucion extends Entidad{
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + cantCamiones;
		result = prime * result + ((ptsReceptores == null) ? 0 : ptsReceptores.hashCode());
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
		CentroDistribucion other = (CentroDistribucion) obj;
		if (cantCamiones != other.cantCamiones)
			return false;
		if (ptsReceptores == null) {
			if (other.ptsReceptores != null)
				return false;
		} else if (!ptsReceptores.equals(other.ptsReceptores)) {
			return false;
		}
		return true;
	}

	private int cantCamiones;
	private List<PuntoDeVenta> ptsReceptores;
	
	
	public int getCantCamiones() {
		return cantCamiones;
	}
	public void setCantCamiones(int cantCamiones) {
		this.cantCamiones = cantCamiones;
	}
	public List<PuntoDeVenta> getPtsReceptores() {
		return ptsReceptores;
	}
	public void setPtsReceptores(List<PuntoDeVenta> ptsReceptores) {
		this.ptsReceptores = ptsReceptores;
	}
	public CentroDistribucion(int posicionX, int posicionY, char tipo, int identificador, int cantCamiones,
			List<PuntoDeVenta> ptsReceptores) {
		super(posicionX, posicionY, tipo, identificador);
		this.cantCamiones = cantCamiones;
		this.ptsReceptores = ptsReceptores;
	}
	
	public Camino generarCamino(){
		Camino resultado = new Camino(this);
		
		for(PuntoDeVenta pdv : ptsReceptores) {
			resultado.agregarEntidad(pdv);
		}
		
		return resultado;
	}
}
