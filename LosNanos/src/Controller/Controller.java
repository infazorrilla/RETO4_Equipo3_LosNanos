package Controller;

//import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Interfaz.Interfaz;
import Manager.Animals.ManagerCheetah;
import Manager.Animals.ManagerCrocodile;
import Manager.Animals.ManagerDolphin;
import Manager.Animals.ManagerGiraffe;
import Manager.Animals.ManagerSnake;
import Manager.People.ManagerBoss;
import Manager.People.ManagerClient;
import Manager.People.ManagerFeeder;
import Manager.People.ManagerVet;
import Manager.Zones.ManagerAquarium;
import Manager.Zones.ManagerSavannah;
import Manager.Zones.ManagerSwamp;
import Manager.Zoo.ManagerTicket;
import Manager.Zoo.ManagerZoo;
import Pojos.Animal.Cheetah;
import Pojos.Animal.Crocodile;
import Pojos.Animal.Dolphin;
import Pojos.Animal.Giraffe;
import Pojos.Animal.Snake;
import Pojos.Person.Boss;
import Pojos.Person.Client;
import Pojos.Person.Feeder;
import Pojos.Person.Vet;
import Pojos.Zone.Aquarium;
import Pojos.Zone.Savannah;
import Pojos.Zone.Swamp;
import Pojos.ZooTicket.Ticket;
import Pojos.ZooTicket.Zoo;

public class Controller {

