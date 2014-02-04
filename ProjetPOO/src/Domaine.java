import java.sql.SQLException;
import com.mysql.jdbc.Statement;

public class Domaine {

	private String nom;
	private Statement st;
	
	private String CREER_DOMAINE = "INSERT INTO DOMAINE VALUES (null,\""+nom+"\")";
	private String SUPPRIMER_DOMAINE = "DELETE FROM DOMAINE WHERE nomDomaine=\""+nom+"\"";
	private String AFFICHER_LISTE_DOMAINES = "SELECT nomDomaine FROM DOMAINE";
	
	public Domaine(String nom) {
		this.nom = nom;
	}
	
	public Domaine() {}
	
	public String getNom() {
		return nom;
	}
 
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void creerDomaine(String nom) {
		try {
			st.executeQuery(CREER_DOMAINE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void supprimerDomaine() {
		try {
			st.executeQuery(SUPPRIMER_DOMAINE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void afficherListeDomaines() {
		try {
			st.executeQuery(AFFICHER_LISTE_DOMAINES);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}