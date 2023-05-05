package Manager.Zones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Manager.ManagerInterface;
import Pojos.Zone.Aquarium;
import utils.DBUtils;

public class ManagerAquarium implements ManagerInterface<Aquarium>{

	@Override
	public ArrayList<Aquarium> selectAll() throws SQLException, Exception {
		ArrayList <Aquarium> ret = null;

		// SQL que queremos lanzar
		String sql = "select * from aquariumComplete";
		
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
					ret = new ArrayList<Aquarium>();
				
				Aquarium aquarium = new Aquarium();
				
				// Sacamos las columnas del RS
				int id = resultSet.getInt("id");
                String extension = resultSet.getString("extension");
                int animalsNumber = resultSet.getInt("animalsNumber");
                int speciesNumber  = resultSet.getInt("speciesNumber");
                float waterTemp  = resultSet.getFloat("waterTemp");
                
                // Metemos los datos a Ejemplo
                aquarium.setId(id);
                aquarium.setExtension(extension);
                aquarium.setAnimalsNumber(animalsNumber);
                aquarium.setSpeciesNumber(speciesNumber);
                aquarium.setWaterTemp(waterTemp);

                // Lo guardamos en ret
                ret.add(aquarium);
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

	@Override
	public void insert(Aquarium aquarium) throws SQLException, Exception {
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
							aquarium.getExtension() + "', '" +  
							aquarium.getAnimalsNumber() + "', '" + 	
							aquarium.getSpeciesNumber() + "')";
					
					String sql2 = "insert into Aquarium (zoneId, waterTemp) SELECT MAX(id), " + 
							aquarium.getWaterTemp() + "FROM zones";
					
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

	@Override
	public void update(Aquarium aquarium) throws SQLException, Exception {
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
					String sql = "update Aquariums set extension = ?, waterTemp = ?, animalsNumber = ?, speciesNumber = ? where zoneId = ?";
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString (1, aquarium.getExtension());
					preparedStatement.setFloat (2, aquarium.getWaterTemp());
					preparedStatement.setInt (3, aquarium.getAnimalsNumber());
					preparedStatement.setInt (4, aquarium.getSpeciesNumber());
					preparedStatement.setInt (5, aquarium.getId());
					
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

	@Override
	public void delete(Aquarium aquarium) throws SQLException, Exception {
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
					String sql = "delete from Aquarium where zoneId = ?";
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt (1, aquarium.getId());
					
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
