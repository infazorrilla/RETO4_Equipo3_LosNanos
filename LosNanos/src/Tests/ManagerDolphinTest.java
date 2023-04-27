package Tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Gestor.ManagerDolphin;
import Gestor.ManagerSnake;
import Pojos.Animal.Dolphin;
import Pojos.Animal.Snake;


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
	public void inserDolphin() throws ParseException {
		ManagerDolphin managerDolphin = new ManagerDolphin();
		DateFormat format = new SimpleDateFormat("DD/MM/YYYY");
		Date date = format.parse("12/03/2016");
		Dolphin dolphinus = new Dolphin (1, "Carlos", "Dolphinus", 10, 10, date, 1, "Carniborous", "mammals", 1);
		managerDolphin.insertDolphin(dolphinus);
        ArrayList<Dolphin> dolphin = managerDolphin.getDolphin();
			String animal = dolphin.get(0).getName();
			assertEquals(animal, "Carlos");
		
		
	}
	
	@Test
	public void deleteDolphin() {
		ManagerDolphin managerDolphin = new ManagerDolphin();
		ArrayList<Dolphin> dolphines = managerDolphin.getDolphin();
		for (int i = 0; i < dolphines.size(); i++) {
			Dolphin dolphin = dolphines.get(i);
        managerDolphin.deleteDolphin(dolphin);
		assertNull(dolphin);
		
	}

}
	

}
