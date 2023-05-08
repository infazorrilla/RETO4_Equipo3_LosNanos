package Tests.Animal;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

import Manager.Animals.ManagerCrocodile;
import Pojos.Animal.Crocodile;

class ManagerCrocodileTest {

	private ManagerCrocodile managerCrocodile = new ManagerCrocodile();
	private Crocodile crocodile = new Crocodile ();
	private ArrayList<Crocodile> crocodiles = null;

	@Test
	void testSelectAll() {
		try {
			crocodiles = managerCrocodile.selectAll();
			crocodile = crocodiles.get(0);
			String expected = crocodile.toString();
			assertEquals("Crocodile [teethNumber=14, shedSkin=2015-11-23, swamp=null, id=12, name=Paulo, scientificName=Crocodileus, height=25.0, weight=25.0, bornDate=2015-11-23, vaccinated=1, diet=Carnivorous]", expected);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testInsert() {
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
			Date date = formato.parse("23/11/2015");
			
			crocodile.setTeethNumber(14);
			crocodile.setShedSkin(date);
			crocodile.setName("Paulo");
			crocodile.setScientificName("Crocodileus");
			crocodile.setHeight(25);
			crocodile.setWeight(25);
			crocodile.setBornDate(date);
			crocodile.setVaccinated(1);
			crocodile.setDiet("Carnivorous");
			
			managerCrocodile.insert(crocodile);
			
			crocodiles = managerCrocodile.selectAll();
			Crocodile insertedCrocodile = crocodiles.get(0);

			
			for(int i = 0 ; i < crocodiles.size() ; i++ ) {
				if (crocodiles.get(i).getId() == insertedCrocodile.getId()) {
					assertEquals(crocodiles.get(i), insertedCrocodile);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testUpdate() {
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
			Date date = formato.parse("23/11/2015");
			crocodile.setId(2);
			crocodile.setTeethNumber(14);
			crocodile.setShedSkin(date);
			crocodile.setName("Paulo");
			crocodile.setScientificName("Crocodileus");
			crocodile.setHeight(25);
			crocodile.setWeight(25);
			crocodile.setBornDate(date);
			crocodile.setVaccinated(1);
			crocodile.setDiet("Carnivorous");
			
			managerCrocodile.update(crocodile);
			
			crocodiles = managerCrocodile.selectAll();
			
			for(int i = 0 ; i < crocodiles.size() ; i++ ) {
				if (crocodiles.get(i).getId() == crocodile.getId()) {
					assertEquals(crocodile, crocodiles.get(i));
				}
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testDelete() {
		try {
			crocodile.setId(2);
			
			managerCrocodile.delete(crocodile);
			
			crocodiles = managerCrocodile.selectAll();
			
			for(int i = 0 ; i < crocodiles.size() ; i++){
				if(crocodiles.get(crocodile.getId()) == null  ) {
					assertEquals(crocodile, null);
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
