package Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utils.DBUtils;
import Pojos.Person.Feeder;
import Pojos.Person.Vet;

public class ManagerBoss {

	private void insertVet(Vet vet) {

		Connection connection = null;
		Statement statement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			statement = connection.createStatement();

			String sql = "insert into vetcomplete (name, surname, id, user, password, ssNumber, SpecializedAnimalType) VALUES ('"
					+ vet.getName() + "', '" + vet.getSurname() + "', '" + vet.getId() + vet.getUser() + "', '"
					+ vet.getPassword() + "', '" + vet.getSsNumber() + "', '" + vet.getSpecializedAnimalType() + "')";

			statement.executeUpdate(sql);

		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}
	}

	private void insertFeeder(Feeder feeder) {

		Connection connection = null;
		Statement statement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			statement = connection.createStatement();

			String sql = "insert into feedercomplete (name, surname, id, user, password, ssNumber, SpecializedDiet) VALUES ('"
					+ feeder.getName() + "', '" + feeder.getSurname() + "', '" + feeder.getId() + feeder.getUser()
					+ "', '" + feeder.getPassword() + "', '" + feeder.getSsNumber() + "', '"
					+ feeder.getSpecializedDiet() + "')";

			statement.executeUpdate(sql);

		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}
	}

	private void borrarVet(Vet vet) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "delete from vetcomplete where name= ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, vet.getName());

			preparedStatement.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}
	}

	private void borrarFeeder(Feeder feeder) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "delete from feedercomplete where name= ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, feeder.getName());

			preparedStatement.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}
	}

	private ArrayList<Vet> selectVet() {
		ArrayList<Vet> ret = null;

		String sql = "select * from vetcomplete";

		Connection connection = null;

		Statement statement = null;

		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				if (null == ret)
					ret = new <Vet>ArrayList();

				Vet vet = new Vet(sql, sql, sql, sql, sql, 0, sql);

				String name = resultSet.getString("name");
				String surName = resultSet.getString("surName");
				String id = resultSet.getString("id");
				String user = resultSet.getString("user");
				String password = resultSet.getString("password");
				int ssNumber = resultSet.getInt("ssNumber");
				String specializedAnimalType = resultSet.getString("specializedAnimalType");

				vet.setName(name);
				vet.setSurname(surName);
				vet.setId(id);
				vet.setUser(user);
				vet.setPassword(password);
				vet.setSsNumber(ssNumber);
				vet.setSpecializedAnimalType(specializedAnimalType);

				ret.add(vet);
			}
		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
			}
			;
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}
		return ret;
	}
	
	private ArrayList<Feeder> selectFeeder() {
		ArrayList<Feeder> ret = null;

		String sql = "select * from vetcomplete";

		Connection connection = null;

		Statement statement = null;

		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				if (null == ret)
					ret = new <Feeder>ArrayList();

				Feeder feeder = new Feeder(sql, sql, sql, sql, sql, 0, null);

				String name = resultSet.getString("name");
				String surName = resultSet.getString("surName");
				String id = resultSet.getString("id");
				String user = resultSet.getString("user");
				String password = resultSet.getString("password");
				int ssNumber = resultSet.getInt("ssNumber");
				String specializedAnimalType = resultSet.getString("specializedAnimalType");

				feeder.setName(name);
				feeder.setSurname(surName);
				feeder.setId(id);
				feeder.setUser(user);
				feeder.setPassword(password);
				feeder.setSsNumber(ssNumber);

				ret.add(feeder);
			}
		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
			}
			;
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
		}
		return ret;
	}

}
