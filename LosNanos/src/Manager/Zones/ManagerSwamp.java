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
	public ArrayList<Swamp> selectAll() throws SQLException, Exception  {
		ArrayList <Swamp> ret = null;
		
		String sql = "select * from swampComplete";
		
		Connection connection = null;
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			Class.forName(DBUtils.DRIVER);
			
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			// Vamos a lanzar la sentencia...
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				
				if (null == ret)
					ret = new ArrayList <Swamp>();
				
				Swamp swamp = new Swamp();
				
				int id = resultSet.getInt("id");
				float extension = resultSet.getFloat("extension");
                int animalsNumber = resultSet.getInt("animalsNumber");
                int speciesNumber  = resultSet.getInt("speciesNumber");
                float waterSurface  = resultSet.getFloat("waterSurface");
                
                swamp.setId(id);
                swamp.setExtension(extension);
                swamp.setAnimalsNumber(animalsNumber);
                swamp.setSpeciesNumber(speciesNumber);
                swamp.setWaterSurface(waterSurface);

                ret.add(swamp);
			}
		} catch (SQLException sqle) {  
			throw new SQLException ();
		} catch(Exception  e){ 
			throw new Exception ();
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
	public void insert(Swamp swamp) throws SQLException, Exception  {
		Connection connection = null;
		
		Statement statement = null;
		
		try {
			Class.forName(DBUtils.DRIVER);
			
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			statement = connection.createStatement();
			
			String sql = "insert into Zones (extension, animalsNumber, speciesNumber) VALUES ('" + 
					swamp.getExtension() + "', '" +  
					swamp.getAnimalsNumber() + "', '" + 	
					swamp.getSpeciesNumber() + "')";
			
			String sql2 = "insert into swamp (zoneId, waterSurface) SELECT MAX(id), " + 
					swamp.getWaterSurface() + "FROM zones";
			
			statement.executeUpdate(sql);
			statement.executeUpdate(sql2);
			
		} catch (SQLException sqle) {  
			throw new SQLException ();
		} catch(Exception  e){ 
			throw new Exception ();
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
	public void update(Swamp swamp) throws SQLException, Exception  {
		Connection connection = null;
		
		PreparedStatement  preparedStatement  = null;
		
		try {
			Class.forName(DBUtils.DRIVER);
			
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			String sql = "update Zones, swamp set waterSurface = ?, extension = ?, animalsNumber = ?, speciesNumber = ? where Id = ? and zoneId = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setFloat (1, swamp.getWaterSurface());
			preparedStatement.setFloat (2, swamp.getExtension());
			preparedStatement.setInt (3, swamp.getAnimalsNumber());
			preparedStatement.setInt (4, swamp.getSpeciesNumber());
			preparedStatement.setInt (5, swamp.getId());
			preparedStatement.setInt (6, swamp.getId());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException sqle) {  
			throw new SQLException ();
		} catch(Exception  e){ 
			throw new Exception ();
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
	public void delete(Swamp swamp) throws SQLException, Exception  {
		Connection connection = null;
		
		PreparedStatement  preparedStatement  = null;
		
		try {
			Class.forName(DBUtils.DRIVER);
			
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			String sql = "delete from zones where Id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt (1, swamp.getId());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException sqle) {  
			throw new SQLException ();
		} catch(Exception  e){ 
			throw new Exception ();
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
