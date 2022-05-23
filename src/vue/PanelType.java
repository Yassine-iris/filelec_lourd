package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.*;

import controleur.Client;
import controleur.Tableau;
import controleur.Type;
import modele.Modele;

public class PanelType extends PanelDeBase implements ActionListener {
	
	private JPanel panelForm = new JPanel();
	private JPanel panelTable = new JPanel();
	
	private JLabel titre = new JLabel("Gestion des types");
	
	Font fButton = new Font("Comic Sans MS", Font.BOLD, 18);
	Font fTitre = new Font("Comic Sans MS", Font.BOLD, 30);
	
	private JTextField txtLibelle = new JTextField();
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btAjouter = new JButton("Ajouter");
	
	private JLabel lbLibelle = new JLabel("Libellé : ", SwingConstants.RIGHT);
	
	private JTable uneTable = null;
	
	private static Tableau unTableau = null;
	
	private JTextField txtMot = new JTextField();
	private JButton btRechercher = new JButton("Rechercher");

	public PanelType(Client unClient) {
		super(new Color(23, 25, 51));
		
		this.titre.setBounds(2, -1, 500, 40);
		this.titre.setForeground(Color.WHITE);
		this.titre.setFont(fTitre);
		this.add(titre);
		
		this.txtMot.setBounds(410, 10, 150, 20);
		this.add(this.txtMot);
		
		this.btRechercher.setBounds(570, 10, 110, 20);
		this.btRechercher.addActionListener(this);
		this.add(this.btRechercher);
		
		this.panelForm.setLayout(new GridLayout(2,2));
		
		this.panelForm.add(lbLibelle);
		this.panelForm.add(this.txtLibelle);
		
		this.btAnnuler.setBackground(new Color(178, 34, 34));
		this.btAnnuler.setForeground(Color.WHITE);
		this.btAnnuler.setFont(fButton);
		this.panelForm.add(this.btAnnuler);
		this.btAjouter.setBackground(new Color(0, 128, 0));
		this.btAjouter.setForeground(Color.WHITE);
		this.btAjouter.setFont(fButton);
		this.panelForm.add(this.btAjouter);
		
		this.panelForm.setBounds(10, 70, 300, 70);
		this.add(this.panelForm);
		
		this.panelTable.setBounds(400, 40, 300, 200);
		this.panelTable.setBackground(new Color(23, 25, 51));
		this.panelTable.setLayout(null);
		String entetes[] = { "ID", "Libellé" };
		Object donnees[][] = this.getLesDonnees("");
		unTableau = new Tableau(entetes, donnees);
		this.uneTable = new JTable(unTableau);
		JScrollPane uneScroll = new JScrollPane(this.uneTable);
		uneScroll.setBounds(10, 10, 300, 119);
		this.panelTable.add(uneScroll);
		this.add(this.panelTable);
		
		this.btAnnuler.addActionListener(this);
		this.btAjouter.addActionListener(this);
		
		this.uneTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int nbclic = e.getClickCount();
				if (nbclic == 2) {
					int numLigne = uneTable.getSelectedRow();
					int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce type ?", 
							"Suppression d'un Type", 
							JOptionPane.YES_NO_OPTION);
					if (retour == 0) {
						int idtype = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
						Modele.deleteType(idtype);
						unTableau.supprimerLigne(numLigne);
						viderChamps();
					}
				} else if (nbclic == 1) {
					int numLigne = uneTable.getSelectedRow();
					String libelle = unTableau.getValueAt(numLigne, 1).toString();
					txtLibelle.setText(libelle);
					btAjouter.setText("Modifier");
				}
			}
		});
	}
	
	public Object[][] getLesDonnees(String mot) {
		ArrayList<Type> lesTypes = null;
		if (mot.equals("")) {
			lesTypes = Modele.selectAllTypes();
		} else {
			lesTypes = Modele.selectLikeType(mot);
		}
		Object[][] matrice = new Object[lesTypes.size()][2];
		int i = 0;
		for (Type unType : lesTypes) {
			matrice[i][0] = unType.getIdtype();
			matrice[i][1] = unType.getLibelle(); 
			i++;
		}
		return matrice;
	}
	
	private void viderChamps() {
		this.txtLibelle.setText("");
		this.btAjouter.setText("Ajouter");
	}
	
	public Type saisirType () {
		Type unType = null;
		String libelle = this.txtLibelle.getText();
		
		if (libelle.equals("")) {
			this.txtLibelle.setBackground(Color.red);
		} else {
			this.txtLibelle.setBackground(Color.white);
		}
		
		if (!libelle.equals("")) {
			unType = new Type (libelle);
		} else {
			return null;
		}
		return unType;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.viderChamps();
		} else if (e.getSource() == this.btAjouter && e.getActionCommand().equals("Ajouter")) {
			Type unType = this.saisirType();
			Modele.insertType(unType);
			unType = Modele.selectWhereType(unType.getLibelle());
			Object ligne[] = {unType.getIdtype(), unType.getLibelle()};
			unTableau.ajouterLigne(ligne);
			JOptionPane.showMessageDialog(this, "Insertion du type réussi !");
			this.viderChamps();
		} else if (e.getSource() == this.btAjouter && e.getActionCommand().equals("Modifier")) {
			Type unType = this.saisirType();
			int numLigne = this.uneTable.getSelectedRow();
			int idtype = Integer.parseInt(uneTable.getValueAt(numLigne, 0).toString());
			unType.setIdtype(idtype);
			Modele.updateType(unType);
			Object ligne[] = {unType.getIdtype(), unType.getLibelle()};
			unTableau.modifierLigne(numLigne, ligne);
			JOptionPane.showMessageDialog(this, "Modification du type réussi !");
			this.btAjouter.setText("Ajouter");
		} else if (e.getSource() == this.btRechercher) {
			String mot = this.txtMot.getText();
			Object matrice [][] = this.getLesDonnees(mot);
			unTableau.setDonnees(matrice);
		}
	}

}
