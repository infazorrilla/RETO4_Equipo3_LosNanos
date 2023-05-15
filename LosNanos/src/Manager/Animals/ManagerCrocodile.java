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
import Pojos.Animal.Crocodile;
import utils.DBUtils;

public class ManagerCrocodile implements ManagerInterface<Crocodile> {
	
	@Override
	public ArrayList<Crocodile> selectAll() throws SQLException, ClassNotFoundException {
		ArrayList<Crocodile> ret = null;

		// SQL que queremos lanzar
		String sql = "select * from crocodileComplete";

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
					ret = new ArrayList<Crocodile>();

				Crocodile crocodile = new Crocodile();

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
				int teethNumber = resultSet.getInt("teethNumber");

				// Metemos los datos a Ejemplo
				crocodile.setId(id);
				crocodile.setName(name);
				crocodile.setScientificName(scientificName);
				crocodile.setHeight(height);
				crocodile.setWeight(weight);
				crocodile.setBornDate(bornDate);
				crocodile.setVaccinated(vaccinated);
				crocodile.setDiet(diet);
				crocodile.setShedSkin(shedSkin);
				crocodile.setTeethNumber(teethNumber);

				// Lo guardamos en ret
				ret.add(crocodile);
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
	public void insert(Crocodile crocodile) throws SQLException, ClassNotFoundException {
		Connection connection = null;

		Statement statement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			statement = connection.createStatement();

			java.sql.Date sqlDateBorn = new java.sql.Date(crocodile.getBornDate().getTime());
			java.sql.Date sqlDateSkin = new java.sql.Date(crocodile.getShedSkin().getTime());

			String sql = "insert into reptile (id, name, scientificName, height, weight, bornDate, vaccinated, diet, shedSkin) VALUES ('"
					+ crocodile.getId() + "', '" + crocodile.getName() + "', '" + crocodile.getScientificName() + "', '"
					+ crocodile.getHeight() + "', '" + crocodile.getWeight() + "', '" + sqlDateBorn + "', '"
					+ crocodile.getVaccinated() + "', '" + crocodile.getDiet() + "', '" + sqlDateSkin + "')";

			String sql2 = "insert into crocodile (id_crocodile, teethNumber) SELECT MAX(id), " + 
					crocodile.getTeethNumber() + " FROM reptile";

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
	public void update(Crocodile crocodile) throws SQLException, ClassNotFoundException {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDateBorn = simpleDateFormat.format(crocodile.getBornDate());
		java.sql.Date bornDate = java.sql.Date.valueOf(formattedDateBorn);

		String formattedDateSkin = simpleDateFormat.format(crocodile.getShedSkin());
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
			String sql = "update reptile, crocodile set name = ?, scientificName = ?, height = ?, weight = ?, bornDate = ?, vaccinated = ?, diet = ?, shedSkin = ?,  teethNumber = ? where id = ? and id_crocodile = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, crocodile.getName());
			preparedStatement.setString(2, crocodile.getScientificName());
			preparedStatement.setFloat(3, crocodile.getHeight());
			preparedStatement.setFloat(4, crocodile.getWeight());
			preparedStatement.setDate(5, bornDate);
			preparedStatement.setInt(6, crocodile.getVaccinated());
			preparedStatement.setString(7, crocodile.getDiet());
			preparedStatement.setDate(8, shedSkin);
			preparedStatement.setInt(9, crocodile.getTeethNumber());
			preparedStatement.setInt(10, crocodile.getId());

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
	public void delete(Crocodile crocodile) throws SQLException, ClassNotFoundException {
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
			preparedStatement.setInt(1, crocodile.getId());

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
