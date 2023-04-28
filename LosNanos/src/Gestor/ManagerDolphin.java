package Gestor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Pojos.Animal.Dolphin;
import utils.DBUtils;

public class ManagerDolphin {

	public void insertDolphin(Dolphin dolphin){
		

		Connection connection = null;
		

		Statement statement = null;
		
		try {
			Class.forName(DBUtils.DRIVER);
			
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			statement = connection.createStatement();
			
			java.sql.Date sqlDate = new java.sql.Date(dolphin.getBornDate().getTime());

			
			String sql = "insert into aquatic (name, scientificName, height, weight, bornDate, vaccinated, diet, animalTipe, zoneId, durationUnderWater) VALUES ('" + 
					dolphin.getName() + "', '" + 
					dolphin.getScientificName() + "', '" + 
					dolphin.getHeight() + "', '" + 
					dolphin.getWeight() + "', '" + 
					sqlDate + "', '" + 
					dolphin.getVaccinated() + "', '" + 
					dolphin.getDiet() + "', '" + 
					dolphin.getAnimalTipe() + "')";
			
//			String sql2 = "insert into dolphincomplete (id, zoneId, durationUnderWater) VALUES ('" + 
//					dolphin.id() + "', '" + 
//					dolphin.getZoneId() + "', '" + 
//					dolphin.getDurationUnderWater() + "')";
			
			
			// La ejecutamos...
			statement.executeUpdate(sql);
//			statement.executeUpdate(sql2);

			
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
	
	
	
public void deleteDolphin(Dolphin dolphin){
		
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
			String name = dolphin.getName();
			String sql = "delete from dolphincomplete where nombre =" + name;
			preparedStatement = connection.prepareStatement(sql);
			
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

public ArrayList <Dolphin> getDolphin (){
	ArrayList <Dolphin> ret = null;
	
	// SQL que queremos lanzar
	String sql = "select * from dolphincomplete";
	
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
				ret = new <Dolphin> ArrayList ();
			
			Dolphin dolphin = new Dolphin (0, sql, sql, 0, 0, null, 0, sql, sql, 0);
			
			// Sacamos las columnas del RS
			int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String scientifiName = resultSet.getString("scientifiName");
            float height = resultSet.getFloat("height");
            float weight = resultSet.getFloat("weight");
            Date bornDate = resultSet.getDate("bornDate");
            int vaccinated = resultSet.getInt("vaccinated");
            String diet = resultSet.getString("diet");
            String animalTipe = resultSet.getString("animalTipe");
            int zoneId = resultSet.getInt("ZoneId");
            int durationUnderWater = resultSet.getInt("durationUnderWater");

            
            
            // Metemos los datos a Ejemplo
            dolphin.setId(id);
            dolphin.setName(name);
            dolphin.setScientificName(scientifiName);
            dolphin.setHeight(height);
            dolphin.setWeight(weight);
            dolphin.setBornDate(bornDate);
            dolphin.setVaccinated(vaccinated);
            dolphin.setDiet(diet);
            dolphin.setAnimalTipe(animalTipe);
            dolphin.setZoneId(zoneId);
            dolphin.setDurationUnderWater(durationUnderWater);
            
            // Lo guardamos en ret
            ret.add(dolphin);
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

}
