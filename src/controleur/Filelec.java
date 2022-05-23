package controleur;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import modele.Modele;
import vue.VueConnexion;
import vue.VueGenerale;

public class Filelec {
	
	private static VueConnexion uneVueConnexion;
	private static VueGenerale uneVueGenerale;
	
	public static void setVisibleVueConnexion (boolean action) {
		uneVueConnexion.setVisible(action);
	}
	
	public static void setVisibleVueGenerale (boolean action) {
		uneVueGenerale.setVisible(action);
	}
	
	public static void instancierVueGenerale (Client unClient) {
		uneVueGenerale = new VueGenerale(unClient);
	}
	
	public static void closeVueGenerale () {
		uneVueGenerale.dispose();
	}

	public static void main(String[] args) {
		uneVueConnexion = new VueConnexion();
	}
	
	public static Client selectWhereEmailClient (String email) {
		Client unClient = Modele.selectWhereEmailClient(email);
		return unClient;
	}
	
	public static Client selectWhereClient (String email, String mdp) {
		Client unClient = Modele.selectWhereClient(email, mdp);
		return unClient;
	}
	
	public static byte[] getSHA(String mdp) {
		byte[] tab = null;
		try { 
	        MessageDigest md = MessageDigest.getInstance("SHA1"); 
	        tab = md.digest(mdp.getBytes(StandardCharsets.UTF_8)); 
		} catch (NoSuchAlgorithmException exp) {
			exp.printStackTrace();
		}
		return tab;
    }
    
    public static String toHexString(byte[] tab) {
        BigInteger number = new BigInteger(1, tab); 
        StringBuilder hexString = new StringBuilder(number.toString(16)); 
        while (hexString.length() < 32) { 
            hexString.insert(0, '0'); 
        } 
        return hexString.toString(); 
    }
	
	public static String crypterMdp(String mdp) {
		return toHexString(getSHA(mdp));
	}

}
