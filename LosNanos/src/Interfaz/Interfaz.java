package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Interfaz {

	private JFrame frame;
	private JTextField txtUser;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 862, 538);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel jpLogin = new JPanel();
		jpLogin.setBounds(0, 10, 848, 491);
		frame.getContentPane().add(jpLogin);
		jpLogin.setLayout(null);
		
		JLabel Title = new JLabel("Zoo");
		Title.setFont(new Font("Tahoma", Font.BOLD, 60));
		Title.setBounds(352, 23, 118, 84);
		jpLogin.add(Title);
		
		txtUser = new JTextField();
		txtUser.setText("User");
		txtUser.setToolTipText("User");
		txtUser.setHorizontalAlignment(SwingConstants.CENTER);
		txtUser.setFont(new Font("Arial", Font.PLAIN, 20));
		txtUser.setBounds(285, 194, 265, 31);
		jpLogin.add(txtUser);
		txtUser.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.setFont(new Font("Arial", Font.PLAIN, 18));
		txtPassword.setText("Password");
		txtPassword.setColumns(10);
		txtPassword.setBounds(285, 276, 265, 31);
		jpLogin.add(txtPassword);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.setBackground(new Color(240, 240, 240));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(227, 376, 118, 42);
		jpLogin.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Enter");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(475, 376, 118, 42);
		jpLogin.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 848, 491);
		jpLogin.add(lblNewLabel);
		
		addImage(jpLogin, lblNewLabel, "Photos/panda.jpg");
		

		

	}
	
	private void addImage(JPanel panel, JLabel label, String path) {
		ImageIcon icon = new ImageIcon(path);
		Image img = icon.getImage();
		Image resizedImg = img.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);
		icon.setImage(resizedImg);
		label.setIcon(icon);
	}
	
	
	
	
}
