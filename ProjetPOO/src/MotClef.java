import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class MotClef {
	private Statement st;
	CategorieMotClef categorie = new CategorieMotClef(st);
	ResultSet rs;
	private String libelle;
	private ArrayList<MotClef> listeMotsClefs = new ArrayList<MotClef>();

	public MotClef(String libelle) {
		this.libelle = libelle;
	}

	public MotClef(Statement st) {
		this.st = st;
	}

	public int getIdMotClef() {
		int recupIdMotClef = 0;
		String id = "SELECT idMotClef FROM MOT_CLEF WHERE libelle = \""
				+ libelle + "\"";
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

	public void creerMotClef() {

		String CREER_MOT_CLEF = "INSERT INTO MOT_CLEF VALUES(null, \""
				+ libelle + "\")";

		try {
			st.executeQuery(CREER_MOT_CLEF);
		} catch (SQLException e) {
			System.out.println("Erreur de requete");
			e.printStackTrace();
		}

	}

	public String[] getNomMotClef() {
		int i = 0;
		int idCategorieMotClef = categorie.getIdCategorieMotClef();
		String recupMotClef = "SELECT libelle FROM MOT_CLEF WHERE idCategorieMotClef = \""
				+ idCategorieMotClef + "\"";

		int nbLignes = 0;
		try {
			rs = st.executeQuery(recupMotClef);
			rs.last();
			nbLignes = rs.getRow();
			rs.first();
		} catch (SQLException e) {
		}

		String[] motClef = new String[nbLignes];
		try {
			while (i != nbLignes) {
				motClef[i] = rs.getString(1);
				i++;
				rs.next();
			}

		} catch (SQLException e) {
		}
		return motClef;
	}

	public void enregistrerMotsClefs(int id) {
		// TODO Auto-generated method stub
		Iterator<MotClef> it = listeMotsClefs.iterator();
		while (it.hasNext()) {
			MotClef mot = it.next();
			try {
				String INSERER_MOT_CLEF = "INSERT INTO MOT_CLEF VALUES (null,\""
						+ mot.getNomMotClef() + "\"," + id + ")";
				st.executeUpdate(INSERER_MOT_CLEF);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}