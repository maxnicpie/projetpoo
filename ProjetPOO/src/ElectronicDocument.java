public class ElectronicDocument extends Document {

	private String link;

	public ElectronicDocument(String document) {
		super(document);
	}

	public ElectronicDocument(String document, String link) {
		this(document);
		this.setLink(link);
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
