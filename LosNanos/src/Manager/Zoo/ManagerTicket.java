package Manager;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Date;

import Pojos.ZooTicket.Ticket;
import utils.DBUtils;

import java.sql.PreparedStatement;

public class ManagerTicket {

	public ArrayList<Ticket> selectTicket(){
		ArrayList<Ticket> ret = null;
		
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
					ret = new ArrayList<Ticket>();

				Ticket ticket = new Ticket();

				Date buyDate = resultSet.getDate("buyDate");
				int idTicket = resultSet.getInt("idTicket");
				
				ticket.setBuyDate(buyDate);
				ticket.setId(idTicket);
				
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

	public void insertTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		
	}
}