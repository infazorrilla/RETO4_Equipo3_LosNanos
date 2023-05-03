package Tests.Zoo;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Manager.Zoo.ManagerTicket;
import Pojos.ZooTicket.Ticket;

class ManagerTicketTest {

	@Test
	public void getIdTicket() {
		ManagerTicket managerTicket = new ManagerTicket();
		ArrayList<Ticket> ticket = managerTicket.selectTicket();
		for (int i = 0; i < ticket.size(); i++) {
			System.out.print(ticket.get(i).getIdTicket());
		}
		assertNotNull(ticket);
	}

	@Test
	public void inserTicket() throws ParseException {
		ManagerTicket managerTicket = new ManagerTicket();
		Ticket ticket = new Ticket();
		managerTicket.insertTicket(ticket);
		Date buyDate = (Date) ticket.get(0).getBuyDate();
		equals(ticket);
	}
}