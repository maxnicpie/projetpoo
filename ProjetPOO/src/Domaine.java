import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class Domaine {

	private String nom;
	private ArrayList<CategorieMotClef> listeCategoriesMotsClefs = new ArrayList<CategorieMotClef>();
	private ArrayList<Critere> listeCriteres = new ArrayList<Critere>();
	private Statement st;
	private ResultSet rs;

	public Domaine(Statement st) {
		this.st = st;
	}

	public Domaine(String nom, Statement st) {
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
		String id = "SELECT idDomaine FROM DOMAINE WHERE nomDomaine=\"" + nom
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
		String CREER_DOMAINE = "INSERT INTO DOMAINE VALUES (null,\"" + nom
				+ "\")";
		try {
			st.executeUpdate(CREER_DOMAINE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void supprimerDomaine() {
		String SUPPRIMER_DOMAINE = "DELETE FROM DOMAINE WHERE nomDomaine=\"" + nom
				+ "\"";
		try {
			st.executeUpdate(SUPPRIMER_DOMAINE);
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
		listeCategoriesMotsClefs.add(c);
	}

	public CategorieMotClef chercherCategorie(Object selectedValue) {
		// TODO Auto-generated method stub
		for (CategorieMotClef c : listeCategoriesMotsClefs) {
			if (c.getNomCategorieMotClef().equals(selectedValue.toString())) {
				return c;
			}
		}
		return null;
	}

	public Object[] afficherCategories() {
		// TODO Auto-generated method stub
		return listeCategoriesMotsClefs.toArray();
	}

	public void ajoutCritere(String nom) {
		// TODO Auto-generated method stub
		Critere c = new Critere(nom,st);
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
				String INSERER_CRITERE = "INSERT INTO CRITERE VALUES (null,\""
						+ c.getNomCritere() + "\"," + id + ")";
				st.executeUpdate(INSERER_CRITERE);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void enregistrerCategoriesMotsClefs(int id) {
		// TODO Auto-generated method stub
		Iterator<CategorieMotClef> it = listeCategoriesMotsClefs.iterator();
		while (it.hasNext()) {
			CategorieMotClef c = it.next();
			try {
				String INSERER_CATEGORIE = "INSERT INTO CATEGORIE_MOT_CLEF VALUES (null,\""
						+ c.getNomCategorieMotClef() + "\"," + id + ")";
				st.executeUpdate(INSERER_CATEGORIE);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String[] getCategories(int idDomaine) {
		// TODO Auto-generated method stub

		String AFFICHER_CATEGORIES = "SELECT nomCategorieMotClef "
				+ "FROM CATEGORIE_MOT_CLEF, DOMAINE "
				+ "WHERE DOMAINE.idDomaine = " + idDomaine + " "
				+ "AND DOMAINE.idDomaine = CATEGORIE_MOT_CLEF.idDomaine";
		int nbLignes = 0;
		int i = 1;
		String[] listeCategories = null;

		try {
			rs = st.executeQuery(AFFICHER_CATEGORIES);
			rs.last();
			nbLignes = rs.getRow();
			listeCategories = new String[nbLignes+1];
			rs.first();
			
			listeCategories[0]="Categorie...";

			while (i != nbLignes+1) {
				listeCategories[i] = rs.getString(1);
				i++;
				rs.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeCategories;
	}

	public String[] getMotsClefs(String selectedCategorie) {
		// TODO Auto-generated method stub
		String AFFICHER_MOTSCLES = "SELECT libelle FROM MOT_CLEF,CATEGORIE_MOT_CLEF "+
									"WHERE nomCategorieMotClef = \""+selectedCategorie+
									"\""+"AND MOT_CLEF.idCategorieMotClef=CATEGORIE_MOT_CLEF.idCategorieMotClef";
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

}
