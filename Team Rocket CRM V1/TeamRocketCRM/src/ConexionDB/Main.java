package ConexionDB;

import ConexionDB.MySQL;

public class Main {

	static String dbName = "empresa";
	static String urlDB = "jdbc:mysql://localhost/" + dbName;
	static String driver = "com.mysql.jdbc.Driver";

	public static void main(String[] args) {

		MySQL db = new MySQL(driver, urlDB, dbName);
		ButtomWindow bw = new ButtomWindow(db.entryUser);
		bw.setVisible(db.validation);
	}
}
