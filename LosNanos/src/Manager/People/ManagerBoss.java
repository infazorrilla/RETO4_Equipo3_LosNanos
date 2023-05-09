package Manager.People;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Manager.ManagerInterface;
import Pojos.Person.Boss;
import utils.DBUtils;

public class ManagerBoss implements ManagerInterface<Boss>{

	@Override
	public ArrayList<Boss> selectAll() {
		ArrayList<Boss> ret = null;

		// SQL que queremos lanzar
		String sql = "select * from bossComplete";

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
					ret = new ArrayList<Boss>();

				Boss boss = new Boss();

				// Sacamos las columnas del RS
				String id = resultSet.getString("id");
				String name = resultSet.getString("name");
				String surname = resultSet.getString("surname");
				String user = resultSet.getString("user");
				String password = resultSet.getString("password");
				int ssNumber = resultSet.getInt("ssNumber");
				int employeeNumCharge = resultSet.getInt("employeeNumCharge");

				// Metemos los datos a Ejemplo
				boss.setId(id);
				boss.setName(name);
				boss.setSurname(surname);
				boss.setUser(user);
				boss.setPassword(password);
				boss.setSsNumber(ssNumber);
				boss.setEmployeeNumCharge(employeeNumCharge);

				// Lo guardamos en ret
				ret.add(boss);
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
	public void insert(Boss boss) throws SQLException, Exception {
		Connection connection = null;

		Statement statement = null;

		try {
			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			statement = connection.createStatement();

			String sql = "insert into employee (name, surname, id, user, password, ssNumber) VALUES ('"
					+ boss.getName() + "', '" + boss.getSurname() + "', '" + boss.getId() + "', '" + boss.getUser()
					+ "', '" + boss.getPassword() + "', '" + boss.getSsNumber() +"')";

			String sql2 = "insert into boss (ssNumber, employeeNumCharge) VALUES ('" + boss.getSsNumber() + "', '"
					+ boss.getEmployeeNumCharge() + "')";
			

			// La ejecutamos...
			statement.executeUpdate(sql);
			statement.executeUpdate(sql2);
	
			
		} catch (SQLException sqle) {  

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
	public void update(Boss boss) throws SQLException, Exception {


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
			String sql = "update employee, boss set name = ?, surname = ?, user = ?, password = ?, employeeNumCharge = ? where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, boss.getName());
			preparedStatement.setString(2, boss.getSurname());
			preparedStatement.setString(3, boss.getUser());
			preparedStatement.setString(4, boss.getPassword());
			preparedStatement.setInt(5, boss.getEmployeeNumCharge());
			preparedStatement.setString(6, boss.getId());

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
	public void delete(Boss boss) throws SQLException, Exception {
		// La conexion con BBDD
		Connection connection = null;
		
		// Vamos a lanzar una sentencia SQL contra la BBDD
		PreparedStatement  preparedStatement  = null;
		
		try {
			// El Driver que vamos a usar
			Class.forName(DBUtils.DRIVER);
			
			// Abrimos la conexion con BBDD
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			// Montamos la SQL. Las ? se rellenan a continuacion
			String id = boss.getId();
			String sql = "delete from employee where id =" + id;
			preparedStatement = connection.prepareStatement(sql);
			
			// La ejecutamos...
			preparedStatement.executeUpdate();
			
		} catch (SQLException sqle) {  
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch(Exception e){ 
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (preparedStatement != null) 
					preparedStatement.close(); 
			} catch(Exception e){ 
				// No hace falta				
			};
			try {
				if (connection != null) 
					connection.close(); 
			} catch(Exception e){ 
				// No hace falta
			};					
		}
	}

}
