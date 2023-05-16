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
	public ArrayList<Savannah> selectAll() throws SQLException, Exception {
		ArrayList <Savannah> ret = null;

		String sql = "select * from savannahComplete";
		
		// La conexion con BBDD
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
					ret = new ArrayList <Savannah>();
				
				Savannah savannah = new Savannah();
				
				int id = resultSet.getInt("id");
                float extension = resultSet.getFloat("extension");
                int animalsNumber = resultSet.getInt("animalsNumber");
                int speciesNumber  = resultSet.getInt("speciesNumber");
                int treeNumber  = resultSet.getInt("treeNumber");
                
                savannah.setId(id);
                savannah.setExtension(extension);
                savannah.setAnimalsNumber(animalsNumber);
                savannah.setSpeciesNumber(speciesNumber);
                savannah.setTreeNumber(treeNumber);

                ret.add(savannah);
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
	public void insert(Savannah savannah) throws SQLException, Exception  {
		Connection connection = null;
		
		Statement statement = null;
		
		try {
			Class.forName(DBUtils.DRIVER);
			
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			statement = connection.createStatement();
			
			String sql = "insert into Zones (extension, animalsNumber, speciesNumber) VALUES ('" + 
					savannah.getExtension() + "', '" +  
					savannah.getAnimalsNumber() + "', '" + 	
					savannah.getSpeciesNumber() + "')";
			
			String sql2 = "insert into Savannah (zoneId, treeNumber) SELECT MAX(id),"
					+ savannah.getTreeNumber() + " FROM zones";

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
	public void update(Savannah savannah) throws SQLException, Exception  {
		Connection connection = null;
		
		PreparedStatement  preparedStatement  = null;
		
		try {
			Class.forName(DBUtils.DRIVER);
			
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			String sql = "update Zones, savannah set treeNumber = ?, extension = ?, animalsNumber = ?, speciesNumber = ? where Id = ? and zoneId = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setFloat (1, savannah.getTreeNumber());
			preparedStatement.setFloat (2, savannah.getExtension());
			preparedStatement.setInt (3, savannah.getAnimalsNumber());
			preparedStatement.setInt (4, savannah.getSpeciesNumber());
			preparedStatement.setInt (5, savannah.getId());
			preparedStatement.setInt (6, savannah.getId());
			
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
	public void delete(Savannah savannah) throws SQLException, Exception  {
		Connection connection = null;
		
		PreparedStatement  preparedStatement  = null;
		
		try {
			Class.forName(DBUtils.DRIVER);
			
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			String sql = "delete from zones where Id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt (1, savannah.getId());
			
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
