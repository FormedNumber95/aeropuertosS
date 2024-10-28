package models;

import java.io.InputStream;

public class ModeloAnimal {

	private String nombre;
	private String especie;
	private String raza;
	private String sexo;
	private int edad;
	private float peso;
	private String observaciones;
	private String primeraConsulta;
	private InputStream foto;
	
	public ModeloAnimal(String nombre, String especie, String raza, String sexo, int edad, float peso,
			String observaciones, String primeraConsulta, InputStream foto) {
		this.nombre = nombre;
		this.especie = especie;
		this.raza = raza;
		this.sexo = sexo;
		this.edad = edad;
		this.peso = peso;
		this.observaciones = observaciones;
		this.primeraConsulta = primeraConsulta;
		this.foto = foto;
	}

	public String getNombre() {
		return nombre;
	}

	public String getEspecie() {
		return especie;
	}

	public String getRaza() {
		return raza;
	}

	public String getSexo() {
		return sexo;
	}

	public int getEdad() {
		return edad;
	}

	public float getPeso() {
		return peso;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public String getPrimeraConsulta() {
		return primeraConsulta;
	}

	public InputStream getFoto() {
		return foto;
	}
	
}
