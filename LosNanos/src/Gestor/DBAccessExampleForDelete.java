package ejemplo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import ejemplo.pojos.Alumno;
import ejemplo.utils.DBUtils;

// Clase para trabajar con la tabla alumno
public class DBAccessExampleForDelete {

	// Modificamos la edad de un alumno
	private void borrarPorNombre(Alumno ejemplo){
		
		// La conexion con BBDD
		Connection connection = null;
		
		// Vamos a lanzar una sentencia SQL contra la BBDD
		PreparedStatement  preparedStatement  = null;
		
		try {
			// El Driver que vamos a usar
			Class.forName(DBUtils.DRIVER);
			
			// Abrimos la conexion con BBDD
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			// Montamos la SQL. Las ? se rellenan a continuacion
			String sql = "delete from t_alumno where nombre = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString (1, ejemplo.getNombre());
			
			// La ejecutamos...
			preparedStatement.executeUpdate();
			
		} catch (SQLException sqle) {  
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch(Exception e){ 
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (preparedStatement != null) 
					preparedStatement.close(); 
			} catch(Exception e){ 
				// No hace falta				
			};
			try {
				if (connection != null) 
					connection.close(); 
			} catch(Exception e){ 
				// No hace falta
			};					
		}
	}

	public static void main(String[] args) {
		DBAccessExampleForDelete dBAccessExample = new DBAccessExampleForDelete();
		
		// Nuevo Ejemplo a insertar...
		Alumno alumno = new Alumno ();
		alumno.setNombre("Andres");
		alumno.setApellidos("Dominguez");
		alumno.setEdad(25);
		
		dBAccessExample.borrarPorNombre(alumno);
	}
}
