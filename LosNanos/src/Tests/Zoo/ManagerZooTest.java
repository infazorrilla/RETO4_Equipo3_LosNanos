package Tests.Zoo;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Pojos.ZooTicket.Zoo;
import Manager.Zoo.ManagerZoo;

class ManagerZooTest {
	
	private ManagerZoo managerZoo = new ManagerZoo();
	private Zoo zoo = new Zoo ();
	private ArrayList<Zoo> zoos = null;

	@Test
	void testSelectAll() throws SQLException {
		try {
			zoos = managerZoo.selectAll();
			zoo = zoos.get(0);
			String expected = zoo.toString();
			System.out.println(expected);
			assertEquals("Zoo [id=1, location=null, name=null]", expected);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testInsert() {
		try {
			zoo.setId(34);
			zoo.setName(null);
			zoo.setLocation(null);
			
			managerZoo.insert(zoo);
			
			zoos = managerZoo.selectAll();
			Zoo insertedZoo = zoos.get(zoos.size()-1);
			
			for(int i = 0 ; i < zoos.size() ; i++ ) {
				if (zoos.get(i).getId() == insertedZoo.getId()) {
					assertEquals(zoos.get(i), insertedZoo);
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
			zoo.setId(6);
			zoo.setName(null);
			zoo.setLocation(null);

			
			managerZoo.update(zoo);
			
			zoos = managerZoo.selectAll();
			
			for(int i = 0 ; i < zoos.size() ; i++ ) {
				if (zoos.get(i).getId() == zoo.getId()) {
					assertEquals(zoo, zoos.get(i));
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
			zoo.setId(5);
			
			managerZoo.delete(zoo);
			
			zoos = managerZoo.selectAll();
			
			for(int i = 0 ; i < zoos.size() ; i++){
				if(zoos.get(zoo.getId()) == null) {
					assertNull(zoo);
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