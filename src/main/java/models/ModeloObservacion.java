package models;

public class ModeloObservacion {
	
	private String observacion;
	private int idAnimal;
	
	public ModeloObservacion(String observacion, int idAnimal) {
		super();
		this.observacion = observacion;
		this.idAnimal = idAnimal;
	}
	
	public int getIdAnimal() {
		return idAnimal;
	}

	public String getObservacion() {
		return observacion;
	}
	
}
