import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class Document {
	MotClef motclef = new MotClef();
	private Statement st;
	private String titre;

	public Document(String titre) {
		this.titre = titre;
	}

	public Document() {

	}

	public void recupererDocument() {

		String idMotClef = motclef.recupIdMotClef();

		String recupDocument = "SELECT titre FROM DOCUMENT, EST_TAGE WHERE idMotClef = \""
				+ idMotClef + "\"";

		try {
			st.executeQuery(recupDocument);
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}

	}

}