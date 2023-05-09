package Tests.Animal;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Manager.Animals.ManagerDolphin;
import Pojos.Animal.Dolphin;

class ManagerDolphinTest {

	private ManagerDolphin managerDolphin = new ManagerDolphin();
	private Dolphin dolphin = new Dolphin ();
	private ArrayList<Dolphin> dolphins = null;

	@Test
	void testSelectAll() {
		try {
			dolphins = managerDolphin.selectAll();
			dolphin = dolphins.get(0);
			String expected = dolphin.toString();
			assertEquals("Dolphin [durationUnderWater=34, animalType=mammals, aquarium=null, id=19, name=Paulo, scientificName=Dolphinus, height=25.0, weight=25.0, bornDate=2015-11-23, vaccinated=1, diet=Carnivorous]", expected);
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
			
			dolphin.setDurationUnderWater(34);
			dolphin.setAnimalType("mammals");
			dolphin.setName("Paulo");
			dolphin.setScientificName("Dolphinus");
			dolphin.setHeight(25);
			dolphin.setWeight(25);
			dolphin.setBornDate(date);
			dolphin.setVaccinated(1);
			dolphin.setDiet("Carnivorous");
			
			managerDolphin.insert(dolphin);
			
			dolphins = managerDolphin.selectAll();
			Dolphin insertedDolphin = dolphins.get(0);

			
			for(int i = 0 ; i < dolphins.size() ; i++ ) {
				if (dolphins.get(i).getId() == insertedDolphin.getId()) {
					assertEquals(dolphins.get(i), insertedDolphin);
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
			dolphin.setId(19);
			dolphin.setDurationUnderWater(34);
			dolphin.setAnimalType("mammals");
			dolphin.setName("Paulo");
			dolphin.setScientificName("Dolphinus");
			dolphin.setHeight(25);
			dolphin.setWeight(25);
			dolphin.setBornDate(date);
			dolphin.setVaccinated(1);
			dolphin.setDiet("Carnivorous");
			
			managerDolphin.update(dolphin);
			
			dolphins = managerDolphin.selectAll();
			
			for(int i = 0 ; i < dolphins.size() ; i++ ) {
				if (dolphins.get(i).getId() == dolphin.getId()) {
					assertEquals(dolphin, dolphins.get(i));
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
			dolphin.setId(2);
			
			managerDolphin.delete(dolphin);
			
			dolphins = managerDolphin.selectAll();
			
			for(int i = 0 ; i < dolphins.size() ; i++){
				if(dolphins.get(dolphin.getId()) == null  ) {
					assertEquals(dolphin, null);
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
