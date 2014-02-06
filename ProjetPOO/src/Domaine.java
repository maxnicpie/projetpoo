import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Domaine {

	private String nom;
	private ArrayList<CategorieMotClef> listeCategoriesMotClefs = new ArrayList<CategorieMotClef>();
	private ArrayList<Critere> listeCriteres = new ArrayList<Critere>();
	private Statement st;
	private ResultSet rs;
	
	public String recupIdDomaine(){
    	String id = "SELECT idDomaine FROM DOMAINE where nomDomaine = \""+nom+"\"";
		try {
			st.executeQuery(id);
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		return id;
	}
	
	public Domaine(Statement st) {
		this.st = st;
	}

	public String getNom() {
		return nom;
	}
 
	public void setNom(String nom){
		this.nom = nom;
	}

	public void creerDomaine() {
		String CREER_DOMAINE = "INSERT INTO DOMAINE VALUES (null,\"" + nom
				+ "\")";
		try {
			st.executeQuery(CREER_DOMAINE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void supprimerDomaine() {
		String SUPPRIMER_DOMAINE = "DELETE FROM DOMAINE WHERE nomDomaine=\""
				+ nom + "\"";
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

	public void enregistrerCriteres() {
		// TODO Auto-generated method stub
		
	}

	public void enregistrerCategoriesMotsCles() {
		// TODO Auto-generated method stub
		for (CategorieMotClef c : listeCategoriesMotClefs) {
			//enregistrement categorie dans BDD
			//enregistrement tout les mots clés associés
		}
	}
}
