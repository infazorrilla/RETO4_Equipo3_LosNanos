package Tests.Zone;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Manager.Zones.ManagerSavannah;
import Pojos.Zone.Savannah;

class ManagerSavannahTest {
	
	private ManagerSavannah managerSavannah = new ManagerSavannah();
	private Savannah savannah = new Savannah ();
	private ArrayList<Savannah> savannahs = null;

	@Test
	void testSelectAll() {
		try {
			savannahs = managerSavannah.selectAll();
			savannah = savannahs.get(0);
			String expected = savannah.toString();
			assertEquals("Savannah [treeNumber=67, terrestrialMammarians=null, id=7, extension=23.0, animalsNumber=13, speciesNumber=2, zoo=null]", expected);
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
			savannah.setTreeNumber(34);
			savannah.setExtension(23);
			savannah.setAnimalsNumber(13);
			savannah.setSpeciesNumber(2);
			
			managerSavannah.insert(savannah);
			
			savannahs = managerSavannah.selectAll();
			Savannah insertedSavannah = savannahs.get(savannahs.size()-1);
			
			for(int i = 0 ; i < savannahs.size() ; i++ ) {
				if (savannahs.get(i).getId() == insertedSavannah.getId()) {
					assertEquals(savannahs.get(i), insertedSavannah);
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
			savannah.setId(9);
			savannah.setTreeNumber(654);
			savannah.setExtension(123);
			savannah.setAnimalsNumber(896);
			savannah.setSpeciesNumber(2);
			
			managerSavannah.update(savannah);
			
			savannahs = managerSavannah.selectAll();
			
			for(int i = 0 ; i < savannahs.size() ; i++ ) {
				if (savannahs.get(i).getId() == savannah.getId()) {
					assertEquals(savannah, savannahs.get(i));
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
			savannah.setId(2);
			
			managerSavannah.delete(savannah);
			
			savannahs = managerSavannah.selectAll();
			
			for(int i = 0 ; i < savannahs.size() ; i++){
				if(savannahs.get(savannah.getId()) == null  ) {
					assertEquals(savannah, null);
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
