import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class Document {
	private Statement st;
	MotClef motclef = new MotClef(st);	
	@SuppressWarnings("unused")
	private String titre;
	private ResultSet rs;
	@SuppressWarnings("unused")
	private String nom;

	public Document(Statement st) {
		this.st = st;
	}

	public Document(String nom){
		this.nom = nom;
	}

	public String[] getNomDocument() {
		int i = 0;
		int idMotClef = motclef.getIdMotClef();

		String recupDocument = "SELECT titre FROM DOCUMENT, EST_TAGE WHERE idMotClef = \""
				+ idMotClef + "\"";
		int nbLignes = 0;
		try {
			rs = st.executeQuery(recupDocument);
			rs.last();
			nbLignes = rs.getRow();
			rs.first();
		}catch (SQLException e) {
		}
		
		String[] document = new String[nbLignes];
		try {
			while(i!=nbLignes){
				document[i] = rs.getString(1);
				i++;
				rs.next();
			}

		} catch (SQLException e) {
		}
		return document;
	}

}