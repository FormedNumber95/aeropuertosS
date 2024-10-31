package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.ConexionBBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.ModeloAnimal;

/**
 * The Class DaoAnimal.
 */
public class DaoAnimal {

	/** The con. */
	private static Connection con;
	
	/**
	 * Conseguir lista todos.
	 *
	 * @return the observable list
	 */
	public static ObservableList<ModeloAnimal> conseguirListaTodos(){
		ObservableList<ModeloAnimal>lst=FXCollections.observableArrayList();
		try {
			con=ConexionBBDD.getConnection();
			String select="SELECT id,nombre,especie,raza,sexo,edad,peso,primera_consulta,foto FROM animales";
			PreparedStatement pstmt = con.prepareStatement(select);
			ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	ModeloAnimal animal=new ModeloAnimal(rs.getString("nombre"),rs.getString("especie"),rs.getString("raza"),rs.getString("sexo"),rs.getInt("edad"),rs.getFloat("peso"),rs.getString("primera_consulta"),rs.getBinaryStream("foto"));
            	animal.setId(rs.getInt("id"));
            	lst.add(animal);
            }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	/**
	 * Eliminar.
	 *
	 * @param id the id
	 */
	public static void eliminar(int id) {
		con=ConexionBBDD.getConnection();
		String delete="DELETE FROM animales WHERE ID=?";
		try {
			PreparedStatement pstmt=con.prepareStatement(delete);
			pstmt.setInt(1,id);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Insertar.
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
	public static void insertar(String nombre, String especie, String raza, String sexo, int edad, float peso, String primeraConsulta,InputStream foto) {
		con=ConexionBBDD.getConnection();
		String insert="INSERT INTO animales (nombre,especie,raza,sexo,edad,peso,primera_consulta,foto) VALUES (?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt=con.prepareStatement(insert);
			pstmt.setString(1,nombre);
			pstmt.setString(2,especie);
			pstmt.setString(3,raza);
			pstmt.setString(4,sexo);
			pstmt.setInt(5,edad);
			pstmt.setFloat(6,peso);
			pstmt.setString(7,primeraConsulta);
			pstmt.setBinaryStream(8,foto);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Modificar.
	 *
	 * @param id the id
	 * @param nombre the nombre
	 * @param especie the especie
	 * @param raza the raza
	 * @param sexo the sexo
	 * @param edad the edad
	 * @param peso the peso
	 * @param primeraConsulta the primera consulta
	 * @param foto the foto
	 */
	public static void modificar(int id,String nombre, String especie, String raza, String sexo, int edad, float peso, String primeraConsulta,InputStream foto) {
		con=ConexionBBDD.getConnection();
		String update="UPDATE animales SET nombre=?,especie=?,raza=?,sexo=?,edad=?,peso=?,primera_consulta=?,foto=? WHERE id=?";
		try {
			PreparedStatement pstmt=con.prepareStatement(update);
			pstmt.setString(1,nombre);
			pstmt.setString(2,especie);
			pstmt.setString(3,raza);
			pstmt.setString(4,sexo);
			pstmt.setInt(5,edad);
			pstmt.setFloat(6,peso);
			pstmt.setString(7,primeraConsulta);
			pstmt.setBinaryStream(8,foto);
			pstmt.setInt(9,id);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
