import java.sql.SQLException;
import java.sql.Statement;

public class Critere {
	private String nom;
	private Statement st;
	private String CREER_CRITERE = "INSERT INTO CRITERE VALUES(null, \""
			+ nom + "\")";

	public Critere(String nom) {
		this.nom = nom;
	}
	
	public String getCritere(){
		return nom;
	}
	
	public void setCritere(String nom){
		this.nom=nom;
	}
	
	public void creer_critere(String nom) {
		try {
			st.executeQuery(CREER_CRITERE);
		} catch (SQLException e) {
			System.out.println("Erreur de requete");
			e.printStackTrace();
		}

	}
}
