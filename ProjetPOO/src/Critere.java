import java.sql.SQLException;
import java.sql.Statement;

public class Critere {
	private String nom;
	private Statement st;
	

	public Critere(String nom) {
		this.nom = nom;
	}
	
	public String getNomCritere(){
		return nom;
	}
	
	public void setCritere(String nom){
		this.nom=nom;
	}
	
	public String toString(){
		return nom;
	}
	
	public void creer_critere() {
		String CREER_CRITERE = "INSERT INTO CRITERE VALUES(null, \""
				+ nom + "\")";
		
		try {
			st.executeQuery(CREER_CRITERE);
		} catch (SQLException e) {
			System.out.println("Erreur de requete");
			e.printStackTrace();
		}

	}
}
