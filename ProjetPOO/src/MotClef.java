import java.sql.SQLException;
import java.sql.Statement;

public class MotClef {
	private String libelle;
	private Statement st;

	private String CREER_MOT_CLEF = "INSERT INTO MOTCLEF VALUES(null, \""
			+ libelle + "\")";

	public MotClef(String libelle) {
		this.libelle = libelle;
	}

	public String getMotClef() {
		return libelle;
	}

	public void setNomMotClef(String libelle) {
		this.libelle = libelle;
	}

	public void creer_mot_clef(String libelle) {
		try {
			st.executeQuery(CREER_MOT_CLEF);
		} catch (SQLException e) {
			System.out.println("Erreur de requete");
			e.printStackTrace();
		}

	}
}
