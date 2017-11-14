package ConexionDB;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MySQL {

	static Connection con = null;
	public String entryUser = "-";
	public String entryPass = "-";
	public static String alias = "";
	public boolean validation = false;

	public MySQL(String driver, String urlDB, String dBname) {

		while (validation == false) {
			String dataUser = login();
			String data[] = dataUser.split(",");// User - Password

			// Exception Empty Spaces
			try {
				entryUser = data[0];
				entryPass = data[1];
			} catch (Exception e) {
				entryUser = "";
				entryPass = "";
				e.printStackTrace();
			}
			startConnection(driver, urlDB, entryUser, entryPass);
		}
		welcomeUsers(entryUser);
		useDatabase(dBname);
	}

	public static void insertData(String tableName, int ID, String descripcion) {

		try {

			String query = "insert into " + tableName + " values(" + ID + "," + "'" + descripcion + "'" + ")";

			PreparedStatement ps = con.prepareStatement(query);
			ps.execute();
			JOptionPane.showMessageDialog(null, "Datos insertados de forma exitosa");

		} catch (SQLException ex) {

			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datos no insertados");
		}
	}

	public static void getDataSystem(String column) {

		try {

			String query = "select " + column + " from mysql.user";

			Statement st = con.createStatement();
			ResultSet resultSet;
			resultSet = st.executeQuery(query);
			ArrayList<String> listDataTable = new ArrayList<String>();

			while (resultSet.next()) {
				listDataTable.add(resultSet.getString(column));
			}

			String arrDataTable[] = new String[listDataTable.size()];
			listDataTable.toArray(arrDataTable);

			JOptionPane.showMessageDialog(null, arrDataTable);

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error en la adquisición de datos");
		}
	}

	public static void showDatabases() {

		try {

			DatabaseMetaData dbmd = con.getMetaData();
			ResultSet rs = dbmd.getCatalogs();
			ArrayList<String> listDataTable = new ArrayList<String>();

			while (rs.next()) {
				listDataTable.add(rs.getString("TABLE_CAT"));
			}

			String arrDataTable[] = new String[listDataTable.size()];
			listDataTable.toArray(arrDataTable);

			JOptionPane.showMessageDialog(null, arrDataTable);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void showTables() {

		try {

			DatabaseMetaData dbmd = con.getMetaData();
			String[] types = { "TABLE" };
			ResultSet rs = dbmd.getTables(null, null, "%", types);
			ArrayList<String> listDataTable = new ArrayList<String>();

			while (rs.next()) {
				listDataTable.add(rs.getString("TABLE_NAME"));
			}

			String arrDataTable[] = new String[listDataTable.size()];
			listDataTable.toArray(arrDataTable);

			JOptionPane.showMessageDialog(null, arrDataTable);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void selectFromTables(String tableName) {

		try {

			String query = "select * from " + tableName;

			Statement st = con.createStatement();
			ResultSet resultSet;
			resultSet = st.executeQuery(query);
			ArrayList<String> listDataTable = new ArrayList<String>();

			while (resultSet.next()) {
				listDataTable.add("ID: " + resultSet.getInt(tableName + "_id") + " --- " + "Descripcion: "
						+ resultSet.getString(tableName + "_descripcion"));
			}

			String arrDataTable[] = new String[listDataTable.size()];
			listDataTable.toArray(arrDataTable);

			JOptionPane.showMessageDialog(null, arrDataTable);

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error en la adquisición de datos");
		}
	}

	public void eliminateTable(String tableName) {

		try {

			String query = "drop table " + tableName;

			PreparedStatement ps = con.prepareStatement(query);
			ps.execute();
			JOptionPane.showMessageDialog(null, "Tabla " + tableName + " borrada");

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			JOptionPane.showMessageDialog(null, "No se ha borrado la tabla " + tableName);
		}
	}

	public static void createUser(String userName, String pass) {

		try {

			String query = "CREATE USER " + "'" + userName + "'" + "@" + "'localhost' IDENTIFIED BY " + "'" + pass
					+ "'";

			PreparedStatement ps = con.prepareStatement(query);
			ps.execute();
			JOptionPane.showMessageDialog(null, "Usuario " + userName + " creado");

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al crear usuario");
		}
	}

	public static void createTable(String tableName) {

		try {

			String query = "create table " + tableName + "(" + tableName + "_id int not null," + tableName
					+ "_descripcion varchar(45) not null," + "constraint " + tableName + "_pk primary key" + "("
					+ (tableName + "_id") + "))";

			PreparedStatement ps = con.prepareStatement(query);
			ps.execute();
			JOptionPane.showMessageDialog(null, "Tabla " + tableName + " creada");

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al crear tabla");
		}
	}

	public void useDatabase(String dBname) {

		try {

			String query = "use " + dBname;

			PreparedStatement ps = con.prepareStatement(query);
			ps.execute();
			JOptionPane.showMessageDialog(null, "Esta usando la base de datos " + dBname);

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			JOptionPane.showMessageDialog(null, "Mala conexion con la bases de datos " + dBname);
		}
	}

	public static void closeConnection() {

		try {

			con.close();
			JOptionPane.showMessageDialog(null, "Se ha cerrado la conexion");

		} catch (SQLException ex) {

			JOptionPane.showMessageDialog(null, "No se ha podido cerrar la conexion");
			ex.printStackTrace();
		}
	}

	public Connection startConnection(String driver, String urlDB, String userDB, String passDB) {

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(urlDB, userDB, passDB);
			JOptionPane.showMessageDialog(null, "Conexion Exitosa", "Inicio de Sesión Exitoso!!!",
					JOptionPane.INFORMATION_MESSAGE);
			validation = true;
		} catch (ClassNotFoundException e) {

			JOptionPane.showMessageDialog(null, "Error De Driver");
			e.printStackTrace();
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, "Usuario/Contraseña Incorrectos, Intentalo De Nuevo", "Error!!!",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		return con;
	}

	public String login() {

		JTextField txtUser = null;
		JPasswordField txtPassword = null; /* Password Black Circles */

		String[] optionsUser = { "Siguiente" };
		String[] optionsPass = { "Iniciar Sesión" };

		JPanel panelUser = new JPanel();
		JPanel panelPass = new JPanel();

		JLabel labelUser = new JLabel("Usuario");
		JLabel labelPass = new JLabel("Contraseña");

		txtUser = new JTextField(10);
		txtPassword = new JPasswordField(10);

		panelUser.add(labelUser);
		panelUser.add(txtUser);
		panelPass.add(labelPass);
		panelPass.add(txtPassword);

		int selectedOptionUser = JOptionPane.showOptionDialog(null, panelUser, "CRM Acceso", JOptionPane.NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, optionsUser, optionsUser[0]);

		if (selectedOptionUser == -1) { // Event
			System.exit(0);
		}

		int selectedOptionPass = JOptionPane.showOptionDialog(null, panelPass, "CRM Acceso", JOptionPane.NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, optionsPass, optionsPass[0]);

		if (selectedOptionPass == -1) { // Event
			System.exit(0);
		}

		String user = txtUser.getText();
		String pass = new String(txtPassword.getPassword());

		return user + "," + pass;
	}

	public static void createDB(String name) {

		try {

			String query = "create database " + name;
			PreparedStatement ps = con.prepareStatement(query);
			ps.execute();
			JOptionPane.showMessageDialog(null, "Se ha creado la base de datos " + name + " de forma exitosa");

		} catch (SQLException ex) {

			JOptionPane.showMessageDialog(null, "No se ha creado la base de datos");
			ex.printStackTrace();
		}
	}

	public void welcomeUsers(String user) {

		if (user.equals("root")) {
			alias = "Admin";
			JOptionPane.showMessageDialog(null, "Hola " + alias + "!!!");
		}
		if (user.equals("ProgramadorGeneral")) {
			alias = "Esteban";
			JOptionPane.showMessageDialog(null, "Hola " + alias + "!!!");
		}
		if (user.equals("ProgramadorDB")) {
			alias = "Michael";
			JOptionPane.showMessageDialog(null, "Hola " + alias + "!!!");
		}
		if (user.equals("GerenteComercial")) {
			alias = "Santiago";
			JOptionPane.showMessageDialog(null, "Hola " + alias + "!!!");
		}
		if (user.equals("GerenteGeneral")) {
			alias = "Felipe";
			JOptionPane.showMessageDialog(null, "Hola " + alias + "!!!");
		}
	}
}
