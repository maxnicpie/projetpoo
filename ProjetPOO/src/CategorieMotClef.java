import java.sql.SQLException;
import java.sql.Statement;

public class CategorieMotClef {
	Domaine domaine = new Domaine();
	private String nom;
	private Statement st;

	public CategorieMotClef() {
		this.nom = nom;
	}

	public String getNomCategorieMotClef() {
		return nom;
	}

	public void setNomCategorieMotClef(String nom) {
		this.nom = nom;
	}
	
	public String recupIdCategorieMotClef(){
    	String id = "SELECT idCategorieMotClef FROM CATEGORIEMOTCLEF where nomCategorieMotClef = \""+nom+"\"";
		try {
			st.executeQuery(id);
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		return id;
	}
	
	public void recupererCategorieMotClef(){
		String idDomaine = domaine.recupIdDomaine();

		String recupCategorieMotClef = "SELECT nomCategorieMotClef FROM CATEGORIEMOTCLEF WHERE idDomaine = \""
				+ idDomaine + "\"";

		try {
			st.executeQuery(recupCategorieMotClef);
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}

	public void creer_categorie_mot_clef() {
		
		String CREER_CATEGORIE_MOT_CLEF = "INSERT INTO CATEGORIEMOTCLEF VALUES(null, \""
				+ nom + "\")";
		try {
			st.executeQuery(CREER_CATEGORIE_MOT_CLEF);
		} catch (SQLException e) {
			System.out.println("Erreur de requete");
			e.printStackTrace();
		}

	}
}
