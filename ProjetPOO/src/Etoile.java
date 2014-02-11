public enum Etoile {

	UNE_ETOILE("Une étoile", 1), DEUX_ETOILES("Deux étoiles", 2), TROIS_ETOILES(
			"Trois étoiles", 3), QUATRE_ETOILES("Quatre étoiles", 4), CINQ_ETOILES(
			"Cinq étoiles", 5);

	@SuppressWarnings("unused")
	private String intitule;
	@SuppressWarnings("unused")
	private int etoiles;

	Etoile(String intitule, int etoiles) {
		this.intitule = intitule;
		this.etoiles = etoiles;
	}
}
