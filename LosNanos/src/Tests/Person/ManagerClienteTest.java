package Tests.Person;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Manager.People.ManagerClient;
import Pojos.Person.Client;

class ManagerClienteTest {

	private ManagerClient managerClient = new ManagerClient();
	private Client client = new Client();
	private ArrayList<Client> clients = null;

	@Test
	void testSelectAll() {
		try {
			clients = managerClient.selectAll();
			client = clients.get(0);
			String expected = client.toString();
			assertEquals("", expected);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testInsert() {
		try {
			client.setName("Ibai");
			client.setSurname("Torre");
			client.setId("12345678A");
			client.setUser("Ibai");
			client.setPassword("1234");
			client.setClientId(1);

			managerClient.insert(client);

			clients = managerClient.selectAll();

			Client insertedBoss = clients.get(clients.size() - 1);

			for (int i = 0; i < clients.size(); i++) {
				if (clients.get(i).getId() == insertedBoss.getId()) {
					assertEquals(clients.get(i), insertedBoss);
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
			client.setName("Ibai");
			client.setSurname("Torre");
			client.setId("12345678A");
			client.setUser("Ibai");
			client.setPassword("1234");
			client.setClientId(1);

			managerClient.update(client);

			clients = managerClient.selectAll();

			for (int i = 0; i < clients.size(); i++) {
				if (clients.get(i).getId() == client.getId()) {
					assertEquals(client, clients.get(i));
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
			client.setName("Ibai");
			client.setSurname("Torre");
			client.setId("12345678A");
			client.setUser("Ibai");
			client.setPassword("1234");
			client.setClientId(1);

			managerClient.delete(client);

			clients = managerClient.selectAll();

			for (int i = 0; i < clients.size(); i++) {
				if (clients.get(i).getId() == client.getId()) {
					assertEquals(client, null);
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
