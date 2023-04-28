package Tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Manager.ManagerDolphin;
import Pojos.Animal.Dolphin;

class ManagerDolphinTest {

	@Test
	public void getDolphin() throws ParseException {
		ManagerDolphin managerDolphin = new ManagerDolphin();
		DateFormat format = new SimpleDateFormat("DD/MM/YYYY");
		Date date = format.parse("12/03/2016");
		Dolphin dolphinus = new Dolphin (4, "Carlos", "Dolphinus", 10, 10, date, 0, "Carnivorous", "mammals", 1, 100, 2);
		managerDolphin.insertDolphin(dolphinus);
		ArrayList<Dolphin> dolphin = managerDolphin.getDolphin();
        managerDolphin.deleteDolphin(dolphinus);
		assertNotNull(dolphin);
	}
	
	@Test
	public void inserDolphin() throws ParseException {
		ManagerDolphin managerDolphin = new ManagerDolphin();
		DateFormat format = new SimpleDateFormat("DD/MM/YYYY");
		Date date = format.parse("12/03/2016");
		Dolphin dolphinus = new Dolphin (4, "Carlos", "Dolphinus", 10, 10, date, 0, "Carnivorous", "mammals", 1, 100, 2);
		managerDolphin.insertDolphin(dolphinus);
        ArrayList<Dolphin> dolphin = managerDolphin.getDolphin();
			String animal = dolphin.get(0).getName();
			assertEquals(animal, "Carlos");
		
		
	}
	

	
	@Test
	public void deleteDolphin() throws ParseException {
		ManagerDolphin managerDolphin = new ManagerDolphin();
		DateFormat format = new SimpleDateFormat("DD/MM/YYYY");
		Date date = format.parse("12/03/2016");
		Dolphin dolphinus = new Dolphin (4, "Carlos", "Dolphinus", 10, 10, date, 0, "Carnivorous", "mammals", 2, 100, 2);
        managerDolphin.deleteDolphin(dolphinus);
        ArrayList<Dolphin> dolphin = managerDolphin.getDolphin();
		assertNull(dolphin);
		
	}

}
	

