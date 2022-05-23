package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;

import controleur.Client;
import controleur.Tableau;
import controleur.VProduit;
import modele.Modele;

public class PanelProduit extends PanelDeBase implements ActionListener {
	
	private JPanel panelForm = new JPanel();
	private JPanel panelTable = new JPanel();
	
	private JLabel titre = new JLabel("Gestion des produits");
	
	Font fButton = new Font("Comic Sans MS", Font.BOLD, 18);
	Font fTitre = new Font("Comic Sans MS", Font.BOLD, 30);
	Font fImage = new Font("Arial", Font.BOLD, 10);
	
	private JButton btImage = new JButton("Sélectionner un image");
	private JButton btAjouter = new JButton("Ajouter");
	private JButton btAnnuler = new JButton("Annuler");
	
	private JTextField txtNomProduit = new JTextField();
	// private JButton btImageProduit = new JButton("Image");
	private JTextField txtDescriptionProduit = new JTextField();
	private JTextField txtQteProduit = new JTextField();
	private JTextField txtPrixProduit = new JTextField();
	
	private JComboBox<String> comboType = new JComboBox<String>(); // Type du produit
	
	private JLabel lbNomProduit = new JLabel("Nom du produit : ", SwingConstants.RIGHT);
	private JLabel lbImageProduit = new JLabel("Image du produit : ", SwingConstants.RIGHT);
	private JLabel lbDescriptionProduit = new JLabel("Description du produit : ", SwingConstants.RIGHT);
	private JLabel lbQteProduit = new JLabel("Quantité du produit : ", SwingConstants.RIGHT);
	private JLabel lbPrixProduit = new JLabel("Prix du produit : ", SwingConstants.RIGHT);
	private JLabel lbTypeProduit = new JLabel("Type du produit : ", SwingConstants.RIGHT);
	
	private JTable uneTable = null;

	private static Tableau unTableau = null;
	
	private JTextField txtMot = new JTextField();
	private JButton btRechercher = new JButton("Rechercher");

	public PanelProduit(Client unClient) {
		super(new Color(23, 25, 51));
		
		this.titre.setBounds(5, -1, 500, 40);
		this.titre.setForeground(Color.WHITE);
		this.titre.setFont(fTitre);
		this.add(titre);
		
		this.txtMot.setBounds(980, 10, 150, 20);
		this.add(this.txtMot);
		
		this.btRechercher.setBounds(1145, 10, 110, 20);
		this.btRechercher.addActionListener(this);
		this.add(this.btRechercher);
		
		// Rendre le champ de type number
		txtQteProduit.addKeyListener(new KeyAdapter() { 
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
		            e.consume();  // Si ce n’est pas un nombre, ignorer l’événement
		        }
		     }
		});
		
		// Options pour le type du produit
		String optionsType[] = {"Autoradio", "GPS", "Aide à la conduite", "Haut-parleurs", "Kit mains-libre", "Amplificateur"};
		this.comboType = new JComboBox(optionsType);
		
		this.panelForm.setLayout(new GridLayout(7,2));
		
		this.panelForm.add(lbNomProduit);
		this.panelForm.add(this.txtNomProduit);
		
		this.panelForm.add(lbImageProduit);
		this.panelForm.add(this.btImage);
		
		this.panelForm.add(lbDescriptionProduit);
		this.panelForm.add(this.txtDescriptionProduit);
		
		this.panelForm.add(lbQteProduit);
		this.panelForm.add(this.txtQteProduit);
		
		this.panelForm.add(lbPrixProduit);
		this.panelForm.add(this.txtPrixProduit);
		
		this.panelForm.add(lbTypeProduit);
		this.panelForm.add(comboType);
		
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btAjouter);
		
		this.panelForm.setBounds(10, 60, 300, 200);
		this.add(this.panelForm);
		
		// Bouton Image
		this.btImage.setBackground(Color.GRAY);
		this.btImage.setForeground(Color.WHITE);
		this.btImage.setFont(fImage);
		this.btImage.addActionListener(this);
		
		// Bouton Annuler
		this.btAnnuler.setBackground(new Color(178, 34, 34));
		this.btAnnuler.setForeground(Color.WHITE);
		this.btAnnuler.setFont(fButton);
		this.btAnnuler.addActionListener(this);
		
		// Bouton Ajouter
		this.btAjouter.setBackground(new Color(0, 128, 0));
		this.btAjouter.setForeground(Color.WHITE);
		this.btAjouter.setFont(fButton);
		this.btAjouter.addActionListener(this);
		
		this.panelTable.setBounds(330, 40, 945, 320); // Dimension du fond
		this.panelTable.setBackground(new Color(23, 25, 51));
		this.panelTable.setLayout(null);
		String entetes[] = { "ID", "Nom", "Image", "Quantité", "Prix", "Type", "Date ajout" };
		Object donnees[][] = this.getLesDonnees("");
		unTableau = new Tableau(entetes, donnees);
		this.uneTable = new JTable(unTableau);
		JScrollPane uneScroll = new JScrollPane(this.uneTable);
		uneScroll.setBounds(10, 10, 925, 250); // Dimension du tableau d'affichage
		this.panelTable.add(uneScroll);
		this.add(this.panelTable);
	}
	
	public Object[][] getLesDonnees(String mot) {
		ArrayList<VProduit> lesVProduits = null;
		if (mot.equals("")) {
			lesVProduits = Modele.selectAllVProduits();
		} else {
			lesVProduits = Modele.selectLikeProduit(mot);
		}
		Object[][] matrice = new Object[lesVProduits.size()][7];
		int i = 0;
		for (VProduit unProduit : lesVProduits) {
			matrice[i][0] = unProduit.getIdproduit();
			matrice[i][1] = unProduit.getNomproduit();
			matrice[i][2] = unProduit.getImageproduit();
			matrice[i][3] = unProduit.getQteproduit();
			matrice[i][4] = unProduit.getPrixproduit() + " €";
			matrice[i][5] = unProduit.getLibelle();
			matrice[i][6] = unProduit.getDate_ajout();
			i++;
		}
		return matrice;
	}
	
	private void viderChamps() {
		this.txtNomProduit.setText("");
		this.txtDescriptionProduit.setText("");
		this.txtQteProduit.setText("");
		this.txtPrixProduit.setText("");
		this.btAjouter.setText("Ajouter");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.viderChamps();
		} else if (e.getSource() == this.btImage) {
			JFileChooser fileChooser = new JFileChooser();
			int result = fileChooser.showOpenDialog(null);
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
			}
		}
	}

}
