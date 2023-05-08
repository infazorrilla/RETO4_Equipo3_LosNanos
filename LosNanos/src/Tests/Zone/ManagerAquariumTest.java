package Tests.Zone;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Manager.Zones.ManagerAquarium;
import Pojos.Zone.Aquarium;

class ManagerAquariumTest {
	
	private ManagerAquarium managerAquarium = new ManagerAquarium();
	private Aquarium aquarium = new Aquarium ();
	private ArrayList<Aquarium> aquariums = null;

	@Test
	void testSelectAll() {
		try {
			aquariums = managerAquarium.selectAll();
			aquarium = aquariums.get(0);
			String expected = aquarium.toString();
			assertEquals("Aquarium [waterTemp=67.0, acuatics=null, id=1, extension=78.0, animalsNumber=13, speciesNumber=2, zoo=null]", expected);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testInsert() {
		try {
			aquarium.setWaterTemp(34);
			aquarium.setExtension(23);
			aquarium.setAnimalsNumber(13);
			aquarium.setSpeciesNumber(2);
			
			managerAquarium.insert(aquarium);
			
			aquariums = managerAquarium.selectAll();
			Aquarium insertedAquarium = aquariums.get(aquariums.size()-1);
			
			for(int i = 0 ; i < aquariums.size() ; i++ ) {
				if (aquariums.get(i).getId() == insertedAquarium.getId()) {
					assertEquals(aquariums.get(i), insertedAquarium);
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
			aquarium.setId(7);
			aquarium.setWaterTemp(67);
			aquarium.setExtension(23);
			aquarium.setAnimalsNumber(13);
			aquarium.setSpeciesNumber(2);
			
			managerAquarium.update(aquarium);
			
			aquariums = managerAquarium.selectAll();
			
			for(int i = 0 ; i < aquariums.size() ; i++ ) {
				if (aquariums.get(i).getId() == aquarium.getId()) {
					assertEquals(aquarium, aquariums.get(i));
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
			aquarium.setId(4);
			
			managerAquarium.delete(aquarium);
			
			aquariums = managerAquarium.selectAll();
			
			for(int i = 0 ; i < aquariums.size() ; i++){
				if(aquariums.get(aquarium.getId()) == null) {
					assertEquals(aquarium, null);
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
