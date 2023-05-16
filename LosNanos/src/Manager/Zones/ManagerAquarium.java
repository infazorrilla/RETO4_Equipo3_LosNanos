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
		
		String sql = "select * from aquariumComplete";
		
		Connection connection = null;
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			Class.forName(DBUtils.DRIVER);
			
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				if (null == ret)
					ret = new ArrayList<Aquarium>();
				
				Aquarium aquarium = new Aquarium();
				
				// Sacamos las columnas del RS
				int id = resultSet.getInt("id");
                float extension = resultSet.getFloat("extension");
                int animalsNumber = resultSet.getInt("animalsNumber");
                int speciesNumber  = resultSet.getInt("speciesNumber");
                float waterTemp  = resultSet.getFloat("waterTemp");
                
                aquarium.setId(id);
                aquarium.setExtension(extension);
                aquarium.setAnimalsNumber(animalsNumber);
                aquarium.setSpeciesNumber(speciesNumber);
                aquarium.setWaterTemp(waterTemp);

                ret.add(aquarium);
			}
		} catch (SQLException sqle) {  
			throw new SQLException ();
		} catch(Exception e){ 
			throw new Exception();
		} finally {
			try {
				if (resultSet != null) 
					resultSet.close(); 
			} catch(Exception e){ 
			};
			try {
				if (statement != null) 
					statement.close(); 
			} catch(Exception e){ 			
			};
			try {
				if (connection != null) 
					connection.close(); 
			} catch(Exception e){ 
			};					
		}
		return ret;
	}

	@Override
	public void insert(Aquarium aquarium) throws SQLException, Exception {
		Connection connection = null;
		
		Statement statement = null;
		
		try {
			Class.forName(DBUtils.DRIVER);
			
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			statement = connection.createStatement();
			
			String sql = "insert into Zones (extension, animalsNumber, speciesNumber) VALUES ('" + 
					aquarium.getExtension() + "', '" +  
					aquarium.getAnimalsNumber() + "', '" + 	
					aquarium.getSpeciesNumber() + "')";
			
			String sql2 = "insert into Aquarium (zoneId, waterTemp) SELECT MAX(id), " + 
					aquarium.getWaterTemp() + " FROM zones";
			
			statement.executeUpdate(sql);
			statement.executeUpdate(sql2);
			
		} catch (SQLException sqle) {  
			throw new SQLException ();
		} catch(Exception e){ 
			throw new Exception();
		} finally {
			try {
				if (statement != null) 
					statement.close(); 
			} catch(Exception e){ 		
			};
			try {
				if (connection != null) 
					connection.close(); 
			} catch(Exception e){ 
			};					
		}
	}

	@Override
	public void update(Aquarium aquarium) throws SQLException, Exception {
		Connection connection = null;
		
		PreparedStatement  preparedStatement  = null;
		
		try {
			Class.forName(DBUtils.DRIVER);
			
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			String sql = "update Zones, aquarium set waterTemp = ?, extension = ?, animalsNumber = ?, speciesNumber = ? where Id = ? and zoneId = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setFloat (1, aquarium.getWaterTemp());
			preparedStatement.setFloat (2, aquarium.getExtension());
			preparedStatement.setInt (3, aquarium.getAnimalsNumber());
			preparedStatement.setInt (4, aquarium.getSpeciesNumber());
			preparedStatement.setInt (5, aquarium.getId());
			preparedStatement.setInt (6, aquarium.getId());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException sqle) {  
			throw new SQLException ();
		} catch(Exception e){ 
			throw new Exception();
		} finally {
			try {
				if (preparedStatement != null) 
					preparedStatement.close(); 
			} catch(Exception e){ 			
			};
			try {
				if (connection != null) 
					connection.close(); 
			} catch(Exception e){ 
			};					
		}
	}

	@Override
	public void delete(Aquarium aquarium) throws SQLException, Exception {
		Connection connection = null;
		
		PreparedStatement  preparedStatement  = null;
		try {
			Class.forName(DBUtils.DRIVER);
			
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			String sql = "delete from zones where Id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt (1, aquarium.getId());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException sqle) {  
			throw new SQLException ();
		} catch(Exception e){ 
			throw new Exception();
		} finally {
			try {
				if (preparedStatement != null) 
					preparedStatement.close(); 
			} catch(Exception e){ 		
			};
			try {
				if (connection != null) 
					connection.close(); 
			} catch(Exception e){ 
			};					
		}
	}
}
