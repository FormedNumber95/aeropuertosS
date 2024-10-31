package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.ConexionBBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.ModeloObservacion;

/**
 * The Class DaoObservacion.
 */
public class DaoObservacion {

	/** The con. */
	private static Connection con;
	
	/**
	 * Conseguir lista todas.
	 *
	 * @param id the id
	 * @return the observable list
	 */
	public static ObservableList<ModeloObservacion>conseguirListaTodas(int id){
		ObservableList<ModeloObservacion>lst=FXCollections.observableArrayList();
		con=ConexionBBDD.getConnection();
		String select="SELECT id,observacion FROM observaciones WHERE id_animal=?";
		try {
			PreparedStatement pstmt=con.prepareStatement(select);
			pstmt.setInt(1,id);
			ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	ModeloObservacion modelo=new ModeloObservacion(rs.getString("observacion"), id);
            	modelo.setId(rs.getInt("id"));
            	lst.add(modelo);
            }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	/**
	 * Insertar.
	 *
	 * @param observacion the observacion
	 * @param id the id
	 */
	public static void insertar(String observacion,int id) {
		con=ConexionBBDD.getConnection();
		String insert="INSERT INTO observaciones (observacion,id_animal) VALUES (?,?)";
		try {
			PreparedStatement pstmt=con.prepareStatement(insert);
			pstmt.setString(1,observacion);
			pstmt.setInt(2,id);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Modificar.
	 *
	 * @param observacion the observacion
	 * @param id the id
	 */
	public static void modificar(String observacion,int id) {
		con=ConexionBBDD.getConnection();
		String update="UPDATE observaciones SET observacion=? WHERE id=?";
		try {
			PreparedStatement pstmt=con.prepareStatement(update);
			pstmt.setString(1,observacion);
			pstmt.setInt(2,id);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Eliminar.
	 *
	 * @param id the id
	 */
	public static void eliminar(int id) {
		con=ConexionBBDD.getConnection();
		String delete="DELETE FROM observaciones WHERE ID=?";
		try {
			PreparedStatement pstmt=con.prepareStatement(delete);
			pstmt.setInt(1,id);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
