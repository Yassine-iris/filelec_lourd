package vue;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controleur.Client;
import controleur.Filelec;
import modele.Modele;

public class VueConnexion extends JFrame implements ActionListener, KeyListener {
	
	JPanel panelConnexion = new JPanel();
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btConnexion = new JButton("Connexion");
	
	private JTextField txtEmail = new JTextField("tom.bruaire@gmail.com");
	private JPasswordField txtMdp = new JPasswordField("Azerty123");
	
	private JLabel lbEmail = new JLabel("Adresse email : ", SwingConstants.RIGHT);
	private JLabel lbMdp = new JLabel("Mot de passe : ", SwingConstants.RIGHT);
	
	Font fLabel = new Font("Comic Sans MS", Font.BOLD, 18);
	Font fButton = new Font("Comic Sans MS", Font.PLAIN, 20);
	
	public VueConnexion () {
		
		this.setTitle("Filelec - Administration");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBounds(300, 300, 600, 250);
		this.setLayout(null);
		this.getContentPane().setBackground(new Color(23, 25, 51));
		
		ImageIcon unLogo = new ImageIcon("src/images/filelec.png");
		JLabel lbLogo = new JLabel(unLogo);
		lbLogo.setBounds(0, 30, 250, 150);
		this.add(lbLogo);
		
		this.panelConnexion.setLayout(new GridLayout(3,2));
		this.panelConnexion.setBounds(250, 30, 320, 150);
		this.panelConnexion.setBackground(new Color(135, 206, 235));
		
		this.panelConnexion.add(lbEmail);
		this.lbEmail.setFont(fLabel);
		this.panelConnexion.add(this.txtEmail);
		
		this.panelConnexion.add(lbMdp);
		this.lbMdp.setFont(fLabel);
		this.panelConnexion.add(this.txtMdp);
		
		this.panelConnexion.add(this.btAnnuler);
		this.panelConnexion.add(this.btConnexion);
		
		this.btAnnuler.setBackground(new Color(178, 34, 34));
		this.btAnnuler.setForeground(Color.WHITE);
		this.btAnnuler.setFont(fButton);
		
		this.btConnexion.setBackground(new Color(0, 128, 0));
		this.btConnexion.setForeground(Color.WHITE);
		this.btConnexion.setFont(fButton);
		
		this.add(this.panelConnexion);
		
		this.btAnnuler.addActionListener(this);
		this.btConnexion.addActionListener(this);
		
		this.setVisible(true);
	}
	
	public void traitement () {
		String email = this.txtEmail.getText();
		String mdp = new String (this.txtMdp.getPassword());
		mdp = Filelec.crypterMdp(mdp); // mdp = sha1(mdp)
		
		Client unClient = Filelec.selectWhereEmailClient(email);
		if (unClient != null && unClient.getEmail().equals(email)) { // Si l'email est correcte
			unClient = Filelec.selectWhereClient(email, mdp);
			if (unClient == null) { // Si le mdp est incorrect
				JOptionPane.showMessageDialog(this, "Mot de passe incorrect !");
				unClient = Filelec.selectWhereEmailClient(email);
				Modele.updateNbTentatives(unClient);
				if (unClient.getNbTentatives() + 1 > 5) {
					// On remet le nombre de tentatives à 0
					Modele.resetNbTentatives(unClient);
					// On bloque le compte du client
					Modele.bloquerCompte(unClient);
				}
			} else {
				// On remet le nombre de tentatives à 0
				Modele.resetNbTentatives(unClient);
				if (unClient.getBloque() == 0) { // Si le compte n'est pas bloqué
					JOptionPane.showMessageDialog(this, "Vous être conntecté en tant que " + unClient.getEmail());
					Filelec.instancierVueGenerale(unClient);
					Filelec.setVisibleVueConnexion(false);
				} else {
					JOptionPane.showMessageDialog(this, "Suite à de nombreuses tentatives infructueurses, votre compte a été bloqué.");
				}
			}
		} else {
			JOptionPane.showMessageDialog(this, "Adresse email incorrecte !");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			traitement();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.txtEmail.setText("");
			this.txtMdp.setText("");
		} else if (e.getSource() == this.btConnexion) {
			traitement();
		}
	}

}
