package controleur;

public class Type {
	
	private int idtype;
	private String libelle;
	
	public Type(int idtype, String libelle) {
		this.idtype = idtype;
		this.libelle = libelle;
	}
	
	public Type(String libelle) {
		this.idtype = 0;
		this.libelle = libelle;
	}

	public int getIdtype() {
		return idtype;
	}

	public void setIdtype(int idtype) {
		this.idtype = idtype;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}
