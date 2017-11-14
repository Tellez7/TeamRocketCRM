package ConexionDB;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ConexionDB.MySQL;

public class ButtomWindow extends JFrame implements ActionListener {

	JButton button1Clientes, button2Integrantes, button3Salir, button4Users, button6Tables, button5Databases,
			button7CreateDB, button8CreateUser, button9CreateTable, button10CreateClientes, button11CreateIntegrantes;
	JLabel labelTitle, labellastAcess, labelAliasScreen;
	JPanel panelUserTime = new JPanel();
	Calendar time = new GregorianCalendar();
	ImageIcon imagen;

	public ButtomWindow(String user) {

		String alias = MySQL.alias;
		int hora = time.get(Calendar.HOUR_OF_DAY);
		int minutos = time.get(Calendar.MINUTE);

		labelAliasScreen = label(labelAliasScreen, "Usuario: " + alias, 0, 385, 400, 30, "", Font.BOLD, 12);
		labellastAcess = label(labellastAcess, "Ultimo Acceso: " + hora + ":" + minutos, 0, 400, 400, 30, "", Font.BOLD,
				12);

		/*labelTitle = label(labelTitle,
				"<html><body>Team Rocket<br>&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;CRM<br></body></html>", 210, 10, 400,
				120, "TimesRoman", Font.BOLD, 36);*/
		button3Salir = button(button3Salir, "Salir", 545, 392, 100, 30, null);

		setSize(650, 450);
		setTitle("CRM");
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		if (user.equals("GerenteGeneral") || user.equals("GerenteComercial")) {

			button1Clientes = button(button1Clientes, "Clientes", 175, 150, 100, 70, null);
			button2Integrantes = button(button2Integrantes, "Integrantes", 350, 150, 100, 70, null);
		}

		if (user.equals("ProgramadorGeneral") || user.equals("ProgramadorDB")) {

			button4Users = button(button4Users, "Usuarios", 130, 160, 130, 70, null);
			button5Databases = button(button5Databases, "Bases De Datos", 175, 289, 275, 70, null);
			button6Tables = button(button6Tables, "Tablas", 360, 160, 130, 70, null);
		}
		if (user.equals("root")) {

			button1Clientes = button(button1Clientes, "Clientes", 20, 250, 100, 70, Color.ORANGE);
			button2Integrantes = button(button2Integrantes, "Integrantes", 500, 250, 100, 70, Color.ORANGE);
			button4Users = button(button4Users, "Usuarios", 130, 160, 130, 70, Color.ORANGE);
			button5Databases = button(button5Databases, "Bases De Datos", 175, 289, 275, 70, Color.ORANGE);
			button6Tables = button(button6Tables, "Tablas", 360, 160, 130, 70, Color.ORANGE);
			button7CreateDB = button(button7CreateDB, "Crear", 260, 260, 100, 30, Color.LIGHT_GRAY);
			button8CreateUser = button(button8CreateUser, "Crear", 155, 131, 80, 30, Color.LIGHT_GRAY);
			button9CreateTable = button(button9CreateTable, "Crear", 385, 131, 80, 30, Color.LIGHT_GRAY);
			button10CreateClientes = button(button10CreateClientes, "Crear", 35, 221, 70, 30, Color.LIGHT_GRAY);
			button11CreateIntegrantes = button(button11CreateIntegrantes, "Crear", 515, 221, 70, 30, Color.LIGHT_GRAY);
		}

		// Imagen Fondo
		ImagenFondo p = new ImagenFondo();
		getContentPane().add(p, BorderLayout.CENTER);
		p.repaint();
	}

	public JLabel label(JLabel label, String text, int x, int y, int width, int height, String nameFont, int typeFont,
			int sizeFont) {

		label = new JLabel(text);
		label.setBounds(x, y, width, height); // x, y, width, height
		label.setFont(new Font(nameFont, typeFont, sizeFont));
		getContentPane().add(label);

		return label;
	}

