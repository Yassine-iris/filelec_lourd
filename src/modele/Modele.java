package modele;

import java.sql.*;
import java.io.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Client;
import controleur.Particulier;
import controleur.Produit;
import controleur.Professionnel;
import controleur.Type;
import controleur.VProduit;
import controleur.Vstatsproduits;

public class Modele {
	
	private static Bdd uneBdd = new Bdd ("localhost", "filelec", "root", "");
	
	/*** GESTION DES CLIENTS ***/
	
	// Sélection d'un client en fonction de son email
	public static Client selectWhereEmailClient (String email) {
		Client unClient = null;
		String requete = "SELECT * FROM client WHERE email = '"+email+"';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				unClient = new Client (
						unResultat.getInt("idclient"),
						unResultat.getString("nom"),
						unResultat.getString("tel"),
						unResultat.getString("email"),
						unResultat.getString("mdp"),
						unResultat.getString("adresse"),
						unResultat.getString("cp"),
						unResultat.getString("ville"),
						unResultat.getString("pays"),
						unResultat.getString("etat"),
						unResultat.getString("role"),
						unResultat.getString("type"),
						unResultat.getInt("nbTentatives"),
						unResultat.getInt("bloque")
						);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return unClient;
	}
	
	// Sélection d'un client en fonction de son email et de son mdp
	public static Client selectWhereClient (String email, String mdp) {
		Client unClient = null;
		String requete = "SELECT * FROM client WHERE email = '"+email+"' and mdp = '"+mdp+"';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				unClient = new Client (
						unResultat.getInt("idclient"),
						unResultat.getString("nom"),
						unResultat.getString("tel"),
						unResultat.getString("email"),
						unResultat.getString("mdp"),
						unResultat.getString("adresse"),
						unResultat.getString("cp"),
						unResultat.getString("ville"),
						unResultat.getString("pays"),
						unResultat.getString("etat"),
						unResultat.getString("role"),
						unResultat.getString("type"),
						unResultat.getInt("nbTentatives"),
						unResultat.getInt("bloque")
						);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return unClient;
	}
	
	// Mise à jour du nombre de tentatives du client (nbTentatives + 1)
	public static void updateNbTentatives (Client unClient) {
		String requete = "UPDATE client SET nbTentatives = "
				+ (unClient.getNbTentatives()+1) + " WHERE email = '"+unClient.getEmail()+"' ;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
	}
	
	// Remise à 0 du nombre de tentatives du client
	public static void resetNbTentatives (Client unClient) {
		String requete = "UPDATE client SET nbTentatives = 0 WHERE email = '"+unClient.getEmail()+"';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
	}
	
	// Bloquage du compte du client
	public static void bloquerCompte (Client unClient) {
		String requete = "UPDATE client SET bloque = 1 WHERE email = '"+unClient.getEmail()+"';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
	}
	
	// Sélection de tous les clients
	public static ArrayList<Client> selectAllClients () {
		ArrayList<Client> lesClients = new ArrayList<Client>();
		String requete = "SELECT * FROM client;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Client unClient = new Client (
						desResultats.getInt("idclient"),
						desResultats.getString("nom"),
						desResultats.getString("tel"),
						desResultats.getString("email"),
						desResultats.getString("mdp"),
						desResultats.getString("adresse"),
						desResultats.getString("cp"),
						desResultats.getString("ville"),
						desResultats.getString("pays"),
						desResultats.getString("etat"),
						desResultats.getString("role"),
						desResultats.getString("type"),
						desResultats.getInt("nbTentatives"),
						desResultats.getInt("bloque")
						);
				lesClients.add(unClient);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesClients;
	}
	
	// Recherche d'un client
	public static ArrayList<Client> selectLikeClient(String mot) {
		ArrayList<Client> lesClients = new ArrayList<Client>();
		String requete = "SELECT * FROM client WHERE "
				+ "nom LIKE '%"+mot+"%' OR "
				+ "tel LIKE '%"+mot+"%' OR "
				+ "email LIKE '%"+mot+"%' OR "
				+ "etat LIKE '%"+mot+"%' OR "
				+ "role LIKE '%"+mot+"%' OR "
				+ "type LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Client unClient = new Client (
						desResultats.getInt("idclient"),
						desResultats.getString("nom"),
						desResultats.getString("tel"),
						desResultats.getString("email"),
						desResultats.getString("mdp"),
						desResultats.getString("adresse"),
						desResultats.getString("cp"),
						desResultats.getString("ville"),
						desResultats.getString("pays"),
						desResultats.getString("etat"),
						desResultats.getString("role"),
						desResultats.getString("type"),
						desResultats.getInt("nbTentatives"),
						desResultats.getInt("bloque")
						);
				lesClients.add(unClient);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesClients;
	}
	
	// Nombre de clients
	public static int countClients () {
		int nbclient = 0;
		String requete = "SELECT count(*) as nb FROM client;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				nbclient = unResultat.getInt("nb");
			}
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return nbclient;
	}
	
	/*** GESTION DES PARTICULIERS ***/
	
	// Insertion d'un particulier
	public static void insertParticulier (Particulier unParticulier) {
		String requete = "call insertParticulier('"
				+ unParticulier.getNom() + "', '"
				+ unParticulier.getPrenom() + "', '"
				+ unParticulier.getTel() + "', '"
				+ unParticulier.getEmail() + "', '"
				+ unParticulier.getMdp() + "', '"
				+ unParticulier.getAdresse() + "', '"
				+ unParticulier.getCp() + "', '"
				+ unParticulier.getVille() + "', '"
				+ unParticulier.getPays() + "', '"
				+ unParticulier.getEtat() + "', '"
				+ unParticulier.getRole() + "'); ";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// Sélection de tous les particuliers
	public static ArrayList<Particulier> selectAllParticuliers () {
		ArrayList<Particulier> lesParticuliers = new ArrayList<Particulier>();
		String requete = "SELECT * FROM particulier;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Particulier unParticulier = new Particulier (
						desResultats.getInt("idclient"),
						desResultats.getString("nom"),
						desResultats.getString("tel"),
						desResultats.getString("email"),
						desResultats.getString("mdp"),
						desResultats.getString("adresse"),
						desResultats.getString("cp"),
						desResultats.getString("ville"),
						desResultats.getString("pays"),
						desResultats.getString("etat"),
						desResultats.getString("role"),
						desResultats.getString("type"),
						desResultats.getString("prenom")
						);
				lesParticuliers.add(unParticulier);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesParticuliers;
	}
	
	// Sélection d'un particulier
	public static Particulier selectWhereParticulier (String tel, String email) {
		Particulier unParticulier = null;
		String requete = "SELECT * FROM particulier WHERE tel = '"+tel+"' AND email = '"+email+"';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				unParticulier = new Particulier (
						unResultat.getInt("idclient"),
						unResultat.getString("nom"),
						unResultat.getString("tel"),
						unResultat.getString("email"),
						unResultat.getString("mdp"),
						unResultat.getString("adresse"),
						unResultat.getString("cp"),
						unResultat.getString("ville"),
						unResultat.getString("pays"),
						unResultat.getString("etat"),
						unResultat.getString("role"),
						unResultat.getString("type"),
						unResultat.getString("prenom")
						);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return unParticulier;
	}
	
	// Edition d'un particulier
	public static void updateParticulier (Particulier unParticulier) {
		String requete = "call updateParticulier('"
				+ unParticulier.getNom() + "', '"
				+ unParticulier.getPrenom() + "', '"
				+ unParticulier.getTel() + "', '"
				+ unParticulier.getEmail() + "', '"
				+ unParticulier.getMdp() + "', '"
				+ unParticulier.getAdresse() + "', '"
				+ unParticulier.getCp() + "', '"
				+ unParticulier.getVille() + "', '"
				+ unParticulier.getPays() + "', '"
				+ unParticulier.getEtat() + "', '"
				+ unParticulier.getRole() + "', 0, 0, sysdate());";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// Suppression d'un particulier
	public static void deleteParticulier (String tel, String email) {
		String requete = "call deleteParticulier('"+tel+"', '"+email+"');";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// Nombre de particuliers
	public static int countParticuliers () {
		int nbparticulier = 0;
		String requete = "SELECT count(*) as nb FROM particulier;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				nbparticulier = unResultat.getInt("nb");
			}
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return nbparticulier;
	}
	
	/*** GESTION DES PROFESSIONNELS ***/
	
	// Insertion d'un professionnel
	public static void insertProfessionnel (Professionnel unProfessionnel) {
		String requete = "call insertProfessionnel('"
				+ unProfessionnel.getNom() + "', '"
				+ unProfessionnel.getTel() + "', '"
				+ unProfessionnel.getEmail() + "', '"
				+ unProfessionnel.getMdp() + "', '"
				+ unProfessionnel.getAdresse() + "', '"
				+ unProfessionnel.getCp() + "', '"
				+ unProfessionnel.getVille() + "', '"
				+ unProfessionnel.getPays() + "', '"
				+ unProfessionnel.getNumSITRET() + "', '"
				+ unProfessionnel.getStatut() + "', '"
				+ unProfessionnel.getEtat() + "', '"
				+ unProfessionnel.getRole() + "'); ";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// Sélection de tous les professionnels
	public static ArrayList<Professionnel> selectAllProfessionnels () {
		ArrayList<Professionnel> lesProfessionnels = new ArrayList<Professionnel>();
		String requete = "SELECT * FROM professionnel;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Professionnel unProfessionnel = new Professionnel (
						desResultats.getInt("idclient"),
						desResultats.getString("nom"),
						desResultats.getString("tel"),
						desResultats.getString("email"),
						desResultats.getString("mdp"),
						desResultats.getString("adresse"),
						desResultats.getString("cp"),
						desResultats.getString("ville"),
						desResultats.getString("pays"),
						desResultats.getString("numSIRET"),
						desResultats.getString("statut"),
						desResultats.getString("etat"),
						desResultats.getString("role"),
						desResultats.getString("type")
						);
				lesProfessionnels.add(unProfessionnel);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesProfessionnels;
	}
	
	// Sélection d'un professionnel
	public static Professionnel selectWhereProfessionnel (String tel, String email) {
		Professionnel unProfessionnel = null;
		String requete = "SELECT * FROM professionnel WHERE tel = '"+tel+"' AND email = '"+email+"';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				unProfessionnel = new Professionnel (
						unResultat.getInt("idclient"),
						unResultat.getString("nom"),
						unResultat.getString("tel"),
						unResultat.getString("email"),
						unResultat.getString("mdp"),
						unResultat.getString("adresse"),
						unResultat.getString("cp"),
						unResultat.getString("ville"),
						unResultat.getString("pays"),
						unResultat.getString("numSIRET"), // 09 ou 10
						unResultat.getString("statut"),
						unResultat.getString("etat"),
						unResultat.getString("role"),
						unResultat.getString("type")
						);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return unProfessionnel;
	}
	
	// Edition d'un professionnel
	public static void updateProfessionnel (Professionnel unProfessionnel) {
		String requete = "call updateProfessionnel('"
				+ unProfessionnel.getNom() + "', '"
				+ unProfessionnel.getTel() + "', '"
				+ unProfessionnel.getEmail() + "', '"
				+ unProfessionnel.getMdp() + "', '"
				+ unProfessionnel.getAdresse() + "', '"
				+ unProfessionnel.getCp() + "', '"
				+ unProfessionnel.getVille() + "', '"
				+ unProfessionnel.getPays() + "', '"
				+ unProfessionnel.getStatut() + "', '"
				+ unProfessionnel.getEtat() + "', '"
				+ unProfessionnel.getRole() + "', 0, 0, sysdate());";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// Suppression d'un professionnel
	public static void deleteProfessionnel (String tel, String email) {
		String requete = "call deleteProfessionnel('"+tel+"', '"+email+"');";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// Nombre de professionnels
	public static int countProfessionnels () {
		int nbprofessionnel = 0;
		String requete = "SELECT count(*) as nb FROM professionnel;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				nbprofessionnel = unResultat.getInt("nb");
			}
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return nbprofessionnel;
	}
	
	/*** GESTION DES TYPES ***/
	
	// Insertion d'un type
	public static void insertType (Type unType) {
		String requete = "INSERT INTO type VALUES (null, '"+unType.getLibelle()+"');";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// Sélection de tous les types
	public static ArrayList<Type> selectAllTypes () {
		ArrayList<Type> lesTypes = new ArrayList<Type>();
		String requete = "SELECT * FROM type ORDER BY idtype;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Type unType = new Type (
						desResultats.getInt("idtype"),
						desResultats.getString("libelle")
						);
				lesTypes.add(unType);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesTypes;
	}
	
	// Sélection d'un type en fonction de son libellé
	public static Type selectWhereType (String libelle) {
		Type unType = null;
		String requete = "SELECT * FROM type WHERE libelle = '"+libelle+"';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				unType = new Type (
						unResultat.getInt("idtype"),
						unResultat.getString("libelle")
						);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return unType;
	}
	
	// Édition d'un type
	public static void updateType (Type unType) {
		String requete = "UPDATE type SET libelle = '"+unType.getLibelle()+"' WHERE idtype = "+unType.getIdtype()+";";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// Suppression d'un type
	public static void deleteType (int idtype) {
		String requete = "DELETE FROM type WHERE idtype = "+idtype+";";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// Recherche d'un type
	public static ArrayList<Type> selectLikeType(String mot) {
		ArrayList<Type> lesTypes = new ArrayList<Type>();
		String requete = "SELECT * FROM type WHERE "
				+ "libelle LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Type unType = new Type (
						desResultats.getInt("idtype"),
						desResultats.getString("libelle")
						);
				lesTypes.add(unType);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesTypes;
	}
	
	// Nombre de types
	public static int countTypes () {
		int nbtype = 0;
		String requete = "SELECT count(*) as nb FROM type;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				nbtype = unResultat.getInt("nb");
			}
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return nbtype;
	}
	
	/*** GESTION DES PRODUITS ***/
	
	// Séléction de tous les produits
	public static ArrayList<Produit> selectAllProduits () {
		ArrayList<Produit> lesProduits = new ArrayList<Produit>();
		String requete = "SELECT * FROM produit;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Produit unProduit = new Produit (
						desResultats.getInt("idproduit"),
						desResultats.getString("nomproduit"),
						desResultats.getString("imageproduit"),
						desResultats.getString("descriptionproduit"),
						desResultats.getInt("qteproduit"),
						desResultats.getFloat("prixproduit"),
						desResultats.getInt("idtype"),
						desResultats.getString("date_ajout")
						);
				lesProduits.add(unProduit);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesProduits;
	}
	
	// Séléction de la view vproduit
	public static ArrayList<VProduit> selectAllVProduits () {
		ArrayList<VProduit> lesVProduits = new ArrayList<VProduit>();
		String requete = "SELECT * FROM vproduit;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				VProduit unVProduit = new VProduit (
						desResultats.getInt("idproduit"),
						desResultats.getString("nomproduit"),
						desResultats.getString("imageproduit"),
						desResultats.getString("descriptionproduit"),
						desResultats.getInt("qteproduit"),
						desResultats.getFloat("prixproduit"),
						desResultats.getString("libelle"),
						desResultats.getString("date_ajout")
						);
				lesVProduits.add(unVProduit);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesVProduits;
	}
	
	// Recherche d'un produit à partir de la view vproduit
	public static ArrayList<VProduit> selectLikeProduit(String mot) {
		ArrayList<VProduit> lesProduits = new ArrayList<VProduit>();
		String requete = "SELECT * FROM vproduit WHERE "
				+ "nomproduit LIKE '%"+mot+"%' OR "
				+ "imageproduit LIKE '%"+mot+"%' OR "
				+ "qteproduit LIKE '%"+mot+"%' OR "
				+ "prixproduit LIKE '%"+mot+"%' OR "
				+ "libelle LIKE '%"+mot+"%' OR "
				+ "date_ajout LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				VProduit unProduit = new VProduit (
						desResultats.getInt("idproduit"),
						desResultats.getString("nomproduit"),
						desResultats.getString("imageproduit"),
						desResultats.getString("descriptionproduit"),
						desResultats.getInt("qteproduit"),
						desResultats.getFloat("prixproduit"),
						desResultats.getString("libelle"),
						desResultats.getString("date_ajout")
						);
				lesProduits.add(unProduit);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesProduits;
	}

	// Nombre de produits
	public static int countProduits () {
		int nbproduit = 0;
		String requete = "SELECT count(*) as nb FROM produit;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				nbproduit = unResultat.getInt("nb");
			}
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return nbproduit;
	}
	
	/*** GESTION DE LA VIEW 'vstatsproduits' ***/
	
	// Séléction de la view vstatsproduits
	public static ArrayList<Vstatsproduits> selectAllVstatsproduits () {
		ArrayList<Vstatsproduits> lesStatsProduits = new ArrayList<Vstatsproduits>();
		String requete = "SELECT * FROM vstatsproduits;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Vstatsproduits unStatProduit = new Vstatsproduits (
						desResultats.getString("nomproduit"),
						desResultats.getString("autoradio"),
						desResultats.getString("gps"),
						desResultats.getString("aide_a_la_conduite"),
						desResultats.getString("haut_parleurs"),
						desResultats.getString("kit_mains_libre"),
						desResultats.getString("amplificateurs")
						);
				lesStatsProduits.add(unStatProduit);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesStatsProduits;
	}
	
}
