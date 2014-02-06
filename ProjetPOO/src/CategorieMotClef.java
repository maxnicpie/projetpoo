import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategorieMotClef {

	private String nom;
	private Statement st;
	private ArrayList<MotClef> listeMotClefs  = new ArrayList<MotClef>();

	public CategorieMotClef(String nom) {
		this.nom = nom;
	}

	public String getNomCategorieMotClef() {
		return nom;
	}

	public void setNomCategorieMotClef(String nom) {
		this.nom = nom;
	}

	public String toString() {
		return nom;
	}

	public void creer_categorie_mot_clef() {

		String CREER_CATEGORIE_MOT_CLEF = "INSERT INTO CATEGORIEMOTCLEF VALUES(null, \""
				+ nom + "\")";
		try {
			st.executeQuery(CREER_CATEGORIE_MOT_CLEF);
		} catch (SQLException e) {
			System.out.println("Erreur de requete");
			e.printStackTrace();
		}

	}

	public void ajoutMotCle(String text) {
		// TODO Auto-generated method stub
		MotClef mot = new MotClef(text);
		listeMotClefs.add(mot);
	}

	public Object[] afficherMotsCles() {
		// TODO Auto-generated method stub
		return listeMotClefs.toArray();
	}
}
