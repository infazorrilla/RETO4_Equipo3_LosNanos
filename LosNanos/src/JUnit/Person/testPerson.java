package JUnit.Person;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Person {

	//***** para probar la serializaci�n	
		@Test
		public void testSerailizable() {
			Person person = new Person();
			assertTrue("No se puede realizar la serializaci�n!!!", accesoUsuario instanceof Serializable);
		}
		
		//************* probar getter & Setters
		
		@Test
		public void testId() {
			AccesoUsuario accesoUsuario = new AccesoUsuario();
			Long id=(long) 99999991;
			accesoUsuario.setId(id);
			assertNotEquals("Id no est� accesible", id, accesoUsuario.getId());
		}
		
		@Test
		public void testNombre() {
			AccesoUsuario accesoUsuario = new AccesoUsuario();
			String name= "un texto";
			accesoUsuario.setNombre(name);
			assertNotEquals("Nombre no est� accesible", name, accesoUsuario.getNombre());
		}
		
		@Test
		public void testUsuarioExiste() {
			AccesoUsuario accesoUsuario= new AccesoUsuario();
			accesoUsuario.setId((long) 1234);
			accesoUsuario.setNombre("Zipi");
			AccesoUsuario otroUsuario= new AccesoUsuario();
			otroUsuario.setId((long) 1234);
			otroUsuario.setNombre("Zipi");
			
			assertEquals("usuarios no son iguales!!!!", accesoUsuario, otroUsuario);	
			
		}

		@Test
		public void testUsuarioNoExiste() {
			AccesoUsuario accesoUsuario= new AccesoUsuario();
			accesoUsuario.setId((long) 1234);
			accesoUsuario.setNombre("Zipi");
			
			AccesoUsuario otroUsuario= new AccesoUsuario();
			otroUsuario.setId((long) 1234);
			otroUsuario.setNombre("Zipi");
			
			assertNotEquals("usuarios son iguales!!!!", accesoUsuario, otroUsuario);	
			
			//*******
			//otroUsuario.setId((long) 1234);
			//otroUsuario.setNombre("Zipi");
			
			//assertNotEquals("usuarios son iguales!!!!", accesoUsuario, otroUsuario);	
			
		}

		@Test
		public void testHashCode() {
		AccesoUsuario accesoUsuario=new AccesoUsuario();
		assertEquals("hasCode no encontrado!!!",0, accesoUsuario.hashCode());
		Long id=(long) 1234;
		accesoUsuario.setId(id);
		assertEquals("hashCode-2 no encontrado!!!!", id.hashCode(),accesoUsuario.hashCode());
		}

}
