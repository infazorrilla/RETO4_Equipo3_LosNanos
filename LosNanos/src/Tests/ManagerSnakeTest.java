package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
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
	public void inserSnake() {
		ManagerSnake managerSnake = new ManagerSnake();
		Snake snakeus = new Snake (1, "Carlos", "Indian Snake", 20, 20, "2022-04-14", 1, "Carnivorous" , "2022-04-14", false, 1);
		managerSnake.insertSnake(snakeus);
        ArrayList<Snake> snake = managerSnake.getSnake();
			String animal = snake.get(0).getName();
			assertEquals(animal, "Carlos");
		
		
	}
	
	@Test
	public void deleteSnake() {
		ManagerSnake managerSnake = new ManagerSnake();
		ArrayList<Snake> snakes = managerSnake.getSnake();
		for (int i = 0; i < snakes.size(); i++) {
			Snake snake = snakes.get(i);
        managerSnake.deleteSnake(snake);
		assertNull(snake);
		
	}

}
	
}