	/**
	 * Registers a client
	 * 
	 * Shows an optionPane with TextFields, instances a Client and inserts to the database
	 */
	public void clientRegister() {
		JTextField name = new JTextField();
		JTextField surname = new JTextField();
		JTextField id = new JTextField();
		JTextField user = new JTextField();
		JTextField password = new JTextField();

		Object[] message = { "Name: *", name, "Surname: *", surname, "Id: *", id, "User: *", user, "Password *",
				password };

		int option = JOptionPane.showConfirmDialog(null, message, "Registrar Cliente", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (name.getText().isEmpty() || surname.getText().isEmpty() || id.getText().isEmpty()
					|| user.getText().isEmpty() || password.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Faltan datos", null, JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					ManagerClient managerClient = new ManagerClient();
					// (name, surname, id, user, password, clientID)
					Client clientToIsert = new Client(name.getText(), surname.getText(), id.getText(), user.getText(),
							password.getText(), 0);
					managerClient.insert(clientToIsert);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Datos Erroneos", null, JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}
	
	/**
	 * @param String user
	 * @param String password
	 * 
	 * Compares both parameters using methods
	 * 
	 * @return int
	 */

	public int checkUser(String user, String password) {
		int ret = 0;
		ret = checkBoss(user, password);
		if (ret == 0) {
			ret = checkClient(user, password);
			if (ret == 0) {
				ret = checkFeeder(user, password);
				if (ret == 0) {
					ret = checkVet(user, password);
				}
			}
		}

		return ret;
	}

	/**
	 * @param String user
	 * @param String password
	 * 
	 * Compares both with the bosses' on the database
	 * 
	 * @return int
	 */
	
	public int checkBoss(String user, String password) {
		int ret = 0;
		ManagerBoss managerBoss = new ManagerBoss();
		for (int i = 0; i < managerBoss.selectAll().size(); i++) {
			String userBoss = managerBoss.selectAll().get(i).getUser();
			if (userBoss.equalsIgnoreCase(user)) {
				String passBoss = managerBoss.selectAll().get(i).getPassword();
				boolean pas = checkPassword(password, passBoss);
				if (pas == true) {
					ret = 1;
				} else {
					ret = 0;
				}
			}
		}
		return ret;
	}
	
	/**
	 * @param String user
	 * @param String password
	 * 
	 * Compares both with the clients' on the database
	 * 
	 * @return int
	 */

	public int checkClient(String user, String password) {
		int ret = 0;
		ManagerClient managerClient = new ManagerClient();
		String userClient = null;
		try {
			for (int i = 0; i < managerClient.selectAll().size(); i++) {
				try {
					userClient = managerClient.selectAll().get(i).getUser();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (userClient.equalsIgnoreCase(user)) {
					String passClient = managerClient.selectAll().get(i).getPassword();
					boolean pas = checkPassword(password, passClient);
					if (pas == true) {
						ret = 2;
					} else {
						ret = 0;
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * @param String user
	 * @param String password
	 * 
	 * Compares both with the feeders' on the database
	 * 
	 * @return int
	 */

	public int checkFeeder(String user, String password) {
		int ret = 0;
		ManagerFeeder managerFeeder = new ManagerFeeder();
		for (int i = 0; i < managerFeeder.selectAll().size(); i++) {
			String userFeeder = managerFeeder.selectAll().get(i).getUser();
			if (userFeeder.equalsIgnoreCase(user)) {
				String passFeeder = managerFeeder.selectAll().get(i).getPassword();
				boolean pas = checkPassword(password, passFeeder);
				if (pas == true) {
					ret = 3;
				} else {
					ret = 0;
				}
			}
		}
		return ret;
	}
	
	/**
	 * @param String user
	 * @param String password
	 * 
	 * Compares both with the vets' on the database
	 * 
	 * @returns int
	 */

	public int checkVet(String user, String password) {
		int ret = 0;
		ManagerVet managerVet = new ManagerVet();
		for (int i = 0; i < managerVet.selectAll().size(); i++) {
			String userVet = managerVet.selectAll().get(i).getUser();
			if (userVet.equalsIgnoreCase(user)) {
				String passVet = managerVet.selectAll().get(i).getPassword();
				boolean pas = checkPassword(password, passVet);
				if (pas == true) {
					ret = 4;
				} else {
					ret = 0;
				}
			}
		}
		return ret;
	}
	
	/**
	 * @param String password
	 * @param String passBoss
	 * 
	 * Compares each other
	 * If they're equals returns true
	 *  
	 * @return boolean
	 */

	public boolean checkPassword(String password, String passBoss) {
		boolean ret = false;
		if (passBoss.equalsIgnoreCase(password)) {
			ret = true;
		}
		return ret;

	}
	
	/**
	 * @param String user
	 * @param String password
	 * 
	 * Gets user's password 
	 */

	public void takeUserPass(String user, String password) {
		ManagerBoss managerBoss = new ManagerBoss();
		for (int i = 0; i < managerBoss.selectAll().size(); i++) {
			String userBoss = managerBoss.selectAll().get(i).getUser();
			String passwordBoss = managerBoss.selectAll().get(i).getPassword();
			System.out.println(userBoss);
			System.out.println(passwordBoss);
		}

	}
	
	/**
	 * Logging as a boss you will see all employees' table
	 */

	public void getTableEmployee(DefaultTableModel model, JTable table) {
		getTableBoss(model, table);
		getTableFeeder(model, table);
		getTableVet(model, table);
		getTableClient(model, table);

	}
	
	/**
	 * Shows all bosses
	 */

	public void getTableBoss(DefaultTableModel model, JTable table) {
		ArrayList<Boss> boss = null;
		ManagerBoss managerFeeder = new ManagerBoss();
		try {

			boss = managerFeeder.selectAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < boss.size(); i++) {
			model.addRow(new Object[] { boss.get(i).getId(), boss.get(i).getName(), boss.get(i).getSurname(),
					boss.get(i).getUser(), boss.get(i).getPassword(), boss.get(i).getEmployeeNumCharge() });
		}

	}
	
	/**
	 * Shows all feeders
	 */

	public void getTableFeeder(DefaultTableModel model, JTable table) {
		ArrayList<Feeder> feeder = null;
		ManagerFeeder managerFeeder = new ManagerFeeder();
		try {

			feeder = managerFeeder.selectAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < feeder.size(); i++) {
			model.addRow(new Object[] { feeder.get(i).getId(), feeder.get(i).getName(), feeder.get(i).getSurname(),
					feeder.get(i).getUser(), feeder.get(i).getPassword(), feeder.get(i).getSpecializedDiet() });
		}

	}
	
	/**
	 * Shows all vets
	 */

	public void getTableVet(DefaultTableModel model, JTable table) {
		ArrayList<Vet> vet = null;
		ManagerVet managerVet = new ManagerVet();
		try {

			vet = managerVet.selectAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < vet.size(); i++) {
			model.addRow(new Object[] { vet.get(i).getId(), vet.get(i).getName(), vet.get(i).getSurname(),
					vet.get(i).getUser(), vet.get(i).getPassword(), vet.get(i).getSpecializedAnimalType() });
		}

	}
	
	/**
	 * Shows all clients
	 */

	public void getTableClient(DefaultTableModel model, JTable table) {
		ArrayList<Client> client = null;
		ManagerClient managerClient = new ManagerClient();
		try {

			client = managerClient.selectAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < client.size(); i++) {
			model.addRow(new Object[] { client.get(i).getId(), client.get(i).getName(), client.get(i).getSurname(),
					client.get(i).getUser(), client.get(i).getPassword() });
		}

	}
	
	/**
	 * Shows all dolphins
	 */

	public void getSelectedDolphin(DefaultTableModel model, JTable table) {
		ArrayList<Dolphin> dolphins = null;
		ManagerDolphin managerSnake = new ManagerDolphin();
		try {

			dolphins = managerSnake.selectAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < dolphins.size(); i++) {
			model.addRow(new Object[] { dolphins.get(i).getId(), dolphins.get(i).getName(),
					dolphins.get(i).getScientificName(), dolphins.get(i).getHeight(), dolphins.get(i).getWeight(),
					dolphins.get(i).getBornDate(), dolphins.get(i).getVaccinated(), dolphins.get(i).getDiet(),
					dolphins.get(i).getAnimalType(), dolphins.get(i).getDurationUnderWater() });
		}

	}
	
	/**
	 * Shows all snakes
	 */

	public void getSelectedSnake(DefaultTableModel model, JTable table) {
		ArrayList<Snake> snakes = null;
		ManagerSnake managerSnake = new ManagerSnake();
		try {

			snakes = managerSnake.selectAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < snakes.size(); i++) {
			model.addRow(new Object[] { snakes.get(i).getId(), snakes.get(i).getName(),
					snakes.get(i).getScientificName(), snakes.get(i).getHeight(), snakes.get(i).getWeight(),
					snakes.get(i).getBornDate(), snakes.get(i).getVaccinated(), snakes.get(i).getDiet(),
					snakes.get(i).getShedSkin(), snakes.get(i).isPoisonus() });
		}

	}

	/**
	 * Shows all crocodiles
	 */
	
	public void getSelectedCrocodile(DefaultTableModel model, JTable table) {
		ArrayList<Crocodile> crocodile = null;
		ManagerCrocodile managerCrocodile = new ManagerCrocodile();
		try {

			crocodile = managerCrocodile.selectAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < crocodile.size(); i++) {
			model.addRow(new Object[] { crocodile.get(i).getId(), crocodile.get(i).getName(),
					crocodile.get(i).getScientificName(), crocodile.get(i).getHeight(), crocodile.get(i).getWeight(),
					crocodile.get(i).getBornDate(), crocodile.get(i).getVaccinated(), crocodile.get(i).getDiet(),
					crocodile.get(i).getShedSkin(), crocodile.get(i).getTeethNumber() });
		}

	}
	
	/**
	 * Shows all giraffes
	 */

	public void getSelectedGiraffe(DefaultTableModel model, JTable table) {
		ArrayList<Giraffe> giraffe = null;
		ManagerGiraffe managerGiraffe = new ManagerGiraffe();
		try {

			giraffe = managerGiraffe.selectAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < giraffe.size(); i++) {
			model.addRow(new Object[] { giraffe.get(i).getId(), giraffe.get(i).getName(),
					giraffe.get(i).getScientificName(), giraffe.get(i).getHeight(), giraffe.get(i).getWeight(),
					giraffe.get(i).getBornDate(), giraffe.get(i).getVaccinated(), giraffe.get(i).getDiet(),
					giraffe.get(i).getHairColor(), giraffe.get(i).getNeckLength() });
		}

	}

	/**
	 * Shows all cheetahs
	 */
	
	public void getSelectedCheetah(DefaultTableModel model, JTable table) {
		ArrayList<Cheetah> cheetah = null;
		ManagerCheetah managerCheetah = new ManagerCheetah();
		try {

			cheetah = managerCheetah.selectAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < cheetah.size(); i++) {
			model.addRow(new Object[] { cheetah.get(i).getId(), cheetah.get(i).getName(),
					cheetah.get(i).getScientificName(), cheetah.get(i).getHeight(), cheetah.get(i).getWeight(),
					cheetah.get(i).getBornDate(), cheetah.get(i).getVaccinated(), cheetah.get(i).getDiet(),
					cheetah.get(i).getHairColor(), cheetah.get(i).getMaxSpeed() });
		}

	}
	
	/**
	 * Shows all aquariums
	 */

	public void getTableAquarium(DefaultTableModel model, JTable table) throws SQLException, ClassNotFoundException {
		ArrayList<Aquarium> aquariums = null;
		ManagerAquarium managerAquarium = new ManagerAquarium();
		aquariums = managerAquarium.selectAll();
		for (int i = 0; i < aquariums.size(); i++) {
			model.addRow(new Object[] { aquariums.get(i).getId(), aquariums.get(i).getExtension(),
					aquariums.get(i).getAnimalsNumber(), aquariums.get(i).getSpeciesNumber(),
					aquariums.get(i).getWaterTemp() });
		}

	}
	
	/**
	 * Shows all swamps
	 */

	public void getTableSwamp(DefaultTableModel model, JTable table) throws SQLException, ClassNotFoundException {
		ArrayList<Swamp> swamps = null;
		ManagerSwamp managerSwamp = new ManagerSwamp();
		swamps = managerSwamp.selectAll();
		for (int i = 0; i < swamps.size(); i++) {
			model.addRow(new Object[] { swamps.get(i).getId(), swamps.get(i).getExtension(),
					swamps.get(i).getAnimalsNumber(), swamps.get(i).getSpeciesNumber(),
					swamps.get(i).getWaterSurface() });
		}
	}
	
	/**
	 * Shows all savannahs
	 */

	public void getTableSavannah(DefaultTableModel model, JTable table) throws SQLException, ClassNotFoundException {
		ArrayList<Savannah> savannahs = null;
		ManagerSavannah managerSavannah = new ManagerSavannah();
		savannahs = managerSavannah.selectAll();
		for (int i = 0; i < savannahs.size(); i++) {
			model.addRow(new Object[] { savannahs.get(i).getId(), savannahs.get(i).getExtension(),
					savannahs.get(i).getAnimalsNumber(), savannahs.get(i).getSpeciesNumber(),
					savannahs.get(i).getTreeNumber() });
		}
	}

//	private String checkBoolean(int bolean) {
//		String ret;
//		if (bolean >= 1) {
//			ret = "Yes";
//		} else {
//			ret = "No";
//		}
//		return ret;
//	}
	
	/**
	 * @param JCheckBox vaccinated
	 * 
	 * Checks if the checkbox is selected or not
	 * 
	 * @returns int
	 */

	private int checkBooleanVaccinated(JCheckBox vaccinated) {
		int ret;
		if (vaccinated.isSelected()) {
			ret = 1;
		} else {
			ret = 0;
		}
		return ret;
	}
	
	/**
	 * @param JCheckBox poisonous
	 * 
	 * Checks if the checkbox is selected or not
	 * 
	 * @returns int
	 */

	private boolean checkBooleanPoisonus(JCheckBox poisonus) {
		boolean ret;
		if (poisonus.isSelected()) {
			ret = true;
		} else {
			ret = false;
		}
		return ret;
	}
	
	/**
	 * 
	 * @param type
	 * @param id
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws Exception
	 * 
	 * Asks you if you are sure for deleting
	 */

	public void questionSure(String type, String id) throws SQLException, ClassNotFoundException, Exception {
		String[] options = { "Yes", "No" };
		int result = JOptionPane.showOptionDialog(null, "Are you sure?", "Sure", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		if (result == 0) {
			if (type == "Boss") {
			} else if (type == "Client") {
			} else if (type == "Feeder") {
			} else if (type == "Vet") {
			} else if (type == "Dolphin") {
				ManagerDolphin managerDolphin = new ManagerDolphin();
				Dolphin dolphin = new Dolphin();
				int idInt = Integer.valueOf(id);
				dolphin.setId(idInt);
				managerDolphin.delete(dolphin);
			} else if (type == "Snake") {
				ManagerSnake managerSnake = new ManagerSnake();
				Snake snake = new Snake();
				int idInt = Integer.valueOf(id);
				snake.setId(idInt);
				managerSnake.delete(snake);
			} else if (type == "Crocodile") {
				ManagerCrocodile managerCrocodile = new ManagerCrocodile();
				Crocodile crocodile = new Crocodile();
				int idInt = Integer.valueOf(id);
				crocodile.setId(idInt);
				managerCrocodile.delete(crocodile);
			} else if (type == "Giraffe") {
				ManagerGiraffe managerGiraffe = new ManagerGiraffe();
				Giraffe giraffe = new Giraffe();
				int idInt = Integer.valueOf(id);
				giraffe.setId(idInt);
				managerGiraffe.delete(giraffe);		
			} else if (type == "Cheetah") {
				ManagerCheetah managerCheetah = new ManagerCheetah();
				Cheetah cheetah = new Cheetah();
				int idInt = Integer.valueOf(id);
				cheetah.setId(idInt);
				managerCheetah.delete(cheetah);
			} else if (type == "Aquarium") {
				ManagerAquarium managerAquarium = new ManagerAquarium();
				Aquarium aquarium = new Aquarium();
				int idInt = Integer.valueOf(id);
				aquarium.setId(idInt);
				managerAquarium.delete(aquarium);
			} else if (type == "Swamp") {
				ManagerSwamp managerSwamp = new ManagerSwamp();
				Swamp swamp = new Swamp();
				int idInt = Integer.valueOf(id);
				swamp.setId(idInt);
				managerSwamp.delete(swamp);
			} else if (type == "Savannah") {
				ManagerSavannah managerSavannah = new ManagerSavannah();
				Savannah savannah= new Savannah();
				int idInt = Integer.valueOf(id);
				savannah.setId(idInt);
				managerSavannah.delete(savannah);
			} 

		} else if (result == 1) {
			
		}
	}

//	public void deleteEmployee(String id) {
//
//		ManagerBoss managerBoss = new ManagerBoss();
//		managerBoss.deleteEmployee(id);
//	}

	public void deleteClient(String id) {

		ManagerClient managerClient = new ManagerClient();
		 managerClient.delete();
	}
	
	/**
	 * 
	 * @param type
	 * @param id
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws Exception
	 * 
	 * 
	 */

	public void updateOption(String type, String id) throws SQLException, ClassNotFoundException, Exception {
		if (type.equals("Aquarium")) {
			updateAquarium(id);
		} else if (type.equals("Swamp")) {
			updateSwamp(id);
		} else if (type.equals("Savannah")) {
			updateSavannah(id);
		} else if (type.equals("Dolphin")) {
			updateDolphin(id);
		} else if (type.equals("Snake")) {
			updateSnake(id);
		} else if (type.equals("Crocodile")) {
			updateCrocodile(id);
		} else if (type.equals("Giraffe")) {
			updateGiraffe(id);
		} else if (type.equals("Cheeta")) {
			updateCheeta(id);
		} else if (type.equals("Boss")) {
			updateBoss(id);
		}
	}
	
	/**
	 * 
	 * @param id
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * 
	 * Shows a JOptionPane with JTextFields, gets the information from those
	 * JTextFields and updates the aquarium with the id passed from parameter
	 * 
	 */

	private void updateAquarium(String id) throws SQLException, ClassNotFoundException {
		int idInt = Integer.parseInt(id);
		JTextField extension = new JTextField();
		JTextField animalsNumber = new JTextField();
		JTextField speciesNumber = new JTextField();
		JTextField waterTemp = new JTextField();

		Object[] message = { "Extension: *", extension, "Animals Number: *", animalsNumber, "Species Number: *",
				speciesNumber, "Water Temperature: *", waterTemp };

		int option = JOptionPane.showConfirmDialog(null, message, "Actualizar Zona", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (extension.getText().isEmpty() || animalsNumber.getText().isEmpty() || speciesNumber.getText().isEmpty()
					|| waterTemp.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Faltan datos", null, JOptionPane.ERROR_MESSAGE);
			} else {
//				try {
				String extensionString = extension.getText();
				float extensionFloat = Float.valueOf(extensionString);
				String animalsNumberString = animalsNumber.getText();
				int animalsNumberFloat = Integer.parseInt(animalsNumberString);
				String speciesNumberString = speciesNumber.getText();
				int speciesNumberInt = Integer.parseInt(speciesNumberString);
				String waterTempString = waterTemp.getText();
				int waterTempInt = Integer.parseInt(waterTempString);

				Aquarium aquariumToUpdate = new Aquarium(idInt, extensionFloat, animalsNumberFloat, speciesNumberInt,
						waterTempInt);

				ManagerAquarium managerAquarium = new ManagerAquarium();
				managerAquarium.update(aquariumToUpdate);
			}
		}
	}
	
	/**
	 * 
	 * @param id
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * 
	 * Shows a JOptionPane with JTextFields, gets the information from those
	 * JTextFields and updates the swamp with the id passed from parameter
	 * 
	 */

	private void updateSwamp(String id) throws SQLException, ClassNotFoundException {
		int idInt = Integer.parseInt(id);
		JTextField extension = new JTextField();
		JTextField animalsNumber = new JTextField();
		JTextField speciesNumber = new JTextField();
		JTextField waterSurface = new JTextField();

		Object[] message = { "Extension: *", extension, "Animals Number: *", animalsNumber, "Species Number: *",
				speciesNumber, "Water Surface: *", waterSurface };

		int option = JOptionPane.showConfirmDialog(null, message, "Actualizar Zona", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (extension.getText().isEmpty() || animalsNumber.getText().isEmpty() || speciesNumber.getText().isEmpty()
					|| waterSurface.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Faltan datos", null, JOptionPane.ERROR_MESSAGE);
			} else {
//				try {
				String extensionString = extension.getText();
				float extensionFloat = Float.valueOf(extensionString);
				String animalsNumberString = animalsNumber.getText();
				int animalsNumberInt = Integer.parseInt(animalsNumberString);
				String speciesNumberString = speciesNumber.getText();
				int speciesNumberInt = Integer.parseInt(speciesNumberString);
				String waterSurfaceString = waterSurface.getText();
				int waterSurfaceInt = Integer.parseInt(waterSurfaceString);

				Swamp swampToUpdate = new Swamp(idInt, extensionFloat, animalsNumberInt, speciesNumberInt,
						waterSurfaceInt);

				ManagerSwamp managerSwamp = new ManagerSwamp();
				managerSwamp.update(swampToUpdate);
			}
		}
	}

	/**
	 * 
	 * @param id
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * 
	 * Shows a JOptionPane with JTextFields, gets the information from those
	 * JTextFields and updates the savannah with the id passed from parameter
	 * 
	 */
	
	private void updateSavannah(String id) throws SQLException, ClassNotFoundException {
		int idInt = Integer.parseInt(id);
		JTextField extension = new JTextField();
		JTextField animalsNumber = new JTextField();
		JTextField speciesNumber = new JTextField();
		JTextField treeNumber = new JTextField();

		Object[] message = { "Extension: *", extension, "Animals Number: *", animalsNumber, "Species Number: *",
				speciesNumber, "Tree Number: *", treeNumber };

		int option = JOptionPane.showConfirmDialog(null, message, "Actualizar Zona", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (extension.getText().isEmpty() || animalsNumber.getText().isEmpty() || speciesNumber.getText().isEmpty()
					|| treeNumber.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Faltan datos", null, JOptionPane.ERROR_MESSAGE);
			} else {
//				try {
				String extensionString = extension.getText();
				float extensionFloat = Float.valueOf(extensionString);
				String animalsNumberString = animalsNumber.getText();
				int animalsNumberInt = Integer.parseInt(animalsNumberString);
				String speciesNumberString = speciesNumber.getText();
				int speciesNumberInt = Integer.parseInt(speciesNumberString);
				String treeNumberString = treeNumber.getText();
				int treeNumberInt = Integer.parseInt(treeNumberString);

				Savannah savannahToUpdate = new Savannah(idInt, extensionFloat, animalsNumberInt, speciesNumberInt,
						treeNumberInt);

				ManagerSavannah managerSavannah = new ManagerSavannah();
				managerSavannah.update(savannahToUpdate);
			}
		}
	}


	public void updateBoss(String id) throws SQLException, Exception {
		JTextField name = new JTextField();
		JTextField surname = new JTextField();
		JTextField user = new JTextField();
		JTextField password = new JTextField();

		JTextField employeeNumCharge = new JTextField();
		JTextField ssNumber = new JTextField();

		Object[] message = { "Name: *", name, "Surname: *", surname, "User: *", user, "Password *", password,
				"Social Security Number *", ssNumber, "employeeNumCharge *", employeeNumCharge };

		int option = JOptionPane.showConfirmDialog(null, message, "Registrar Jefe", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (name.getText().isEmpty() || surname.getText().isEmpty() || user.getText().isEmpty()
					|| password.getText().isEmpty() || ssNumber.getText().isEmpty()
					|| employeeNumCharge.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Faltan datos", null, JOptionPane.ERROR_MESSAGE);
			} else {
					ManagerBoss managerBoss = new ManagerBoss();
					int ssNumberInt = Integer.parseInt(ssNumber.getText());
					int employeeNumChargeInt = Integer.parseInt(employeeNumCharge.getText());
					Boss bossToUpdate = new Boss(name.getText(), surname.getText(), user.getText(), password.getText(),
							id, ssNumberInt, employeeNumChargeInt);
					managerBoss.update(bossToUpdate);
			}
		}

	}

	public void updateFeeder() {
		JTextField name = new JTextField();
		JTextField surname = new JTextField();
		JTextField id = new JTextField();
		JTextField user = new JTextField();
		JTextField password = new JTextField();

		JTextField specializedDiet = new JTextField();
		JTextField ssNumber = new JTextField();

		Object[] message = { "Name: *", name, "Surname: *", surname, "Id: *", id, "User: *", user, "Password *",
				password, "Social Security Number *", ssNumber, "employeeNumCharge *", specializedDiet };

		int option = JOptionPane.showConfirmDialog(null, message, "Registrar Jefe", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (name.getText().isEmpty() || surname.getText().isEmpty() || id.getText().isEmpty()
					|| user.getText().isEmpty() || password.getText().isEmpty() || ssNumber.getText().isEmpty()
					|| specializedDiet.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Faltan datos", null, JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					ManagerFeeder managerFeeder = new ManagerFeeder();
					int ssNumberInt = Integer.parseInt(ssNumber.getText());
					Feeder feederToInsert = new Feeder(name.getText(), surname.getText(), id.getText(), user.getText(),
							password.getText(), ssNumberInt, specializedDiet.getText());
					managerFeeder.insert(feederToInsert);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Datos Erroneos", null, JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}

	public void updateVet() {
		JTextField name = new JTextField();
		JTextField surname = new JTextField();
		JTextField id = new JTextField();
		JTextField user = new JTextField();
		JTextField password = new JTextField();

		JTextField specializedDiet = new JTextField();
		JTextField ssNumber = new JTextField();

		Object[] message = { "Name: *", name, "Surname: *", surname, "Id: *", id, "User: *", user, "Password *",
				password, "Social Security Number *", ssNumber, "employeeNumCharge *", specializedDiet };

		int option = JOptionPane.showConfirmDialog(null, message, "Registrar Jefe", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (name.getText().isEmpty() || surname.getText().isEmpty() || id.getText().isEmpty()
					|| user.getText().isEmpty() || password.getText().isEmpty() || ssNumber.getText().isEmpty()
					|| specializedDiet.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Faltan datos", null, JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					ManagerFeeder managerFeeder = new ManagerFeeder();
					int ssNumberInt = Integer.parseInt(ssNumber.getText());
					Feeder feederToInsert = new Feeder(name.getText(), surname.getText(), id.getText(), user.getText(),
							password.getText(), ssNumberInt, specializedDiet.getText());
					managerFeeder.insert(feederToInsert);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Datos Erroneos", null, JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}

	public void updateDolphin(String id) {
		int idInt = Integer.valueOf(id);
		JTextField name = new JTextField();
		JTextField scientificName = new JTextField();
		JTextField height = new JTextField();
		JTextField weight = new JTextField();
		JTextField bornDate = new JTextField();
		JCheckBox vaccinated = new JCheckBox();
		JComboBox diet = new JComboBox();
		diet.addItem("Carnivorous");
		diet.addItem("Herbivorous");
		JTextField animalType = new JTextField();
		JTextField durationUnderWater = new JTextField();

		Object[] message = { "Name: *", name, "ScientificName: *", scientificName, "Height: *", height, "Weight: *",
				weight, "Born-Date *", bornDate, "Vacinated: *", vaccinated, "Diet: *", diet, "Animal-Tipe: *",
				animalType, "Duration Under Water: *", durationUnderWater };

		int option = JOptionPane.showConfirmDialog(null, message, "Actualizar Delfin", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (name.getText().isEmpty() || scientificName.getText().isEmpty() || height.getText().isEmpty()
					|| weight.getText().isEmpty() || bornDate.getText().isEmpty() || animalType.getText().isEmpty()
					|| durationUnderWater.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Faltan datos", null, JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					ManagerDolphin managerDolphin = new ManagerDolphin();
					String heightString = height.getText();
					float heightFloat = Float.valueOf(heightString);
					String weightString = weight.getText();
					float weightFloat = Float.valueOf(weightString);
					String dateString = bornDate.getText();
					SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
					Date date = formato.parse(dateString);
					int vaccinatedBoolean = checkBooleanVaccinated(vaccinated);
					String durationUnderWaterString = durationUnderWater.getText();
					int durationUnderWaterInt = Integer.valueOf(durationUnderWaterString);

					Dolphin dolphinToIsert = new Dolphin(idInt, name.getText(), scientificName.getText(), heightFloat,
							weightFloat, date, vaccinatedBoolean, diet.getSelectedItem().toString(),
							animalType.getText(), durationUnderWaterInt);
					managerDolphin.update(dolphinToIsert);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Datos Erroneos", null, JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	public void updateSnake(String id) {
		int idInt = Integer.valueOf(id);
		JTextField name = new JTextField();
		JTextField scientificName = new JTextField();
		JTextField height = new JTextField();
		JTextField weight = new JTextField();
		JTextField bornDate = new JTextField();
		JCheckBox vaccinated = new JCheckBox();
		JComboBox diet = new JComboBox();
		diet.addItem("Carnivorous");
		diet.addItem("Herbivorous");
		JTextField shedSkin = new JTextField();
		JCheckBox poisonus = new JCheckBox();

		Object[] message = { "Name: *", name, "ScientificName: *", scientificName, "Height: *", height, "Weight: *",
				weight, "Born-Date *", bornDate, "Vacinated: *", vaccinated, "Diet: *", diet, "ShedSkin: *", shedSkin,
				"Poisonus: *", poisonus };

		int option = JOptionPane.showConfirmDialog(null, message, "Actualizar Serpiente", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (name.getText().isEmpty() || scientificName.getText().isEmpty() || height.getText().isEmpty()
					|| weight.getText().isEmpty() || bornDate.getText().isEmpty() || shedSkin.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Faltan datos", null, JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					ManagerSnake managerSnake = new ManagerSnake();
					String heightString = height.getText();
					float heightFloat = Float.valueOf(heightString);
					String weightString = weight.getText();
					float weightFloat = Float.valueOf(weightString);
					String dateString = bornDate.getText();
					SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
					Date date = formato.parse(dateString);
					int vaccinatedBoolean = checkBooleanVaccinated(vaccinated);
					String shedSkinString = shedSkin.getText();
					Date dateShedSkin = formato.parse(shedSkinString);
					boolean PoisonusBoolean = checkBooleanPoisonus(poisonus);

					Snake snakeToInsert = new Snake(idInt, name.getText(), scientificName.getText(), heightFloat,
							weightFloat, date, vaccinatedBoolean, diet.getSelectedItem().toString(), dateShedSkin,
							PoisonusBoolean);
					managerSnake.update(snakeToInsert);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Datos Erroneos", null, JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}

	public void updateCrocodile(String id) {
		int idInt = Integer.valueOf(id);
		JTextField name = new JTextField();
		JTextField scientificName = new JTextField();
		JTextField height = new JTextField();
		JTextField weight = new JTextField();
		JTextField bornDate = new JTextField();
		JCheckBox vaccinated = new JCheckBox();
		JComboBox diet = new JComboBox();
		diet.addItem("Carnivorous");
		diet.addItem("Herbivorous");
		JTextField shedSkin = new JTextField();
		JTextField teethNumber = new JTextField();

		Object[] message = { "Name: *", name, "ScientificName: *", scientificName, "Height: *", height, "Weight: *",
				weight, "Born-Date *", bornDate, "Vacinated: *", vaccinated, "Diet: *", diet, "ShedSkin: *", shedSkin,
				"teethNumber: *", teethNumber };

		int option = JOptionPane.showConfirmDialog(null, message, "Actualizar Cocodrilo", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (name.getText().isEmpty() || scientificName.getText().isEmpty() || height.getText().isEmpty()
					|| weight.getText().isEmpty() || bornDate.getText().isEmpty() || shedSkin.getText().isEmpty()
					|| teethNumber.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Faltan datos", null, JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					ManagerCrocodile managerCrocodile = new ManagerCrocodile();
					String heightString = height.getText();
					float heightFloat = Float.valueOf(heightString);
					String weightString = weight.getText();
					float weightFloat = Float.valueOf(weightString);
					String dateString = bornDate.getText();
					SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
					Date date = formato.parse(dateString);
					int vaccinatedBoolean = checkBooleanVaccinated(vaccinated);
					String shedSkinString = shedSkin.getText();
					Date dateShedSkin = formato.parse(shedSkinString);
					String teethNumberString = teethNumber.getText();
					int teethNumberInt = Integer.valueOf(teethNumberString);

					Crocodile crocodileToInsert = new Crocodile(idInt, name.getText(), scientificName.getText(),
							heightFloat, weightFloat, date, vaccinatedBoolean, diet.getSelectedItem().toString(),
							dateShedSkin, teethNumberInt);
					managerCrocodile.update(crocodileToInsert);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Datos Erroneos", null, JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}

	public void updateGiraffe(String id) throws ParseException {
		int idInt = Integer.valueOf(id);
		JTextField name = new JTextField();
		JTextField scientificName = new JTextField();
		JTextField height = new JTextField();
		JTextField weight = new JTextField();
		JTextField bornDate = new JTextField();
		JCheckBox vaccinated = new JCheckBox();
		JComboBox diet = new JComboBox();
		diet.addItem("Carnivorous");
		diet.addItem("Herbivorous");
		JTextField hairColor = new JTextField();
		JTextField neckLegnth = new JTextField();

		Object[] message = { "Name: *", name, "ScientificName: *", scientificName, "Height: *", height, "Weight: *",
				weight, "Born-Date *", bornDate, "Vacinated: *", vaccinated, "Diet: *", diet, "Hair Color: *",
				hairColor, "Neck-Legnth: *", neckLegnth };

		int option = JOptionPane.showConfirmDialog(null, message, "Registrar Girafa", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (name.getText().isEmpty() || scientificName.getText().isEmpty() || height.getText().isEmpty()
					|| weight.getText().isEmpty() || bornDate.getText().isEmpty() || hairColor.getText().isEmpty()
					|| neckLegnth.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Faltan datos", null, JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					ManagerGiraffe managerGiraffe = new ManagerGiraffe();
					String heightString = height.getText();
					float heightFloat = Float.valueOf(heightString);
					String weightString = weight.getText();
					float weightFloat = Float.valueOf(weightString);
					String dateString = bornDate.getText();
					SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
					Date date = formato.parse(dateString);
					int vaccinatedBoolean = checkBooleanVaccinated(vaccinated);
					String neckLegnthString = neckLegnth.getText();
					int neckLegnthInt = Integer.valueOf(neckLegnthString);

					Giraffe giraffeToIsert = new Giraffe(idInt, name.getText(), scientificName.getText(), heightFloat,
							weightFloat, date, vaccinatedBoolean, diet.getSelectedItem().toString(),
							hairColor.getText(), neckLegnthInt);
					managerGiraffe.update(giraffeToIsert);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Datos Erroneos", null, JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}

	public void updateCheeta(String id) throws ParseException {
		int idInt = Integer.valueOf(id);
		JTextField name = new JTextField();
		JTextField scientificName = new JTextField();
		JTextField height = new JTextField();
		JTextField weight = new JTextField();
		JTextField bornDate = new JTextField();
		JCheckBox vaccinated = new JCheckBox();
		JComboBox diet = new JComboBox();
		diet.addItem("Carnivorous");
		diet.addItem("Herbivorous");
		JTextField hairColor = new JTextField();
		JTextField maxSpeed = new JTextField();

		Object[] message = { "Name: *", name, "ScientificName: *", scientificName, "Height: *", height, "Weight: *",
				weight, "Born-Date *", bornDate, "Vacinated: *", vaccinated, "Diet: *", diet, "Hair Color: *",
				hairColor, "Max-Speed: *", maxSpeed };

		int option = JOptionPane.showConfirmDialog(null, message, "Registrar Guepardo", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (name.getText().isEmpty() || scientificName.getText().isEmpty() || height.getText().isEmpty()
					|| weight.getText().isEmpty() || bornDate.getText().isEmpty() || hairColor.getText().isEmpty()
					|| maxSpeed.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Faltan datos", null, JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					ManagerCheetah managerCheetah = new ManagerCheetah();
					String heightString = height.getText();
					float heightFloat = Float.valueOf(heightString);
					String weightString = weight.getText();
					float weightFloat = Float.valueOf(weightString);
					String dateString = bornDate.getText();
					SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
					Date date = formato.parse(dateString);
					int vaccinatedBoolean = checkBooleanVaccinated(vaccinated);
					String maxSpeedString = maxSpeed.getText();
					int maxSpeedInt = Integer.valueOf(maxSpeedString);

					Cheetah cheetahToIsert = new Cheetah(idInt, name.getText(), scientificName.getText(), heightFloat,
							weightFloat, date, vaccinatedBoolean, diet.getSelectedItem().toString(),
							hairColor.getText(), maxSpeedInt);
					managerCheetah.update(cheetahToIsert);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Datos Erroneos", null, JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}

	public void addOption() throws SQLException, Exception {
		String[] options = { "Employee", "Animal", "Zone", "Zoo" };
		int result = JOptionPane.showOptionDialog(null, "What do you want to add?", "Add", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (result == 0) {
			addOptionEmployee();
		} else if (result == 1) {
			addOptionAnimal();
		} else if (result == 2) {
			addOptionZone();
		} else if (result ==3) {
			addOptionZoo();
		}
	}

	private void addOptionEmployee() {
		String[] optionsEmployee = { "Boss", "Feeder", "Vet" };
		int resultEmployee = JOptionPane.showOptionDialog(null, "What kind of employee?", "Add",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsEmployee, optionsEmployee[0]);
		switch (resultEmployee) {
		case 0:
			addBoss();
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		}
	}
	
	/**
	 * 
	 * @param id
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * 
	 * Shows a JOptionPane with JTextFields, gets the information from those
	 * JTextFields and inserts the boss into the database
	 * 
	 */

	public void addBoss() {
		JTextField name = new JTextField();
		JTextField surname = new JTextField();
		JTextField id = new JTextField();
		JTextField user = new JTextField();
		JTextField password = new JTextField();

		JTextField employeeNumCharge = new JTextField();
		JTextField ssNumber = new JTextField();

		Object[] message = { "Id: *", id, "Name: *", name, "Surname: *", surname, "User: *", user, "Password *",
				password, "Social Security Number *", ssNumber, "employeeNumCharge *", employeeNumCharge };

		int option = JOptionPane.showConfirmDialog(null, message, "Registrar Jefe", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (name.getText().isEmpty() || surname.getText().isEmpty() || id.getText().isEmpty()
					|| user.getText().isEmpty() || password.getText().isEmpty() || ssNumber.getText().isEmpty()
					|| employeeNumCharge.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Faltan datos", null, JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					ManagerBoss managerBoss = new ManagerBoss();
					int ssNumberInt = Integer.parseInt(ssNumber.getText());
					int employeeNumChargeInt = Integer.parseInt(employeeNumCharge.getText());
					Boss bossToInsert = new Boss(name.getText(), surname.getText(), id.getText(), user.getText(),
							password.getText(), ssNumberInt, employeeNumChargeInt);
					managerBoss.insert(bossToInsert);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Datos Erroneos", null, JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}

	public void addOptionAnimal() throws ParseException {
		String[] optionsAnimal = { "Dolphin", "Crocodile", "Snake", "Giraffe", "Cheetah" };
		int resultEmployee = JOptionPane.showOptionDialog(null, "What kind of Animal?", "Add",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsAnimal, optionsAnimal[0]);

		switch (resultEmployee) {
		case 0:
			addDolphin();
			break;
		case 1:
			addCrocodile();
			break;
		case 2:
			addSnake();
			break;
		case 3:
			addGiraffe();
			break;
		case 4:
			addCheetah();
			break;
		}
	}
	
	/**
	 * 
	 * @param id
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * 
	 * Shows a JOptionPane with the Zones options, depending on
	 * which zone you select, a different JOptionPane will appear
	 * 
	 */

	private void addOptionZone() throws SQLException, ClassNotFoundException {
		String[] optionsZone = { "Aquarium", "Swamp", "Savannah" };
		int resultZone = JOptionPane.showOptionDialog(null, "What kind of Zone?", "Add", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, optionsZone, optionsZone[0]);
		if (resultZone == 0) {
			addAquarium();
		} else if (resultZone == 1) {
			addSwamp();
		} else if (resultZone == 2) {
			addSavannah();
		}
	}
	
	/**
	 * 
	 * @param id
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * 
	 * Shows a JOptionPane with JTextFields, gets the information from those
	 * JTextFields and inserts the aquarium into the database
	 * 
	 */

	private void addAquarium() throws SQLException, ClassNotFoundException {
		JTextField extension = new JTextField();
		JTextField animalsNumber = new JTextField();
		JTextField speciesNumber = new JTextField();
		JTextField waterTemp = new JTextField();

		Object[] message = { "Extension: *", extension, "Animals Number: *", animalsNumber, "Species Number: *",
				speciesNumber, "Water Temperature: *", waterTemp };

		int option = JOptionPane.showConfirmDialog(null, message, "Registrar Zona", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (extension.getText().isEmpty() || animalsNumber.getText().isEmpty() || speciesNumber.getText().isEmpty()
					|| waterTemp.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Faltan datos", null, JOptionPane.ERROR_MESSAGE);
			} else {
//				try {
				String extensionString = extension.getText();
				float extensionFloat = Float.valueOf(extensionString);
				String animalsNumberString = animalsNumber.getText();
				int animalsNumberFloat = Integer.parseInt(animalsNumberString);
				String speciesNumberString = speciesNumber.getText();
				int speciesNumberInt = Integer.parseInt(speciesNumberString);
				String waterTempString = waterTemp.getText();
				int waterTempInt = Integer.parseInt(waterTempString);

				Aquarium aquariumToInsert = new Aquarium(extensionFloat, animalsNumberFloat, speciesNumberInt,
						waterTempInt);

				ManagerAquarium managerAquarium = new ManagerAquarium();
				managerAquarium.insert(aquariumToInsert);
			}
		}

	}

	/**
	 * 
	 * @param id
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * 
	 * Shows a JOptionPane with JTextFields, gets the information from those
	 * JTextFields and inserts the swamp into the database
	 * 
	 */
	
	private void addSwamp() throws SQLException, ClassNotFoundException {
		JTextField extension = new JTextField();
		JTextField animalsNumber = new JTextField();
		JTextField speciesNumber = new JTextField();
		JTextField waterSurface = new JTextField();

		Object[] message = { "Extension: *", extension, "Animals Number: *", animalsNumber, "Species Number: *",
				speciesNumber, "Water Surface: *", waterSurface };

		int option = JOptionPane.showConfirmDialog(null, message, "Registrar Zona", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (extension.getText().isEmpty() || animalsNumber.getText().isEmpty() || speciesNumber.getText().isEmpty()
					|| waterSurface.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Faltan datos", null, JOptionPane.ERROR_MESSAGE);
			} else {
//				try {
				String extensionString = extension.getText();
				float extensionFloat = Float.valueOf(extensionString);
				String animalsNumberString = animalsNumber.getText();
				int animalsNumberInt = Integer.parseInt(animalsNumberString);
				String speciesNumberString = speciesNumber.getText();
				int speciesNumberInt = Integer.parseInt(speciesNumberString);
				String waterSurfaceString = waterSurface.getText();
				int waterSurfaceInt = Integer.parseInt(waterSurfaceString);

				Swamp swampToInsert = new Swamp(extensionFloat, animalsNumberInt, speciesNumberInt, waterSurfaceInt);

				ManagerSwamp managerSwamp = new ManagerSwamp();
				managerSwamp.insert(swampToInsert);
			}
		}
	}
	
	/**
	 * 
	 * @param id
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * 
	 * Shows a JOptionPane with JTextFields, gets the information from those
	 * JTextFields and inserts the savannah into the database
	 * 
	 */

	private void addSavannah() throws SQLException, ClassNotFoundException {
		JTextField extension = new JTextField();
		JTextField animalsNumber = new JTextField();
		JTextField speciesNumber = new JTextField();
		JTextField treeNumber = new JTextField();

		Object[] message = { "Extension: *", extension, "Animals Number: *", animalsNumber, "Species Number: *",
				speciesNumber, "Tree Number: *", treeNumber };

		int option = JOptionPane.showConfirmDialog(null, message, "Registrar Zona", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (extension.getText().isEmpty() || animalsNumber.getText().isEmpty() || speciesNumber.getText().isEmpty()
					|| treeNumber.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Faltan datos", null, JOptionPane.ERROR_MESSAGE);
			} else {
//				try {
				String extensionString = extension.getText();
				float extensionFloat = Float.valueOf(extensionString);
				String animalsNumberString = animalsNumber.getText();
				int animalsNumberInt = Integer.parseInt(animalsNumberString);
				String speciesNumberString = speciesNumber.getText();
				int speciesNumberInt = Integer.parseInt(speciesNumberString);
				String treeNumberString = treeNumber.getText();
				int treeNumberInt = Integer.parseInt(treeNumberString);

				Savannah savannahToInsert = new Savannah(extensionFloat, animalsNumberInt, speciesNumberInt,
						treeNumberInt);

				ManagerSavannah managerSavannah = new ManagerSavannah();
				managerSavannah.insert(savannahToInsert);
			}
		}

	}

	public void questionLogOut(JPanel jpBoss, JPanel jpLogin, JPanel jpClient, JPanel jpFeeder, JPanel jpVet) {
		String[] options = { "LogOut", "No" };
		int result = JOptionPane.showOptionDialog(null, "LogOut?", "Sure", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (result == 0) {
			Interfaz interfaz = new Interfaz();
			interfaz.panelLogin(jpLogin, jpVet, jpBoss, jpFeeder, jpClient);
			jpBoss.setVisible(false);
			jpLogin.setVisible(true);

		} else if (result == 1) {

		}

	}

	public void addDolphin() throws ParseException {
		JTextField name = new JTextField();
		JTextField scientificName = new JTextField();
		JTextField height = new JTextField();
		JTextField weight = new JTextField();
		JTextField bornDate = new JTextField();
		JCheckBox vaccinated = new JCheckBox();
		JComboBox diet = new JComboBox();
		diet.addItem("Carnivorous");
		diet.addItem("Herbivorous");
		JTextField animalType = new JTextField();
		JTextField durationUnderWater = new JTextField();

		Object[] message = { "Name: *", name, "ScientificName: *", scientificName, "Height: *", height, "Weight: *",
				weight, "Born-Date *", bornDate, "Vacinated: *", vaccinated, "Diet: *", diet, "Animal-Tipe: *",
				animalType, "Duration Under Water: *", durationUnderWater };

		int option = JOptionPane.showConfirmDialog(null, message, "Registrar Delfin", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (name.getText().isEmpty() || scientificName.getText().isEmpty() || height.getText().isEmpty()
					|| weight.getText().isEmpty() || bornDate.getText().isEmpty() || animalType.getText().isEmpty()
					|| durationUnderWater.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Faltan datos", null, JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					ManagerDolphin managerDolphin = new ManagerDolphin();
					String heightString = height.getText();
					float heightFloat = Float.valueOf(heightString);
					String weightString = weight.getText();
					float weightFloat = Float.valueOf(weightString);
					String dateString = bornDate.getText();
					SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
					Date date = formato.parse(dateString);
					int vaccinatedBoolean = checkBooleanVaccinated(vaccinated);
					String durationUnderWaterString = durationUnderWater.getText();
					int durationUnderWaterInt = Integer.valueOf(durationUnderWaterString);

					Dolphin dolphinToIsert = new Dolphin(0, name.getText(), scientificName.getText(), heightFloat,
							weightFloat, date, vaccinatedBoolean, diet.getSelectedItem().toString(),
							animalType.getText(), durationUnderWaterInt);
					managerDolphin.insert(dolphinToIsert);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Datos Erroneos", null, JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}

	public void addSnake() {
		JTextField name = new JTextField();
		JTextField scientificName = new JTextField();
		JTextField height = new JTextField();
		JTextField weight = new JTextField();
		JTextField bornDate = new JTextField();
		JCheckBox vaccinated = new JCheckBox();
		JComboBox diet = new JComboBox();
		diet.addItem("Carnivorous");
		diet.addItem("Herbivorous");
		JTextField shedSkin = new JTextField();
		JCheckBox poisonus = new JCheckBox();

		Object[] message = { "Name: *", name, "ScientificName: *", scientificName, "Height: *", height, "Weight: *",
				weight, "Born-Date *", bornDate, "Vacinated: *", vaccinated, "Diet: *", diet, "ShedSkin: *", shedSkin,
				"Poisonus: *", poisonus };

		int option = JOptionPane.showConfirmDialog(null, message, "Registrar Serpiente", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (name.getText().isEmpty() || scientificName.getText().isEmpty() || height.getText().isEmpty()
					|| weight.getText().isEmpty() || bornDate.getText().isEmpty() || shedSkin.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Faltan datos", null, JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					ManagerSnake managerSnake = new ManagerSnake();
					String heightString = height.getText();
					float heightFloat = Float.valueOf(heightString);
					String weightString = weight.getText();
					float weightFloat = Float.valueOf(weightString);
					String dateString = bornDate.getText();
					SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
					Date date = formato.parse(dateString);
					int vaccinatedBoolean = checkBooleanVaccinated(vaccinated);
					String shedSkinString = shedSkin.getText();
					Date dateShedSkin = formato.parse(shedSkinString);
					boolean PoisonusBoolean = checkBooleanPoisonus(poisonus);

					Snake snakeToInsert = new Snake(0, name.getText(), scientificName.getText(), heightFloat,
							weightFloat, date, vaccinatedBoolean, diet.getSelectedItem().toString(), dateShedSkin,
							PoisonusBoolean);
					managerSnake.insert(snakeToInsert);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Datos Erroneos", null, JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}

	public void addCrocodile() {
		JTextField name = new JTextField();
		JTextField scientificName = new JTextField();
		JTextField height = new JTextField();
		JTextField weight = new JTextField();
		JTextField bornDate = new JTextField();
		JCheckBox vaccinated = new JCheckBox();
		JComboBox diet = new JComboBox();
		diet.addItem("Carnivorous");
		diet.addItem("Herbivorous");
		JTextField shedSkin = new JTextField();
		JTextField teethNumber = new JTextField();

		Object[] message = { "Name: *", name, "ScientificName: *", scientificName, "Height: *", height, "Weight: *",
				weight, "Born-Date *", bornDate, "Vacinated: *", vaccinated, "Diet: *", diet, "ShedSkin: *", shedSkin,
				"teethNumber: *", teethNumber };

		int option = JOptionPane.showConfirmDialog(null, message, "Registrar Cocodrilo", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (name.getText().isEmpty() || scientificName.getText().isEmpty() || height.getText().isEmpty()
					|| weight.getText().isEmpty() || bornDate.getText().isEmpty() || shedSkin.getText().isEmpty()
					|| teethNumber.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Faltan datos", null, JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					ManagerCrocodile managerCrocodile = new ManagerCrocodile();
					String heightString = height.getText();
					float heightFloat = Float.valueOf(heightString);
					String weightString = weight.getText();
					float weightFloat = Float.valueOf(weightString);
					String dateString = bornDate.getText();
					SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
					Date date = formato.parse(dateString);
					int vaccinatedBoolean = checkBooleanVaccinated(vaccinated);
					String shedSkinString = shedSkin.getText();
					Date dateShedSkin = formato.parse(shedSkinString);
					String teethNumberString = teethNumber.getText();
					int teethNumberInt = Integer.valueOf(teethNumberString);

					Crocodile crocodileToInsert = new Crocodile(0, name.getText(), scientificName.getText(),
							heightFloat, weightFloat, date, vaccinatedBoolean, diet.getSelectedItem().toString(),
							dateShedSkin, teethNumberInt);
					managerCrocodile.insert(crocodileToInsert);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Datos Erroneos", null, JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}

	public void addGiraffe() throws ParseException {
		JTextField name = new JTextField();
		JTextField scientificName = new JTextField();
		JTextField height = new JTextField();
		JTextField weight = new JTextField();
		JTextField bornDate = new JTextField();
		JCheckBox vaccinated = new JCheckBox();
		JComboBox diet = new JComboBox();
		diet.addItem("Carnivorous");
		diet.addItem("Herbivorous");
		JTextField hairColor = new JTextField();
		JTextField neckLegnth = new JTextField();

		Object[] message = { "Name: *", name, "ScientificName: *", scientificName, "Height: *", height, "Weight: *",
				weight, "Born-Date *", bornDate, "Vacinated: *", vaccinated, "Diet: *", diet, "Hair Color: *",
				hairColor, "Neck-Legnth: *", neckLegnth };

		int option = JOptionPane.showConfirmDialog(null, message, "Registrar Girafa", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (name.getText().isEmpty() || scientificName.getText().isEmpty() || height.getText().isEmpty()
					|| weight.getText().isEmpty() || bornDate.getText().isEmpty() || hairColor.getText().isEmpty()
					|| neckLegnth.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Faltan datos", null, JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					ManagerGiraffe managerGiraffe = new ManagerGiraffe();
					String heightString = height.getText();
					float heightFloat = Float.valueOf(heightString);
					String weightString = weight.getText();
					float weightFloat = Float.valueOf(weightString);
					String dateString = bornDate.getText();
					SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
					Date date = formato.parse(dateString);
					int vaccinatedBoolean = checkBooleanVaccinated(vaccinated);
					String neckLegnthString = neckLegnth.getText();
					int neckLegnthInt = Integer.valueOf(neckLegnthString);

					Giraffe giraffeToIsert = new Giraffe(0, name.getText(), scientificName.getText(), heightFloat,
							weightFloat, date, vaccinatedBoolean, diet.getSelectedItem().toString(),
							hairColor.getText(), neckLegnthInt);
					managerGiraffe.insert(giraffeToIsert);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Datos Erroneos", null, JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}

	public void addCheetah() throws ParseException {
		JTextField name = new JTextField();
		JTextField scientificName = new JTextField();
		JTextField height = new JTextField();
		JTextField weight = new JTextField();
		JTextField bornDate = new JTextField();
		JCheckBox vaccinated = new JCheckBox();
		JComboBox diet = new JComboBox();
		diet.addItem("Carnivorous");
		diet.addItem("Herbivorous");
		JTextField hairColor = new JTextField();
		JTextField maxSpeed = new JTextField();

		Object[] message = { "Name: *", name, "ScientificName: *", scientificName, "Height: *", height, "Weight: *",
				weight, "Born-Date *", bornDate, "Vacinated: *", vaccinated, "Diet: *", diet, "Hair Color: *",
				hairColor, "Max-Speed: *", maxSpeed };

		int option = JOptionPane.showConfirmDialog(null, message, "Registrar Guepardo", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (name.getText().isEmpty() || scientificName.getText().isEmpty() || height.getText().isEmpty()
					|| weight.getText().isEmpty() || bornDate.getText().isEmpty() || hairColor.getText().isEmpty()
					|| maxSpeed.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Faltan datos", null, JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					ManagerCheetah managerCheetah = new ManagerCheetah();
					String heightString = height.getText();
					float heightFloat = Float.valueOf(heightString);
					String weightString = weight.getText();
					float weightFloat = Float.valueOf(weightString);
					String dateString = bornDate.getText();
					SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
					Date date = formato.parse(dateString);
					int vaccinatedBoolean = checkBooleanVaccinated(vaccinated);
					String maxSpeedString = maxSpeed.getText();
					int maxSpeedInt = Integer.valueOf(maxSpeedString);

					Cheetah cheetahToIsert = new Cheetah(0, name.getText(), scientificName.getText(), heightFloat,
							weightFloat, date, vaccinatedBoolean, diet.getSelectedItem().toString(),
							hairColor.getText(), maxSpeedInt);
					managerCheetah.insert(cheetahToIsert);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Datos Erroneos", null, JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}
	private void addOptionZoo() {
		JTextField name = new JTextField();
		JTextField location = new JTextField();


		Object[] message = {"Name: *", name, "Location: *", location };

		int option = JOptionPane.showConfirmDialog(null, message, "Registrar Zoo", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (name.getText().isEmpty() || location.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Faltan datos", null, JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					ManagerZoo managerZoo = new ManagerZoo();
					Zoo zooToInsert = new Zoo(name.getText(), location.getText());
							managerZoo.insert(zooToInsert);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Datos Erroneos", null, JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
