import java.sql.SQLException;
import java.sql.Statement;

public class Note {
	private int etoiles;
	private Statement st;

	public Note(Statement sta, Integer value) {
		// TODO Auto-generated constructor stub
		this.st = sta;
		this.etoiles = value;
	}

	public void enregistrerNote(String selectedItem, int idDocument) {
		// TODO Auto-generated method stub
		Critere critere = new Critere(st);

		String NOTE = "INSERT INTO note VALUES (null," + etoiles + ","
				+ critere.getIdCritere(selectedItem) + "," + idDocument + ")";
		try {
			st.executeUpdate(NOTE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}