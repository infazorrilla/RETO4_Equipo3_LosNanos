package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Manager.ManagerSnake;
import Manager.ManagerZoo;
import Pojos.Animal.Snake;
import Pojos.ZooTicket.Zoo;

class ManagerZooTest {
	
	@Test
	public void getName() {
		ManagerZoo managerzoo = new ManagerZoo();
		ArrayList<Zoo> Name = ManagerZoo.getName();
				
	@Test
	public void getSnake() {
		ManagerSnake managerSnake = new ManagerSnake();
		ArrayList<Snake> snake = managerSnake.getSnake();
		for (int i = 0; i < snake.size(); i++) {
			System.out.print(snake.get(i).getName());
		}
		assertNotNull(snake);
	}
	
}
