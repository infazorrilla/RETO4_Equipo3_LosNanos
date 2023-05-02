package Tests.Zoo;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Pojos.ZooTicket.Zoo;

class ManagerZooTest {
	
	private void insertZoo() {
		// TODO Auto-generated method stub
		
	}
	private ArrayList<Zoo> getZoo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Test
	public void getBoss() {
		ManagerZooTest managerZoo = new ManagerZooTest();
		ArrayList<Zoo> zoo = managerZoo.getZoo();
		for (int i = 0; i < zoo.size(); i++) {
			System.out.print(zoo.get(i).getName());
		}
		assertNotNull(zoo, "Los Nanos");
	}


	@Test
	public void inserBoss() throws ParseException {
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
		ArrayList<Zoo> boss = managerZoo.getZoo();
		assertNull(boss);
	}
}