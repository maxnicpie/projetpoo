import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {

	private Connection conn;

	public Statement connexion(String nom, String mdp, String adresse) {

		Statement st = null;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println("Erreur driver");
		}

		try {
			conn = DriverManager.getConnection("jdbc:mysql://" + adresse, nom,
					mdp);
		} catch (SQLException e) {
			return null;
		}

		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			System.out.println("Erreur création statement");
		}
		return st;
	}
}