package Tests.Zoo;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Manager.ManagerTicket;
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
	public void inserBoss() throws ParseException {
		ManagerTicket managerTicket = new ManagerTicket();
		Ticket ticket = new Ticket();
		managerTicket.insertTicket(ticket);
		String empleado = ticket.get(0).getName();
		assertEquals();

	}

	@Test
	public void deleteBoss() throws ParseException {
		ManagerTicket managerTicket = new ManagerTicket();
		Boss bosses = new Boss("Ibai", "Torre", "12345678A", "ibai", "abcd1234", 1, 1);
		managerBoss.deleteBoss(bosses);
		ArrayList<Boss> boss = managerBoss.getBoss();
		assertNull(boss);

	}
}

	
	}

}
