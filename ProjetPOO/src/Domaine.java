import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class Domaine {

	private String nom;
	private ArrayList<CategorieMotClef> listeCategoriesMotClefs = new ArrayList<CategorieMotClef>();
	private ArrayList<Critere> listeCriteres = new ArrayList<Critere>();
	private Statement st;
	private ResultSet rs;

	public Domaine(Statement st) {
		this.st = st;
	}
	
	public Domaine(String nom,Statement st) {
		this.nom = nom;
		this.st = st;
	}

	public String getNomDomaine() {
		return nom;
	}

	public void setNomDomaine(String nom) {
		this.nom = nom;
	}

	public int getIdDomaine() {
		int recupId = 0;
		String id = "SELECT idDomaine FROM DOMAINE where nomDomaine = \"" + nom
				+ "\"";
		try {
			rs = st.executeQuery(id);
			rs.next();
			recupId = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		return recupId;
	}

	public void creerDomaine() {
		String CREER_DOMAINE = "INSERT INTO DOMAINE VALUES (null," + nom + ")";
		try {
			st.executeQuery(CREER_DOMAINE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void supprimerDomaine() {
		String SUPPRIMER_DOMAINE = "DELETE FROM DOMAINE WHERE nomDomaine="
				+ nom;
		try {
			st.executeQuery(SUPPRIMER_DOMAINE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String[] afficherListeDomaines() {
		String AFFICHER_LISTE_DOMAINES = "SELECT nomDomaine FROM DOMAINE";
		int nbLignes = 0;
		int i = 0;
		String[] listeDomaines = null;

		try {
			rs = st.executeQuery(AFFICHER_LISTE_DOMAINES);
			rs.last();
			nbLignes = rs.getRow();
			listeDomaines = new String[nbLignes];
			rs.first();

			while (i != nbLignes) {
				listeDomaines[i] = rs.getString(1);
				i++;
				rs.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeDomaines;
	}

	public void ajouterCategorie(String text) {
		// TODO Auto-generated method stub
		CategorieMotClef c = new CategorieMotClef(text);
		listeCategoriesMotClefs.add(c);
	}

	public CategorieMotClef chercherCategorie(Object selectedValue) {
		// TODO Auto-generated method stub
		for (CategorieMotClef c : listeCategoriesMotClefs) {
			if (c.getNomCategorieMotClef().equals(selectedValue.toString())) {
				return c;
			}
		}
		return null;
	}

	public Object[] afficherCategories() {
		// TODO Auto-generated method stub
		return listeCategoriesMotClefs.toArray();
	}

	public void ajoutCritere(String text) {
		// TODO Auto-generated method stub
		Critere c = new Critere(text);
		listeCriteres.add(c);
	}

	public Object[] afficherCriteres() {
		// TODO Auto-generated method stub
		return listeCriteres.toArray();
	}

	public ArrayList<Critere> getListeCriteres() {
		// TODO Auto-generated method stub
		return listeCriteres;
	}

	public void enregistrerCriteres(int id) {
		// TODO Auto-generated method stub
		Iterator<Critere> it = listeCriteres.iterator();
		while (it.hasNext()) {
			Critere c = it.next();
			try {
				String INSERER_CRITERE = "INSERT INTO CRITERE VALUES (null,"
						+ c.getNomCritere() + "," + id + ")";
				st.executeQuery(INSERER_CRITERE);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void enregistrerCategoriesMotsCles(int id) {
		// TODO Auto-generated method stub
		Iterator<CategorieMotClef> it = listeCategoriesMotClefs.iterator();
		while (it.hasNext()) {
			CategorieMotClef c = it.next();
			try {
				String INSERER_CRITERE = "INSERT INTO CATEGORIEMOTCLEF VALUES (null,"
						+ c.getNomCategorieMotClef() + "," + id + ")";
				st.executeQuery(INSERER_CRITERE);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String[] getCategories(int idDomaine) {
		// TODO Auto-generated method stub
		String AFFICHER_CATEGORIES = "select nomCategorieMotClef "+
									 "FROM CATEGORIEMOTCLEF,DOMAINE "+
									 "where DOMAINE.idDomaine=1 "+
									 "AND DOMAINE.idDomaine=CATEGORIEMOTCLEF.idDomaine";
		int nbLignes = 0;
		int i = 0;
		String[] listeCategories = null;

		try {
			rs = st.executeQuery(AFFICHER_CATEGORIES);
			rs.last();
			nbLignes = rs.getRow();
			listeCategories = new String[nbLignes];
			rs.first();

			while (i != nbLignes) {
				listeCategories[i] = rs.getString(1);
				i++;
				rs.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeCategories;
	}
}
