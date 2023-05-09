package Manager.Zoo;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
					String sql = "insert into Ticket (BuyDate, IdTicket) VALUES ('" + 
							ticket.getIdTicket() + "', '" +  
							ticket.getIdTicket() + "')";
					
					// La ejecutamos...
					statement.executeUpdate(sql);
					
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
	public void update(Ticket ticket) throws SQLException, Exception {
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
					String sql = "update Ticket set BuyDate = ? where IdTicket = ?";
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setDate (1, (java.sql.Date) ticket.getBuyDate());
					preparedStatement.setInt (2, ticket.getIdTicket());
					
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
	public void delete(Ticket ticket) throws SQLException, Exception {
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
					String sql = "delete from Ticket where IdTicket = ?";
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt (1, ticket.getIdTicket());
					
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