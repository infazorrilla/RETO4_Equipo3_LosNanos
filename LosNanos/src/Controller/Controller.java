package Controller;

//import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
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
import Pojos.Animal.Cheetah;
import Pojos.Animal.Crocodile;
import Pojos.Animal.Dolphin;
import Pojos.Animal.Giraffe;
import Pojos.Animal.Snake;
import Pojos.Person.Boss;
import Pojos.Person.Client;
import Pojos.Person.Employee;
import Pojos.Person.Feeder;
import Pojos.Person.Vet;
import Pojos.Zone.Aquarium;
import Pojos.Zone.Savannah;
import Pojos.Zone.Swamp;

public class Controller {
	
	public Boss searchBossId(String id) {
		Boss ret = new Boss();
		ManagerBoss managerBoss = new ManagerBoss();
		ArrayList<Boss> bosses = managerBoss.selectAll();
		for (int i = 0 ; i < bosses.size() ; i++) {
			if (bosses.get(i).getId() == id) {
				ret = bosses.get(i);
			}
		}
		return ret;
	}
	
	public Client searchClientId(String id) {
		Client ret = new Client();
		ManagerClient managerClient = new ManagerClient();
		ArrayList<Client> clients;
		try {
			clients = managerClient.selectAll();
			for (int i = 0 ; i < clients.size() ; i++) {
				if (clients.get(i).getId() == id) {
					ret = clients.get(i);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

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

	public boolean checkPassword(String password, String passBoss) {
		boolean ret = false;
		if (passBoss.equalsIgnoreCase(password)) {
			ret = true;
		}
		return ret;

	}

	public void takeUserPass(String user, String password) {
		ManagerBoss managerBoss = new ManagerBoss();
		for (int i = 0; i < managerBoss.selectAll().size(); i++) {
			String userBoss = managerBoss.selectAll().get(i).getUser();
			String passwordBoss = managerBoss.selectAll().get(i).getPassword();
			System.out.println(userBoss);
			System.out.println(passwordBoss);
		}

	}

	public void getTableEmployee(DefaultTableModel model, JTable table) {
		getTableBoss(model, table);
		getTableFeeder(model, table);
		getTableVet(model, table);
		getTableClient(model, table);

	}

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
			model.addRow(
					new Object[] { boss.get(i).getName(), boss.get(i).getSurname(), boss.get(i).getId(),
							boss.get(i).getUser(), boss.get(i).getPassword(), boss.get(i).getEmployeeNumCharge()});
		}

	}
	
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
			model.addRow(
					new Object[] { feeder.get(i).getName(), feeder.get(i).getSurname(), feeder.get(i).getId(),
							feeder.get(i).getUser(), feeder.get(i).getPassword(), feeder.get(i).getSpecializedDiet()});
		}

	}
	
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
			model.addRow(
					new Object[] { vet.get(i).getName(), vet.get(i).getSurname(), vet.get(i).getId(),
							vet.get(i).getUser(), vet.get(i).getPassword(), vet.get(i).getSpecializedAnimalType()});
		}

	}

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
			model.addRow(
					new Object[] { client.get(i).getName(), client.get(i).getSurname(), client.get(i).getId(),
							client.get(i).getUser(), client.get(i).getPassword()});
		}

	}

	public void getSelectedDolphin(String box, DefaultTableModel model, JTable table) {
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

	public void getSelectedSnake(String box, DefaultTableModel model, JTable table) {
		ArrayList<Snake> snakes = null;
		ManagerSnake managerSnake = new ManagerSnake();
		try {

			snakes = managerSnake.selectAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < snakes.size(); i++) {
			model.addRow(
					new Object[] { snakes.get(i).getId(), snakes.get(i).getName(), snakes.get(i).getScientificName(),
							snakes.get(i).getHeight(), snakes.get(i).getWeight(), snakes.get(i).getBornDate(),
							snakes.get(i).getVaccinated(), snakes.get(i).getDiet(), snakes.get(i).getShedSkin(), snakes.get(i).isPoisonus() });
		}

	}
	
	public void getSelectedCrocodile(String box, DefaultTableModel model, JTable table) {
		ArrayList<Crocodile> crocodile = null;
		ManagerCrocodile managerCrocodile = new ManagerCrocodile();
		try {

			crocodile = managerCrocodile.selectAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < crocodile.size(); i++) {
			model.addRow(
					new Object[] { crocodile.get(i).getId(), crocodile.get(i).getName(), crocodile.get(i).getScientificName(),
							crocodile.get(i).getHeight(), crocodile.get(i).getWeight(), crocodile.get(i).getBornDate(),
							crocodile.get(i).getVaccinated(), crocodile.get(i).getDiet(), crocodile.get(i).getShedSkin(), crocodile.get(i).getTeethNumber() });
		}

	}
	
	public void getSelectedGiraffe(String box, DefaultTableModel model, JTable table) {
		ArrayList<Giraffe> giraffe = null;
		ManagerGiraffe managerGiraffe = new ManagerGiraffe();
		try {

			giraffe = managerGiraffe.selectAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < giraffe.size(); i++) {
			model.addRow(
					new Object[] { giraffe.get(i).getId(), giraffe.get(i).getName(), giraffe.get(i).getScientificName(),
							giraffe.get(i).getHeight(), giraffe.get(i).getWeight(), giraffe.get(i).getBornDate(),
							giraffe.get(i).getVaccinated(), giraffe.get(i).getDiet(), giraffe.get(i).getHairColor(), giraffe.get(i).getNeckLength() });
		}

	}
	
	public void getSelectedCheetah(String box, DefaultTableModel model, JTable table) {
		ArrayList<Cheetah> cheetah = null;
		ManagerCheetah managerCheetah = new ManagerCheetah();
		try {

			cheetah = managerCheetah.selectAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < cheetah.size(); i++) {
			model.addRow(
					new Object[] { cheetah.get(i).getId(), cheetah.get(i).getName(), cheetah.get(i).getScientificName(),
							cheetah.get(i).getHeight(), cheetah.get(i).getWeight(), cheetah.get(i).getBornDate(),
							cheetah.get(i).getVaccinated(), cheetah.get(i).getDiet(), cheetah.get(i).getHairColor(), cheetah.get(i).getMaxSpeed() });
		}

	}

	public void getTableAquarium(DefaultTableModel model, JTable table) {
		ArrayList<Aquarium> aquariums = null;
		try {
			ManagerAquarium managerAquarium = new ManagerAquarium();

			aquariums = managerAquarium.selectAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < aquariums.size(); i++) {
			model.addRow(new Object[] { aquariums.get(i).getId(), aquariums.get(i).getExtension(),
					aquariums.get(i).getAnimalsNumber(), aquariums.get(i).getSpeciesNumber(),
					aquariums.get(i).getWaterTemp() });
		}

	}

	public void getTableSwamp(DefaultTableModel model, JTable table) {
		ArrayList<Swamp> swamps = null;
		ManagerSwamp managerSwamp = new ManagerSwamp();
		try {

			swamps = managerSwamp.selectAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < swamps.size(); i++) {
			model.addRow(new Object[] { swamps.get(i).getId(), swamps.get(i).getExtension(),
					swamps.get(i).getAnimalsNumber(), swamps.get(i).getSpeciesNumber(),
					swamps.get(i).getWaterSurface() });
		}
	}

	public void getTableSavannah(DefaultTableModel model, JTable table) {
		ArrayList<Savannah> savannahs = null;
		ManagerSavannah managerSavannah = new ManagerSavannah();
		try {

			savannahs = managerSavannah.selectAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < savannahs.size(); i++) {
			model.addRow(new Object[] { savannahs.get(i).getId(), savannahs.get(i).getExtension(),
					savannahs.get(i).getAnimalsNumber(), savannahs.get(i).getSpeciesNumber(),
					savannahs.get(i).getTreeNumber() });
		}
	}

	private String checkBoolean(int bolean) {
		String ret;
		if (bolean >= 1) {
			ret = "Yes";
		} else {
			ret = "No";
		}
		return ret;
	}

	private int checkBooleanTwo(String vaccinatedString) {
		int ret;
		if (vaccinatedString == "Yes") {
			ret = 1;
		} else {
			ret = 0;
		}
		return ret;
	}

	public void questionSure(JButton jbutton, String type, String id) {
		String[] options = { "Yes", "No" };
		int result = JOptionPane.showOptionDialog(null, "Are you sure?", "Sure", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		ManagerBoss managerBoss = new ManagerBoss();
		
		if (result == 0) {
			if (type == "Boss") {
				Boss boss = searchBossId(id);
				try {
					managerBoss.delete(boss);
				} catch (Exception e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (type == "Client") {
				Client client = searchClientId(id);
				try {
	//				managerClient.delete(client);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (type == "Feeder") {
				
			} else if (type == "Vet") {
				
			}
		} else if (result == 1) {

		}
	}

	public void deleteEmployee(String id) {

		ManagerBoss managerBoss = new ManagerBoss();
		managerBoss.deleteEmployee(id);
	}

	public void deleteClient(String id) {

		ManagerClient managerClient = new ManagerClient();
	//	managerClient.deleteClient(id);
	}
	
	public void updateOption() {
		String[] options = { "Employee", "Animal", "Zone"};
		int result = JOptionPane.showOptionDialog(null, "What do you want to add?", "Add", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (result == 0) {
			updateOptionEmployee();
		}
//		else if (result == 1) {
//			updateOptionAnimal();
//		} else if (result == 2) {
//			updateOptionZone();
//		}
	}
	
	private void updateOptionEmployee() {
		String[] optionsEmployee = { "Boss", "Feeder", "Vet" };
		int resultEmployee = JOptionPane.showOptionDialog(null, "What kind of employee?", "Add",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsEmployee, optionsEmployee[0]);
		switch (resultEmployee) {
		case 0:
			updateBoss();
			break;
		case 1:
			updateFeeder();
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		}
	}
	
	public void updateBoss() {
		JTextField name = new JTextField();
		JTextField surname = new JTextField();
		JTextField user = new JTextField();
		JTextField password = new JTextField();

		JTextField employeeNumCharge = new JTextField();
		JTextField ssNumber = new JTextField();

		Object[] message = { "Name: *", name, "Surname: *", surname, "User: *", user, "Password *",
				password, "Social Security Number *", ssNumber, "employeeNumCharge *", employeeNumCharge};

		int option = JOptionPane.showConfirmDialog(null, message, "Registrar Jefe", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (name.getText().isEmpty() || surname.getText().isEmpty()	|| user.getText().isEmpty() || password.getText().isEmpty()
					|| ssNumber.getText().isEmpty() || employeeNumCharge.getText().isEmpty() ) {
				JOptionPane.showMessageDialog(null, "Faltan datos", null, JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					ManagerBoss managerBoss= new ManagerBoss();
					int ssNumberInt = Integer.parseInt(ssNumber.getText());
					int employeeNumChargeInt = Integer.parseInt(employeeNumCharge.getText());
					Boss bossToUpdate = new Boss(name.getText(), surname.getText(), user.getText(),
							password.getText(), ssNumberInt, employeeNumChargeInt);
					managerBoss.update(bossToUpdate);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Datos Erroneos", null, JOptionPane.ERROR_MESSAGE);
				}
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
				password, "Social Security Number *", ssNumber, "employeeNumCharge *", specializedDiet};

		int option = JOptionPane.showConfirmDialog(null, message, "Registrar Jefe", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (name.getText().isEmpty() || surname.getText().isEmpty() || id.getText().isEmpty()
					|| user.getText().isEmpty() || password.getText().isEmpty() || ssNumber.getText().isEmpty() || specializedDiet.getText().isEmpty() ) {
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
				password, "Social Security Number *", ssNumber, "employeeNumCharge *", specializedDiet};

		int option = JOptionPane.showConfirmDialog(null, message, "Registrar Jefe", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (name.getText().isEmpty() || surname.getText().isEmpty() || id.getText().isEmpty()
					|| user.getText().isEmpty() || password.getText().isEmpty() || ssNumber.getText().isEmpty() || specializedDiet.getText().isEmpty() ) {
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

	public void addOption() {
		String[] options = { "Employee", "Animal", "Zone"};
		int result = JOptionPane.showOptionDialog(null, "What do you want to add?", "Add", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (result == 0) {
			addOptionEmployee();
		} else if (result == 1) {
			addOptionAnimal();
		} else if (result == 2) {
			addOptionZone();
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
	
	public void addBoss() {
		JTextField name = new JTextField();
		JTextField surname = new JTextField();
		JTextField id = new JTextField();
		JTextField user = new JTextField();
		JTextField password = new JTextField();

		JTextField employeeNumCharge = new JTextField();
		JTextField ssNumber = new JTextField();

		Object[] message = { "Name: *", name, "Surname: *", surname, "Id: *", id, "User: *", user, "Password *",
				password, "Social Security Number *", ssNumber, "employeeNumCharge *", employeeNumCharge};

		int option = JOptionPane.showConfirmDialog(null, message, "Registrar Jefe", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (name.getText().isEmpty() || surname.getText().isEmpty() || id.getText().isEmpty()
					|| user.getText().isEmpty() || password.getText().isEmpty() || ssNumber.getText().isEmpty() || employeeNumCharge.getText().isEmpty() ) {
				JOptionPane.showMessageDialog(null, "Faltan datos", null, JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					ManagerBoss managerBoss= new ManagerBoss();
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

	private void addOptionAnimal() {
		String[] optionsAnimal = { "Dolphin", "Crocodile", "Snake", "Giraffe", "Cheetah" };
		int resultEmployee = JOptionPane.showOptionDialog(null, "What kind of Animal?", "Add",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsAnimal, optionsAnimal[0]);

		switch (resultEmployee) {
		case 0:
			addDolphin();
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
	
	private void addOptionZone() {
		String[] optionsZone = { "Aquarium", "Swamp", "Savannah"};
		int resultEmployee = JOptionPane.showOptionDialog(null, "What kind of Zone?", "Add",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsZone, optionsZone[0]);

		switch (resultEmployee) {
		case 0:
			addAquarium();
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
	
	private void addAquarium() {
		JTextField extension = new JTextField();
		JTextField animalsNumber= new JTextField();
		JTextField speciesNumber = new JTextField();
		JTextField waterTemp = new JTextField();
		
		Object[] message = { "Extension: *", extension, "Animals Number: *", animalsNumber, "Species Number: *", speciesNumber,
				"Water Temperature: *", waterTemp};
		
		int option = JOptionPane.showConfirmDialog(null, message, "Registrar Zona", JOptionPane.OK_CANCEL_OPTION);
		
		if (option == JOptionPane.OK_OPTION) {
			if (extension.getText().isEmpty() || animalsNumber.getText().isEmpty() || speciesNumber.getText().isEmpty()
					|| waterTemp.getText().isEmpty()){
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
				
				Aquarium aquariumToInsert = new Aquarium(extensionFloat, animalsNumberFloat, speciesNumberInt, waterTempInt);
				
				ManagerAquarium managerAquarium = new ManagerAquarium();
				try {
					managerAquarium.insert(aquariumToInsert);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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

	public void addDolphin() {
		JTextField name = new JTextField();
		JTextField scientificName = new JTextField();
		JTextField height = new JTextField();
		JTextField weight = new JTextField();
		JTextField bornDate = new JTextField();
		JTextField vaccinated = new JTextField();
		JComboBox diet = new JComboBox();
		diet.addItem("Carnivorous");
		diet.addItem("Herbivorous");
		JTextField animalType = new JTextField();
		JTextField durationUnderWater = new JTextField();

		Object[] message = { "Name: *", name, "ScientificName: *", scientificName, "Height: *", height, "Weight: *",
				weight, "Born-Date *", bornDate, "Vacinated: *", vaccinated, "Diet: *", diet, "Animal-Tipe: *",
				animalType, "Duration Under Water: *", durationUnderWater };

		int option = JOptionPane.showConfirmDialog(null, message, "Registrar Cliente", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (name.getText().isEmpty() || scientificName.getText().isEmpty() || height.getText().isEmpty()
					|| weight.getText().isEmpty() || bornDate.getText().isEmpty() || vaccinated.getText().isEmpty()
					|| animalType.getText().isEmpty() || durationUnderWater.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Faltan datos", null, JOptionPane.ERROR_MESSAGE);
			} else {
//				try {
				ManagerDolphin managerDolphin = new ManagerDolphin();
				String heightString = height.getText();
				float heightFloat = Float.valueOf(heightString);
				String weightString = weight.getText();
				float weightFloat = Float.valueOf(weightString);
				String dateString = bornDate.getText();
				Date date = new Date(dateString);
//					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//			        String formattedDate = simpleDateFormat.format(DateString);
//			        System.out.println(formattedDate);
//			        java.sql.Date date = java.sql.Date.valueOf(formattedDate);
				System.out.println(date);
				String vaccinatedString = vaccinated.getText();
				int vaccinatedInt = checkBooleanTwo(vaccinatedString);
				String durationUnderWaterString = vaccinated.getText();
				int durationUnderWaterInt = Integer.valueOf(durationUnderWaterString);

				Dolphin dolphinToIsert = new Dolphin(0, name.getText(), scientificName.getText(), heightFloat,
						weightFloat, date, vaccinatedInt, diet.getSelectedItem().toString(), animalType.getText(),
						durationUnderWaterInt);
				System.out.println(dolphinToIsert);
				System.out.println("Fin");

				managerDolphin.insert(dolphinToIsert);
//				} 
//					catch (Exception e) {
//					JOptionPane.showMessageDialog(null, "Datos Erroneos", null, JOptionPane.ERROR_MESSAGE);
//				}
			}
		}

	}

	public void addSnake() {
		JTextField name = new JTextField();
		JTextField scientificName = new JTextField();
		JTextField height = new JTextField();
		JTextField weight = new JTextField();
		JTextField bornDate = new JTextField();
		JTextField vaccinated = new JTextField();
		JComboBox diet = new JComboBox();
		diet.addItem("Carnivorous");
		diet.addItem("Herbivorous");
		JTextField shedSkin = new JTextField();
		JTextField poisonus = new JTextField();

		Object[] message = { "Name: *", name, "ScientificName: *", scientificName, "Height: *", height, "Weight: *",
				weight, "Born-Date *", bornDate, "Vacinated: *", vaccinated, "Diet: *", diet, "ShedSkin: *", shedSkin,
				"Poisonus: *", poisonus };

		int option = JOptionPane.showConfirmDialog(null, message, "Registrar Cliente", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if (name.getText().isEmpty() || scientificName.getText().isEmpty() || height.getText().isEmpty()
					|| weight.getText().isEmpty() || bornDate.getText().isEmpty() || vaccinated.getText().isEmpty()
					|| shedSkin.getText().isEmpty() || poisonus.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Faltan datos", null, JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					ManagerSnake managerSnake = new ManagerSnake();
					String heightString = height.getText();
					float heightFloat = Float.valueOf(heightString);
					String weightString = weight.getText();
					float weightFloat = Float.valueOf(weightString);
					String DateBorn = bornDate.getText();
					SimpleDateFormat formatBorn = new SimpleDateFormat("dd/MM/yyyy");
					java.util.Date dateBorn = formatBorn.parse(DateBorn);
					String vaccinatedString = vaccinated.getText();
					int vaccinatedInt = checkBooleanTwo(vaccinatedString);
					String zoneIdString = vaccinated.getText();
					String DateSkin = shedSkin.getText();
					SimpleDateFormat formatSkin = new SimpleDateFormat("dd/MM/yyyy");
					java.util.Date dateSkin = formatSkin.parse(DateSkin);
					int zoneIdInt = Integer.valueOf(zoneIdString);
					String poisonusString = poisonus.getText();
					int poisonusInt = checkBooleanTwo(poisonusString);

//					Snake snakeToIsert = new Snake(0, name.getText(), scientificName.getText(), heightFloat,
//							weightFloat, dateBorn, vaccinatedInt, diet.getSelectedItem().toString(), dateSkin,
//							zoneIdInt, poisonusInt, 0);
//					managerSnake.insertSnake(snakeToIsert);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Datos Erroneos", null, JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}
	
}
