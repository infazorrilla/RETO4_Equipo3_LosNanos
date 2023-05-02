package Controler;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Manager.People.ManagerBoss;
import Manager.People.ManagerClient;
import Manager.People.ManagerFeeder;
import Manager.People.ManagerVet;
import Pojos.Person.Client;

public class Controler {

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
					managerClient.insertClient(clientToIsert);
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
		for (int i = 0; i < managerBoss.getBoss().size(); i++) {
			String userBoss = managerBoss.getBoss().get(i).getUser();
			if (userBoss.equalsIgnoreCase(user)) {
				String passBoss = managerBoss.getBoss().get(i).getPassword();
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
		for (int i = 0; i < managerClient.getClient().size(); i++) {
			String userClient = managerClient.getClient().get(i).getUser();
			if (userClient.equalsIgnoreCase(user)) {
				String passClient = managerClient.getClient().get(i).getPassword();
				boolean pas = checkPassword(password, passClient);
				if (pas == true) {
					ret = 2;
				} else {
					ret = 0;
				}
			}
		}
		return ret;
	}

	public int checkFeeder(String user, String password) {
		int ret = 0;
		ManagerFeeder managerFeeder = new ManagerFeeder();
		for (int i = 0; i < managerFeeder.getFeeder().size(); i++) {
			String userFeeder = managerFeeder.getFeeder().get(i).getUser();
			if (userFeeder.equalsIgnoreCase(user)) {
				String passFeeder = managerFeeder.getFeeder().get(i).getPassword();
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
		for (int i = 0; i < managerVet.getVet().size(); i++) {
			String userVet = managerVet.getVet().get(i).getUser();
			if (userVet.equalsIgnoreCase(user)) {
				String passVet = managerVet.getVet().get(i).getPassword();
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
		for (int i = 0; i < managerBoss.getBoss().size(); i++) {
			String userBoss = managerBoss.getBoss().get(i).getUser();
			String passwordBoss = managerBoss.getBoss().get(i).getPassword();
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
		ManagerBoss managerBoss = new ManagerBoss();
		for (int i = 0; i < managerBoss.getBoss().size(); i++) {
			int empNumCharg = managerBoss.getBoss().get(i).getEmployeeNumCharge();
			String numCharge = String.valueOf(empNumCharg);
			model.addRow(new String[] { managerBoss.getBoss().get(i).getName(),
					managerBoss.getBoss().get(i).getSurname(), managerBoss.getBoss().get(i).getId(),
					managerBoss.getBoss().get(i).getUser(), managerBoss.getBoss().get(i).getPassword(), numCharge });
		}

	}

	public void getTableFeeder(DefaultTableModel model, JTable table) {
		ManagerFeeder managerFeeder = new ManagerFeeder();
		for (int i = 0; i < managerFeeder.getFeeder().size(); i++) {
			model.addRow(new String[] { managerFeeder.getFeeder().get(i).getName(),
					managerFeeder.getFeeder().get(i).getSurname(), managerFeeder.getFeeder().get(i).getId(),
					managerFeeder.getFeeder().get(i).getUser(), managerFeeder.getFeeder().get(i).getPassword(),
					managerFeeder.getFeeder().get(i).getSpecializedDiet() });
		}

	}

	public void getTableVet(DefaultTableModel model, JTable table) {
		ManagerVet managerVet = new ManagerVet();
		for (int i = 0; i < managerVet.getVet().size(); i++) {
			model.addRow(new String[] { managerVet.getVet().get(i).getName(), managerVet.getVet().get(i).getSurname(),
					managerVet.getVet().get(i).getId(), managerVet.getVet().get(i).getUser(),
					managerVet.getVet().get(i).getPassword(), managerVet.getVet().get(i).getSpecializedAnimalType() });
		}

	}

	public void getTableClient(DefaultTableModel model, JTable table) {
		ManagerClient managerClient = new ManagerClient();
		for (int i = 0; i < managerClient.getClient().size(); i++) {
			model.addRow(new String[] { managerClient.getClient().get(i).getName(), managerClient.getClient().get(i).getSurname(),
					managerClient.getClient().get(i).getId(), managerClient.getClient().get(i).getUser(),
					managerClient.getClient().get(i).getPassword() });
		}

	}
	
}
