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
			aquarium = aquariums.get(1);
			String expected = aquarium.toString();
			assertEquals("Aquarium [waterTemp=12.0, acuatics=null, id=2, extension=64.0, animalsNumber=12, speciesNumber=1, zoo=null]", expected);
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
			aquarium.setExtension("23m2");
			aquarium.setAnimalsNumber(13);
			aquarium.setSpeciesNumber(2);
			
			managerAquarium.insert(aquarium);
			
			aquariums = managerAquarium.selectAll();
			Aquarium insertedAquarium = aquariums.get(aquariums.size() - 1);
			
			assertEquals(aquarium, insertedAquarium);
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
			aquarium.setId(1);
			aquarium.setWaterTemp(34);
			aquarium.setExtension("23m2");
			aquarium.setAnimalsNumber(13);
			aquarium.setSpeciesNumber(2);
			
			managerAquarium.update(aquarium);
			
			aquariums = managerAquarium.selectAll();
			
			for(Aquarium searchedAquarium  : aquariums) {
				if(searchedAquarium.getId() == aquarium.getId()) {
					assertEquals(aquarium, searchedAquarium);
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
			aquarium.setId(2);
			
			managerAquarium.delete(aquarium);
			
			aquariums = managerAquarium.selectAll();
			
			for(int i = 0 ; i < aquariums.size() ; i++){
				if(aquariums.get(aquarium.getId() - 1) == null  ) {
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
