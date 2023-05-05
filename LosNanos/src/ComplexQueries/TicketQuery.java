package ComplexQueries;

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

public class TicketQuery {

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