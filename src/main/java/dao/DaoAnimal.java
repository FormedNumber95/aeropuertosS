package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.ConexionBBDD;

public class DaoAnimal {

	private static Connection con;
	
	public static Integer conseguirID(String nombre, String especie, String raza, String sexo, int edad, float peso, String primeraConsulta) {
		con=ConexionBBDD.getConnection();
		String select="SELECT ID FROM animales WHERE nombre=? AND especie=? AND raza=? AND sexo=? AND edad=? AND peso=? AND primera_consulta=?";
		try {
			PreparedStatement pstmt=con.prepareStatement(select);
			pstmt.setString(1,nombre);
			pstmt.setString(2,especie);
			pstmt.setString(3,raza);
			pstmt.setString(4,sexo);
			pstmt.setInt(5,edad);
			pstmt.setFloat(6,peso);
			pstmt.setString(7,primeraConsulta);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("id");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
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
	
}
