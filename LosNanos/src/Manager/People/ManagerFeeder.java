package Manager.People;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Manager.ManagerInterface;
import Pojos.Person.Feeder;
import utils.DBUtils;

public class ManagerFeeder implements ManagerInterface<Feeder> {

	@Override
	public ArrayList<Feeder> selectAll() {
		ArrayList<Feeder> ret = null;

		// SQL que queremos lanzar
		String sql = "select * from feederComplete";

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
					ret = new ArrayList<Feeder>();

				Feeder feeder = new Feeder();

				// Sacamos las columnas del RS
				String id = resultSet.getString("id");
				String name = resultSet.getString("name");
				String surname = resultSet.getString("surname");
				String user = resultSet.getString("user");
				String password = resultSet.getString("password");
				int ssNumber = resultSet.getInt("ssNumber");
				String specializedDiet = resultSet.getString("specializedDiet");

				// Metemos los datos a Ejemplo
				feeder.setId(id);
				feeder.setName(name);
				feeder.setSurname(surname);
				feeder.setUser(user);
				feeder.setPassword(password);
				feeder.setSsNumber(ssNumber);
				feeder.setSpecializedDiet(specializedDiet);

				// Lo guardamos en ret
				ret.add(feeder);
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
	public void insert(Feeder feeder) throws SQLException, Exception {

		Connection connection = null;

		Statement statement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			statement = connection.createStatement();

			String sql = "insert into employee (name, surname, id, user, password, ssNumber, idZoo) VALUES ('"
					+ feeder.getName() + "', '" + feeder.getSurname() + "', '" + feeder.getId() + "', '"
					+ feeder.getUser() + "', '" + feeder.getPassword() + "', '" + feeder.getSsNumber() + "')";

			String sql2 = "insert into feeder (ssNumber, specialzedDiet) VALUES ('" + feeder.getSsNumber() + "', '"
					+ feeder.getSpecializedDiet() + "')";

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
	public void update(Feeder feeder) throws SQLException, Exception {

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
			String sql = "update employee, feeder set name = ?, surname = ?, user = ?, password = ?, specializedDiet = ? where ssNumber = ?, id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, feeder.getName());
			preparedStatement.setString(2, feeder.getSurname());
			preparedStatement.setString(3, feeder.getUser());
			preparedStatement.setString(4, feeder.getPassword());
			preparedStatement.setString(5, feeder.getSpecializedDiet());
			preparedStatement.setInt(6, feeder.getSsNumber());
			preparedStatement.setString(7, feeder.getId());

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
	public void delete(Feeder feeder) throws SQLException, Exception {
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
			String id = feeder.getId();
			String sql = "delete from employee where id =" + id;
			preparedStatement = connection.prepareStatement(sql);

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
