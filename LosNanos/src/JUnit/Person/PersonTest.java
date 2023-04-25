package JUnit.Person;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.Serializable;

import org.junit.jupiter.api.Test;

import Pojos.Person.Person;

class PersonTest {

	// ***** para probar la serializacion
	@Test
	public void testSerailizable() {
		Person person = new Person(null, null, null, null,null);
		assertTrue("No se puede realizar la serializacion!!!", person instanceof Serializable);
	}

}
