import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class CategorieMotClef {
	ResultSet rs;
	private String nom;
	private Statement st;
	Domaine domaine = new Domaine(st);
	private ArrayList<MotClef> listeMotsClefs = new ArrayList<MotClef>();

	public CategorieMotClef(String nom) {
		this.nom = nom;
	}

	public CategorieMotClef(Statement st) {
		this.st = st;
	}

	public CategorieMotClef() {

	}

	public String toString() {
		return nom;
	}

	public String getNomCategorieMotClef() {
		return nom;
	}

	public void setNomCategorieMotClef(String nom) {
		this.nom = nom;
	}

	public int getIdCategorieMotClef() {
		int recupIdCategorieMotClef = 0;
		String id = "SELECT idCategorieMotClef FROM CATEGORIE_MOT_CLEF WHERE nomCategorieMotClef=\""
				+ nom + "\"";
		try {
			rs = st.executeQuery(id);
			rs.next();
			recupIdCategorieMotClef = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		return recupIdCategorieMotClef;
	}

	public void creerCategorieMotClef() {
		String CREER_CATEGORIE_MOT_CLEF = "INSERT INTO CATEGORIE_MOT_CLEF VALUES(null, \""
				+ nom + "\")";
		try {
			st.executeUpdate(CREER_CATEGORIE_MOT_CLEF);
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

	public void enregistrerMotsClefs(int id) {
		// TODO Auto-generated method stub
		Iterator<MotClef> it = listeMotsClefs.iterator();
		while (it.hasNext()) {
			MotClef mot = it.next();
			try {
				String INSERER_MOT_CLEF = "INSERT INTO MOT_CLEF VALUES (null,\""
						+ mot.getLibelleMotClef() + "\"," + id + ")";
				st.executeUpdate(INSERER_MOT_CLEF);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