	public JButton button(JButton button, String text, int x, int y, int width, int height, Color color) {

		button = new JButton(text);
		button.setBounds(x, y, width, height);
		getContentPane().add(button);
		button.addActionListener(this);

		if (color == null) {
			button.setBackground(new JButton().getBackground());
		} else {
			button.setBackground(color);
		}

		return button;
	}

	public void createUserMenu(String type) {

		if (type.equals("Usuario")) {

			String[] optionsUser = { "Siguiente" };
			String[] optionsPass = { "Crear" };

			JPanel panelUser = new JPanel();
			JPanel panelPass = new JPanel();

			JLabel labelUser = new JLabel("Usuario");
			JLabel labelPass = new JLabel("Contraseña");

			JTextField txtUser = new JTextField(10);
			JTextField txtPassword = new JTextField(10);

			panelUser.add(labelUser);
			panelUser.add(txtUser);
			panelPass.add(labelPass);
			panelPass.add(txtPassword);

			int selectedOptionUser = JOptionPane.showOptionDialog(null, panelUser, "Crear Usuario",
					JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsUser, optionsUser[0]);

			int selectedOptionPass = JOptionPane.showOptionDialog(null, panelPass, "Crear Usuario",
					JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsPass, optionsPass[0]);

			MySQL.createUser(txtUser.getText(), txtPassword.getText());
		}
		if (type.equals("DB")) {

			String[] option = { "Crear" };
			JLabel label = new JLabel("Nombre base de datos");
			JTextField txt = new JTextField(10);
			JPanel panel = new JPanel();
			panel.add(label);
			panel.add(txt);

			int selectedOptionUser = JOptionPane.showOptionDialog(null, panel, "Crear DB", JOptionPane.NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, option, option[0]);

			MySQL.createDB(txt.getText());
		}
		if (type.equals("Tabla")) {

			String[] option = { "Crear" };
			JLabel label = new JLabel("Nombre Tabla");
			JTextField txt = new JTextField(10);
			JPanel panel = new JPanel();
			panel.add(label);
			panel.add(txt);

			int selectedOptionUser = JOptionPane.showOptionDialog(null, panel, "Crear Tablas", JOptionPane.NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, option, option[0]);

			MySQL.createTable(txt.getText());
		}
	}

	public void insertaDataTable(String tableName) {

		String[] optionTableName = { "Añadir" };

		JPanel panel = new JPanel();
		JLabel id = new JLabel("ID");
		JTextField txtID = new JTextField(10);
		JLabel descripcion = new JLabel("Descripcion");
		JTextField txtDescripcion = new JTextField(10);

		panel.add(id);
		panel.add(txtID);
		panel.add(descripcion);
		panel.add(txtDescripcion);

		int selectedOptionTableName = JOptionPane.showOptionDialog(null, panel, "Registro", JOptionPane.NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, optionTableName, optionTableName[0]);

		MySQL.insertData(tableName, Integer.parseInt(txtID.getText()), txtDescripcion.getText());
	}

	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == button1Clientes) {
			MySQL.selectFromTables((button1Clientes.getText()));
		}
		if (event.getSource() == button2Integrantes) {
			MySQL.selectFromTables((button2Integrantes.getText()));
		}
		if (event.getSource() == button3Salir) {
			MySQL.closeConnection();
			System.exit(0);
		}
		if (event.getSource() == button4Users) {
			MySQL.getDataSystem("User");
		}
		if (event.getSource() == button5Databases) {
			MySQL.showDatabases();
		}
		if (event.getSource() == button6Tables) {
			MySQL.showTables();
		}
		if (event.getSource() == button7CreateDB) {
			createUserMenu("DB");
		}
		if (event.getSource() == button8CreateUser) {
			createUserMenu("Usuario");
		}
		if (event.getSource() == button9CreateTable) {
			createUserMenu("Tabla");
		}
		if (event.getSource() == button10CreateClientes) {
			insertaDataTable("Clientes");
		}
		if (event.getSource() == button11CreateIntegrantes) {
			insertaDataTable("Integrantes");
		}
	}
}
