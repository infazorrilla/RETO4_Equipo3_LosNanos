package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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

import Controller.Controller;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class Interfaz {

	private JFrame frame;
	private JTextField usrTfLogin;
	private JPasswordField passTfLogin;
	private JTable table;
	private String type;
	private JComboBox cbBossZones;

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
		jpLogin.setBounds(0, 0, 848, 501);
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
		personModel(model);

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

		passTfLogin = new JPasswordField();
		passTfLogin.setForeground(new Color(255, 255, 255));
		passTfLogin.setBackground(new Color(201, 190, 190));
		passTfLogin.setHorizontalAlignment(SwingConstants.CENTER);
		passTfLogin.setFont(new Font("Arial", Font.PLAIN, 18));
		passTfLogin.setText("");
		passTfLogin.setColumns(10);
		passTfLogin.setBounds(285, 276, 265, 31);
		jpLogin.add(passTfLogin);

		JButton regTfLogin = new JButton("Register");
		regTfLogin.setForeground(new Color(255, 255, 255));
		regTfLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller controller = new Controller();
				controller.clientRegister();
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
				Controller controller = new Controller();
				String user = usrTfLogin.getText();
				String password = passTfLogin.getText();
				int finalUser = controller.checkUser(user, password);

				switch (finalUser) {
				case 0:
					panelLogin(jpLogin, jpClient, jpBoss, jpFeeder, jpVet);
					JOptionPane.showMessageDialog(null, "Contraseña Erronea", null, JOptionPane.ERROR_MESSAGE);
					break;
				case 1:
					panelBoss(jpLogin, jpClient, jpBoss, jpFeeder, jpVet);
					controller.getTableEmployee(model, table);
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
				
				controller.getDolphinId();
				
				usrTfLogin.setText("");
				passTfLogin.setText("");


			}
		});
		enterTfLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		enterTfLogin.setBounds(475, 376, 118, 42);
		jpLogin.add(enterTfLogin);

		JLabel backgroundLogin = new JLabel("");
		backgroundLogin.setBounds(0, 0, 848, 503);
		jpLogin.add(backgroundLogin);
		addImage(jpLogin, backgroundLogin, "src/Photos/panda.jpg");

		JLabel lblNewLabel_3 = new JLabel("Vet");
		lblNewLabel_3.setBounds(401, 231, 45, 13);
		jpVet.add(lblNewLabel_3);
		
		JButton btnBossLogout_1 = new JButton("LogOut");
		btnBossLogout_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller controller = new Controller();
				controller.questionLogOut(jpBoss, jpLogin, jpClient, jpFeeder, jpVet);
			}
		});
		btnBossLogout_1.setForeground(Color.WHITE);
		btnBossLogout_1.setBackground(Color.BLACK);
		btnBossLogout_1.setBounds(0, 0, 89, 23);
		jpVet.add(btnBossLogout_1);

		JLabel lblNewLabel_2 = new JLabel("Feeder");
		lblNewLabel_2.setBounds(396, 92, 45, 13);
		jpFeeder.add(lblNewLabel_2);
		
		JButton btnBossLogout_2 = new JButton("LogOut");
		btnBossLogout_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller controller = new Controller();
				controller.questionLogOut(jpBoss, jpLogin, jpClient, jpFeeder, jpVet);
			}
		});
		btnBossLogout_2.setForeground(Color.WHITE);
		btnBossLogout_2.setBackground(Color.BLACK);
		btnBossLogout_2.setBounds(0, 0, 89, 23);
		jpFeeder.add(btnBossLogout_2);

		JLabel lblNewLabel_1 = new JLabel("Client");
		lblNewLabel_1.setBounds(383, 159, 45, 13);
		jpClient.add(lblNewLabel_1);
		
		JButton btnBossLogout_3 = new JButton("LogOut");
		btnBossLogout_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller controller = new Controller();
				controller.questionLogOut(jpBoss, jpLogin, jpClient, jpFeeder, jpVet);
			}
		});
		btnBossLogout_3.setForeground(Color.WHITE);
		btnBossLogout_3.setBackground(Color.BLACK);
		btnBossLogout_3.setBounds(0, 0, 89, 23);
		jpClient.add(btnBossLogout_3);
		
		JButton btnBossLogout = new JButton("LogOut");
		btnBossLogout.setForeground(new Color(255, 255, 255));
		btnBossLogout.setBackground(new Color(0, 0, 0));
		btnBossLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Controller controller = new Controller();
				controller.questionLogOut(jpBoss, jpLogin, jpClient, jpFeeder, jpVet);
				
			}
		});
		btnBossLogout.setBounds(749, 11, 89, 23);
		jpBoss.add(btnBossLogout);

		JScrollPane spEmployeeBoss = new JScrollPane();
		spEmployeeBoss.setBounds(31, 102, 640, 188);
		jpBoss.add(spEmployeeBoss);

		table = new JTable();
		table.setFillsViewportHeight(true);
		spEmployeeBoss.setViewportView(table);
		table.setModel(model);

		JLabel lbBossTitle = new JLabel("Boss");
		lbBossTitle.setForeground(new Color(255, 255, 255));
		lbBossTitle.setFont(new Font("Arial", Font.BOLD, 39));
		lbBossTitle.setBounds(367, 26, 107, 56);
		jpBoss.add(lbBossTitle);

		JButton btnBossDelete = new JButton("Delete");
		btnBossDelete.setEnabled(false);
		btnBossDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tm = (DefaultTableModel) table.getModel();
				String id = String.valueOf(tm.getValueAt(table.getSelectedRow(), 2));
				Controller controller = new Controller();
				controller.questionSure(type, id);
			}
		});
		btnBossDelete.setBackground(new Color(235, 199, 150));
		btnBossDelete.setForeground(new Color(255, 255, 255));
		btnBossDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBossDelete.setBounds(696, 253, 121, 37);
		jpBoss.add(btnBossDelete);

		JButton btnBossSeeBoss = new JButton("Boss");
		btnBossSeeBoss.setBackground(new Color(235, 199, 150));
		btnBossSeeBoss.setForeground(new Color(255, 255, 255));
		btnBossSeeBoss.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller controller = new Controller();
				model.setRowCount(0);
				model.setColumnCount(0);
				personModel(model);
				controller.getTableBoss(model, table);
				btnBossDelete.setEnabled(true);
				type = "Boss";

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
				Controller controller = new Controller();
				model.setRowCount(0);
				model.setColumnCount(0);
				personModel(model);
				controller.getTableFeeder(model, table);
				btnBossDelete.setEnabled(true);
				type = "Feeder";
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
				Controller controller = new Controller();
				model.setRowCount(0);
				model.setColumnCount(0);
				personModel(model);
				controller.getTableVet(model, table);
				btnBossDelete.setEnabled(true);
				type = "Vet";
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
				Controller controller = new Controller();
				controller.addOption();
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

		JButton btnBossSeeClient = new JButton("Client");
		btnBossSeeClient.setBackground(new Color(235, 199, 150));
		btnBossSeeClient.setForeground(new Color(255, 255, 255));
		btnBossSeeClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller controller = new Controller();
				model.setRowCount(0);
				model.setColumnCount(0);
				personModel(model);
				controller.getTableClient(model, table);
				btnBossDelete.setEnabled(true);
				type = "Client";
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

		cbBossZones = new JComboBox();
		cbBossZones.addItem("Aquarium");
		cbBossZones.addItem("Swamp");
		cbBossZones.addItem("Savannah");
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
				Controller controller = new Controller();
				String box = (String) cbBossAnimals.getSelectedItem();
				model.setRowCount(0);
				model.setColumnCount(0);
				switch (box) {
				case "Dolphin":
					dolphinModel(model);
					controller.getSelectedDolphin(box, model, table);
					break;
				case "Snake":
					snakeModel(model);
					controller.getSelectedSnake(box, model, table);
					break;
				case "Crocodile":
//					crocodileModel(model);
				case "Giraffe":
//					giraffeModel(model);
				case "Cheetah":
//					cheetahModel(model);
				}

			}

		});
		btBossSeeAnimal.setBackground(new Color(235, 199, 150));
		btBossSeeAnimal.setForeground(new Color(255, 255, 255));
		btBossSeeAnimal.setBounds(172, 443, 85, 21);
		jpBoss.add(btBossSeeAnimal);

		JButton btBossSeeZone = new JButton("Search");
		btBossSeeZone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller controller = new Controller();
				String box = (String) cbBossZones.getSelectedItem();
				model.setRowCount(0);
				model.setColumnCount(0);
				switch (box) {
				case "Aquarium":
					dolphinModel(model);
					controller.getTableAquarium( model, table);
					break;
				case "Swamp":
					snakeModel(model);
					controller.getTableSwamp( model, table);
					break;
				case "Savannah":
					snakeModel(model);
					controller.getTableSavannah( model, table);
					break;
				}
			}
		});
		btBossSeeZone.setForeground(new Color(255, 255, 255));
		btBossSeeZone.setBackground(new Color(235, 199, 150));
		btBossSeeZone.setBounds(473, 443, 85, 21);
		jpBoss.add(btBossSeeZone);

		JLabel lbBossBackground = new JLabel("");
		lbBossBackground.setBounds(-59, 0, 924, 501);
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
		model.addColumn("shed-Skin");
	}

	private void personModel(DefaultTableModel model) {
		model.addColumn("Name");
		model.addColumn("Surname");
		model.addColumn("Id");
		model.addColumn("User");
		model.addColumn("Password");
		model.addColumn("Special");
	}
	
	private void zoneModel(DefaultTableModel model) {
		model.addColumn("Id");
		model.addColumn("Extension");
		model.addColumn("animalsNumber");
		model.addColumn("SpeciesNumber");
		if(cbBossZones.getSelectedItem().equals("Aquarium")) {
			model.addColumn("waterTemp");
		} else if (cbBossZones.getSelectedItem().equals("Swamp")) {
			model.addColumn("waterSurface");
		} else if (cbBossZones.getSelectedItem().equals("Savannah")) {
			model.addColumn("Tree Number");
		}
	}

	private void addImage(JPanel panel, JLabel label, String path) {
		ImageIcon icon = new ImageIcon(path);
		Image img = icon.getImage();
		Image resizedImg = img.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);
		icon.setImage(resizedImg);
		label.setIcon(icon);
	}
}
