package Gestor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Pojos.Animal.Snake;
import utils.DBUtils;

public class ManagerSnake {
	// Inserta un alumno
	public void insertSnake(Snake snake) {

		// La conexion con BBDD
		Connection connection = null;

		// Vamos a lanzar una sentencia SQL contra la BBDD
		Statement statement = null;

		try {
			// El Driver que vamos a usar
			Class.forName(DBUtils.DRIVER);

			// Abrimos la conexion con BBDD
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			// Vamos a lanzar la sentencia...
			statement = connection.createStatement();

			// Montamos la SQL
			String sql = "insert into reptile (name, scientificName, height, weight, bornDate, vaccinated, diet, shedSkin) VALUES ('"
					+ snake.getName() + "', '" + snake.getScientificName() + "', '" + snake.getHeight() + "', '"
					+ snake.getWeight() + "', '" + snake.getBornDate() + "', '" + snake.getVaccinated() + "', '"
					+ snake.getDiet() + "', '" + snake.getShedSkin() + "')";

			// La ejecutamos...
			statement.executeUpdate(sql);

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

	public void deleteSnake(Snake snake) {

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
			String sql = "delete from reptile where name = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, snake.getName());

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

	public ArrayList<Snake> getSnake() {
		ArrayList<Snake> ret = null;

		// SQL que queremos lanzar
		String sql = "select * from reptile";

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
					ret = new <Snake>ArrayList();

				Snake snake = new Snake(0, sql, sql, 0, 0, sql, 0, sql, null, false, 0);

				// Sacamos las columnas del RS
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String scientificName = resultSet.getString("scientificName");
				float height = resultSet.getFloat("height");
				float weight = resultSet.getFloat("weight");
				String bornDate = resultSet.getString("bornDate");
				int vaccinated = resultSet.getInt("vaccinated");
				String diet = resultSet.getString("diet");
				String shedSkin = resultSet.getString("shedSkin");
				int zoneId = resultSet.getInt("ZoneId");

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
				snake.setZoneId(zoneId);

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
}
