package ejemplo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;

import ejemplo.pojos.Alumno;
import ejemplo.utils.DBUtils;

// Clase para trabajar con la tabla alumno
public class DBAccessExampleForInsert {

	// Inserta un alumno
	private void insertEjemplo(Alumno alumno){
		
		// La conexion con BBDD
		Connection connection = null;
		
		// Vamos a lanzar una sentencia SQL contra la BBDD
		Statement statement = null;
		
		try {
			// El Driver que vamos a usar
			Class.forName(DBUtils.DRIVER);
			
			// Abrimos la conexion con BBDD
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			// Vamos a lanzar la sentencia...
			statement = connection.createStatement();
			
			// Montamos la SQL 
			String sql = "insert into t_alumno (nombre, apellidos, edad) VALUES ('" +  
					alumno.getNombre() + "', '" + 
					alumno.getApellidos() + "', '" + 
					alumno.getEdad() + "')";
			
			// La ejecutamos...
			statement.executeUpdate(sql);
			
		} catch (SQLException sqle) {  
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch(Exception e){ 
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (statement != null) 
					statement.close(); 
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
		DBAccessExampleForInsert dBAccessExample = new DBAccessExampleForInsert();
		
		// Nuevo alumno a insertar...
		Alumno alumno = new Alumno ();
		alumno.setNombre("Pablo");
		alumno.setApellidos("Martinez");
		alumno.setEdad(23);
		
		dBAccessExample.insertEjemplo(alumno);
	}
}
