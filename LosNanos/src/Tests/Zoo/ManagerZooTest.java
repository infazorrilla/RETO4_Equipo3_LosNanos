package Tests.Zoo;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Pojos.ZooTicket.Zoo;
import Manager.Zoo.ManagerZoo;

class ManagerZooTest {

	@Test
	public ArrayList<Zoo> testGetZoo() {
		ManagerZooTest managerZoo = new ManagerZooTest();
		ArrayList<Zoo> zoo = ManagerZoo.selectZoo();
		for (int i = 0; i < zoo.size(); i++) {
			zoo.get(i).getName();
		}
		assertNotNull(zoo, "Los Nanos");
		return zoo;
	}


	@Test
	public void inserZoo() throws ParseException {
		ManagerZooTest managerZoo = new ManagerZooTest();
		Zoo zoo= new Zoo();
		managerZoo.insertZoo();
		ArrayList<Zoo> Zoo = managerZoo.getZoo();
		Zoo.get(0).getName();
		assertEquals(zoo, "Los Nanos");

	}


	@Test
	public void deleteZoo() throws ParseException {
		ManagerZooTest managerZoo = new ManagerZooTest();
		new Zoo();
		managerZoo.deleteZoo();
		ArrayList<Zoo> zoo = managerZoo.getZoo();
		assertNull(zoo);
	}
}