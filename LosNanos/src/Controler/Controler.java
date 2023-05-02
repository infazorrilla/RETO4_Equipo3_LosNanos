package Controler;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

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
	      JOptionPane.showMessageDialog(null, "You chose yes!");
	    }
	}
}
