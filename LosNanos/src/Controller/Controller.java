package Controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Manager.Animals.ManagerDolphin;
import Manager.Animals.ManagerSnake;
import Manager.People.ManagerBoss;
import Manager.People.ManagerClient;
import Manager.People.ManagerFeeder;
import Manager.People.ManagerVet;
import Pojos.Person.Client;

public class Controller {

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
			model.addRow(new String[] { managerClient.getClient().get(i).getName(),
					managerClient.getClient().get(i).getSurname(), managerClient.getClient().get(i).getId(),
					managerClient.getClient().get(i).getUser(), managerClient.getClient().get(i).getPassword() });
		}

	}



	public void getSelectedDolphin(String box, DefaultTableModel model, JTable table) {
		ManagerDolphin managerDolphin = new ManagerDolphin();
		for (int i = 0; i < managerDolphin.getDolphin().size(); i++) {
			int idInt = managerDolphin.getDolphin().get(i).getId();
			String id = String.valueOf(idInt);
			float heightInt = managerDolphin.getDolphin().get(i).getHeight();
			String height = String.valueOf(heightInt);
			float weightInt = managerDolphin.getDolphin().get(i).getWeight();
			String weight = String.valueOf(weightInt);
			int zoneInt = managerDolphin.getDolphin().get(i).getId();
			String zoneId = String.valueOf(zoneInt);
			int bolean = managerDolphin.getDolphin().get(i).getVaccinated();
			String vaccinated = checkBoolean(bolean);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			String date = sdf.format(managerDolphin.getDolphin().get(i).getBornDate());
			
			model.addRow(new String[] { id, managerDolphin.getDolphin().get(i).getName(),
					managerDolphin.getDolphin().get(i).getScientificName(), height, weight, date, vaccinated,
					managerDolphin.getDolphin().get(i).getDiet(), zoneId, managerDolphin.getDolphin().get(i).getAnimalTipe() });
		}

	}
	
	public void getSelectedSnake(String box, DefaultTableModel model, JTable table) {
		ManagerSnake managerSnake = new ManagerSnake();
		for (int i = 0; i < managerSnake.getSnake().size(); i++) {
			int idInt = managerSnake.getSnake().get(i).getId();
			String id = String.valueOf(idInt);
			float heightInt = managerSnake.getSnake().get(i).getHeight();
			String height = String.valueOf(heightInt);
			float weightInt = managerSnake.getSnake().get(i).getWeight();
			String weight = String.valueOf(weightInt);
			int zoneInt = managerSnake.getSnake().get(i).getId();
			String zoneId = String.valueOf(zoneInt);
			int bolean = managerSnake.getSnake().get(i).getVaccinated();
			String vaccinated = checkBoolean(bolean);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			String date = sdf.format(managerSnake.getSnake().get(i).getBornDate());
			
			model.addRow(new String[] { id, managerSnake.getSnake().get(i).getName(),
					managerSnake.getSnake().get(i).getScientificName(), height, weight, date, vaccinated,
					managerSnake.getSnake().get(i).getDiet(), zoneId, managerSnake.getSnake().get(i).getShedSkin() });
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
	
	public void addOption() {
		  String[] options = {"Employee", "Animal"}; 
          int result = JOptionPane.showOptionDialog(
             null,
             "What do you want to add?", 
             "Add",            
             JOptionPane.YES_NO_OPTION,
             JOptionPane.QUESTION_MESSAGE,
             null,     
             options,  
             options[0] 
          );
          if (result == 0) {
        	  String[] optionsEmployee = {"Boss", "Fedeer", "Vet"}; 
              int resultEmployee = JOptionPane.showOptionDialog(
                 null,
                 "What kind of employee?", 
                 "Add",            
                 JOptionPane.YES_NO_OPTION,
                 JOptionPane.QUESTION_MESSAGE,
                 null,     
                 optionsEmployee,
                 options[0] 
              );
        }
	}
}