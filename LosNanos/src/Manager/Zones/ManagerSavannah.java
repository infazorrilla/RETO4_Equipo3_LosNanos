package Manager.Zones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Pojos.Zone.Savannah;
import utils.DBUtils;

public class ManagerSavannah {
	
	public ArrayList <Savannah> getSavannahs(){
		ArrayList <Savannah> ret = null;

		// SQL que queremos lanzar
		String sql = "select * from Savannahs";
		
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
					ret = new ArrayList <Savannah>();
				
				Savannah savannah = new Savannah();
				
				// Sacamos las columnas del RS
				int id = resultSet.getInt("id");
                String extension = resultSet.getString("extension");
                int animalsNumber = resultSet.getInt("animalsNumber");
                int speciesNumber  = resultSet.getInt("speciesNumber");
                int treeNumber  = resultSet.getInt("treeNumber");
                
                // Metemos los datos a Ejemplo
                savannah.setId(id);
                savannah.setExtension(extension);
                savannah.setAnimalsNumber(animalsNumber);
                savannah.setSpeciesNumber(speciesNumber);
                savannah.setTreeNumber(treeNumber);

                // Lo guardamos en ret
                ret.add(savannah);
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
	
	public void insertSavannah(Savannah savannah){
		
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
			String sql = "insert into Zones (extension, animalsNumber, speciesNumber) VALUES ('" + 
					savannah.getExtension() + "', '" +  
					savannah.getAnimalsNumber() + "', '" + 	
					savannah.getSpeciesNumber() + "')";
			
			String sql2 = "insert into Savannah (treeNumber) VALUES ('" + 
					savannah.getTreeNumber() + "')";
			
			// La ejecutamos...
			statement.executeUpdate(sql);
			statement.executeUpdate(sql2);
			
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
	
	public void updateSavannah(Savannah savannah, int newTreeNumber){
		
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
			String sql = "update Savannahs set treeNumber = ? where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt (1, newTreeNumber);
			preparedStatement.setInt (2, savannah.getId());
			
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
	
	public void deleteSavannah(Savannah savannah){
		
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
			String sql = "delete from Savannahs where zoneId = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt (1, savannah.getId());
			
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

}
