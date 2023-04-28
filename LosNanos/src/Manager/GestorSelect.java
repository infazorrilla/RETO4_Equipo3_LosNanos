package Manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.util.ArrayList;


// Clase para trabajar con la tabla alumno
public class GestorSelect {

	// Retorna todas las filas de la tabla alumno
	// Si no hay nada, retorna NULL
	private ArrayList <Alumno> getAllEjemplos (){
		ArrayList <Alumno> ret = null;
		
		// SQL que queremos lanzar
		String sql = "select * from t_alumno";
		
		// La conexion con BBDD
		Connection connection = null;
		
		// Vamos a lanzar una sentencia SQL contra la BBDD
		// Result set va a contener todo lo que devuelve la BBDD
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			// El Driver que vamos a usar
			Class.forName(DBUtils.DRIVER);
			
			// Abrimos la conexion con BBDD
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			// Vamos a lanzar la sentencia...
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			// No es posible saber cuantas cosas nos ha devuelto el resultSet.
			// Hay que ir 1 por 1 y guardandolo todo en su objeto Ejemplo correspondiente
			while(resultSet.next()) {
				
				// Si es necesario, inicializamos la lista
				if (null == ret)
					ret = new <Alumno> ArrayList ();
				
				Alumno alumno = new Alumno ();
				
				// Sacamos las columnas del RS
				int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String apellidos = resultSet.getString("apellidos");
                int edad = resultSet.getInt("edad");
                
                // Metemos los datos a Ejemplo
                alumno.setId(id);
                alumno.setNombre(nombre);
                alumno.setApellidos(apellidos);
                alumno.setEdad(edad);
                
                // Lo guardamos en ret
                ret.add(alumno);
			}
		} catch (SQLException sqle) {  
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch(Exception e){ 
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (resultSet != null) 
					resultSet.close(); 
			} catch(Exception e){ 
				// No hace falta 
			};
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
		return ret;
	}

	public static void main(String[] args) {
		DBAccessExampleForSelect dBAccessExample = new DBAccessExampleForSelect();
		ArrayList<Alumno> alumnos = dBAccessExample.getAllEjemplos();
		
		Menu menu = new Menu ();
		for (int i = 0; i < alumnos.size(); i++) {
			menu.mostrarAlumno (alumnos.get(i));
		}
	}
}
