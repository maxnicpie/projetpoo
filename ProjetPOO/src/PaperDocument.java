import java.sql.SQLException;
import java.sql.Statement;

public class PaperDocument extends Document {
	
	private String commentaire;
	private Statement st;

	public PaperDocument(String document,Statement st){
		super(document);
		this.st = st;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public void enregistrerPapier(int idDocument) {
		// TODO Auto-generated method stub
		String PAPER = "INSERT INTO PAPER_DOCUMENT VALUES (null,\"" + commentaire
				+ "\","+idDocument+" )";
		try {
			st.executeUpdate(PAPER);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
