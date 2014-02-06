import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MotClef {
	private Statement st;
	CategorieMotClef categorie_mot_clef = new CategorieMotClef(st);
	ResultSet rs;
	private String libelle;
	
	public MotClef(String libelle) {
		this.libelle = libelle;
	}
	
	public MotClef(Statement st) {
		this.st = st;
	}

	public int getIdMotClef(){
		int recupIdMotClef = 0;
    	String id = "SELECT idMotClef FROM MOT_CLEF where libelle = \""+libelle+"\"";
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

	public void creer_mot_clef() {
		
		String CREER_MOT_CLEF = "INSERT INTO MOT_CLEF VALUES(null, \""
				+ libelle + "\")";
		
		try {
			st.executeQuery(CREER_MOT_CLEF);
		} catch (SQLException e) {
			System.out.println("Erreur de requete");
			e.printStackTrace();
		}

	}
	
	public String[] getNomMotClef(){
		int i = 0;
		int idCategorieMotClef = categorie_mot_clef.getIdCategorieMotClef();
		String recupMotClef = "SELECT libelle FROM MOT_CLEF WHERE idCategorieMotClef = \""
				+ idCategorieMotClef + "\"";
		
		int nbLignes = 0;
		try {
			rs = st.executeQuery(recupMotClef);
			rs.last();
			nbLignes = rs.getRow();
			rs.first();
		}catch (SQLException e) {
		}
		
		String[] motClef = new String[nbLignes];
		try {
			while(i!=nbLignes){
				motClef[i] = rs.getString(1);
				i++;
				rs.next();
			}

		} catch (SQLException e) {
		}
		return motClef;
	}
}
