package Gestor;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.util.ArrayList;

import Pojos.ZooTicket.Zoo;
import utils.DBUtils;

import java.sql.PreparedStatement;

public class ManagerZoo {
	
	public ArrayList<Zoo> selectZoo(){
		ArrayList<Zoo> ret = null;
		
		String sql = "";
		
		Connection connection = null;
		Statement statement = null;

		ResultSet resultSet = null;
		
		PreparedStatement preparedStatement = null;
		
		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				
				if (null == ret)
					ret = new ArrayList<Zoo>();

				Zoo zoo= new Zoo();

				String location = resultSet.getString("location");
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				
				zoo.setLocation(location);
				zoo.setId(id);
				zoo.setName(name);
				
				ret.add(zoo);
				
			}
		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {

			}
			;
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
			
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			
			}
		}
		return ret;
	}
}
