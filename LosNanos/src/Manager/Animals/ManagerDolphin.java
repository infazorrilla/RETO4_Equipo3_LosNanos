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
import Pojos.Animal.Dolphin;
import utils.DBUtils;

public class ManagerDolphin implements ManagerInterface<Dolphin> {

	@Override
	public ArrayList<Dolphin> selectAll() throws SQLException, ClassNotFoundException {
		ArrayList<Dolphin> ret = null;

		// SQL que queremos lanzar
		String sql = "select * from dolphinComplete";

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
					ret = new ArrayList<Dolphin>();

				Dolphin dolphin = new Dolphin();

				// Sacamos las columnas del RS
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String scientificName = resultSet.getString("scientificName");
				float height = resultSet.getFloat("height");
				float weight = resultSet.getFloat("weight");
				Date bornDate = resultSet.getDate("bornDate");
				int vaccinated = resultSet.getInt("vaccinated");
				String diet = resultSet.getString("diet");
				String animalTipe = resultSet.getString("animalTipe");
				int durationUnderWater = resultSet.getInt("durationUnderWater");

				// Metemos los datos a Ejemplo
				dolphin.setId(id);
				dolphin.setName(name);
				dolphin.setScientificName(scientificName);
				dolphin.setHeight(height);
				dolphin.setWeight(weight);
				dolphin.setBornDate(bornDate);
				dolphin.setVaccinated(vaccinated);
				dolphin.setDiet(diet);
				dolphin.setAnimalType(animalTipe);
				dolphin.setDurationUnderWater(durationUnderWater);

				// Lo guardamos en ret
				ret.add(dolphin);
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
	public void insert(Dolphin dolphin) throws SQLException, ClassNotFoundException {
		Connection connection = null;

		Statement statement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			statement = connection.createStatement();

			java.sql.Date sqlDate = new java.sql.Date(dolphin.getBornDate().getTime());

			String sql = "insert into aquatic (name, scientificName, height, weight, bornDate, vaccinated, diet, animalTipe) VALUES ('"
					+ dolphin.getName() + "', '" + dolphin.getScientificName() + "', '"
					+ dolphin.getHeight() + "', '" + dolphin.getWeight() + "', '" + sqlDate + "', '"
					+ dolphin.getVaccinated() + "', '" + dolphin.getDiet() + "', '" + dolphin.getAnimalType() + "')";
			
			String sql2 = "insert into dolphin (id_dolphin, DurationUnderWater) SELECT MAX(id), " + 
					dolphin.getDurationUnderWater() + " FROM aquatic";

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
	public void update(Dolphin dolphin) throws SQLException, ClassNotFoundException {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = simpleDateFormat.format(dolphin.getBornDate());
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
			String sql = "update aquatic, dolphin set name = ?, scientificName = ?, height = ?, weight = ?, bornDate = ?, vaccinated = ?, diet = ?, animalTipe = ?, durationUnderWater = ? where id = ? and id_dolphin = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, dolphin.getName());
			preparedStatement.setString(2, dolphin.getScientificName());
			preparedStatement.setFloat(3, dolphin.getHeight());
			preparedStatement.setFloat(4, dolphin.getWeight());
			preparedStatement.setDate(5, bornDate);
			preparedStatement.setInt(6, dolphin.getVaccinated());
			preparedStatement.setString(7, dolphin.getDiet());
			preparedStatement.setString(8, dolphin.getAnimalType());
			preparedStatement.setInt(9, dolphin.getDurationUnderWater());
			preparedStatement.setInt(10, dolphin.getId());
			preparedStatement.setInt(11, dolphin.getId());


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
	public void delete(Dolphin dolphin) throws SQLException, ClassNotFoundException {
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
			String sql = "delete from aquatic where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, dolphin.getId());

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
