package vue;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import controleur.Tableau;
import modele.Modele;

public class PanelStats extends PanelDeBase {
	
	private JLabel titre = new JLabel("Statistiques de Filelec");
	
	Font fTitre = new Font("Comic Sans MS", Font.BOLD, 30);
	
	private JPanel panelStat = new JPanel();

	public PanelStats() {
		super(new Color(23, 25, 51));
		
		this.titre.setBounds(505, -1, 500, 40);
		this.titre.setForeground(Color.WHITE);
		this.titre.setFont(fTitre);
		this.add(titre);

		this.panelStat.setLayout(new GridLayout(5, 1));
		this.panelStat.setBounds(10, 50, 1270, 200);
		this.panelStat.setBackground(new Color(23, 25, 51));
		// this.panelStat.setLayout(null);
		this.setVisible(false);

		String entetes[] = { "NB Clients", "NB Particulier", "NB Professionnels", "NB Types", "NB Produits" };
		Object matrice[][] = { { 
			Modele.countClients(), 
			Modele.countParticuliers(), 
			Modele.countProfessionnels(),
			Modele.countTypes(), 
			Modele.countProduits() 
		} };
		Tableau unTableau = new Tableau(entetes, matrice);
		JTable uneTable = new JTable(unTableau);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < uneTable.getColumnCount(); i++) {
			uneTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		JScrollPane uneScroll = new JScrollPane(uneTable);
		uneScroll.setBounds(40, 40, 250, 0);
		this.panelStat.add(uneScroll);

		this.add(this.panelStat);
	}

}
