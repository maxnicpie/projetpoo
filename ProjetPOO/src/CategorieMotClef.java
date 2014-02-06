import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategorieMotClef {
	ResultSet rs;
	private String nom;
	private Statement st;
	Domaine domaine = new Domaine(st);
	private ArrayList<MotClef> listeMotsClefs = new ArrayList<MotClef>();

	public CategorieMotClef(String text) {
		this.nom = text;
	}
	
	public CategorieMotClef(Statement st) {
		this.st = st;
	}
	
	public CategorieMotClef() {

	}
	
	public String getNomCategorieMotClef() {
		return nom;
	}

	public void setNomCategorieMotClef(String nom) {
		this.nom = nom;
	}

	public int getIdCategorieMotClef() {
		int recupIdCategorieEtudiant = 0;
		String id = "SELECT idCategorieMotClef FROM CATEGORIEMOTCLEF WHERE nomCategorieMotClef="
				+ nom;
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

	public void creerCategorieMotClef() {
		String CREER_CATEGORIE_MOT_CLEF = "INSERT INTO CATEGORIEMOTCLEF VALUES(null,"
				+ nom + ")";
		try {
			st.executeQuery(CREER_CATEGORIE_MOT_CLEF);
		} catch (SQLException e) {
			System.out.println("Erreur de requete");
			e.printStackTrace();
		}
	}

	public void ajouterMotClef(String text) {
		// TODO Auto-generated method stub
		MotClef mot = new MotClef(text);
		listeMotsClefs.add(mot);
	}

	public Object[] afficherListeMotsClefs() {
		// TODO Auto-generated method stub
		return listeMotsClefs.toArray();
	}
}
