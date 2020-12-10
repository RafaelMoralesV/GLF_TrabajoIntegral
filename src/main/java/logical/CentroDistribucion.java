package logical;

import java.util.List;

public class CentroDistribucion extends Entidad{
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
