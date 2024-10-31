package models;

import java.io.InputStream;
import java.util.Objects;

/**
 * The Class ModeloAnimal.
 */
public class ModeloAnimal {

	/** The id. */
	private int id;
	
	/** The nombre. */
	private String nombre;
	
	/** The especie. */
	private String especie;
	
	/** The raza. */
	private String raza;
	
	/** The sexo. */
	private String sexo;
	
	/** The edad. */
	private int edad;
	
	/** The peso. */
	private float peso;
	
	/** The primera consulta. */
	private String primeraConsulta;
	
	/** The foto. */
	private InputStream foto;
	
	/**
	 * Instantiates a new modelo animal.
	 *
	 * @param nombre the nombre
	 * @param especie the especie
	 * @param raza the raza
	 * @param sexo the sexo
	 * @param edad the edad
	 * @param peso the peso
	 * @param primeraConsulta the primera consulta
	 * @param foto the foto
	 */
	public ModeloAnimal(String nombre, String especie, String raza, String sexo, int edad, float peso, String primeraConsulta, InputStream foto) {
		this.nombre = nombre;
		this.especie = especie;
		this.raza = raza;
		this.sexo = sexo;
		this.edad = edad;
		this.peso = peso;
		this.primeraConsulta = primeraConsulta;
		this.foto = foto;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Gets the especie.
	 *
	 * @return the especie
	 */
	public String getEspecie() {
		return especie;
	}

	/**
	 * Gets the raza.
	 *
	 * @return the raza
	 */
	public String getRaza() {
		return raza;
	}

	/**
	 * Gets the sexo.
	 *
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * Gets the edad.
	 *
	 * @return the edad
	 */
	public int getEdad() {
		return edad;
	}

	/**
	 * Gets the peso.
	 *
	 * @return the peso
	 */
	public float getPeso() {
		return peso;
	}

	/**
	 * Gets the primera consulta.
	 *
	 * @return the primera consulta
	 */
	public String getPrimeraConsulta() {
		return primeraConsulta;
	}

	/**
	 * Gets the foto.
	 *
	 * @return the foto
	 */
	public InputStream getFoto() {
		return foto;
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

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(edad, especie, nombre, peso, primeraConsulta, raza, sexo);
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
		ModeloAnimal other = (ModeloAnimal) obj;
		return edad == other.edad && Objects.equals(especie, other.especie) && Objects.equals(nombre, other.nombre)
				&& Float.floatToIntBits(peso) == Float.floatToIntBits(other.peso)
				&& Objects.equals(primeraConsulta, other.primeraConsulta) && Objects.equals(raza, other.raza)
				&& Objects.equals(sexo, other.sexo);
	}
	
	
}
