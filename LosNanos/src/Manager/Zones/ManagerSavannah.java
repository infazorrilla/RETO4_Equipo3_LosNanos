package Manager.Zones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Manager.ManagerInterface;
import Pojos.Zone.Savannah;
import utils.DBUtils;

public class ManagerSavannah implements ManagerInterface<Savannah> {

	@Override
	public ArrayList<Savannah> selectAll() throws SQLException, ClassNotFoundException {
		ArrayList <Savannah> ret = null;

		// SQL que queremos lanzar
		String sql = "select * from savannahComplete";
		
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
                float extension = resultSet.getFloat("extension");
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
			throw new SQLException ();
		} catch(ClassNotFoundException e){ 
			throw new ClassNotFoundException();
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

	@Override
	public void insert(Savannah savannah) throws SQLException, ClassNotFoundException {
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
			
			String sql2 = "insert into Savannah (zoneId, treeNumber) SELECT MAX(id),"
					+ savannah.getTreeNumber() + " FROM zones";

			// La ejecutamos...
			statement.executeUpdate(sql);
			statement.executeUpdate(sql2);
			
		} catch (SQLException sqle) {  
			throw new SQLException ();
		} catch(ClassNotFoundException e){ 
			throw new ClassNotFoundException();
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

	@Override
	public void update(Savannah savannah) throws SQLException, ClassNotFoundException {
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
			String sql = "update Zones, savannah set treeNumber = ?, extension = ?, animalsNumber = ?, speciesNumber = ? where Id = ? and zoneId = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setFloat (1, savannah.getTreeNumber());
			preparedStatement.setFloat (2, savannah.getExtension());
			preparedStatement.setInt (3, savannah.getAnimalsNumber());
			preparedStatement.setInt (4, savannah.getSpeciesNumber());
			preparedStatement.setInt (5, savannah.getId());
			preparedStatement.setInt (6, savannah.getId());
			
			// La ejecutamos...
			preparedStatement.executeUpdate();
			
		} catch (SQLException sqle) {  
			throw new SQLException ();
		} catch(ClassNotFoundException e){ 
			throw new ClassNotFoundException();
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

	@Override
	public void delete(Savannah savannah) throws SQLException, ClassNotFoundException {
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
			String sql = "delete from zones where Id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt (1, savannah.getId());
			
			// La ejecutamos...
			preparedStatement.executeUpdate();
			
		} catch (SQLException sqle) {  
			throw new SQLException ();
		} catch(ClassNotFoundException e){ 
			throw new ClassNotFoundException();
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
