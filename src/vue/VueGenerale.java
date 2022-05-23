package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controleur.Client;
import controleur.Filelec;

public class VueGenerale extends JFrame implements ActionListener {
	
	private JButton btProfil = new JButton("Profil");
	private JButton btClient = new JButton("Clients");
	private JButton btType = new JButton("Types");
	private JButton btProduit = new JButton("Produits");
	private JButton btStats = new JButton("Statistiques");
	private JButton btBoard = new JButton("Dashboard");
	private JButton btDeconnexion = new JButton("Déconnexion");
	
	private JPanel panelProfile = new JPanel();
	private JPanel panelMenu = new JPanel();
	
	private static PanelClient unPanelClient;
	private static PanelType unPanelType;
	private static PanelProduit unPanelProduit;
	private static PanelStats unPanelStats = new PanelStats();
	private static PanelBoard unPanelBoard = new PanelBoard();
	
	Font fButton = new Font("Comic Sans MS", Font.BOLD, 16);
	
	public VueGenerale (Client unClient) {
		
		this.unPanelClient = new PanelClient(unClient);
		this.unPanelType = new PanelType(unClient);
		this.unPanelProduit = new PanelProduit(unClient);
		
		this.setTitle("Filelec - Administration");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBounds(300, 100, 1350, 600);
		this.setLayout(null);
		this.getContentPane().setBackground(new Color(23, 25, 51));
		
		this.panelMenu.setLayout(new GridLayout(1,7));
		this.panelMenu.setBounds(20, 10, 1300, 40);
		this.panelMenu.setBackground(new Color(23, 25, 51));
		
		this.btProfil.setFont(fButton);
		this.panelMenu.add(btProfil);
		
		this.btClient.setFont(fButton);
		this.panelMenu.add(btClient);
		
		this.btType.setFont(fButton);
		this.panelMenu.add(btType);
		
		this.btProduit.setFont(fButton);
		this.panelMenu.add(btProduit);
		
		this.btStats.setFont(fButton);
		this.panelMenu.add(btStats);
		
		this.btBoard.setFont(fButton);
		this.panelMenu.add(btBoard);
		
		this.btDeconnexion.setBackground(new Color(178, 34, 34));
		this.btDeconnexion.setForeground(Color.WHITE);
		this.btDeconnexion.setFont(fButton);
		this.panelMenu.add(btDeconnexion);
		
		this.add(this.panelMenu);
		
		this.panelProfile.setLayout(new GridLayout(8, 1));
		this.panelProfile.setBounds(450, 70, 400, 300);
		this.panelProfile.setBackground(Color.CYAN);
		this.panelProfile.setVisible(true);
		
		this.panelProfile.add(new JLabel(" ID : " + unClient.getIdclient()));
		this.panelProfile.add(new JLabel(" Nom : " + unClient.getNom()));
		this.panelProfile.add(new JLabel(" Tel : " + unClient.getTel()));
		this.panelProfile.add(new JLabel(" Email : " + unClient.getEmail()));
		this.panelProfile.add(new JLabel(" Etat : " + unClient.getEtat()));
		this.panelProfile.add(new JLabel(" Role : " + unClient.getRole()));
		this.panelProfile.add(new JLabel(" Type : " + unClient.getType()));
		
		this.add(this.panelProfile);
		
		this.add(unPanelClient);
		this.add(unPanelType);
		this.add(unPanelProduit);
		this.add(unPanelStats);
		this.add(unPanelBoard);
		
		this.btProfil.addActionListener(this);
		this.btClient.addActionListener(this);
		this.btType.addActionListener(this);
		this.btProduit.addActionListener(this);
		this.btStats.addActionListener(this);
		this.btBoard.addActionListener(this);
		this.btDeconnexion.addActionListener(this);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btDeconnexion) {
			Filelec.closeVueGenerale();
			Filelec.setVisibleVueConnexion(true);
		} else if (e.getSource() == this.btProfil) {
			this.btProfil.setBackground(Color.BLACK);
			this.btProfil.setForeground(Color.WHITE);
			this.btClient.setBackground(getBackground());
			this.btClient.setForeground(getForeground());
			this.btType.setBackground(getBackground());
			this.btType.setForeground(getForeground());
			this.btProduit.setBackground(getBackground());
			this.btProduit.setForeground(getForeground());
			this.btStats.setBackground(getBackground());
			this.btStats.setForeground(getForeground());
			this.btBoard.setBackground(getBackground());
			this.btBoard.setForeground(getForeground());
			this.panelProfile.setVisible(true);
			unPanelClient.setVisible(false);
			unPanelType.setVisible(false);
			unPanelProduit.setVisible(false);
			unPanelStats.setVisible(false);
			unPanelBoard.setVisible(false);
		} else if (e.getSource() == this.btClient) {
			this.btProfil.setBackground(getBackground());
			this.btProfil.setForeground(getForeground());
			this.btClient.setBackground(Color.BLACK);
			this.btClient.setForeground(Color.WHITE);
			this.btType.setBackground(getBackground());
			this.btType.setForeground(getForeground());
			this.btProduit.setBackground(getBackground());
			this.btProduit.setForeground(getForeground());
			this.btStats.setBackground(getBackground());
			this.btStats.setForeground(getForeground());
			this.btBoard.setBackground(getBackground());
			this.btBoard.setForeground(getForeground());
			this.panelProfile.setVisible(false);
			unPanelClient.setVisible(true);
			unPanelType.setVisible(false);
			unPanelProduit.setVisible(false);
			unPanelStats.setVisible(false);
			unPanelBoard.setVisible(false);
		} else if (e.getSource() == this.btType) {
			this.btProfil.setBackground(getBackground());
			this.btProfil.setForeground(getForeground());
			this.btClient.setBackground(getBackground());
			this.btClient.setForeground(getForeground());
			this.btType.setBackground(Color.BLACK);
			this.btType.setForeground(Color.WHITE);
			this.btProduit.setBackground(getBackground());
			this.btProduit.setForeground(getForeground());
			this.btStats.setBackground(getBackground());
			this.btStats.setForeground(getForeground());
			this.btBoard.setBackground(getBackground());
			this.btBoard.setForeground(getForeground());
			this.panelProfile.setVisible(false);
			unPanelClient.setVisible(false);
			unPanelType.setVisible(true);
			unPanelProduit.setVisible(false);
			unPanelStats.setVisible(false);
			unPanelBoard.setVisible(false);
		} else if (e.getSource() == this.btProduit) {
			this.btProfil.setBackground(getBackground());
			this.btProfil.setForeground(getForeground());
			this.btClient.setBackground(getBackground());
			this.btClient.setForeground(getForeground());
			this.btType.setBackground(getBackground());
			this.btType.setForeground(getForeground());
			this.btProduit.setBackground(Color.BLACK);
			this.btProduit.setForeground(Color.WHITE);
			this.btStats.setBackground(getBackground());
			this.btStats.setForeground(getForeground());
			this.btBoard.setBackground(getBackground());
			this.btBoard.setForeground(getForeground());
			this.panelProfile.setVisible(false);
			unPanelClient.setVisible(false);
			unPanelType.setVisible(false);
			unPanelProduit.setVisible(true);
			unPanelStats.setVisible(false);
			unPanelBoard.setVisible(false);
		} else if (e.getSource() == this.btStats) {
			this.btProfil.setBackground(getBackground());
			this.btProfil.setForeground(getForeground());
			this.btClient.setBackground(getBackground());
			this.btClient.setForeground(getForeground());
			this.btType.setBackground(getBackground());
			this.btType.setForeground(getForeground());
			this.btProduit.setBackground(getBackground());
			this.btProduit.setForeground(getForeground());
			this.btStats.setBackground(Color.BLACK);
			this.btStats.setForeground(Color.WHITE);
			this.btBoard.setBackground(getBackground());
			this.btBoard.setForeground(getForeground());
			this.panelProfile.setVisible(false);
			unPanelClient.setVisible(false);
			unPanelType.setVisible(false);
			unPanelProduit.setVisible(false);
			unPanelStats.setVisible(true);
			unPanelBoard.setVisible(false);
		} else if (e.getSource() == this.btBoard) {
			this.btProfil.setBackground(getBackground());
			this.btProfil.setForeground(getForeground());
			this.btClient.setBackground(getBackground());
			this.btClient.setForeground(getForeground());
			this.btType.setBackground(getBackground());
			this.btType.setForeground(getForeground());
			this.btProduit.setBackground(getBackground());
			this.btProduit.setForeground(getForeground());
			this.btStats.setBackground(getBackground());
			this.btStats.setForeground(getForeground());
			this.btBoard.setBackground(Color.BLACK);
			this.btBoard.setForeground(Color.WHITE);
			this.panelProfile.setVisible(false);
			unPanelClient.setVisible(false);
			unPanelType.setVisible(false);
			unPanelProduit.setVisible(false);
			unPanelStats.setVisible(false);
			unPanelBoard.setVisible(true);
		}
	}

}
