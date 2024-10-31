package models;

import java.util.Objects;

/**
 * The Class ModeloObservacion.
 */
public class ModeloObservacion {
	
	/** The observacion. */
	private String observacion;
	
	/** The id animal. */
	private int idAnimal;
	
	/** The id. */
	private int id;
	
	/**
	 * Instantiates a new modelo observacion.
	 *
	 * @param observacion the observacion
	 * @param idAnimal the id animal
	 */
	public ModeloObservacion(String observacion, int idAnimal) {
		super();
		this.observacion = observacion;
		this.idAnimal = idAnimal;
	}
	
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(idAnimal, observacion);
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModeloObservacion other = (ModeloObservacion) obj;
		return idAnimal == other.idAnimal && Objects.equals(observacion, other.observacion);
	}

	/**
	 * Gets the id animal.
	 *
	 * @return the id animal
	 */
	public int getIdAnimal() {
		return idAnimal;
	}

	/**
	 * Gets the observacion.
	 *
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
}
