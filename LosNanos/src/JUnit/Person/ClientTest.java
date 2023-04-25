package JUnit.Person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.Serializable;

import org.junit.jupiter.api.Test;

import Pojos.Person.Client;
import Pojos.Person.Employee;

class ClientTest {

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
		employee.setName(name);
		assertNotEquals("The name it is not accessible", name, employee.getName());
	}

	@Test
	public void testSurName() {
		Client client = new Client(null, null, null, null, null, 0);
		String surName = "1111111";
		employee.setSurname(surName);
		assertNotEquals("The name it is not accessible", surName, employee.getSurname());
	}

	@Test
	public void testId() {
		Client client = new Client(null, null, null, null, null, 0);
		String id = "99999991";
		employee.setId(id);
		assertNotEquals("The name it is not accessible", id, employee.getId());
	}

	@Test
	public void testUser() {
		Client client = new Client(null, null, null, null, null, 0);
		String user = "1111111";
		employee.setSurname(user);
		assertNotEquals("The user it is not accessible", user, employee.getUser());
	}

	@Test
	public void testPassword() {
		Client client = new Client(null, null, null, null, null, 0);
		String password = "1111111";
		employee.setSurname(password);
		assertNotEquals("The password it is not accessible", password, employee.getPassword());
	}

	@Test
	public void testSsNumber() {
		Client client = new Client(null, null, null, null, null, 0);
		int ssNumber = 1111111;
		employee.setSsNumber(ssNumber);
		assertNotEquals("The password it is not accessible", ssNumber, employee.getSsNumber());
	}

	@Test
	public void testPersonExiste() {
		Client client = new Client(null, null, null, null, null, 0);
		employee.setName("Ibai");
		employee.setSurname("Torre");
		employee.setId("12345678A");
		employee.setUser("Ibai");
		employee.setPassword("acbd1234");
		employee.setSsNumber(1);
		Client client = new Client(null, null, null, null, null, 0);
		otherEmployee.setName("Rodri");
		otherEmployee.setSurname("Gomez");
		otherEmployee.setId("87654321A");
		otherEmployee.setUser("Rodri");
		otherEmployee.setPassword("1234abcd");
		otherEmployee.setSsNumber(11);

		assertEquals("people are not the same", employee, otherEmployee);

	}

	@Test
	public void testPersonNoExiste() {
		Client client = new Client(null, null, null, null, null, 0);
		employee.setName("Ibai");
		employee.setSurname("Torre");
		employee.setId("12345678A");
		employee.setUser("Ibai");
		employee.setPassword("acbd1234");
		employee.setSsNumber(1);
		Client client = new Client(null, null, null, null, null, 0);
		otherEmployee.setName("Ibai");
		otherEmployee.setSurname("Torre");
		otherEmployee.setId("12345678A");
		otherEmployee.setUser("Ibai");
		otherEmployee.setPassword("acbd1234");
		otherEmployee.setSsNumber(1);

		assertNotEquals("usuarios son iguales!!!!", employee, otherEmployee);
	}

	@Test
	public void testHashCode() {
		Client client = new Client(null, null, null, null, null, 0);
		assertEquals("hasCode no encontrado!!!", 0, employee.hashCode());
		String id = "12345678A";
		employee.setId(id);
		assertEquals("hashCode-2 no encontrado!!!!", id.hashCode(), employee.hashCode());
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
				+ ", ssNumber=" + client. + "]";

		assertEquals(expected, client.toString());
	}

}
