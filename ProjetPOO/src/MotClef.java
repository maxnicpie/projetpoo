import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class MotClef {
	private Statement st;
	CategorieMotClef categorie = new CategorieMotClef(st);
	ResultSet rs;
	private String libelle;

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
	
	public int getIdMotClef() {
		int recupIdMotClef = 0;
		String id = "SELECT idMotClef FROM MOT_CLEF WHERE libelle=\""+ libelle +"\"";
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

	public String[] getMotsClefs() {
		int i = 0;
		int idCategorieMotClef = categorie.getIdCategorieMotClef();
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
}