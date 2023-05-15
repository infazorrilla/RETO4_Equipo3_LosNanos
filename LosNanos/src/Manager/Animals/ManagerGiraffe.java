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
import Pojos.Animal.Giraffe;
import utils.DBUtils;

public class ManagerGiraffe implements ManagerInterface<Giraffe> {
	@Override
	public ArrayList<Giraffe> selectAll() throws SQLException, ClassNotFoundException {
		ArrayList<Giraffe> ret = null;

		// SQL que queremos lanzar
		String sql = "select * from giraffeComplete";

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
					ret = new ArrayList<Giraffe>();

				Giraffe giraffe = new Giraffe();

				// Sacamos las columnas del RS
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String scientificName = resultSet.getString("scientificName");
				float height = resultSet.getFloat("height");
				float weight = resultSet.getFloat("weight");
				Date bornDate = resultSet.getDate("bornDate");
				int vaccinated = resultSet.getInt("vaccinated");
				String diet = resultSet.getString("diet");
				String hairColor = resultSet.getString("hairColor");
				int neckLength = resultSet.getInt("neckLength");

				// Metemos los datos a Ejemplo
				giraffe.setId(id);
				giraffe.setName(name);
				giraffe.setScientificName(scientificName);
				giraffe.setHeight(height);
				giraffe.setWeight(weight);
				giraffe.setBornDate(bornDate);
				giraffe.setVaccinated(vaccinated);
				giraffe.setDiet(diet);
				giraffe.setHairColor(hairColor);
				giraffe.setNeckLength(neckLength);

				// Lo guardamos en ret
				ret.add(giraffe);
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
	public void insert(Giraffe giraffe) throws SQLException, ClassNotFoundException {
		Connection connection = null;

		Statement statement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			statement = connection.createStatement();

			java.sql.Date sqlDate = new java.sql.Date(giraffe.getBornDate().getTime());

			String sql = "insert into terrestrialmammalian (id, name, scientificName, height, weight, bornDate, vaccinated, diet, hairColor) VALUES ('"
					+ giraffe.getId() + "', '" + giraffe.getName() + "', '" + giraffe.getScientificName() + "', '"
					+ giraffe.getHeight() + "', '" + giraffe.getWeight() + "', '" + sqlDate + "', '"
					+ giraffe.getVaccinated() + "', '" + giraffe.getDiet() + "', '" + giraffe.getHairColor() + "')";
			
			String sql2 = "insert into giraffe (id_giraffe, neckLength) SELECT MAX(id), " + 
					giraffe.getNeckLength() + " FROM terrestrialmammalian";

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
	public void update(Giraffe giraffe) throws SQLException, ClassNotFoundException {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = simpleDateFormat.format(giraffe.getBornDate());
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
			String sql = "update terrestrialmammalian, giraffe set name = ?, scientificName = ?, height = ?, weight = ?, bornDate = ?, vaccinated = ?, diet = ?, hairColor = ?, neckLength = ? where id = ?, id_giraffe = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, giraffe.getName());
			preparedStatement.setString(2, giraffe.getScientificName());
			preparedStatement.setFloat(3, giraffe.getHeight());
			preparedStatement.setFloat(4, giraffe.getWeight());
			preparedStatement.setDate(5, bornDate);
			preparedStatement.setInt(6, giraffe.getVaccinated());
			preparedStatement.setString(7, giraffe.getDiet());
			preparedStatement.setString(8, giraffe.getHairColor());
			preparedStatement.setInt(9, giraffe.getNeckLength());
			preparedStatement.setInt(10, giraffe.getId());
			preparedStatement.setInt(11, giraffe.getId());


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
	public void delete(Giraffe giraffe) throws SQLException, ClassNotFoundException {
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
			preparedStatement.setInt(1, giraffe.getId());

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
