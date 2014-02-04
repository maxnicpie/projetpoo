import java.sql.SQLException;
import java.sql.Statement;

public class CategorieMotClef {

	private String nom;
	private Statement st;
	private String CREER_CATEGORIE_MOT_CLEF = "INSERT INTO CATEGORIEMOTCLEF VALUES(null, \""
			+ nom + "\")";

	public CategorieMotClef(String nom) {
		this.nom = nom;
	}

	public String getNomCategorieMotClef() {
		return nom;
	}

	public void setNomCategorieMotClef(String nom) {
		this.nom = nom;
	}

	public void creer_categorie_mot_clef(String nom) {
		try {
			st.executeQuery(CREER_CATEGORIE_MOT_CLEF);
		} catch (SQLException e) {
			System.out.println("Erreur de requete");
			e.printStackTrace();
		}

	}
}
