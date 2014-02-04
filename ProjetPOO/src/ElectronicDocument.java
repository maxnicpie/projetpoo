public class ElectronicDocument extends Document {
	
	private String link;
	
	public ElectronicDocument(String document){
		super(document);
	}
	
	public ElectronicDocument(String document, String link){
		this(document);
		this.link = link;
	}
}