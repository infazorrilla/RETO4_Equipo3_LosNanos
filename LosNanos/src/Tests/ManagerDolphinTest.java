package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Gestor.ManagerDolphin;
import Pojos.Animal.Dolphin;


class ManagerDolphinTest {

	@Test
	public void getDolphin() {
		ManagerDolphin managerDolphin = new ManagerDolphin();
		ArrayList<Dolphin> dolphin = managerDolphin.getDolphin();
		for (int i = 0; i < dolphin.size(); i++) {
			System.out.print(dolphin.get(i).getName());
		}
		assertNotNull(dolphin);
	}
	
	@Test
	public void inserDolphin() {
		ManagerDolphin managerDolphin = new ManagerDolphin();
		Dolphin dolphinus = new Dolphin (1, "Mario", "Delphinus", 20, 20, null, true, null, null, 15);
		managerDolphin.insertDolphin(dolphinus);
        ArrayList<Dolphin> dolphin = managerDolphin.getDolphin();
			String animal = dolphin.get(0).getName();
			assertEquals(animal, "Mario");
		
		
	}
	
	@Test
	public void deleteDolphin() {
		ManagerDolphin managerDolphin = new ManagerDolphin();
		Dolphin dolphinus = new Dolphin (1, "Mario", "Delphinus", 20, 20, null, true, null, null, 15);
		managerDolphin.insertDolphin(dolphinus);
		managerDolphin.deleteDolphin(dolphinus);
        ArrayList<Dolphin> dolphin = managerDolphin.getDolphin();
		for (int i = 0; i < dolphin.size(); i++) {
			System.out.print(dolphin.get(i).getName());
		}
		assertNull(dolphin);
		
	}
	

}
