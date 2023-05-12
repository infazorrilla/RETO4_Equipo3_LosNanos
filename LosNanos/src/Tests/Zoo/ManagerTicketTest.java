package Tests.Zoo;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Pojos.ZooTicket.Ticket;
import Manager.Zoo.ManagerTicket;

class ManagerTicketTest {
	
	private ManagerTicket managerTicket = new ManagerTicket();
	private Ticket ticket = new Ticket (dfds);
	private ArrayList<Ticket> tickets = null;

	@Test
	void testSelectAll() throws SQLException {
		try {
			tickets = managerTicket.selectAll();
			ticket = tickets.get(0);
			String expected = ticket.toString();
			System.out.println(expected);
			assertEquals("Ticket [idTicket=1, ticket=null]", expected);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testInsert() {
		try {
			ticket.setIdTicket(34);
			ticket.setBuyDate(null);
			
			managerTicket.insert(ticket);
			
			tickets = managerTicket.selectAll();
			Ticket insertedTicket = tickets.get(tickets.size()-1);
			
			for(int i = 0 ; i < tickets.size() ; i++ ) {
				if (tickets.get(i).getIdTicket() == insertedTicket.getIdTicket()) {
					assertEquals(tickets.get(i), insertedTicket);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testUpdate() {
		try {
			ticket.setIdTicket(6);
			ticket.setBuyDate(null);
			
			managerTicket.update(ticket);
			
			tickets = managerTicket.selectAll();
			
			for(int i = 0 ; i < tickets.size() ; i++ ) {
				if (tickets.get(i).getIdTicket() == ticket.getIdTicket()) {
					assertEquals(ticket, tickets.get(i));
					break;
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testDelete() {
		try {
			ticket.setIdTicket(5);
			
			managerTicket.delete(ticket);
			
			tickets = managerTicket.selectAll();
			
			for(int i = 0 ; i < tickets.size() ; i++){
				if(tickets.get(ticket.getIdTicket()) == null) {
					assertNull(ticket);
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}