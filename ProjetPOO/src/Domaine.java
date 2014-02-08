import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Domaine {

	private String nom;
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
		String SUPPRIMER_DOMAINE = "DELETE FROM DOMAINE WHERE nomDomaine=\""
				+ nom + "\"";
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

}
