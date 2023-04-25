package JUnit.Person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.Serializable;

import org.junit.jupiter.api.Test;

import JUnit.Animal.Cine;
import Pojos.Person.Person;

class PersonTest {

	// Serializacion
	@Test
	public void testSerailizable() {
		Person person = new Person();
		assertTrue("You can't do the serialization!!!", person instanceof Serializable);
	}

	// Getter & Setters

	@Test
	public void testName() {
		Person person = new Person();
		String name = "1111111";
		person.setName(name);
		assertNotEquals("The name it is not accessible", name, person.getName());
	}

	@Test
	public void testSurName() {
		Person person = new Person();
		String surName = "1111111";
		person.setSurname(surName);
		assertNotEquals("The name it is not accessible", surName, person.getSurname());
	}

	@Test
	public void testId() {
		Person person = new Person();
		String id = "99999991";
		person.setId(id);
		assertNotEquals("The name it is not accessible", id, person.getId());
	}

	@Test
	public void testUser() {
		Person person = new Person();
		String user = "1111111";
		person.setSurname(user);
		assertNotEquals("The user it is not accessible", user, person.getUser());
	}

	@Test
	public void testPassword() {
		Person person = new Person();
		String password = "1111111";
		person.setSurname(password);
		assertNotEquals("The password it is not accessible", password, person.getPassword());
	}

	@Test
	public void testPersonExiste() {
		Person person = new Person();
		person.setName("Ibai");
		person.setSurname("Torre");
		person.setId("12345678A");
		person.setUser("Ibai");
		person.setPassword("acbd1234");
		Person otherPerson = new Person();
		otherPerson.setName("Rodri");
		otherPerson.setSurname("Gomez");
		otherPerson.setId("87654321A");
		otherPerson.setUser("Rodri");
		otherPerson.setPassword("1234abcd");

		assertEquals("people are not the same", person, otherPerson);

	}

	@Test
	public void testPersonNoExiste() {
		Person person = new Person();
		person.setName("Ibai");
		person.setSurname("Torre");
		person.setId("12345678A");
		person.setUser("Ibai");
		person.setPassword("acbd1234");
		Person otherPerson = new Person();
		otherPerson.setName("Ibai");
		otherPerson.setSurname("Torre");
		otherPerson.setId("12345678A");
		otherPerson.setUser("Ibai");
		otherPerson.setPassword("acbd1234");

		assertNotEquals("usuarios son iguales!!!!", person, otherPerson);
	}

	@Test
	public void testHashCode() {
		Person person = new Person();
		assertEquals("hasCode no encontrado!!!", 0, person.hashCode());
		String id = "12345678A";
		person.setId(id);
		assertEquals("hashCode-2 no encontrado!!!!", id.hashCode(), person.hashCode());
	}

	@Test
	public void testToString() {
		Person person = new Person();
		person.setName("Ibai");
		person.setSurname("Torre");
		person.setId("12345678A");
		person.setUser("Ibai");
		person.setPassword("acbd1234");
		String expected = "Person [name=" + person.getName() + ", surname=" + person.getSurname() + ", id="
				+ person.getId() + ", user=" + person.getUser() + ", password=" + person.getPassword() + "]";

		assertEquals(expected, person.toString());
	}

}
