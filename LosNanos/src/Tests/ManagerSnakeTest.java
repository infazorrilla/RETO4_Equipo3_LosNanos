package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public void inserSnake() throws ParseException {
		ManagerSnake managerSnake = new ManagerSnake();
		DateFormat format = new SimpleDateFormat("DD/MM/YYYY");
		Date dates = format.parse("12/03/2016");
		Snake snakeus = new Snake (1, "Carlos", "Indian Snake", 20, 20, dates, 1, "Carnivorous" , "2022-04-14", false, 1);
		System.out.print(snakeus.getName());
		System.out.print(snakeus);
		managerSnake.insertSnake(snakeus);
        ArrayList<Snake> snake = managerSnake.getSnake();
			String animal = snake.get(0).getName();
			assertEquals(animal, "Carlos");
		
		
	}
	
	@Test
	public void deleteSnake() throws ParseException {
		ManagerSnake managerSnake = new ManagerSnake();
		DateFormat format = new SimpleDateFormat("DD/MM/YYYY");
		Date dates = format.parse("12/03/2016");
		Snake snakeus = new Snake (1, "Carlos", "Indian Snake", 20, 20, dates, 1, "Carnivorous" , "2022-04-14", false, 1);
        managerSnake.deleteSnake(snakeus);
		ArrayList<Snake> snake = managerSnake.getSnake();
		assertNull(snake);
		
	}

}
	
	
