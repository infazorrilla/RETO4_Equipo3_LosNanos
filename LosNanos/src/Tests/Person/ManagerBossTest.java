package Tests.Person;

import static org.junit.jupiter.api.Assertions.*;
import java.text.ParseException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import Manager.People.ManagerBoss;
import Pojos.Person.Boss;

class ManagerBossTest {

	@Test
	public void getBoss() {
		ManagerBoss managerBoss = new ManagerBoss();
		ArrayList<Boss> boss = managerBoss.getBoss();
		for (int i = 0; i < boss.size(); i++) {
			System.out.print(boss.get(i).getName());
		}
		assertNotNull(boss);
	}

	@Test
	public void inserBoss() throws ParseException {
		ManagerBoss managerBoss = new ManagerBoss();
		Boss bosses = new Boss("Ibai", "Torre", "12345678A", "ibai", "abcd1234", 1, 1);
		managerBoss.insertBoss(bosses);
		ArrayList<Boss> boss = managerBoss.getBoss();
		String empleado = boss.get(0).getName();
		assertEquals(empleado, "Ibai");

	}

	@Test
	public void deleteBoss() throws ParseException {
		ManagerBoss managerBoss = new ManagerBoss();
		Boss bosses = new Boss("Ibai", "Torre", "12345678A", "ibai", "abcd1234", 1, 1);
		managerBoss.deleteBoss(bosses);
		ArrayList<Boss> boss = managerBoss.getBoss();
		assertNull(boss);

	}
}
