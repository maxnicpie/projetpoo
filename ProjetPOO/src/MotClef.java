import java.sql.SQLException;
import java.sql.Statement;

public class MotClef {
	
	CategorieMotClef categorie_mot_clef = new CategorieMotClef();
	
	private String libelle;
	private Statement st;

	public MotClef(String libelle) {
		this.libelle = libelle;
	}
	
	public MotClef() {
	}

	public String recupIdMotClef(){
    	String id = "SELECT idMotClef FROM MOTCLEF where libelle = \""+libelle+"\"";
		try {
			st.executeQuery(id);
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		return id;
	}

	public String getMotClef() {
		return libelle;
	}
	
	public String toString() {
		return libelle;
	}

	public void setNomMotClef(String libelle) {
		this.libelle = libelle;
	}

	public void creer_mot_clef() {
		
		String CREER_MOT_CLEF = "INSERT INTO MOTCLEF VALUES(null, \""
				+ libelle + "\")";
		try {
			st.executeQuery(CREER_MOT_CLEF);
		} catch (SQLException e) {
			System.out.println("Erreur de requete");
			e.printStackTrace();
		}

	}
	
	public void recupererMotClef(){
		String idCategorieMotClef = categorie_mot_clef.recupIdCategorieMotClef();

		String recupMotClef = "SELECT libelle FROM MOTCLEF WHERE idCategorieMotClef = \""
				+ idCategorieMotClef + "\"";

		try {
			st.executeQuery(recupMotClef);
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}
}
