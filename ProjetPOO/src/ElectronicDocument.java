import java.sql.SQLException;
import java.sql.Statement;

public class ElectronicDocument extends Document {

	private String link;
	private Statement st;

	public ElectronicDocument(String document, Statement st) {
		super(document);
		this.st = st;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void enregistrerElectronique(int idDocument) {
		// TODO Auto-generated method stub
		String ELECTRONIQUE = "INSERT INTO PAPER_DOCUMENT VALUES (null,\""
				+ link + "\"," + idDocument + " )";
		try {
			st.executeUpdate(ELECTRONIQUE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}