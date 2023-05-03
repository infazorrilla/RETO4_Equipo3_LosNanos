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
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

import Controler.Controler;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class Interfaz {

	private JFrame frame;
	private JTextField usrTfLogin;
	private JTextField passTfLogin;
	private JTable table;

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
		jpLogin.setVisible(true);

		JPanel jpClient = new JPanel();
		jpClient.setBounds(0, 0, 848, 501);
		frame.getContentPane().add(jpClient);
		jpClient.setLayout(null);
		jpClient.setVisible(false);

		JPanel jpFeeder = new JPanel();
		jpFeeder.setBounds(0, 0, 848, 501);
		frame.getContentPane().add(jpFeeder);
		jpFeeder.setLayout(null);
		jpFeeder.setVisible(false);

		JPanel jpVet = new JPanel();
		jpVet.setBounds(0, 0, 848, 501);
		frame.getContentPane().add(jpVet);
		jpVet.setLayout(null);
		jpVet.setVisible(false);

		JPanel jpBoss = new JPanel();
		jpBoss.setBounds(0, 0, 848, 501);
		frame.getContentPane().add(jpBoss);
		jpBoss.setLayout(null);
		jpBoss.setVisible(false);


		final DefaultTableModel model;
		model = new DefaultTableModel();

		model.addColumn("Name");
		model.addColumn("Surname");
		model.addColumn("Id");
		model.addColumn("User");
		model.addColumn("Password");
		model.addColumn("Special");

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
					controler.getTableEmployee(model, table);
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



		JScrollPane spEmployeeBoss = new JScrollPane();
		spEmployeeBoss.setBounds(31, 102, 640, 188);
		jpBoss.add(spEmployeeBoss);

		table = new JTable();
		table.setEnabled(false);
		spEmployeeBoss.setViewportView(table);
		table.setModel(model);

		JLabel lbBossTitle = new JLabel("Boss");
		lbBossTitle.setForeground(new Color(255, 255, 255));
		lbBossTitle.setFont(new Font("Arial", Font.BOLD, 39));
		lbBossTitle.setBounds(367, 26, 107, 56);
		jpBoss.add(lbBossTitle);

		JButton btnBossSeeBoss = new JButton("Boss");
		btnBossSeeBoss.setBackground(new Color(235, 199, 150));
		btnBossSeeBoss.setForeground(new Color(255, 255, 255));
		btnBossSeeBoss.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controler controler = new Controler();
				model.setRowCount(0);
				controler.getTableBoss(model, table);
			}
		});
		btnBossSeeBoss.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBossSeeBoss.setBounds(78, 311, 121, 37);
		jpBoss.add(btnBossSeeBoss);

		JButton btnBossSeeFedeer = new JButton("Feeder");
		btnBossSeeFedeer.setBackground(new Color(235, 199, 150));
		btnBossSeeFedeer.setForeground(new Color(255, 255, 255));
		btnBossSeeFedeer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controler controler = new Controler();
				model.setRowCount(0);
				controler.getTableFeeder(model, table);

			}
		});
		btnBossSeeFedeer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBossSeeFedeer.setBounds(229, 311, 121, 37);
		jpBoss.add(btnBossSeeFedeer);

		JButton btnBossSeeVet = new JButton("Vet");
		btnBossSeeVet.setBackground(new Color(235, 199, 150));
		btnBossSeeVet.setForeground(new Color(255, 255, 255));
		btnBossSeeVet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controler controler = new Controler();
				model.setRowCount(0);
				controler.getTableVet(model, table);
			}
		});
		btnBossSeeVet.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBossSeeVet.setBounds(377, 311, 121, 37);
		jpBoss.add(btnBossSeeVet);

		JButton btnBossAddEmployee = new JButton("Add");
		btnBossAddEmployee.setBackground(new Color(235, 199, 150));
		btnBossAddEmployee.setForeground(new Color(255, 255, 255));
		btnBossAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBossAddEmployee.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBossAddEmployee.setBounds(696, 102, 121, 37);
		jpBoss.add(btnBossAddEmployee);

		JButton btnBossUpdateEmployee = new JButton("Update");
		btnBossUpdateEmployee.setBackground(new Color(235, 199, 150));
		btnBossUpdateEmployee.setForeground(new Color(255, 255, 255));
		btnBossUpdateEmployee.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBossUpdateEmployee.setBounds(696, 178, 121, 37);
		jpBoss.add(btnBossUpdateEmployee);

		JButton btnBossDeleteEmployee = new JButton("Delete");
		btnBossDeleteEmployee.setBackground(new Color(235, 199, 150));
		btnBossDeleteEmployee.setForeground(new Color(255, 255, 255));
		btnBossDeleteEmployee.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBossDeleteEmployee.setBounds(696, 253, 121, 37);
		jpBoss.add(btnBossDeleteEmployee);

		JButton btnBossSeeClient = new JButton("Client");
		btnBossSeeClient.setBackground(new Color(235, 199, 150));
		btnBossSeeClient.setForeground(new Color(255, 255, 255));
		btnBossSeeClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controler controler = new Controler();
				model.setRowCount(0);
				controler.getTableClient(model, table);
			}
		});
		btnBossSeeClient.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBossSeeClient.setBounds(524, 311, 121, 37);
		jpBoss.add(btnBossSeeClient);

		JComboBox cbBossAnimals = new JComboBox();
		cbBossAnimals.addItem("Dolphin");
		cbBossAnimals.addItem("Snake");
		cbBossAnimals.addItem("Cocodrile");
		cbBossAnimals.addItem("Giraffe");
		cbBossAnimals.addItem("Cheetah");
		cbBossAnimals.setBackground(new Color(235, 199, 150));
		cbBossAnimals.setForeground(new Color(255, 255, 255));
		cbBossAnimals.setBounds(78, 412, 272, 21);
		jpBoss.add(cbBossAnimals);

		JLabel lblBossSeeAnimal = new JLabel("Animals");
		lblBossSeeAnimal.setForeground(new Color(255, 255, 255));
		lblBossSeeAnimal.setFont(new Font("Arial", Font.BOLD, 20));
		lblBossSeeAnimal.setBounds(181, 380, 76, 21);
		jpBoss.add(lblBossSeeAnimal);

		JComboBox cbBossZones = new JComboBox();
		cbBossZones.setBackground(new Color(235, 199, 150));
		cbBossZones.setForeground(new Color(255, 255, 255));
		cbBossZones.setBounds(367, 412, 272, 21);
		jpBoss.add(cbBossZones);

		JLabel lblBossSeeZone = new JLabel("Zones");
		lblBossSeeZone.setForeground(new Color(255, 255, 255));
		lblBossSeeZone.setFont(new Font("Arial", Font.BOLD, 20));
		lblBossSeeZone.setBounds(486, 380, 58, 21);
		jpBoss.add(lblBossSeeZone);

		JButton btBossSeeAnimal = new JButton("Search");
		btBossSeeAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controler controler = new Controler();
				String box = (String)cbBossAnimals.getSelectedItem();
				model.setRowCount(0);
				model.setColumnCount(0);
				switch (box) {
				case "Dolphin":
					dolphinModel(model);
					break;
				case "Snake":
					snakeModel(model);
					break;
				case "Crocodile":
//					crocodileModel(model);
				case "Giraffe":
//					giraffeModel(model);
				case "Cheetah":
//					cheetahModel(model);
				}
				model.setRowCount(0);
				model.setColumnCount(0);
				controler.getSelectedAnimal(box, model, table);
			}


		});
		btBossSeeAnimal.setBackground(new Color(235, 199, 150));
		btBossSeeAnimal.setForeground(new Color(255, 255, 255));
		btBossSeeAnimal.setBounds(172, 443, 85, 21);
		jpBoss.add(btBossSeeAnimal);

		JButton btBossSeeZone = new JButton("Search");
		btBossSeeZone.setForeground(new Color(255, 255, 255));
		btBossSeeZone.setBackground(new Color(235, 199, 150));
		btBossSeeZone.setBounds(473, 443, 85, 21);
		jpBoss.add(btBossSeeZone);

		JLabel lbBossBackground = new JLabel("");
		lbBossBackground.setBounds(0, 0, 848, 501);
		jpBoss.add(lbBossBackground);
		addImage(jpBoss, lbBossBackground, "src/Photos/lion.png");


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
	
	private void dolphinModel(DefaultTableModel model) {
		model.addColumn("Id");
		model.addColumn("Name");
		model.addColumn("ScientificName");
		model.addColumn("Height");
		model.addColumn("Weight");
		model.addColumn("Born-Date");
		model.addColumn("Vaccinated");				
		model.addColumn("Diet");	
		model.addColumn("ZoneId");
		model.addColumn("Animal-Tipe");	
	}
	
	private void snakeModel(DefaultTableModel model) {
		model.addColumn("Id");
		model.addColumn("Name");
		model.addColumn("ScientificName");
		model.addColumn("Height");
		model.addColumn("Weight");
		model.addColumn("Born-Date");
		model.addColumn("Vaccinated");				
		model.addColumn("Diet");	
		model.addColumn("ZoneId");
		model.addColumn("shed-Skin");	
	}

	private void addImage(JPanel panel, JLabel label, String path) {
		ImageIcon icon = new ImageIcon(path);
		Image img = icon.getImage();
		Image resizedImg = img.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);
		icon.setImage(resizedImg);
		label.setIcon(icon);
	}
}
