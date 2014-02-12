import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class MotClef {
	private Statement st;
	private ResultSet rs;
	private String libelle;
	private ArrayList<MotClef> listeMotsClefs = new ArrayList<MotClef>();

	public MotClef(String libelle) {
		this.libelle = libelle;
	}

	public MotClef(Statement st) {
		this.st = st;
	}

	public MotClef(String libelle, Statement st) {
		this.st = st;
		this.libelle = libelle;
	}

	public MotClef() {
		// TODO Auto-generated constructor stub
	}

	public int getIdMotClef() {
		int recupIdMotClef = 0;
		String id = "SELECT idMotClef FROM MOT_CLEF WHERE libelle=\"" + libelle
				+ "\"";
		try {
			rs = st.executeQuery(id);
			rs.next();
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
			st.executeUpdate(CREER_MOT_CLEF);
		} catch (SQLException e) {
			System.out.println("Erreur de requete");
			e.printStackTrace();
		}
	}

	public String[] getMotsClefs(CategorieMotClef c) {
		int i = 0;
		int idCategorieMotClef = c.getIdCategorieMotClef();
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

	public String[] getMotsClefs(String selectedCategorie) {
		// TODO Auto-generated method stub
		String AFFICHER_MOTSCLES = "SELECT libelle FROM MOT_CLEF,CATEGORIE_MOT_CLEF "
				+ "WHERE nomCategorieMotClef = \""
				+ selectedCategorie
				+ "\""
				+ "AND MOT_CLEF.idCategorieMotClef=CATEGORIE_MOT_CLEF.idCategorieMotClef";
		int nbLignes = 0;
		int i = 0;
		String[] listeMotsCles = null;

		try {
			rs = st.executeQuery(AFFICHER_MOTSCLES);
			rs.last();
			nbLignes = rs.getRow();
			listeMotsCles = new String[nbLignes];
			rs.first();

			while (i != nbLignes) {
				listeMotsCles[i] = rs.getString(1);
				i++;
				rs.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeMotsCles;
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

	public void ajouterMotClef(String text) {
		// TODO Auto-generated method stub
		MotClef mot = new MotClef(text, st);
		listeMotsClefs.add(mot);
	}

	public Object[] afficherListeMotsClefs() {
		// TODO Auto-generated method stub
		return listeMotsClefs.toArray();
	}

	public ArrayList<MotClef> getListeMotsClefs() {
		// TODO Auto-generated method stub
		return listeMotsClefs;
	}

	public void supprimerMotClef(Object selectedValue) {
		// TODO Auto-generated method stub
		MotClef temp = new MotClef();
		for (MotClef mot : listeMotsClefs) {
			if (mot.getLibelleMotClef().equals(selectedValue.toString())) {
				temp = mot;
			}
		}
		listeMotsClefs.remove(temp);
	}
}