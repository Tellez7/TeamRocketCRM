package ConexionDB;

public class Test {

	static String driver = "com.mysql.jdbc.Driver";
	static String dbName = "id3417968_empresa";
	// static String urlDB = "jdbc:mysql://trcrm.000webhostapp.com:3306/";
	// static String urlDB = "jdbc:mysql://www.000webhost.com/cpanel-login";
	static String urlDB = "jdbc:mysql://www.000webhost.com";
	static String userName = "es-tellez@hotmail.com";
	static String password = "tr987";

	public static void main(String[] args) {

		TestClass test = new TestClass(driver, urlDB, userName, password);
	}
}