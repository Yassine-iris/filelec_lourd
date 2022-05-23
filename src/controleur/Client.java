package controleur;

public class Client {
	
	protected int idclient;
	protected String nom, tel, email, mdp, adresse, cp, ville, pays, etat, role, type;
	private int nbTentatives, bloque;
	
	public Client(int idclient, String nom, String tel, String email, String mdp, String adresse, String cp,
			String ville, String pays, String etat, String role, String type, int nbTentatives, int bloque) {
		this.idclient = idclient;
		this.nom = nom;
		this.tel = tel;
		this.email = email;
		this.mdp = mdp;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
		this.pays = pays;
		this.etat = etat;
		this.role = role;
		this.type = type;
		this.nbTentatives = nbTentatives;
		this.bloque = bloque;
	}
	
	public Client(String nom, String tel, String email, String mdp, String adresse, String cp,
			String ville, String pays, String etat, String role, String type, int nbTentatives, int bloque) {
		this.idclient = 0;
		this.nom = nom;
		this.tel = tel;
		this.email = email;
		this.mdp = mdp;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
		this.pays = pays;
		this.etat = etat;
		this.role = role;
		this.type = type;
		this.nbTentatives = nbTentatives;
		this.bloque = bloque;
	}

	public int getIdclient() {
		return idclient;
	}

	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public int getNbTentatives() {
		return nbTentatives;
	}

	public void setNbTentatives(int nbTentatives) {
		this.nbTentatives = nbTentatives;
	}
	
	public int getBloque() {
		return bloque;
	}

	public void setBloque(int bloque) {
		this.bloque = bloque;
	}

}
