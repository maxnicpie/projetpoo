import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class CategorieMotClef {
	ResultSet rs;
	private String nom;
	private Statement st;	
	Domaine domaine = new Domaine(st);
	private ArrayList<MotClef> listeMotClefs  = new ArrayList<MotClef>();

	public CategorieMotClef(String text) {
		this.nom = text;
	}
	
	public CategorieMotClef() {
		
	}

	public String getNomCategorieMotClef() {
		return nom;
	}

	public void setNomCategorieMotClef(String nom) {
		this.nom = nom;
	}
	
	public int getIdCategorieMotClef(){
		int recupIdCategorieEtudiant = 0;
    	String id = "SELECT idCategorieMotClef FROM CATEGORIEMOTCLEF where nomCategorieMotClef = \""+nom+"\"";
		try {
			rs = st.executeQuery(id);
			rs.next();
			recupIdCategorieEtudiant = rs.getInt(1);	
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		return recupIdCategorieEtudiant;
	}
	
	public void recupererCategorieMotClef(){

		int idDomaine = domaine.getIdDomaine();

		String recupCategorieMotClef = "SELECT nomCategorieMotClef FROM CATEGORIEMOTCLEF WHERE idDomaine = \""
				+ idDomaine + "\"";

		try {
			st.executeQuery(recupCategorieMotClef);
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}

	public String toString() {
		return nom;
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

	public void ajoutMotCle(String text) {
		// TODO Auto-generated method stub
		MotClef mot = new MotClef(text);
		listeMotClefs.add(mot);
	}

	public Object[] afficherMotsCles() {
		// TODO Auto-generated method stub
		return listeMotClefs.toArray();
	}
}
