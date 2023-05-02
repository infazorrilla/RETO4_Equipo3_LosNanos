package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Manager.ManagerBoss;
import Manager.ManagerClient;
import Pojos.Person.Boss;
import Pojos.Person.Client;

class ManagerClienteTest {

	@Test
	public void getClient() {
		ManagerClient managerClient = new ManagerClient();
		ArrayList<Client> client = managerClient.getClient();
		for (int i = 0; i < client.size(); i++) {
			System.out.print(client.get(i).getName());
		}
		assertNotNull(client);
	}

	@Test
	public void inserClient() throws ParseException {
		ManagerClient managerClient = new ManagerClient();
		Client clients = new Client("Ibai", "Torre", "12345678A", "ibai", "abcd1234", 1);
		managerClient.insertClient(clients);
		ArrayList<Client> client = managerClient.getClient();
		String empleado = client.get(0).getName();
		assertEquals(empleado, "Ibai");

	}

	@Test
	public void deleteBoss() throws ParseException {
		ManagerClient managerClient = new ManagerClient();
		Client clients = new Client("Ibai", "Torre", "12345678A", "ibai", "abcd1234", 1);
		managerClient.deleteClient(clients);
		ArrayList<Client> client = managerClient.getClient();
		assertNull(client);

	}

}
