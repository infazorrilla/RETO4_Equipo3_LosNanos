package Manager.Animals;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Manager.ManagerInterface;
import Pojos.Animal.Snake;
import utils.DBUtils;

public class ManagerSnake implements ManagerInterface<Snake> {

	@Override
	public ArrayList<Snake> selectAll() {
		ArrayList<Snake> ret = null;

		// SQL que queremos lanzar
		String sql = "select * from snakeComplete";

		// La conexion con BBDD
		Connection connection = null;

		// Vamos a lanzar una sentencia SQL contra la BBDD
		// Result set va a contener todo lo que devuelve la BBDD
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// El Driver que vamos a usar
			Class.forName(DBUtils.DRIVER);

			// Abrimos la conexion con BBDD
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			// Vamos a lanzar la sentencia...
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			// No es posible saber cuantas cosas nos ha devuelto el resultSet.
			// Hay que ir 1 por 1 y guardandolo todo en su objeto Ejemplo correspondiente
			while (resultSet.next()) {

				// Si es necesario, inicializamos la lista
				if (null == ret)
					ret = new ArrayList<Snake>();

				Snake snake = new Snake();

				// Sacamos las columnas del RS
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String scientificName = resultSet.getString("scientificName");
				float height = resultSet.getFloat("height");
				float weight = resultSet.getFloat("weight");
				Date bornDate = resultSet.getDate("bornDate");
				int vaccinated = resultSet.getInt("vaccinated");
				String diet = resultSet.getString("diet");
				Date shedSkin = resultSet.getDate("shedSkin");
				Boolean poisonus = resultSet.getBoolean("poisonus");

				// Metemos los datos a Ejemplo
				snake.setId(id);
				snake.setName(name);
				snake.setScientificName(scientificName);
				snake.setHeight(height);
				snake.setWeight(weight);
				snake.setBornDate(bornDate);
				snake.setVaccinated(vaccinated);
				snake.setDiet(diet);
				snake.setShedSkin(shedSkin);
				snake.setPoisonus(poisonus);

				// Lo guardamos en ret
				ret.add(snake);
			}
		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				// No hace falta
			}
			;
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
				// No hace falta
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// No hace falta
			}
			;
		}
		return ret;
	}

	@Override
	public void insert(Snake snake) {
		Connection connection = null;

		Statement statement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			statement = connection.createStatement();

			java.sql.Date sqlDateBorn = new java.sql.Date(snake.getBornDate().getTime());
			java.sql.Date sqlDateSkin = new java.sql.Date(snake.getShedSkin().getTime());

			String sql = "insert into reptile (id, name, scientificName, height, weight, bornDate, vaccinated, diet, shedSkin) VALUES ('"
					+ snake.getId() + "', '" + snake.getName() + "', '" + snake.getScientificName() + "', '"
					+ snake.getHeight() + "', '" + snake.getWeight() + "', '" + sqlDateBorn + "', '"
					+ snake.getVaccinated() + "', '" + snake.getDiet() + "', '" + sqlDateSkin + "')";
			
			String sql2 = "insert into snake (id_snake, poisonus) SELECT MAX(id), " + 
					snake.isPoisonus() + " FROM reptile";

			// La ejecutamos...
			statement.executeUpdate(sql);
			statement.executeUpdate(sql2);

		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
				// No hace falta
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// No hace falta
			}
			;
		}
	}

	@Override
	public void update(Snake snake) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDateBorn = simpleDateFormat.format(snake.getBornDate());
		java.sql.Date bornDate = java.sql.Date.valueOf(formattedDateBorn);

		String formattedDateSkin = simpleDateFormat.format(snake.getShedSkin());
		java.sql.Date shedSkin = java.sql.Date.valueOf(formattedDateSkin);

		// La conexion con BBDD
		Connection connection = null;

		// Vamos a lanzar una sentencia SQL contra la BBDD
		PreparedStatement preparedStatement = null;

		try {
			// El Driver que vamos a usar
			Class.forName(DBUtils.DRIVER);

			// Abrimos la conexion con BBDD
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			// Montamos la SQL. Las ? se rellenan a continuacion
			String sql = "update reptile, snake set name = ?, scientificName = ?, height = ?, weight = ?, bornDate = ?, vaccinated = ?, diet = ?, shedSkin = ?,  poisonus = ? where id = ? and id_snake = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, snake.getName());
			preparedStatement.setString(2, snake.getScientificName());
			preparedStatement.setFloat(3, snake.getHeight());
			preparedStatement.setFloat(4, snake.getWeight());
			preparedStatement.setDate(5, bornDate);
			preparedStatement.setInt(6, snake.getVaccinated());
			preparedStatement.setString(7, snake.getDiet());
			preparedStatement.setDate(8, shedSkin);
			preparedStatement.setBoolean(9, snake.isPoisonus());
			preparedStatement.setInt(10, snake.getId());
			preparedStatement.setInt(11, snake.getId());


			// La ejecutamos...
			preparedStatement.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
				// No hace falta
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// No hace falta
			}
			;
		}
	}

	@Override
	public void delete(Snake snake) throws SQLException, Exception {
		// La conexion con BBDD
		Connection connection = null;

		// Vamos a lanzar una sentencia SQL contra la BBDD
		PreparedStatement preparedStatement = null;

		try {
			// El Driver que vamos a usar
			Class.forName(DBUtils.DRIVER);

			// Abrimos la conexion con BBDD
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			// Montamos la SQL. Las ? se rellenan a continuacion
			String sql = "delete from reptile where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, snake.getId());

			// La ejecutamos...
			preparedStatement.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
				// No hace falta
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// No hace falta
			}
			;
		}
	}
}
