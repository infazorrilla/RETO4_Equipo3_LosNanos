package Manager.Zones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Manager.ManagerInterface;
import Pojos.Zone.Swamp;
import utils.DBUtils;

public class ManagerSwamp implements ManagerInterface<Swamp> {

	@Override
	public ArrayList<Swamp> selectAll() throws SQLException, Exception {
		ArrayList <Swamp> ret = null;
		
		// SQL que queremos lanzar
		String sql = "select * from swampComplete";
		
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
					ret = new ArrayList <Swamp>();
				
				Swamp swamp = new Swamp();
				
				// Sacamos las columnas del RS
				int id = resultSet.getInt("id");
				float extension = resultSet.getFloat("extension");
                int animalsNumber = resultSet.getInt("animalsNumber");
                int speciesNumber  = resultSet.getInt("speciesNumber");
                float waterSurface  = resultSet.getFloat("waterSurface");
                
                // Metemos los datos a Ejemplo
                swamp.setId(id);
                swamp.setExtension(extension);
                swamp.setAnimalsNumber(animalsNumber);
                swamp.setSpeciesNumber(speciesNumber);
                swamp.setWaterSurface(waterSurface);

                // Lo guardamos en ret
                ret.add(swamp);
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
	public void insert(Swamp swamp) throws SQLException, Exception {
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
							swamp.getExtension() + "', '" +  
							swamp.getAnimalsNumber() + "', '" + 	
							swamp.getSpeciesNumber() + "')";
					
					String sql2 = "insert into swamp (zoneId, waterSurface) SELECT MAX(id), " + 
							swamp.getWaterSurface() + "FROM zones";
					
					
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
	public void update(Swamp swamp) throws SQLException, Exception {
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
					String sql = "update Zones, swamp set waterSurface = ?, extension = ?, animalsNumber = ?, speciesNumber = ? where Id = ?";
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setFloat (1, swamp.getWaterSurface());
					preparedStatement.setFloat (2, swamp.getExtension());
					preparedStatement.setInt (3, swamp.getAnimalsNumber());
					preparedStatement.setInt (4, swamp.getSpeciesNumber());
					preparedStatement.setInt (5, swamp.getId());
					
					
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
	public void delete(Swamp swamp) throws SQLException, Exception {
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
					String sql = "delete from Swamp where zoneId = ?";
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt (1, swamp.getId());
					
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
