package JUnit.Person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.Serializable;

import org.junit.jupiter.api.Test;

import Pojos.Person.Employee;

class EmployeeTest {

	// Serializacion
	@Test
	public void testSerailizable() {
		Employee employee = new Employee();
		assertTrue("You can't do the serialization!!!", employee instanceof Serializable);
	}

	// Getter & Setters

	@Test
	public void testName() {
		Employee employee = new Employee();
		String name = "1111111";
		employee.setName(name);
		assertNotEquals("The name it is not accessible", name, employee.getName());
	}

	@Test
	public void testSurName() {
		Employee employee = new Employee();
		String surName = "1111111";
		employee.setSurname(surName);
		assertNotEquals("The name it is not accessible", surName, employee.getSurname());
	}

	@Test
	public void testId() {
		Employee employee = new Employee();
		String id = "99999991";
		employee.setId(id);
		assertNotEquals("The name it is not accessible", id, employee.getId());
	}

	@Test
	public void testUser() {
		Employee employee = new Employee();
		String user = "1111111";
		employee.setSurname(user);
		assertNotEquals("The user it is not accessible", user, employee.getUser());
	}

	@Test
	public void testPassword() {
		Employee employee = new Employee();
		String password = "1111111";
		employee.setSurname(password);
		assertNotEquals("The password it is not accessible", password, employee.getPassword());
	}

	@Test
	public void testSsNumber() {
		Employee employee = new Employee();
		int ssNumber = 1111111;
		employee.setSsNumber(ssNumber);
		assertNotEquals("The password it is not accessible", ssNumber, employee.getSsNumber());
	}

	@Test
	public void testPersonExiste() {
		Employee employee = new Employee();
		employee.setName("Ibai");
		employee.setSurname("Torre");
		employee.setId("12345678A");
		employee.setUser("Ibai");
		employee.setPassword("acbd1234");
		employee.setSsNumber(1);
		Employee otherEmployee = new Employee();
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
		Employee employee = new Employee();
		employee.setName("Ibai");
		employee.setSurname("Torre");
		employee.setId("12345678A");
		employee.setUser("Ibai");
		employee.setPassword("acbd1234");
		employee.setSsNumber(1);
		Employee otherEmployee = new Employee();
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
		Employee employee = new Employee();
		assertEquals("hasCode no encontrado!!!", 0, employee.hashCode());
		String id = "12345678A";
		employee.setId(id);
		assertEquals("hashCode-2 no encontrado!!!!", id.hashCode(), employee.hashCode());
	}

	@Test
	public void testToString() {
		Employee employee = new Employee();
		employee.setName("Ibai");
		employee.setSurname("Torre");
		employee.setId("12345678A");
		employee.setUser("Ibai");
		employee.setPassword("acbd1234");
		employee.setSsNumber(1);
		String expected = "Person [name=" + employee.getName() + ", surname=" + employee.getSurname() + ", id="
				+ employee.getId() + ", user=" + employee.getUser() + ", password=" + employee.getPassword()
				+ ", ssNumber=" + employee.getSsNumber() + "]";

		assertEquals(expected, employee.toString());
	}

}
