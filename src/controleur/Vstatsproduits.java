package controleur;

public class Vstatsproduits {
	
	private String nomproduit, autoradio, gps, aide_a_la_conduite, haut_parleurs, kit_mains_libre, amplificateurs;

	public Vstatsproduits(String nomproduit, String autoradio, String gps, String aide_a_la_conduite,
			String haut_parleurs, String kit_mains_libre, String amplificateurs) {
		this.nomproduit = nomproduit;
		this.autoradio = autoradio;
		this.gps = gps;
		this.aide_a_la_conduite = aide_a_la_conduite;
		this.haut_parleurs = haut_parleurs;
		this.kit_mains_libre = kit_mains_libre;
		this.amplificateurs = amplificateurs;
	}

	public String getNomproduit() {
		return nomproduit;
	}

	public void setNomproduit(String nomproduit) {
		this.nomproduit = nomproduit;
	}

	public String getAutoradio() {
		return autoradio;
	}

	public void setAutoradio(String autoradio) {
		this.autoradio = autoradio;
	}

	public String getGps() {
		return gps;
	}

	public void setGps(String gps) {
		this.gps = gps;
	}

	public String getAide_a_la_conduite() {
		return aide_a_la_conduite;
	}

	public void setAide_a_la_conduite(String aide_a_la_conduite) {
		this.aide_a_la_conduite = aide_a_la_conduite;
	}

	public String getHaut_parleurs() {
		return haut_parleurs;
	}

	public void setHaut_parleurs(String haut_parleurs) {
		this.haut_parleurs = haut_parleurs;
	}

	public String getKit_mains_libre() {
		return kit_mains_libre;
	}

	public void setKit_mains_libre(String kit_mains_libre) {
		this.kit_mains_libre = kit_mains_libre;
	}

	public String getAmplificateurs() {
		return amplificateurs;
	}

	public void setAmplificateurs(String amplificateurs) {
		this.amplificateurs = amplificateurs;
	}

}
