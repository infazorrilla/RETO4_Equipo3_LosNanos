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
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

import Controller.Controller;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Interfaz {

	private JFrame frame;
	private JTextField usrTfLogin;
	private JPasswordField passTfLogin;
	public JTable tableBoss;
	private String type;
	private JComboBox<String> cbBossZones;
	private JButton btnBossUpdate;

	Controller controller = new Controller();
	private JButton btnBossDelete;
	private JTable tableVet;
	private JTable tableFeeder;
	private JTable tableClient;

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
		frame.setResizable(false);

		final DefaultTableModel model;
		model = new DefaultTableModel();

		JPanel jpLogin = new JPanel();
		jpLogin.setBounds(0, 0, 848, 501);
		frame.getContentPane().add(jpLogin);
		jpLogin.setLayout(null);
		jpLogin.setVisible(true);

		JPanel jpBoss = new JPanel();
		jpBoss.setBounds(0, 0, 848, 501);
		frame.getContentPane().add(jpBoss);
		jpBoss.setLayout(null);
		jpBoss.setVisible(false);

		JPanel jpVet = new JPanel();
		jpVet.setBounds(0, 0, 848, 501);
		frame.getContentPane().add(jpVet);
		jpVet.setLayout(null);
		jpVet.setVisible(false);

		JPanel jpFeeder = new JPanel();
		jpFeeder.setBounds(0, 0, 848, 501);
		frame.getContentPane().add(jpFeeder);
		jpFeeder.setVisible(false);
		jpFeeder.setLayout(null);

		JPanel jpClient = new JPanel();
		jpClient.setBounds(0, 0, 848, 501);
		frame.getContentPane().add(jpClient);
		jpClient.setLayout(null);
		jpClient.setVisible(false);

		JLabel Title = new JLabel("Zoo");
		Title.setForeground(new Color(255, 255, 255));
		Title.setFont(new Font("Tahoma", Font.BOLD, 60));
		Title.setBounds(352, 23, 118, 84);
		jpLogin.add(Title);

		usrTfLogin = new JTextField();
		usrTfLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (usrTfLogin.getText().length() >= 10) {
					e.consume();
				}
			}
		});
		usrTfLogin.setForeground(new Color(255, 255, 255));
		usrTfLogin.setBackground(new Color(201, 190, 190));
		usrTfLogin.setToolTipText("User");
		usrTfLogin.setHorizontalAlignment(SwingConstants.CENTER);
		usrTfLogin.setFont(new Font("Arial", Font.PLAIN, 20));
		usrTfLogin.setBounds(285, 194, 265, 31);
		jpLogin.add(usrTfLogin);
		usrTfLogin.setColumns(10);

		passTfLogin = new JPasswordField();
		passTfLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (passTfLogin.getText().length() >= 10) {
					e.consume();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
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
						personModel(model);
						controller.getTableEmployee(model, tableBoss);
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

					usrTfLogin.setText("");
					passTfLogin.setText("");

				}
			}
		});
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
					personModel(model);
					controller.getTableEmployee(model, tableBoss);
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

		JLabel lbClient = new JLabel("Client");
		lbClient.setFont(new Font("Arial", Font.BOLD, 39));
		lbClient.setBounds(343, 39, 114, 34);
		jpClient.add(lbClient);

		JButton btnClientLogout = new JButton("LogOut");
		btnClientLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller controller = new Controller();
				controller.questionLogOut(jpBoss, jpLogin, jpClient, jpFeeder, jpVet);
			}
		});
		btnClientLogout.setForeground(Color.WHITE);
		btnClientLogout.setBackground(Color.BLACK);
		btnClientLogout.setBounds(0, 0, 89, 23);
		jpClient.add(btnClientLogout);

		JScrollPane spClient = new JScrollPane();
		spClient.setBounds(45, 107, 755, 261);
		spClient.setOpaque(false);
		jpClient.add(spClient);

		tableClient = new JTable();
		spClient.setViewportView(tableClient);
		spClient.setViewportView(tableClient);
		tableClient.setEnabled(false);
		tableClient.setOpaque(false);
		tableClient.setModel(model);

		JComboBox<String> cbClientSeeAnimals = new JComboBox<String>();
		cbClientSeeAnimals.addItem("Dolphin");
		cbClientSeeAnimals.addItem("Snake");
		cbClientSeeAnimals.addItem("Crocodile");
		cbClientSeeAnimals.addItem("Giraffe");
		cbClientSeeAnimals.addItem("Cheetah");
		cbClientSeeAnimals.setForeground(Color.WHITE);
		cbClientSeeAnimals.setBackground(new Color(225, 238, 166));
		cbClientSeeAnimals.setBounds(45, 393, 370, 31);
		jpClient.add(cbClientSeeAnimals);

		JButton btnClientSearch = new JButton("Search");
		btnClientSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller controller = new Controller();
				String box = (String) cbClientSeeAnimals.getSelectedItem();
				model.setRowCount(0);
				model.setColumnCount(0);
				switch (box) {
				case "Dolphin":
					dolphinModel(model);
					try {
						controller.getSelectedDolphin(model, tableClient);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "No hay datos que mostrar", null,
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error inesperado", null, JOptionPane.ERROR_MESSAGE);
					}
					type = "Dolphin";
					break;
				case "Snake":
					snakeModel(model);
					try {
						controller.getSelectedSnake(model, tableClient);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "No hay datos que mostrar", null,
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error inesperado", null, JOptionPane.ERROR_MESSAGE);
					}
					type = "Snake";
					break;
				case "Crocodile":
					crocodileModel(model);
					try {
						controller.getSelectedCrocodile(model, tableClient);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "No hay datos que mostrar", null,
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error inesperado", null, JOptionPane.ERROR_MESSAGE);
					}
					type = "Crocodile";
					break;
				case "Giraffe":
					giraffeModel(model);
					try {
						controller.getSelectedGiraffe(model, tableClient);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "No hay datos que mostrar", null,
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error inesperado", null, JOptionPane.ERROR_MESSAGE);
					}
					type = "Giraffe";
					break;
				case "Cheetah":
					cheetahModel(model);
					try {
						controller.getSelectedCheetah(model, tableClient);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "No hay datos que mostrar", null,
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error inesperado", null, JOptionPane.ERROR_MESSAGE);
					}
					type = "Cheetah";
					break;
				}
			}
		});
		btnClientSearch.setForeground(Color.WHITE);
		btnClientSearch.setBackground(new Color(225, 238, 166));
		btnClientSearch.setBounds(184, 434, 89, 23);
		jpClient.add(btnClientSearch);

		JButton btnClientBuy = new JButton("Buy Tickets");
		btnClientBuy.setBackground(new Color(225, 238, 166));
		btnClientBuy.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnClientBuy.setBounds(495, 393, 244, 64);
		jpClient.add(btnClientBuy);

		JLabel lbClientBackground = new JLabel("");
		lbClientBackground.setBounds(0, 0, 848, 501);
		jpClient.add(lbClientBackground);

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

		JScrollPane spBoss = new JScrollPane();
		spBoss.setBounds(31, 102, 640, 188);
		jpBoss.add(spBoss);

		tableBoss = new JTable();
		tableBoss.setFillsViewportHeight(true);
		spBoss.setViewportView(tableBoss);
		tableBoss.setModel(model);

		JLabel lbBossTitle = new JLabel("Boss");
		lbBossTitle.setForeground(new Color(255, 255, 255));
		lbBossTitle.setFont(new Font("Arial", Font.BOLD, 39));
		lbBossTitle.setBounds(367, 26, 107, 56);
		jpBoss.add(lbBossTitle);

		btnBossDelete = new JButton("Delete");
		btnBossDelete.setEnabled(true);
		btnBossDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel tm = (DefaultTableModel) tableBoss.getModel();
					String id = String.valueOf(tm.getValueAt(tableBoss.getSelectedRow(), 0));
					Controller controller = new Controller();
					controller.questionSure(type, id);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "No se ha podido borrar", null, JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error inesperado", null, JOptionPane.ERROR_MESSAGE);
				}
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
				controller.getTableBoss(model, tableBoss);
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
				controller.getTableFeeder(model, tableBoss);
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
				controller.getTableVet(model, tableBoss);
				type = "Vet";
			}
		});
		btnBossSeeVet.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBossSeeVet.setBounds(377, 311, 121, 37);
		jpBoss.add(btnBossSeeVet);

		JButton btnBossAdd = new JButton("Add");
		btnBossAdd.setBackground(new Color(235, 199, 150));
		btnBossAdd.setForeground(new Color(255, 255, 255));
		btnBossAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller controller = new Controller();
				try {
					controller.addOption();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBossAdd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBossAdd.setBounds(696, 102, 121, 37);
		jpBoss.add(btnBossAdd);

		btnBossUpdate = new JButton("Update");
		btnBossUpdate.setEnabled(true);
		btnBossUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller controller = new Controller();
				try {
					DefaultTableModel tm = (DefaultTableModel) tableBoss.getModel();
					String id = String.valueOf(tm.getValueAt(tableBoss.getSelectedRow(), 0));
					controller.updateOption(type, id);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "No se ha podido actualizar", null, JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error inesperado", null, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnBossUpdate.setBackground(new Color(235, 199, 150));
		btnBossUpdate.setForeground(new Color(255, 255, 255));
		btnBossUpdate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBossUpdate.setBounds(696, 178, 121, 37);
		jpBoss.add(btnBossUpdate);

		JButton btnBossSeeClient = new JButton("Client");
		btnBossSeeClient.setBackground(new Color(235, 199, 150));
		btnBossSeeClient.setForeground(new Color(255, 255, 255));
		btnBossSeeClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller controller = new Controller();
				model.setRowCount(0);
				model.setColumnCount(0);
				personModel(model);
				controller.getTableClient(model, tableBoss);
				type = "Client";
			}
		});
		btnBossSeeClient.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBossSeeClient.setBounds(524, 311, 121, 37);
		jpBoss.add(btnBossSeeClient);

		JComboBox<String> cbBossAnimals = new JComboBox<String>();
		cbBossAnimals.addItem("Dolphin");
		cbBossAnimals.addItem("Snake");
		cbBossAnimals.addItem("Crocodile");
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

		cbBossZones = new JComboBox<String>();
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
					try {
						controller.getSelectedDolphin(model, tableBoss);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "No hay datos que mostrar", null,
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error inesperado", null, JOptionPane.ERROR_MESSAGE);
					}
					type = "Dolphin";
					break;
				case "Snake":
					snakeModel(model);
					try {
						controller.getSelectedSnake(model, tableBoss);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "No hay datos que mostrar", null,
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error inesperado", null, JOptionPane.ERROR_MESSAGE);
					}
					type = "Snake";
					break;
				case "Crocodile":
					crocodileModel(model);
					try {
						controller.getSelectedCrocodile(model, tableBoss);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "No hay datos que mostrar", null,
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error inesperado", null, JOptionPane.ERROR_MESSAGE);
					}
					type = "Crocodile";
					break;
				case "Giraffe":
					giraffeModel(model);
					try {
						controller.getSelectedGiraffe(model, tableBoss);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "No hay datos que mostrar", null,
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error inesperado", null, JOptionPane.ERROR_MESSAGE);
					}
					type = "Giraffe";
					break;
				case "Cheetah":
					cheetahModel(model);
					try {
						controller.getSelectedCheetah(model, tableBoss);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "No hay datos que mostrar", null,
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error inesperado", null, JOptionPane.ERROR_MESSAGE);
					}
					type = "Cheetah";
					break;
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
				String selectedZone = (String) cbBossZones.getSelectedItem();
				model.setRowCount(0);
				model.setColumnCount(0);
				switch (selectedZone) {
				case "Aquarium":
					zoneModel(model);
					try {
						controller.getTableAquarium(model, tableBoss);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "No hay datos que mostrar", null,
								JOptionPane.ERROR_MESSAGE);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error inesperado", null, JOptionPane.ERROR_MESSAGE);
					}
					type = "Aquarium";
					break;
				case "Swamp":
					zoneModel(model);
					try {
						controller.getTableSwamp(model, tableBoss);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "No hay datos que mostrar", null,
								JOptionPane.ERROR_MESSAGE);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error inesperado", null, JOptionPane.ERROR_MESSAGE);
					}
					type = "Swamp";
					break;
				case "Savannah":
					zoneModel(model);
					try {
						controller.getTableSavannah(model, tableBoss);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "No hay datos que mostrar", null,
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error inesperado", null, JOptionPane.ERROR_MESSAGE);
					}
					type = "Savannah";
					break;
				}
			}
		});
		btBossSeeZone.setForeground(new Color(255, 255, 255));
		btBossSeeZone.setBackground(new Color(235, 199, 150));
		btBossSeeZone.setBounds(473, 443, 85, 21);
		jpBoss.add(btBossSeeZone);

		JLabel lbBossBackground = new JLabel("");
		lbBossBackground.setBounds(0, 0, 848, 501);
		jpBoss.add(lbBossBackground);
		addImage(jpBoss, lbBossBackground, "src/Photos/lion.png");

		JLabel lbVet = new JLabel("Vet");
		lbVet.setFont(new Font("Arial", Font.BOLD, 39));
		lbVet.setBounds(365, 23, 97, 34);
		jpVet.add(lbVet);

		JButton btnVetLogout = new JButton("LogOut");
		btnVetLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller controller = new Controller();
				controller.questionLogOut(jpBoss, jpLogin, jpClient, jpFeeder, jpVet);
			}
		});
		btnVetLogout.setForeground(Color.WHITE);
		btnVetLogout.setBackground(Color.BLACK);
		btnVetLogout.setBounds(0, 0, 89, 23);
		jpVet.add(btnVetLogout);

		JScrollPane spVet = new JScrollPane();
		spVet.setBounds(10, 87, 668, 280);
		jpVet.add(spVet);

		tableVet = new JTable();
		spVet.setViewportView(tableVet);
		tableVet.setModel(model);

		JComboBox<String> cbVetSeeAnimals = new JComboBox<String>();
		cbVetSeeAnimals.setForeground(new Color(255, 255, 255));
		cbVetSeeAnimals.setBackground(new Color(243, 225, 201));
		cbVetSeeAnimals.addItem("Dolphin");
		cbVetSeeAnimals.addItem("Snake");
		cbVetSeeAnimals.addItem("Crocodile");
		cbVetSeeAnimals.addItem("Giraffe");
		cbVetSeeAnimals.addItem("Cheetah");
		cbVetSeeAnimals.setBounds(688, 183, 150, 23);
		cbVetSeeAnimals.setOpaque(false);
		jpVet.add(cbVetSeeAnimals);

		JButton btnVetSearch = new JButton("Search");
		btnVetSearch.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnVetSearch.setForeground(new Color(255, 255, 255));
		btnVetSearch.setBackground(new Color(243, 225, 201));
		btnVetSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller controller = new Controller();
				String box = (String) cbVetSeeAnimals.getSelectedItem();
				model.setRowCount(0);
				model.setColumnCount(0);
				switch (box) {
				case "Dolphin":
					dolphinModel(model);
					try {
						controller.getSelectedDolphin(model, tableVet);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "No hay datos que mostrar", null,
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error inesperado", null, JOptionPane.ERROR_MESSAGE);
					}
					type = "Dolphin";
					break;
				case "Snake":
					snakeModel(model);
					try {
						controller.getSelectedSnake(model, tableVet);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "No hay datos que mostrar", null,
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error inesperado", null, JOptionPane.ERROR_MESSAGE);
					}
					type = "Snake";
					break;
				case "Crocodile":
					crocodileModel(model);
					try {
						controller.getSelectedCrocodile(model, tableVet);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "No hay datos que mostrar", null,
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error inesperado", null, JOptionPane.ERROR_MESSAGE);
					}
					type = "Crocodile";
					break;
				case "Giraffe":
					giraffeModel(model);
					try {
						controller.getSelectedGiraffe(model, tableVet);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "No hay datos que mostrar", null,
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error inesperado", null, JOptionPane.ERROR_MESSAGE);
					}
					type = "Giraffe";
					break;
				case "Cheetah":
					cheetahModel(model);
					try {
						controller.getSelectedCheetah(model, tableVet);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "No hay datos que mostrar", null,
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error inesperado", null, JOptionPane.ERROR_MESSAGE);
					}
					type = "Cheetah";
					break;
				}
			}

		});
		btnVetSearch.setBounds(718, 233, 89, 23);
		jpVet.add(btnVetSearch);

		JButton btnVetAdd = new JButton("ADD");
		btnVetAdd.setForeground(new Color(255, 255, 255));
		btnVetAdd.setBackground(new Color(243, 225, 201));
		btnVetAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller controller = new Controller();
				try {
					controller.addOptionAnimal();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnVetAdd.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnVetAdd.setBounds(89, 412, 121, 37);
		jpVet.add(btnVetAdd);

		JButton btnVetUpdate = new JButton("UPDATE");
		btnVetUpdate.setForeground(new Color(255, 255, 255));
		btnVetUpdate.setBackground(new Color(243, 225, 201));
		btnVetUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller controller = new Controller();
				DefaultTableModel tm = (DefaultTableModel) tableVet.getModel();
				String id = String.valueOf(tm.getValueAt(tableVet.getSelectedRow(), 0));
				try {
					controller.updateOption(type, id);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "No hay datos que mostrar", null, JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error inesperado", null, JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		btnVetUpdate.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnVetUpdate.setBounds(266, 412, 121, 37);
		jpVet.add(btnVetUpdate);

		JButton btnVetDelete = new JButton("DELETE");
		btnVetDelete.setForeground(new Color(255, 255, 255));
		btnVetDelete.setBackground(new Color(243, 225, 201));
		btnVetDelete.setEnabled(true);
		btnVetDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tm = (DefaultTableModel) tableVet.getModel();
				String id = String.valueOf(tm.getValueAt(tableVet.getSelectedRow(), 0));
				Controller controller = new Controller();
				try {
					controller.questionSure(type, id);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "No hay datos que mostrar", null, JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error inesperado", null, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnVetDelete.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnVetDelete.setBounds(450, 412, 121, 37);
		jpVet.add(btnVetDelete);

		JLabel lbVetBackground = new JLabel("");
		lbVetBackground.setBounds(0, 0, 848, 501);
		jpVet.add(lbVetBackground);

		JLabel lbFeeder = new JLabel("Feeder");
		lbFeeder.setForeground(new Color(255, 255, 255));
		lbFeeder.setBounds(349, 28, 129, 46);
		lbFeeder.setFont(new Font("Arial", Font.BOLD, 39));
		jpFeeder.add(lbFeeder);

		JButton btnFeeder = new JButton("LogOut");
		btnFeeder.setBounds(0, 0, 89, 23);
		btnFeeder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller controller = new Controller();
				controller.questionLogOut(jpBoss, jpLogin, jpClient, jpFeeder, jpVet);
			}
		});
		btnFeeder.setForeground(Color.WHITE);
		btnFeeder.setBackground(Color.BLACK);
		jpFeeder.add(btnFeeder);

		JScrollPane spFeeder = new JScrollPane();
		spFeeder.setBounds(40, 111, 769, 291);
		spFeeder.setOpaque(false);
		jpFeeder.add(spFeeder);

		tableFeeder = new JTable();
		tableFeeder.setForeground(new Color(255, 255, 255));
		tableFeeder.setBackground(new Color(255, 255, 255));
		spFeeder.setViewportView(tableFeeder);
		tableFeeder.setEnabled(false);
		tableFeeder.setModel(model);

		JComboBox<String> cbFeederSeeAnimals = new JComboBox<String>();
		cbFeederSeeAnimals.setBackground(new Color(66, 111, 51));
		cbFeederSeeAnimals.setForeground(new Color(255, 255, 255));
		cbFeederSeeAnimals.addItem("Dolphin");
		cbFeederSeeAnimals.addItem("Snake");
		cbFeederSeeAnimals.addItem("Crocodile");
		cbFeederSeeAnimals.addItem("Giraffe");
		cbFeederSeeAnimals.addItem("Cheetah");

		cbFeederSeeAnimals.setBounds(235, 413, 370, 31);
		jpFeeder.add(cbFeederSeeAnimals);

		JButton btnFeederSearch = new JButton("Search");
		btnFeederSearch.setBackground(new Color(66, 111, 51));
		btnFeederSearch.setForeground(new Color(255, 255, 255));
		btnFeederSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller controller = new Controller();
				String box = (String) cbFeederSeeAnimals.getSelectedItem();
				model.setRowCount(0);
				model.setColumnCount(0);
				switch (box) {
				case "Dolphin":
					dolphinModel(model);
					try {
						controller.getSelectedDolphin(model, tableFeeder);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "No hay datos que mostrar", null,
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error inesperado", null, JOptionPane.ERROR_MESSAGE);
					}
					type = "Dolphin";
					break;
				case "Snake":
					snakeModel(model);
					try {
						controller.getSelectedSnake(model, tableFeeder);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "No hay datos que mostrar", null,
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error inesperado", null, JOptionPane.ERROR_MESSAGE);
					}
					type = "Snake";
					break;
				case "Crocodile":
					crocodileModel(model);
					try {
						controller.getSelectedCrocodile(model, tableFeeder);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "No hay datos que mostrar", null,
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error inesperado", null, JOptionPane.ERROR_MESSAGE);
					}
					type = "Crocodile";
					break;
				case "Giraffe":
					giraffeModel(model);
					try {
						controller.getSelectedGiraffe(model, tableFeeder);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "No hay datos que mostrar", null,
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error inesperado", null, JOptionPane.ERROR_MESSAGE);
					}
					type = "Giraffe";
					break;
				case "Cheetah":
					cheetahModel(model);
					try {
						controller.getSelectedCheetah(model, tableFeeder);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "No hay datos que mostrar", null,
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error inesperado", null, JOptionPane.ERROR_MESSAGE);
					}
					type = "Cheetah";
					break;
				}
			}
		});
		btnFeederSearch.setBounds(377, 455, 89, 23);
		jpFeeder.add(btnFeederSearch);

		JLabel lbFeederBackground = new JLabel("");
		lbFeederBackground.setBounds(0, 0, 848, 501);
		jpFeeder.add(lbFeederBackground);
		addImage(jpFeeder, lbFeederBackground, "src/Photos/zebra.png");
		addImage(jpClient, lbClientBackground, "src/Photos/Elephant.png");
		addImage(jpVet, lbVetBackground, "src/Photos/Giraff.png");

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
		model.addColumn("DurationUnderWater");

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
		model.addColumn("Poisonus");

	}

	private void crocodileModel(DefaultTableModel model) {
		model.addColumn("Id");
		model.addColumn("Name");
		model.addColumn("ScientificName");
		model.addColumn("Height");
		model.addColumn("Weight");
		model.addColumn("Born-Date");
		model.addColumn("Vaccinated");
		model.addColumn("Diet");
		model.addColumn("shed-Skin");
		model.addColumn("teethNumber");

	}

	private void giraffeModel(DefaultTableModel model) {
		model.addColumn("Id");
		model.addColumn("Name");
		model.addColumn("ScientificName");
		model.addColumn("Height");
		model.addColumn("Weight");
		model.addColumn("Born-Date");
		model.addColumn("Vaccinated");
		model.addColumn("Diet");
		model.addColumn("shed-Skin");
		model.addColumn("Neck-Legnth");

	}

	private void cheetahModel(DefaultTableModel model) {
		model.addColumn("Id");
		model.addColumn("Name");
		model.addColumn("ScientificName");
		model.addColumn("Height");
		model.addColumn("Weight");
		model.addColumn("Born-Date");
		model.addColumn("Vaccinated");
		model.addColumn("Diet");
		model.addColumn("shed-Skin");
		model.addColumn("Max-Speed");

	}

	private void personModel(DefaultTableModel model) {
		model.addColumn("Id");
		model.addColumn("Name");
		model.addColumn("Surname");
		model.addColumn("User");
		model.addColumn("Password");
		model.addColumn("Special");
	}

	private void zoneModel(DefaultTableModel model) {
		model.addColumn("Id");
		model.addColumn("Extension");
		model.addColumn("animalsNumber");
		model.addColumn("SpeciesNumber");
		if (cbBossZones.getSelectedItem().equals("Aquarium")) {
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