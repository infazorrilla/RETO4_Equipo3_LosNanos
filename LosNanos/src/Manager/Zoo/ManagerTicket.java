package Manager.Zoo;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Date;

import Manager.ManagerInterface;
import Pojos.ZooTicket.Ticket;
import utils.DBUtils;

import java.sql.PreparedStatement;

public class ManagerTicket implements ManagerInterface<Ticket> {
	
	@Override
	public ArrayList<Ticket> selectAll(){
		ArrayList<Ticket> ret = null;

		String sql = "select * from ticket";
		
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
					ret = new ArrayList<Ticket>();

				Ticket ticket = new Ticket();

				Date buyDate = resultSet.getDate("buyDate");
				int idTicket = resultSet.getInt("idTicket");
				
				ticket.setBuyDate(buyDate);
				ticket.setIdTicket(idTicket);
				
				ret.add(ticket);
				
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
				;
			}
			return ret;
	}
	
	@Override
	public void insert(Ticket ticket) throws SQLException, Exception {
				Connection connection = null;
				
				Statement statement = null;
				
				try {
					Class.forName(DBUtils.DRIVER);
					
					connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
					
					statement = connection.createStatement();
					
					String sql = "insert into Ticket (BuyDate, IdTicket) VALUES ('" + 
							ticket.getIdTicket() + "', '" +  
							ticket.getIdTicket() + "')";
					
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
	public void update(Ticket ticket) throws SQLException, Exception {
				Connection connection = null;
				
				PreparedStatement  preparedStatement  = null;
				
				try {
					Class.forName(DBUtils.DRIVER);
					
					connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
					
					String sql = "update Ticket set BuyDate = ? where IdTicket = ?";
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setDate (1, (java.sql.Date) ticket.getBuyDate());
					preparedStatement.setInt (2, ticket.getIdTicket());
					
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
	public void delete(Ticket ticket) throws SQLException, Exception {
				Connection connection = null;
				
				PreparedStatement  preparedStatement  = null;
				
				try {
					Class.forName(DBUtils.DRIVER);
					
					connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
					
					String sql = "delete from Ticket where IdTicket = ?";
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt (1, ticket.getIdTicket());
					
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