package controleur;

public class Professionnel extends Client {
	
	private String numSITRET, statut;

	public Professionnel(int idclient, String nom, String tel, String email, String mdp, String adresse, String cp,
			String ville, String pays, String etat, String role, String type, String numSITRET, String statut) {
		super(idclient, nom, tel, email, mdp, adresse, cp, ville, pays, etat, role, type, 0, 0);
		this.numSITRET = numSITRET;
		this.statut = statut;
	}
	
	public Professionnel(String nom, String tel, String email, String mdp, String adresse, String cp,
			String ville, String pays, String etat, String role, String type, String numSITRET, String statut) {
		super(nom, tel, email, mdp, adresse, cp, ville, pays, etat, role, type, 0, 0);
		this.numSITRET = numSITRET;
		this.statut = statut;
	}

	public String getNumSITRET() {
		return numSITRET;
	}

	public void setNumSITRET(String numSITRET) {
		this.numSITRET = numSITRET;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

}
