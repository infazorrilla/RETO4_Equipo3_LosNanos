package Tests.Person;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Manager.People.ManagerBoss;
import Pojos.Person.Boss;

class ManagerBossTest {

	private ManagerBoss managerBoss = new ManagerBoss();
	private Boss boss = new Boss();
	private ArrayList<Boss> bosses = null;

	@Test
	void testSelectAll() {
		try {
			bosses = managerBoss.selectAll();
			boss = bosses.get(0);
			String expected = boss.toString();
			assertEquals(
					"",
					expected);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testInsert() {
		try {
			boss.setName("Ibai");
			boss.setSurname("Torre");
			boss.setId("12345678A");
			boss.setUser("Ibai");
			boss.setPassword("1234");
			boss.setEmployeeNumCharge(1);
			boss.setSsNumber(1);

			managerBoss.insert(boss);

			bosses = managerBoss.selectAll();

			Boss insertedBoss = bosses.get(bosses.size() - 1);

			for (int i = 0; i < bosses.size(); i++) {
				if (bosses.get(i).getId() == insertedBoss.getId()) {
					assertEquals(bosses.get(i), insertedBoss);
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

	@Test
	void testUpdate() {
		try {
			boss.setName("Ibai");
			boss.setSurname("Torre");
			boss.setId("12345678A");
			boss.setUser("Ibai");
			boss.setPassword("1234");
			boss.setEmployeeNumCharge(1);
			boss.setSsNumber(1);
			
			managerBoss.update(boss);

			bosses = managerBoss.selectAll();

			for (int i = 0; i < bosses.size(); i++) {
				if (bosses.get(i).getId() == boss.getId()) {
					assertEquals(boss, bosses.get(i));
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

	@Test
	void testDelete() {
		try {
			boss.setName("Ibai");
			boss.setSurname("Torre");
			boss.setId("12345678A");
			boss.setUser("Ibai");
			boss.setPassword("1234");
			boss.setEmployeeNumCharge(1);
			boss.setSsNumber(1);

			managerBoss.delete(boss);

			bosses = managerBoss.selectAll();

			for (int i = 0; i < bosses.size(); i++) {
				if (bosses.get(i).getId() == boss.getId()) {
					assertEquals(boss, null);
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
