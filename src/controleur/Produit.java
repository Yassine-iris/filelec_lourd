package controleur;

public class Produit {
	
	private int idproduit;
	private String nomproduit, imageproduit, descriptionproduit;
	private int qteproduit;
	private float prixproduit;
	private int idtype;
	private String date_ajout;
	
	public Produit(int idproduit, String nomproduit, String imageproduit, String descriptionproduit, int qteproduit,
			float prixproduit, int idtype, String date_ajout) {
		this.idproduit = idproduit;
		this.nomproduit = nomproduit;
		this.imageproduit = imageproduit;
		this.descriptionproduit = descriptionproduit;
		this.qteproduit = qteproduit;
		this.prixproduit = prixproduit;
		this.idtype = idtype;
		this.date_ajout = date_ajout;
	}
	
	public Produit(String nomproduit, String imageproduit, String descriptionproduit, int qteproduit,
			float prixproduit, int idtype, String date_ajout) {
		this.idproduit = 0;
		this.nomproduit = nomproduit;
		this.imageproduit = imageproduit;
		this.descriptionproduit = descriptionproduit;
		this.qteproduit = qteproduit;
		this.prixproduit = prixproduit;
		this.idtype = idtype;
		this.date_ajout = date_ajout;
	}

	public int getIdproduit() {
		return idproduit;
	}

	public void setIdproduit(int idproduit) {
		this.idproduit = idproduit;
	}

	public String getNomproduit() {
		return nomproduit;
	}

	public void setNomproduit(String nomproduit) {
		this.nomproduit = nomproduit;
	}

	public String getImageproduit() {
		return imageproduit;
	}

	public void setImageproduit(String imageproduit) {
		this.imageproduit = imageproduit;
	}

	public String getDescriptionproduit() {
		return descriptionproduit;
	}

	public void setDescriptionproduit(String descriptionproduit) {
		this.descriptionproduit = descriptionproduit;
	}

	public int getQteproduit() {
		return qteproduit;
	}

	public void setQteproduit(int qteproduit) {
		this.qteproduit = qteproduit;
	}

	public float getPrixproduit() {
		return prixproduit;
	}

	public void setPrixproduit(float prixproduit) {
		this.prixproduit = prixproduit;
	}

	public int getIdtype() {
		return idtype;
	}

	public void setIdtype(int idtype) {
		this.idtype = idtype;
	}

	public String getDate_ajout() {
		return date_ajout;
	}

	public void setDate_ajout(String date_ajout) {
		this.date_ajout = date_ajout;
	}

}
