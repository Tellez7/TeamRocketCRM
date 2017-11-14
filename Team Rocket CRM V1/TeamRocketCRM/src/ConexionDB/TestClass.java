package ConexionDB;

import java.sql.DriverManager;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

public class TestClass {

	public TestClass(String driver, String urlDB, String userName, String password) {

		try {

			Class.forName(driver);
			Connection con = (Connection) DriverManager.getConnection(urlDB, userName, password);
			JOptionPane.showMessageDialog(null, "Conexion Exitosa");
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Sin Conexion","NO",JOptionPane.ERROR_MESSAGE);
		}
	}
}
