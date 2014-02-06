import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MotClef {
	private Statement st;
	CategorieMotClef categorie_mot_clef = new CategorieMotClef(st);
	ResultSet rs;
	private String libelle;
	
	public MotClef(String libelle) {
		this.libelle = libelle;
	}
	
	public MotClef(Statement st) {
		this.st = st;
	}

	public int getIdMotClef(){
		int recupIdMotClef = 0;
    	String id = "SELECT idMotClef FROM MOTCLEF where libelle = \""+libelle+"\"";
		try {
			rs.next();
			rs = st.executeQuery(id);
			recupIdMotClef = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		return recupIdMotClef;
	}

	public String getLibelleMotClef() {
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
	
	public void getNomMotClef(){
		int i = 1;
		int idCategorieMotClef = categorie_mot_clef.getIdCategorieMotClef();
		String recupMotClef = "SELECT libelle FROM MOTCLEF WHERE idCategorieMotClef = \""
				+ idCategorieMotClef + "\"";
		
		int nbLignes = 0;
		try {
			st.executeQuery(recupMotClef);
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}
}
