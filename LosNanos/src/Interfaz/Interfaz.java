package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

import Controler.Controler;

public class Interfaz {

	private JFrame frame;
	private JTextField usrTfLogin;
	private JTextField passTfLogin;

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
		
		
		JPanel jpBoss = new JPanel();
		jpBoss.setBounds(0, 0, 848, 501);
		frame.getContentPane().add(jpBoss);
		jpBoss.setLayout(null);
		
		JPanel jpClient = new JPanel();
		jpClient.setBounds(0, 0, 848, 501);
		frame.getContentPane().add(jpClient);
		jpClient.setLayout(null);
		
		JPanel jpFeeder = new JPanel();
		jpFeeder.setBounds(0, 0, 848, 501);
		frame.getContentPane().add(jpFeeder);
		jpFeeder.setLayout(null);
		
		JPanel jpVet = new JPanel();
		jpVet.setBounds(0, 0, 848, 501);
		frame.getContentPane().add(jpVet);
		jpVet.setLayout(null);
		
		JLabel Title = new JLabel("Zoo");
		Title.setForeground(new Color(255, 255, 255));
		Title.setFont(new Font("Tahoma", Font.BOLD, 60));
		Title.setBounds(352, 23, 118, 84);
		jpLogin.add(Title);
		
		usrTfLogin = new JTextField();
		usrTfLogin.setForeground(new Color(255, 255, 255));
		usrTfLogin.setBackground(new Color(201, 190, 190));
		usrTfLogin.setText("User");
		usrTfLogin.setToolTipText("User");
		usrTfLogin.setHorizontalAlignment(SwingConstants.CENTER);
		usrTfLogin.setFont(new Font("Arial", Font.PLAIN, 20));
		usrTfLogin.setBounds(285, 194, 265, 31);
		jpLogin.add(usrTfLogin);
		usrTfLogin.setColumns(10);
		
		passTfLogin = new JTextField();
		passTfLogin.setForeground(new Color(255, 255, 255));
		passTfLogin.setBackground(new Color(201, 190, 190));
		passTfLogin.setHorizontalAlignment(SwingConstants.CENTER);
		passTfLogin.setFont(new Font("Arial", Font.PLAIN, 18));
		passTfLogin.setText("Password");
		passTfLogin.setColumns(10);
		passTfLogin.setBounds(285, 276, 265, 31);
		jpLogin.add(passTfLogin);
		
		JButton regTfLogin = new JButton("Register");
		regTfLogin.setForeground(new Color(255, 255, 255));
		regTfLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controler controler = new Controler();
				controler.clientRegister();
			}
		});
		regTfLogin.setBackground(new Color(201, 190, 190));
		regTfLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		regTfLogin.setBounds(227, 376, 118, 42);
		jpLogin.add(regTfLogin);
		
		JButton enterTfLogin = new JButton("Enter");
		enterTfLogin.setForeground(new Color(255, 255, 255));
		enterTfLogin.setBackground(new Color(201, 190, 190));
		enterTfLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controler controler = new Controler();
				String user = usrTfLogin.getText();
				String password = passTfLogin.getText();
				int finalUser = controler.checkUser(user, password);
				
				switch (finalUser) {
				case 0:
					panelLogin(jpLogin, jpClient, jpBoss, jpFeeder, jpVet);
					JOptionPane.showMessageDialog(null, "Contraseña Erronea", null, JOptionPane.ERROR_MESSAGE);
					break;
				case 1:
					panelBoss(jpLogin, jpClient, jpBoss, jpFeeder, jpVet);
					break;
				case 2:
					panelClient(jpLogin, jpClient, jpBoss, jpFeeder, jpVet);
					break;
				case 3:
					panelFeeder(jpLogin, jpClient, jpBoss, jpFeeder, jpVet);
					break;
				case 4:
					panelVet(jpLogin, jpClient, jpBoss, jpFeeder, jpVet);
					break;
				}
				
			}
		});
		enterTfLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		enterTfLogin.setBounds(475, 376, 118, 42);
		jpLogin.add(enterTfLogin);
		
		JLabel backgroundLogin = new JLabel("");
		backgroundLogin.setBounds(0, 0, 848, 491);
		jpLogin.add(backgroundLogin);
		addImage(jpLogin, backgroundLogin, "src/Photos/panda.jpg");
		
		JLabel lblNewLabel_3 = new JLabel("Vet");
		lblNewLabel_3.setBounds(401, 231, 45, 13);
		jpVet.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("Feeder");
		lblNewLabel_2.setBounds(396, 92, 45, 13);
		jpFeeder.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Client");
		lblNewLabel_1.setBounds(383, 159, 45, 13);
		jpClient.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Boss");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(367, 114, 189, 101);
		jpBoss.add(lblNewLabel);
		

		

	}
	
	public void panelBoss(JPanel jpLogin, JPanel jpClient, JPanel jpBoss, JPanel jpFeeder, JPanel jpVet) {
		jpBoss.setVisible(true);
		jpClient.setVisible(false);
		jpFeeder.setVisible(false);
		jpVet.setVisible(false);
		jpLogin.setVisible(false);
	}
	
	public void panelClient(JPanel jpLogin, JPanel jpClient, JPanel jpBoss, JPanel jpFeeder, JPanel jpVet) {
		jpBoss.setVisible(false);
		jpClient.setVisible(true);
		jpFeeder.setVisible(false);
		jpVet.setVisible(false);
		jpLogin.setVisible(false);

		
	}
	
	public void panelFeeder(JPanel jpLogin, JPanel jpClient, JPanel jpBoss, JPanel jpFeeder, JPanel jpVet) {
		jpBoss.setVisible(false);
		jpClient.setVisible(false);
		jpFeeder.setVisible(true);
		jpVet.setVisible(false);
		jpLogin.setVisible(false);

		
	}
	
	public void panelVet(JPanel jpLogin, JPanel jpClient, JPanel jpBoss, JPanel jpFeeder, JPanel jpVet) {
		jpBoss.setVisible(false);
		jpClient.setVisible(false);
		jpFeeder.setVisible(false);
		jpVet.setVisible(true);
		jpLogin.setVisible(false);

		
	}
	
	public void panelLogin(JPanel jpLogin, JPanel jpClient, JPanel jpBoss, JPanel jpFeeder, JPanel jpVet) {
		jpBoss.setVisible(false);
		jpClient.setVisible(false);
		jpFeeder.setVisible(false);
		jpVet.setVisible(false);
		jpLogin.setVisible(true);

		
	}
	
	
	private void addImage(JPanel panel, JLabel label, String path) {
		ImageIcon icon = new ImageIcon(path);
		Image img = icon.getImage();
		Image resizedImg = img.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);
		icon.setImage(resizedImg);
		label.setIcon(icon);
	}
	
	
	
	
}
