package Controler;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Manager.ManagerBoss;
import Manager.ManagerClient;
import Pojos.Person.Boss;
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
<<<<<<< HEAD

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

	public void checkLogin(String user, String password) {
		ManagerBoss managerBoss = new ManagerBoss();

		ArrayList<Boss> boss = managerBoss.getBoss();
		for (int i = 0; i < boss.size(); i++) {
			String userBoss = boss.getUser();
=======
	
	
		if (option == JOptionPane.OK_OPTION) {
	      JOptionPane.showMessageDialog(null, "You chose yes!");
	    }
	}
>>>>>>> branch 'main' of https://github.com/infazorrilla/RETO4_Equipo3_LosNanos.git
}
		 
	}
}












