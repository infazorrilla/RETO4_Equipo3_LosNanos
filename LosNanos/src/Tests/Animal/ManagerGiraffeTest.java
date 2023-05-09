package Tests.Animal;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

import Manager.Animals.ManagerGiraffe;
import Pojos.Animal.Giraffe;

class ManagerGiraffeTest {

	private ManagerGiraffe managerGiraffe = new ManagerGiraffe();
	private Giraffe giraffe = new Giraffe ();
	private ArrayList<Giraffe> giraffes = null;

	@Test
	void testSelectAll() {
		try {
			giraffes = managerGiraffe.selectAll();
			giraffe = giraffes.get(0);
			String expected = giraffe.toString();
			System.out.println(expected);
			assertEquals("Giraffe [neckLength=10, hairColor=Green, savannah=null, id=1, name=Paulo, scientificName=Giraffeus, height=25.0, weight=25.0, bornDate=2015-11-23, vaccinated=1, diet=Carnivorous]", expected);
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
			
			giraffe.setNeckLength(14);
			giraffe.setHairColor("Red");
			giraffe.setName("Paulo");
			giraffe.setScientificName("Giraffeus");
			giraffe.setHeight(25);
			giraffe.setWeight(25);
			giraffe.setBornDate(date);
			giraffe.setVaccinated(1);
			giraffe.setDiet("Carnivorous");
			
			managerGiraffe.insert(giraffe);
			
			giraffes = managerGiraffe.selectAll();
			Giraffe insertedGiraffe = giraffes.get(0);

			
			for(int i = 0 ; i < giraffes.size() ; i++ ) {
				if (giraffes.get(i).getId() == insertedGiraffe.getId()) {
					assertEquals(giraffes.get(i), insertedGiraffe);
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
			giraffe.setId(1);
			giraffe.setNeckLength(10);
			giraffe.setHairColor("Green");
			giraffe.setName("Paulo");
			giraffe.setScientificName("Giraffeus");
			giraffe.setHeight(25);
			giraffe.setWeight(25);
			giraffe.setBornDate(date);
			giraffe.setVaccinated(1);
			giraffe.setDiet("Carnivorous");
			
			managerGiraffe.update(giraffe);
			
			giraffes = managerGiraffe.selectAll();
			
			for(int i = 0 ; i < giraffes.size() ; i++ ) {
				if (giraffes.get(i).getId() == giraffe.getId()) {
					assertEquals(giraffe, giraffes.get(i));
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
			giraffe.setId(5);
			
			managerGiraffe.delete(giraffe);
			
			giraffes = managerGiraffe.selectAll();
			
			for(int i = 0 ; i < giraffes.size() ; i++){
				if(giraffes.get(giraffe.getId()) == null  ) {
					assertEquals(giraffe, null);
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
