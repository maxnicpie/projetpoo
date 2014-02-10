import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ListModel;

public class Document {
	private Statement st;
	MotClef motclef = new MotClef(st);
	private String titre;
	private ResultSet rs;
	@SuppressWarnings("unused")
	private String nom;

	public Document(Statement st) {
		this.st = st;
	}

	public Document(String nom) {
		this.nom = nom;
	}

	public int getIdDocument() {
		int recupIdDocument = 0;
		String id = "SELECT idDocument FROM DOCUMENT where titre = \"" + titre
				+ "\"";
		try {
			rs.next();
			rs = st.executeQuery(id);
			recupIdDocument = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		return recupIdDocument;
	}

	public String[] getNomDocumentByMotsCles() {
		int i = 0;
		int idMotClef = motclef.getIdMotClef();
		// int idDocument = getIdDocument();

		String recupDocument = "SELECT titre FROM DOCUMENT d, EST_TAGE e, MOT_CLEF m  "
				+ "WHERE m.idMotClef = e.idMotClef AND d.idDocument=e.idDocument AND m.idMotClef = \""
				+ idMotClef + "\";";

		int nbLignes = 0;
		try {
			rs = st.executeQuery(recupDocument);
			rs.last();
			nbLignes = rs.getRow();
			rs.first();
		} catch (SQLException e) {
		}

		String[] document = new String[nbLignes];
		try {
			while (i != nbLignes) {
				document[i] = rs.getString(1);
				i++;
				rs.next();
			}

		} catch (SQLException e) {
		}
		return document;
	}

	public void setNom(String string) {
		// TODO Auto-generated method stub
		this.nom = string;
	}

	public void creerDocument() {
		// TODO Auto-generated method stub
		String CREER_DOCUMENT = "INSERT INTO DOCUMENT VALUES (null,\"" + nom
				+ "\")";
		try {
			st.executeUpdate(CREER_DOCUMENT);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void affecterMotsCles(int idDomaine,
			@SuppressWarnings("rawtypes") ListModel model) {
		// TODO Auto-generated method stub
		for (int i = 0; i < model.getSize(); i++) {
			MotClef mot = new MotClef(model.getElementAt(i).toString(), st);
			String AFFECTER_MOTSCLES = "INSERT INTO EST_TAGE VALUES ("
					+ idDomaine + "," + mot.getIdMotClef() + ")";
			try {
				st.executeUpdate(AFFECTER_MOTSCLES);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}