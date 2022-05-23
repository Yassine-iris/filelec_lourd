package vue;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import controleur.Tableau;
import controleur.Vstatsproduits;
import modele.Modele;

public class PanelBoard extends PanelDeBase {
	
	private JLabel titre = new JLabel("Tableau de bord de Filelec");
	
	Font fTitre = new Font("Comic Sans MS", Font.BOLD, 30);
	
	private JPanel panelBoard = new JPanel();
	
	private JTable uneTable = null;
	
	private static Tableau unTableau = null;
	
	public PanelBoard() {
		super(new Color(23, 25, 51));
		
		this.titre.setBounds(475, -1, 500, 40);
		this.titre.setForeground(Color.WHITE);
		this.titre.setFont(fTitre);
		this.add(titre);
		
		this.panelBoard.setBounds(10, 50, 1250, 303);
		this.panelBoard.setBackground(new Color(23, 25, 51));
		this.panelBoard.setLayout(null);
		String entetes[] = {"Produit", "Autoradio", "GPS", "Aide à la conduite", "Haut-parleurs", "Kit mains-libre", "Amplificateurs"};
		Object donnees [][] = this.getLesDonnees();
		unTableau = new Tableau(entetes, donnees);
		this.uneTable = new JTable(unTableau);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		for (int i=1; i<uneTable.getColumnCount(); i++) {
			this.uneTable.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
		}
		JScrollPane uneScroll = new JScrollPane(this.uneTable);
		uneScroll.setBounds(10, 20, 1230, 280);
		this.panelBoard.add(uneScroll);
		
		this.add(this.panelBoard);
		this.setVisible(false);
	}
	
	public Object[][] getLesDonnees() {
		ArrayList<Vstatsproduits> lesProduits = Modele.selectAllVstatsproduits();
		Object[][] matrice = new Object[lesProduits.size()][7];
		int i = 0;
		for (Vstatsproduits unProduit : lesProduits) {
			matrice[i][0] = unProduit.getNomproduit();
			matrice[i][1] = unProduit.getAutoradio() + " € ";
			matrice[i][2] = unProduit.getGps() + " € ";
			matrice[i][3] = unProduit.getAide_a_la_conduite() + " € ";
			matrice[i][4] = unProduit.getHaut_parleurs() + " € ";
			matrice[i][5] = unProduit.getKit_mains_libre() + " € ";
			matrice[i][6] = unProduit.getAmplificateurs() + " € ";
			i++;
		}
		return matrice;
	}

}
