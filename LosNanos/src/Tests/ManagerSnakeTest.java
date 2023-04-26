package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Gestor.ManagerSnake;
import Pojos.Animal.Snake;

class ManagerSnakeTest {

	@Test
	public void getSnake() {
		ManagerSnake managerSnake = new ManagerSnake();
		ArrayList<Snake> snake = managerSnake.getSnake();
		for (int i = 0; i < snake.size(); i++) {
			System.out.print(snake.get(i).getName());
		}
		assertNotNull(snake);
	}
	
	@Test
	public void inserDolphin() {
		ManagerSnake managerSnake = new ManagerSnake();
		Snake snakeus = new Snake (1, "Carlos", "Indian Snake", 20, 20, null, true, null, null, false, 15);
		managerSnake.insertSnake(snakeus);
        ArrayList<Snake> snake = managerSnake.getSnake();
			String animal = snake.get(0).getName();
			assertEquals(animal, "Carlos");
		
		
	}
	
	@Test
	public void deleteDolphin() {
		ManagerSnake managerSnake = new ManagerSnake();
		Snake snakeus = new Snake (1, "Carlos", "Indian Snake", 20, 20, null, true, null, null, false, 15);
		managerSnake.insertSnake(snakeus);
		managerSnake.deleteSnake(snakeus);
        ArrayList<Snake> snake = managerSnake.getSnake();
		for (int i = 0; i < snake.size(); i++) {
			System.out.print(snake.get(i).getName());
		}
		assertNull(snake);
		
	}

}
