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

import Pojos.ZooTicket.Ticket;
import utils.DBUtils;

import java.sql.PreparedStatement;

public class ManagerTicket {

	public ArrayList<Ticket> selectTicket(){
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
		List<Ticket> Ticket_sorted = Ticket.stream()
                .sorted(Comparator.comparing(Ticket::getBuyDate))
                .collect(Collectors.toList());

		LocalDate specificDate = LocalDate.of(2021, 5, 1);
		List<Ticket> Ticket_filtered = Ticket.stream()
				.filter(t -> t.getBuyDate().isAfter(specificDate))
				.collect(Collectors.toList());

		Map<Integer, Long> Ticket_by_year = Ticket.stream()
				.collect(Collectors.groupingBy(t -> t.getBuyDate().getYear(),
	         Collectors.counting()));
	
		int specificTicketId = 1234;
		Ticket specificTicket = Ticket.stream()
	         .filter(t -> t.getIdTicket() == specificTicketId)
	         .findFirst()
	         .orElse(null);
	}
}