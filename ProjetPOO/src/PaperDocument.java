
public class PaperDocument extends Document {
	
	private String commentaire;

	public PaperDocument(String document){
		super(document);
	}
	
	public PaperDocument(String document, String commentaire){
		this(document);
		this.commentaire=commentaire;
	}
	

}
