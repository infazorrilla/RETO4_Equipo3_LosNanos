package Manager.Animals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Manager.ManagerInterface;
import Pojos.Animal.Cheetah;
import utils.DBUtils;

public class ManagerCheetah implements ManagerInterface<Cheetah> {
	
	@Override
	public ArrayList<Cheetah> selectAll() {
		ArrayList<Cheetah> ret = null;

		// SQL que queremos lanzar
		String sql = "select * from cheetahComplete";

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
					ret = new ArrayList<Cheetah>();

				Cheetah cheetah = new Cheetah();

				// Sacamos las columnas del RS
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String scientificName = resultSet.getString("scientificName");
				float height = resultSet.getFloat("height");
				float weight = resultSet.getFloat("weight");
				Date bornDate = resultSet.getDate("bornDate");
				Boolean vaccinated = resultSet.getBoolean("vaccinated");
				String diet = resultSet.getString("diet");
				String hairColor = resultSet.getString("hairColor");
				int maxSpeed = resultSet.getInt("maxSpeed");

				// Metemos los datos a Ejemplo
				cheetah.setId(id);
				cheetah.setName(name);
				cheetah.setScientificName(scientificName);
				cheetah.setHeight(height);
				cheetah.setWeight(weight);
				cheetah.setBornDate(bornDate);
				cheetah.setVaccinated(vaccinated);
				cheetah.setDiet(diet);
				cheetah.setHairColor(hairColor);
				cheetah.setMaxSpeed(maxSpeed);

				// Lo guardamos en ret
				ret.add(cheetah);
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
	public void insert(Cheetah cheetah) {
		Connection connection = null;

		Statement statement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			statement = connection.createStatement();

			java.sql.Date sqlDate = new java.sql.Date(cheetah.getBornDate().getTime());

			String sql = "insert into terrestrialmammalian (id, name, scientificName, height, weight, bornDate, vaccinated, diet, animalTipe) VALUES ('"
					+ cheetah.getId() + "', '" + cheetah.getName() + "', '" + cheetah.getScientificName() + "', '"
					+ cheetah.getHeight() + "', '" + cheetah.getWeight() + "', '" + sqlDate + "', '"
					+ cheetah.isVaccinated() + "', '" + cheetah.getDiet() + "', '" + cheetah.getHairColor() + "')";

			String sql2 = "insert into cheetah (id_cheetah, maxSpeed) SELECT MAX(id), " + 
					cheetah.getMaxSpeed() + "FROM terrestrialmammalian";
			
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
	public void update(Cheetah cheetah) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = simpleDateFormat.format(cheetah.getBornDate());
		java.sql.Date bornDate = java.sql.Date.valueOf(formattedDate);

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
			String sql = "update dophinComplete set name = ?, scientificName = ?, height = ?, weight = ?, height = ?, bornDate = ?, vaccianted = ?, diet = ?, animalTipe = ?, maxSpeed = ? where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, cheetah.getName());
			preparedStatement.setString(1, cheetah.getScientificName());
			preparedStatement.setFloat(2, cheetah.getHeight());
			preparedStatement.setFloat(3, cheetah.getWeight());
			preparedStatement.setDate(4, bornDate);
			preparedStatement.setBoolean(5, cheetah.isVaccinated());
			preparedStatement.setString(6, cheetah.getDiet());
			preparedStatement.setString(7, cheetah.getHairColor());
			preparedStatement.setInt(8, cheetah.getMaxSpeed());
			preparedStatement.setInt(9, cheetah.getId());

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
	public void delete(Cheetah cheetah) throws SQLException, Exception {
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
			String sql = "delete from terrestrialmammalian where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cheetah.getId());

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
