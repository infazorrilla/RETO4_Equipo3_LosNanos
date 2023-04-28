package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Manager.ManagerZoo;
import Pojos.ZooTicket.Zoo;
import utils.DBUtils;

class ManagerZooTest {
	
	@Test
	public void getId() {
		ManagerZoo managerZoo = new ManagerZoo();
		ArrayList<id> Id = Zoo.getId();
		for (int id) {
			System.out.print(id.get(i).getId());
		}
		assertNotNull(id);
	}
}
