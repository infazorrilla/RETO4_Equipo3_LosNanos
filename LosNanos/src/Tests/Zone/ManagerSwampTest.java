package Tests.Zone;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Manager.Zones.ManagerSwamp;
import Pojos.Zone.Swamp;

class ManagerSwampTest {
	
	private ManagerSwamp managerSwamp = new ManagerSwamp();
	private Swamp swamp = new Swamp();
	private ArrayList<Swamp> swamps = null;

	@Test
	void testSelectAll() {
		try {
			swamps = managerSwamp.selectAll();
			swamp = swamps.get(1);
			String expected = swamp.toString();
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
			swamp.setWaterTemp(34);
			swamp.setExtension("23m2");
			swamp.setAnimalsNumber(13);
			swamp.setSpeciesNumber(2);
			
			managerSwamp.insert(swamp);
			
			swamps = managerSwamp.selectAll();
			Swamp insertedSwamp = swamps.get(swamps.size() - 1);
			
			assertEquals(swamp, insertedSwamp);
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
			swamp.setId(1);
			swamp.setWaterTemp(34);
			swamp.setExtension("23m2");
			swamp.setAnimalsNumber(13);
			swamp.setSpeciesNumber(2);
			
			managerSwamp.update(swamp);
			
			swamps = managerSwamp.selectAll();
			
			for(Swamp searchedSwamp  : swamps) {
				if(searchedSwamp.getId() == swamp.getId()) {
					assertEquals(swamp, searchedSwamp);
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
			swamp.setId(2);
			
			managerSwamp.delete(swamp);
			
			swamps = managerSwamp.selectAll();
			
			for(int i = 0 ; i < swamps.size() ; i++){
				if(swamps.get(swamp.getId() - 1) == null  ) {
					assertEquals(swamp, null);
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
