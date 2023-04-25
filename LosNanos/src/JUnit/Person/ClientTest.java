package JUnit.Person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.junit.jupiter.api.Test;

import Pojos.Person.Client;

class ClientTest{

	// Serializacion
	@Test
	public void testSerailizable() {
		Client client = new Client(null, null, null, null, null, 0);
		assertTrue("You can't do the serialization!!!", client instanceof Serializable);
	}

	// Getter & Setters

	@Test
	public void testName() {
		Client client = new Client(null, null, null, null, null, 0);
		String name = "1111111";
		client.setName(name);
		assertNotEquals("The name it is not accessible", name, client.getName());
	}

	@Test
	public void testSurName() {
		Client client = new Client(null, null, null, null, null, 0);
		String surName = "1111111";
		client.setSurname(surName);
		assertNotEquals("The name it is not accessible", surName, client.getSurname());
	}

	@Test
	public void testId() {
		Client client = new Client(null, null, null, null, null, 0);
		String id = "99999991";
		client.setId(id);
		assertNotEquals("The name it is not accessible", id, client.getId());
	}

	@Test
	public void testUser() {
		Client client = new Client(null, null, null, null, null, 0);
		String user = "1111111";
		client.setSurname(user);
		assertNotEquals("The user it is not accessible", user, client.getUser());
	}

	@Test
	public void testPassword() {
		Client client = new Client(null, null, null, null, null, 0);
		String password = "1111111";
		client.setSurname(password);
		assertNotEquals("The password it is not accessible", password, client.getPassword());
	}

	@Test
	public void testSsNumber() {
		Client client = new Client(null, null, null, null, null, 0);
		int clientId = 11111111;
		client.setClientId(clientId);
		assertNotEquals("The password it is not accessible", clientId, client.getClientId());
	}

	@Test
	public void testPersonExiste() {
		Client client = new Client(null, null, null, null, null, 0);
		client.setName("Ibai");
		client.setSurname("Torre");
		client.setId("12345678A");
		client.setUser("Ibai");
		client.setPassword("acbd1234");
		client.setClientId(1);
		Client othetClient = new Client(null, null, null, null, null, 0);
		othetClient.setName("Rodri");
		othetClient.setSurname("Gomez");
		othetClient.setId("87654321A");
		othetClient.setUser("Rodri");
		othetClient.setPassword("1234abcd");
		othetClient.setClientId(11);

		assertEquals("people are not the same", client, othetClient);

	}

	@Test
	public void testPersonNoExiste() {
		Client client = new Client(null, null, null, null, null, 0);
		client.setName("Ibai");
		client.setSurname("Torre");
		client.setId("12345678A");
		client.setUser("Ibai");
		client.setPassword("acbd1234");
		client.setClientId(1);
		Client othetClient = new Client(null, null, null, null, null, 0);
		othetClient.setName("Ibai");
		othetClient.setSurname("Torre");
		othetClient.setId("12345678A");
		othetClient.setUser("Ibai");
		othetClient.setPassword("acbd1234");
		othetClient.setClientId(1);

		assertNotEquals("usuarios son iguales!!!!", client, othetClient);
	}

	@Test
	public void testHashCode() {
		Client client = new Client(null, null, null, null, null, 0);
		assertEquals("hasCode no encontrado!!!", 0, client.hashCode());
		String id = "12345678A";
		client.setId(id);
		assertEquals("hashCode-2 no encontrado!!!!", id.hashCode(), client.hashCode());
	}

	@Test
	public void testToString() {
		Client client = new Client(null, null, null, null, null, 0);
		client.setName("Ibai");
		client.setSurname("Torre");
		client.setId("12345678A");
		client.setUser("Ibai");
		client.setPassword("acbd1234");
		client.setClientId(1);
		String expected = "Person [name=" + client.getName() + ", surname=" + client.getSurname() + ", id="
				+ client.getId() + ", user=" + client.getUser() + ", password=" + client.getPassword()
				+ ", ssNumber=" + client.getClientId() + "]";

		assertEquals(expected, client.toString());
	}

}
