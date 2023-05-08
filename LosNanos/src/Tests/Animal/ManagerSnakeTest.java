package Tests.Animal;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.Test;

import Manager.Animals.ManagerSnake;
import Pojos.Animal.Snake;


class ManagerSnakeTest {

	private ManagerSnake managerSnake = new ManagerSnake();
	private Snake snake = new Snake ();
	private ArrayList<Snake> snakes = null;

	@Test
	void testSelectAll() {
		try {
			snakes = managerSnake.selectAll();
			snake = snakes.get(0);
			String expected = snake.toString();
			assertEquals("Snake [poisonus=true, shedSkin=2022-04-14, swamp=null, id=4, name=Carlos, scientificName=Indian Snake, height=20.0, weight=20.0, bornDate=2016-01-04, vaccinated=1, diet=Carnivorous]", expected);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testInsert() {
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
			Date date = formato.parse("23/11/2015");
			
			snake.setPoisonus(true);
			snake.setShedSkin(date);
			snake.setName("Paulo");
			snake.setScientificName("Snakeus");
			snake.setHeight(25);
			snake.setWeight(25);
			snake.setBornDate(date);
			snake.setVaccinated(1);
			snake.setDiet("Carnivorous");
			
			managerSnake.insert(snake);
			
			snakes = managerSnake.selectAll();
			Snake insertedSnake = snakes.get(0);

			
			for(int i = 0 ; i < snakes.size() ; i++ ) {
				if (snakes.get(i).getId() == insertedSnake.getId()) {
					assertEquals(snakes.get(i), insertedSnake);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testUpdate() {
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
			Date date = formato.parse("23/11/2015");
			snake.setId(19);
			snake.setPoisonus(true);
			snake.setShedSkin(date);
			snake.setName("Paulo");
			snake.setScientificName("Snakeus");
			snake.setHeight(25);
			snake.setWeight(25);
			snake.setBornDate(date);
			snake.setVaccinated(1);
			snake.setDiet("Carnivorous");
			
			managerSnake.update(snake);
			
			snakes = managerSnake.selectAll();
			
			for(int i = 0 ; i < snakes.size() ; i++ ) {
				if (snakes.get(i).getId() == snake.getId()) {
					assertEquals(snake, snakes.get(i));
				}
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testDelete() {
		try {
			snake.setId(2);
			
			managerSnake.delete(snake);
			
			snakes = managerSnake.selectAll();
			
			for(int i = 0 ; i < snakes.size() ; i++){
				if(snakes.get(snake.getId()) == null  ) {
					assertEquals(snake, null);
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
	
	
