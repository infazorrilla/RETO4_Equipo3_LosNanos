package Manager.Zoo;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.util.ArrayList;

import Manager.ManagerInterface;
import Pojos.ZooTicket.Zoo;
import utils.DBUtils;

import java.sql.PreparedStatement;

public class ManagerZoo implements ManagerInterface<Zoo>{

	@Override
	public ArrayList<Zoo> selectAll(){
		ArrayList<Zoo> ret = null;
		
		String sql = "select * from zoo";
		
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

				Zoo zoo = new Zoo();

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
		@Override
		public void insert(Zoo zoo) throws SQLException, Exception {
					Connection connection = null;
					
					Statement statement = null;
					
					try {
						Class.forName(DBUtils.DRIVER);
						
						connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
						
						statement = connection.createStatement();
						
						String sql = "insert into Zoo (Name, Location ) VALUES ('" + 
								zoo.getName() + "', '" + 
								zoo.getLocation() + "')";
						
						statement.executeUpdate(sql);
						
					} catch (SQLException sqle) {  
						System.out.println("Error con la BBDD - " + sqle.getMessage());
					} catch(Exception e){ 
						System.out.println("Error generico - " + e.getMessage());
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
		public void update(Zoo zoo) throws SQLException, Exception {
					Connection connection = null;
					
					PreparedStatement  preparedStatement  = null;
					
					try {
						Class.forName(DBUtils.DRIVER);
						
						connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
						
						String sql = "update Zoo set BuyDate = ? where IdTicket = ?";
						preparedStatement = connection.prepareStatement(sql);
						preparedStatement.setString (1, zoo.getName());
						preparedStatement.setString (1, zoo.getLocation());
						preparedStatement.setInt (2, zoo.getId());
						
						preparedStatement.executeUpdate();
						
					} catch (SQLException sqle) {  
						System.out.println("Error con la BBDD - " + sqle.getMessage());
					} catch(Exception e){ 
						System.out.println("Error generico - " + e.getMessage());
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
		public void delete(Zoo zoo) throws SQLException, Exception {
					Connection connection = null;
					
					PreparedStatement  preparedStatement  = null;
					
					try {
						Class.forName(DBUtils.DRIVER);
						
						connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
						
						String sql = "delete from Ticket where IdTicket = ?";
						preparedStatement = connection.prepareStatement(sql);
						preparedStatement.setInt (1, zoo.getId());
						preparedStatement.setString (2, zoo.getName());
						preparedStatement.setString (2, zoo.getLocation());

						
						preparedStatement.executeUpdate();
						
					} catch (SQLException sqle) {  
						System.out.println("Error con la BBDD - " + sqle.getMessage());
					} catch(Exception e){ 
						System.out.println("Error generico - " + e.getMessage());
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
