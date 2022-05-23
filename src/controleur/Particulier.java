package controleur;

public class Particulier extends Client {
	
	private String prenom;

	public Particulier(int idclient, String nom, String tel, String email, String mdp, String adresse, String cp,
			String ville, String pays, String etat, String role, String type, String prenom) {
		super(idclient, nom, tel, email, mdp, adresse, cp, ville, pays, etat, role, type, 0, 0);
		this.prenom = prenom;
	}
	
	public Particulier(String nom, String tel, String email, String mdp, String adresse, String cp,
			String ville, String pays, String etat, String role, String type, String prenom) {
		super(nom, tel, email, mdp, adresse, cp, ville, pays, etat, role, type, 0, 0);
		this.prenom = prenom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

}
