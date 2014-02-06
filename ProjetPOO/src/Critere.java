import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Critere {
	private String nom;
	private Statement st;
	private ResultSet rs;

	public Critere(String nom,Statement st) {
		this.nom = nom;
		this.st = st;
	}

	public Critere() {
		// TODO Auto-generated constructor stub
	}

	public String getNomCritere() {
		return nom;
	}

	public void setNomCritere(String nom) {
		this.nom = nom;
	}

	public void creerCritere() {
		String CREER_CRITERE = "INSERT INTO CRITERE VALUES(null," + nom + ")";

		try {
			st.executeQuery(CREER_CRITERE);
		} catch (SQLException e) {
			System.out.println("Erreur de requete");
			e.printStackTrace();
		}

	}

	public String[] getCriteres(int idDomaine) {
		// TODO Auto-generated method stub
		String AFFICHER_CRITERES = "SELECT nomCritere FROM CRITERE c,DOMAINE d "
				+ "WHERE d.idDomaine="
				+ idDomaine
				+ " AND c.idDomaine=d.idDomaine";
		int nbLignes = 0;
		int i = 0;
		String[] listeCriteres = null;

		try {
			rs = st.executeQuery(AFFICHER_CRITERES);
			rs.last();
			nbLignes = rs.getRow();
			listeCriteres = new String[nbLignes];
			rs.first();

			while (i != nbLignes) {
				listeCriteres[i] = rs.getString(1);
				i++;
				rs.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeCriteres;
	}
}
