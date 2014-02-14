import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ListModel;

public class Document {
	private Statement st;
	private ResultSet rs;
	private String nom;

	public Document(String document) {
		// TODO Auto-generated constructor stub
	}

	public Document(Statement st2) {
		// TODO Auto-generated constructor stub
		this.st = st2;
	}

	public int getIdDocument() {
		int recupIdDocument = 0;
		String id = "SELECT idDocument FROM DOCUMENT where titre = \"" + nom
				+ "\"";
		try {
			rs = st.executeQuery(id);
			rs.next();
			recupIdDocument = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		return recupIdDocument;
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

	public void affecterMotsClefs(int idDomaine,
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

	public String getNom() {
		// TODO Auto-generated method stub
		return nom;
	}

	public String[] getDocumentByMotClef(MotClef motClef) {
		int i = 0;
		int nbLignes = 0;
		String[] document = null;
		String recupDocument = "SELECT titre FROM DOCUMENT d, EST_TAGE e, MOT_CLEF m  "
				+ "WHERE m.idMotClef = e.idMotClef AND d.idDocument = e.idDocument AND m.idMotClef = \""
				+ motClef.getIdMotClef() + "\"";
		try {
			rs = st.executeQuery(recupDocument);
			rs.last();
			nbLignes = rs.getRow();
			document = new String[nbLignes + 1];
			rs.first();
			while (i != nbLignes) {
				document[i] = rs.getString(1);
				i++;
				rs.next();
			}
		} catch (SQLException e) {
		}
		return document;
	}

	public String[] getDocumentByCritere(Critere critere) {
		int i = 0;
		int nbLignes = 0;
		String[] document = null;
		String recupDocument = "SELECT titre FROM DOCUMENT d, NOTE n, CRITERE c "
				+ "WHERE c.idCritere = n.idCritere AND d.idDocument = n.idDocument AND c.idCritere = \""
				+ critere.getIdCritere() + "\"";
		try {
			rs = st.executeQuery(recupDocument);
			rs.last();
			nbLignes = rs.getRow();
			document = new String[nbLignes + 1];
			rs.first();
			while (i != nbLignes) {
				document[i] = rs.getString(1);
				i++;
				rs.next();
			}
		} catch (SQLException e) {
		}
		return document;
	}
}