package vue;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

import controleur.Client;
import controleur.Filelec;
import controleur.Particulier;
import controleur.Professionnel;
import controleur.Tableau;
import modele.Modele;

public class PanelClient extends PanelDeBase implements ActionListener, ListSelectionListener, ItemListener {
	
	private JPanel panelForm1 = new JPanel(); // Formulaire de base
	private JPanel panelForm2 = new JPanel(); // Formulaire Particulier
	private JPanel panelForm3 = new JPanel(); // Formulaire Professionnel
	private JPanel panelForm4 = new JPanel(); // Bouton (Ajouter)
	
	private JLabel titre = new JLabel("Gestion des clients");
	private JLabel tbClient = new JLabel("Liste des clients");
	
	Font fButton = new Font("Comic Sans MS", Font.BOLD, 18);
	Font fTitre = new Font("Comic Sans MS", Font.BOLD, 30);
	Font fListe = new Font("Comic Sans MS", Font.BOLD, 20);
	Font fLabel = new Font("Comic Sans MS", Font.BOLD, 20);
	
	private JPanel panelTable = new JPanel();
	
	private JTextField txtNom = new JTextField();
	private JTextField txtPrenom = new JTextField(); // Pour le Particulier seulement
	private JTextField txtTel = new JTextField();
	private JTextField txtEmail = new JTextField();
	private JPasswordField txtMdp = new JPasswordField();
	private JTextField txtAdresse = new JTextField();
	private JTextField txtCp = new JTextField();
	private JTextField txtVille = new JTextField();
	private JTextField txtPays = new JTextField();
	private JTextField txtStatut = new JTextField(); // Pour le Professionnel seulement
	
	private JComboBox<String> comboEtat = new JComboBox<String>(); // Etat
	private JComboBox<String> comboRole = new JComboBox<String>(); // Rôle
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btAjouter = new JButton("Ajouter");
	
	private JLabel lbNom = new JLabel("Nom du client : ", SwingConstants.RIGHT);
	private JLabel lbPrenom = new JLabel("Prénom du particulier : ", SwingConstants.RIGHT);
	private JLabel lbTel = new JLabel("Téléphone du client : ", SwingConstants.RIGHT);
	private JLabel lbEmail = new JLabel("Adresse email du client : ", SwingConstants.RIGHT);
	private JLabel lbMdp = new JLabel("Mot de passe du client : ", SwingConstants.RIGHT);
	private JLabel lbAdresse = new JLabel("Adresse du client : ", SwingConstants.RIGHT);
	private JLabel lbCp = new JLabel("Code postal du client : ", SwingConstants.RIGHT);
	private JLabel lbVille = new JLabel("Ville du client : ", SwingConstants.RIGHT);
	private JLabel lbPays = new JLabel("Pays du client : ", SwingConstants.RIGHT);
	private JLabel lbStatut = new JLabel("Statut du professionnel : ", SwingConstants.RIGHT);
	private JLabel lbEtat = new JLabel("État du client : ", SwingConstants.RIGHT);
	private JLabel lbRole = new JLabel("Rôle du client : ", SwingConstants.RIGHT);
	
	private JTable uneTable = null;
	
	private static Tableau unTableau = null;
	
	private JTextField txtMot = new JTextField();
	private JButton btRechercher = new JButton("Rechercher");
	
	private JRadioButton radioParticulier = new JRadioButton();
	private JRadioButton radioProfessionnel = new JRadioButton();

	public PanelClient(Client unClient) {
		super(new Color(23, 25, 51));
		
		this.titre.setBounds(2, -1, 500, 40);
		this.titre.setForeground(Color.WHITE);
		this.titre.setFont(fTitre);
		this.add(titre);
		
		this.txtMot.setBounds(1000, 10, 150, 20);
		this.add(txtMot);
		
		this.btRechercher.setBounds(1160, 10, 110, 20);
		this.add(this.btRechercher);
		
		// Options pour l'état du Client
		String optionsEtat[] = {"Prospect", "Client actif", "Client très actif"};
		this.comboEtat = new JComboBox(optionsEtat);
		
		// Options pour le rôle du Client
		String optionsRole[] = {"client", "admin"};
		this.comboRole = new JComboBox(optionsRole);
		
		this.panelForm1.setLayout(new GridLayout(10, 2));
		
		this.panelForm1.add(lbNom);
		this.panelForm1.add(this.txtNom);
		
		this.panelForm1.add(lbTel);
		this.panelForm1.add(this.txtTel);
		
		this.panelForm1.add(lbEmail);
		this.panelForm1.add(this.txtEmail);
		
		this.panelForm1.add(lbMdp);
		this.panelForm1.add(this.txtMdp);
		
		this.panelForm1.add(lbAdresse);
		this.panelForm1.add(this.txtAdresse);
		
		this.panelForm1.add(lbCp);
		this.panelForm1.add(this.txtCp);
		
		this.panelForm1.add(lbVille);
		this.panelForm1.add(this.txtVille);
		
		this.panelForm1.add(lbPays);
		this.panelForm1.add(this.txtPays);
		
		this.panelForm1.add(lbEtat);
		this.panelForm1.add(comboEtat);
		
		this.panelForm1.add(lbRole);
		this.panelForm1.add(comboRole);
		
		this.panelForm2.setLayout(new GridLayout(1, 2));
		
		this.panelForm2.add(lbPrenom);
		this.panelForm2.add(this.txtPrenom);
		
		this.panelForm3.setLayout(new GridLayout(1, 2));
		
		this.panelForm3.add(lbStatut);
		this.panelForm3.add(this.txtStatut);
		
		this.panelForm4.setLayout(new GridLayout(1, 2));
		this.btAnnuler.setBackground(new Color(178, 34, 34));
		this.btAnnuler.setForeground(Color.WHITE);
		this.btAnnuler.setFont(fButton);
		this.panelForm4.add(this.btAnnuler);
		this.btAjouter.setBackground(new Color(0, 128, 0));
		this.btAjouter.setForeground(Color.WHITE);
		this.btAjouter.setFont(fButton);
		this.panelForm4.add(this.btAjouter);
		
		this.panelForm1.setBounds(10, 100, 300, 210); // Form
		this.panelForm2.setBounds(10, 320, 300, 22); // Prénom
		this.panelForm3.setBounds(10, 350, 300, 22); // Statut
		this.panelForm4.setBounds(10, 390, 300, 40); // Boutons
		
		if (unClient.getRole().equals("admin")) {
			this.add(this.panelForm1);
			this.add(this.panelForm2);
			this.add(this.panelForm3);
			this.add(this.panelForm4);
			
			JLabel lbParticulier = new JLabel("Particulier");
			lbParticulier.setBounds(25, 60, 100, 20);
			lbParticulier.setFont(fLabel);
			lbParticulier.setForeground(Color.WHITE);
			this.add(lbParticulier);
			this.radioParticulier.setBounds(0, 60, 20, 20);
			this.add(this.radioParticulier);
			
			JLabel lbProfessionnel = new JLabel("Professionnel");
			lbProfessionnel.setBounds(175, 60, 130, 20);
			lbProfessionnel.setFont(fLabel);
			lbProfessionnel.setForeground(Color.WHITE);
			this.add(lbProfessionnel);
			this.radioProfessionnel.setBounds(150, 60, 20, 20);
			this.add(this.radioProfessionnel);
			
			this.radioParticulier.setSelected(true);
			this.radioProfessionnel.setSelected(false);
			this.panelForm2.setVisible(true);
			this.panelForm3.setVisible(false);
		} else {
			// Modifier dimensions tableaux d'affichage
		}
		
		/* Liste des clients */
		this.tbClient.setBounds(350, 5, 500, 40);
		this.tbClient.setForeground(Color.WHITE);
		this.tbClient.setFont(fListe);
		this.add(tbClient);
		this.panelTable.setBounds(335, 35, 950, 450);
		this.panelTable.setBackground(new Color(23, 25, 51));
		this.panelTable.setLayout(null);
		String entetes[] = {"ID", "Nom", "Tel", "Email", "Etat", "Rôle", "Type"};
		Object donnees[][] = this.getLesDonnees("");
		unTableau = new Tableau(entetes, donnees);
		this.uneTable = new JTable(unTableau);
		uneTable.getColumnModel().getColumn(0).setPreferredWidth(5); // Largeur de la colonne ID
		uneTable.getColumnModel().getColumn(2).setPreferredWidth(15); // Largeur de la colonne Tel
		uneTable.getColumnModel().getColumn(5).setPreferredWidth(5); // Largeur de la colonne Rôle
		JScrollPane uneScroll = new JScrollPane(this.uneTable);
		uneScroll.setBounds(10, 10, 900, 400);
		this.panelTable.add(uneScroll);
		this.add(this.panelTable);
		
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
				if (unClient.getRole().equals("admin")) {
					int nbclic = e.getClickCount();
					if (nbclic == 2) {
						int numLigne = uneTable.getSelectedRow();
						int retour = JOptionPane.showConfirmDialog(null, 
								"Voulez-vous vraiement supprimer ce client ?",
								"Suppression d'un client",
								JOptionPane.YES_NO_OPTION);
						if (retour == 0) {
							String tel = unTableau.getValueAt(numLigne, 2).toString();
							String email = unTableau.getValueAt(numLigne, 3).toString();
							String type = unTableau.getValueAt(numLigne, 6).toString();
							unTableau.supprimerLigne(numLigne);
							if (type.equals("Particulier")) {
								Modele.deleteParticulier(tel, email);
							} else if (type.equals("Professionnel")) {
								Modele.deleteProfessionnel(tel, email);
							}
							viderChamps();
						}
					} else if (nbclic == 1) {
						int numLigne = uneTable.getSelectedRow();
						
						String nom = unTableau.getValueAt(numLigne, 1).toString();
						txtNom.setText(nom);
						
						String tel = unTableau.getValueAt(numLigne, 2).toString();
						txtTel.setText(tel);
						
						String email = unTableau.getValueAt(numLigne, 3).toString();
						txtEmail.setText(email);
						
						String mdp = unTableau.getValueAt(numLigne, 7).toString();
						txtMdp.setText(mdp);
						
						String adresse = unTableau.getValueAt(numLigne, 8).toString();
						txtAdresse.setText(adresse);
						
						String cp = unTableau.getValueAt(numLigne, 9).toString();
						txtCp.setText(cp);
						
						String ville = unTableau.getValueAt(numLigne, 10).toString();
						txtVille.setText(ville);
						
						String pays = unTableau.getValueAt(numLigne, 11).toString();
						txtPays.setText(pays);
						
						String etat = unTableau.getValueAt(numLigne, 12).toString();
						comboEtat.setSelectedItem(etat);
						
						String role = unTableau.getValueAt(numLigne, 13).toString();
						comboRole.setSelectedItem(role);
						
						String type = unTableau.getValueAt(numLigne, 6).toString();
						if (type.equals("Particulier") && radioParticulier.isSelected()) {
							String prenom = unTableau.getValueAt(numLigne, 14).toString();
							txtPrenom.setText(prenom);
						} else if (type.equals("Professionnel") && radioProfessionnel.isSelected()) {
							String statut = unTableau.getValueAt(numLigne, 15).toString();
							txtStatut.setText(statut);
						}
						btAjouter.setText("Modifier");
					}
				}
			}
		});
		
		this.btAnnuler.addActionListener(this);
		this.btAjouter.addActionListener(this);
		this.btRechercher.addActionListener(this);
		this.radioParticulier.addActionListener(this);
		this.radioProfessionnel.addActionListener(this);
	}
	
	public Object[][] getLesDonnees(String mot) {
		ArrayList<Client> lesClients = null;
		if (mot.equals("")) {
			lesClients = Modele.selectAllClients();
		} else {
			lesClients = Modele.selectLikeClient(mot);
		}
		Object[][] matrice = new Object[lesClients.size()][16];
		int i = 0;
		for (Client unClient : lesClients) {
			matrice[i][0] = unClient.getIdclient();
			matrice[i][1] = unClient.getNom();
			matrice[i][2] = unClient.getTel();
			matrice[i][3] = unClient.getEmail();
			matrice[i][4] = unClient.getEtat();
			matrice[i][5] = unClient.getRole();
			matrice[i][6] = unClient.getType();
			matrice[i][7] = unClient.getMdp();
			matrice[i][8] = unClient.getAdresse();
			matrice[i][9] = unClient.getCp();
			matrice[i][10] = unClient.getVille();
			matrice[i][11] = unClient.getPays();
			matrice[i][12] = unClient.getEtat();
			matrice[i][13] = unClient.getRole();
			if (unClient.getType().equals("Particulier")) {
				ArrayList<Particulier> lesParticuliers = Modele.selectAllParticuliers();
				for (Particulier unParticulier : lesParticuliers) {
					matrice[i][14] = unParticulier.getPrenom();
				}
			} else if (unClient.getType().equals("Professionnel")) {
				ArrayList<Professionnel> lesProfessionnels = Modele.selectAllProfessionnels();
				for (Professionnel unProfessionnel : lesProfessionnels) {
					matrice[i][15] = unProfessionnel.getStatut();
				}
			}
			i++;
		}
		return matrice;
	}

	public void viderChamps () {
		this.txtNom.setText("");
		this.txtPrenom.setText("");
		this.txtTel.setText("");
		this.txtEmail.setText("");
		this.txtMdp.setText("");
		this.txtAdresse.setText("");
		this.txtCp.setText("");
		this.txtVille.setText("");
		this.txtPays.setText("");
		this.txtStatut.setText("");
		this.btAjouter.setText("Ajouter");
	}
	
	public Particulier saisirParticulier () {
		Particulier unParticulier = null;
		String nom = this.txtNom.getText();
		String tel = this.txtTel.getText();
		String email = this.txtEmail.getText();
		String mdp = String.valueOf(this.txtMdp.getPassword());
		String adresse = this.txtAdresse.getText();
		String cp = this.txtCp.getText();
		String ville = this.txtVille.getText();
		String pays = this.txtPays.getText();
		String etat = this.comboEtat.getSelectedItem().toString();
		String role = this.comboRole.getSelectedItem().toString();
		String type = "Particulier";
		String prenom = this.txtPrenom.getText();
		
		boolean ok = true;
		
		if (!nom.equals("")) {
			if (nom.matches("^[A-Z][a-zA-Z]+$")) {
				if (!prenom.equals("")) {
					if (prenom.matches("^[A-Z][a-zA-Z]+$")) {
						if (!tel.equals("")) {
							if (tel.matches("^[0-9]{10,10}$")) {
								if (!email.equals("")) {
									if (email.matches("^[a-zA-Z0-9._-]+@[a-z._-]{2,}$")) {
										if (!mdp.equals("")) {
											if (mdp.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#&()-[{}]:;',?/*~$^+=<>]).{8,}$")) {
												if (!adresse.equals("")) {
													if (!cp.equals("")) {
														if (cp.matches("^[0-9]{5}(?:-[0-9]{4})?$")) {
															if (!ville.equals("")) {
																if (ville.matches("^[A-Z][a-zA-Z]+$")) {
																	if (!pays.equals("")) {
																		if (pays.matches("^[A-Z][a-zA-Z]+$")) {
																			if (ok == true) {
																				unParticulier = new Particulier(nom, tel, email, mdp, adresse, cp, ville, pays, etat, role, type, prenom);
																			} else {
																				return null;
																			}
																		} else {
																			JOptionPane.showMessageDialog(this, "Le nom du pays doit commencer par une lettre majuscule.");
																			this.txtPays.setBackground(Color.RED);
																			ok = false;
																		}
																	} else {
																		JOptionPane.showMessageDialog(this, "Veuillez saisir un pays.");
																		this.txtPays.setBackground(Color.RED);
																		ok = false;
																	}
																} else {
																	JOptionPane.showMessageDialog(this, "Le nom de la ville doit commencer par une lettre majuscule.");
																	this.txtVille.setBackground(Color.RED);
																	ok = false;
																}
															} else {
																JOptionPane.showMessageDialog(this, "Veuillez saisir une ville.");
																this.txtVille.setBackground(Color.RED);
																ok = false;
															}
														} else {
															JOptionPane.showMessageDialog(this, "Format du code postal invalide.");
															this.txtCp.setBackground(Color.RED);
															ok = false;
														}
													} else {
														JOptionPane.showMessageDialog(this, "Veuillez saisir un code postal.");
														this.txtCp.setBackground(Color.RED);
														ok = false;
													}
												} else {
													JOptionPane.showMessageDialog(this, "Veuillez saisir une adresse.");
													this.txtAdresse.setBackground(Color.RED);
													ok = false;
												}
											} else {
												JOptionPane.showMessageDialog(this, "Le mot de passe doit contenir au moins une lettre majuscule, une lettre minuscule, un chiffre et 8 caractères .");
												this.txtMdp.setBackground(Color.RED);
												ok = false;
											}
										} else {
											JOptionPane.showMessageDialog(this, "Veuillez saisir un mot de passe.");
											this.txtMdp.setBackground(Color.RED);
											ok = false;
										}
									} else {
										JOptionPane.showMessageDialog(this, "Format de l'adresse email invalid.");
										this.txtEmail.setBackground(Color.RED);
										ok = false;
									}
								} else {
									JOptionPane.showMessageDialog(this, "Veuillez saisir une adresse email.");
									this.txtEmail.setBackground(Color.RED);
									ok = false;
								}
							} else {
								JOptionPane.showMessageDialog(this, "Le numéro de téléphone doit contenir que des chiffres.");
								this.txtTel.setBackground(Color.RED);
								ok = false;
							}
						} else {
							JOptionPane.showMessageDialog(this, "Veuillez saisir un numéro de téléphone.");
							this.txtTel.setBackground(Color.RED);
							ok = false;
						}
					} else {
						JOptionPane.showMessageDialog(this, "Le prénom doit commencer par une lettre majuscule.");
						this.txtPrenom.setBackground(Color.RED);
						ok = false;
					}
				} else {
					JOptionPane.showMessageDialog(this, "Veuillez saisir un prénom.");
					this.txtPrenom.setBackground(Color.RED);
					ok = false;
				}
			} else {
				JOptionPane.showMessageDialog(this, "Le nom doit commencer par une lettre majuscule.");
				this.txtNom.setBackground(Color.RED);
				ok = false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Veuillez saisir un nom.");
			this.txtNom.setBackground(Color.RED);
			ok = false;
		}
		return unParticulier;
	}
	
	public Professionnel saisirProfessionnel () {
		Professionnel unProfessionnel = null;
		String nom = this.txtNom.getText();
		String tel = this.txtTel.getText();
		String email = this.txtEmail.getText();
		String mdp = String.valueOf(this.txtMdp.getPassword());
		String adresse = this.txtAdresse.getText();
		String cp = this.txtCp.getText();
		String ville = this.txtVille.getText();
		String pays = this.txtPays.getText();
		String etat = this.comboEtat.getSelectedItem().toString();
		String role = this.comboRole.getSelectedItem().toString();
		String type = "Professionnel";
		String numSIRET = "521 868 267 00014";
		String statut = this.txtStatut.getText();
		
		boolean ok = true;
		
		if (!nom.equals("")) {
			if (nom.matches("^[A-Z][a-zA-Z]+$")) {
				if (!tel.equals("")) {
					if (tel.matches("^[0-9]+$")) {
						if (!email.equals("")) {
							if (email.matches("^[a-zA-Z0-9._-]+@[a-z._-]{2,}$")) {
								if (!mdp.equals("")) {
									if (mdp.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#&()-[{}]:;',?/*~$^+=<>]).{8,}$")) {
										if (!adresse.equals("")) {
											if (!cp.equals("")) {
												if (cp.matches("^[0-9]{5}(?:-[0-9]{4})?$")) {
													if (!ville.equals("")) {
														if (ville.matches("^[A-Z][a-zA-Z]+$")) {
															if (!pays.equals("")) {
																if (pays.matches("^[A-Z][a-zA-Z]+$")) {
																	if (!statut.equals("")) {
																		if (statut.matches("^[A-Z][a-zA-Z]+$")) {
																			if (ok == true) {
																				unProfessionnel = new Professionnel(nom, tel, email, mdp, adresse, cp, ville, pays, etat, role, type, numSIRET, statut);
																			} else {
																				return null;
																			}
																		} else {
																			JOptionPane.showMessageDialog(this, "Le statut doit commncer par une lettre majuscule.");
																			this.txtStatut.setBackground(Color.RED);
																			ok = false;
																		}
																	} else {
																		JOptionPane.showMessageDialog(this, "Veuillez saisir un statut.");
																		this.txtStatut.setBackground(Color.RED);
																		ok = false;
																	}
																} else {
																	JOptionPane.showMessageDialog(this, "Le nom du pays doit commencer par une lettre majuscule.");
																	this.txtPays.setBackground(Color.RED);
																	ok = false;
																}
															} else {
																JOptionPane.showMessageDialog(this, "Veuillez saisir un pays.");
																this.txtPays.setBackground(Color.RED);
																ok = false;
															}
														} else {
															JOptionPane.showMessageDialog(this, "Le nom de la ville doit commencer par une lettre majuscule.");
															this.txtVille.setBackground(Color.RED);
															ok = false;
														}
													} else {
														JOptionPane.showMessageDialog(this, "Veuillez saisir une ville.");
														this.txtVille.setBackground(Color.RED);
														ok = false;
													}
												} else {
													JOptionPane.showMessageDialog(this, "Format du code postal invalide.");
													this.txtCp.setBackground(Color.RED);
													ok = false;
												}
											} else {
												JOptionPane.showMessageDialog(this, "Veuillez saisir un code postal.");
												this.txtCp.setBackground(Color.RED);
												ok = false;
											}
										} else {
											JOptionPane.showMessageDialog(this, "Veuillez saisir une adresse.");
											this.txtAdresse.setBackground(Color.RED);
											ok = false;
										}
									} else {
										JOptionPane.showMessageDialog(this, "Le mot de passe doit contenir au moins une lettre majuscule, une lettre minuscule, un chiffre et 8 caractères .");
										this.txtMdp.setBackground(Color.RED);
										ok = false;
									}
								} else {
									JOptionPane.showMessageDialog(this, "Veuillez saisir un mot de passe.");
									this.txtMdp.setBackground(Color.RED);
									ok = false;
								}
							} else {
								JOptionPane.showMessageDialog(this, "Format de l'adresse email invalid.");
								this.txtEmail.setBackground(Color.RED);
								ok = false;
							}
						} else {
							JOptionPane.showMessageDialog(this, "Veuillez saisir une adresse email.");
							this.txtEmail.setBackground(Color.RED);
							ok = false;
						}
					} else {
						JOptionPane.showMessageDialog(this, "Le numéro de téléphone doit contenir que des chiffres.");
						this.txtTel.setBackground(Color.RED);
						ok = false;
					}
				} else {
					JOptionPane.showMessageDialog(this, "Veuillez saisir un numéro de téléphone.");
					this.txtTel.setBackground(Color.RED);
					ok = false;
				}
			} else {
				JOptionPane.showMessageDialog(this, "Le nom doit commencer par une lettre majuscule.");
				this.txtNom.setBackground(Color.RED);
				ok = false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Veuillez saisir un nom.");
			this.txtNom.setBackground(Color.RED);
			ok = false;
		}
		return unProfessionnel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.viderChamps();
		} else if (e.getSource() == this.btRechercher) {
			String mot = this.txtMot.getText();
			Object matrice [][] = this.getLesDonnees(mot);
			unTableau.setDonnees(matrice);
		} else if (e.getSource() == this.btAjouter && e.getActionCommand().equalsIgnoreCase("Ajouter")) {
			if (this.radioParticulier.isSelected()) {
				Particulier unParticulier = this.saisirParticulier();
				if (unParticulier != null) {
					Modele.insertParticulier(unParticulier);
					unParticulier = Modele.selectWhereParticulier(unParticulier.getTel(), unParticulier.getEmail());
					
					Object ligne[] = {
						unParticulier.getIdclient(),
						unParticulier.getNom(),
						unParticulier.getTel(),
						unParticulier.getEmail(),
						unParticulier.getEtat(),
						unParticulier.getRole(),
						unParticulier.getType()
					};
					unTableau.ajouterLigne(ligne);
					unTableau.fireTableDataChanged();
					
					JOptionPane.showMessageDialog(this, "Insertion du particulier réussi !");
					this.viderChamps();
				} else {
					JOptionPane.showMessageDialog(this, "Echec de l'insertion !");
				}
			} else if (this.radioProfessionnel.isSelected()) {
				Professionnel unProfessionnel = this.saisirProfessionnel();
				if (unProfessionnel != null) {
					Modele.insertProfessionnel(unProfessionnel);
					unProfessionnel = Modele.selectWhereProfessionnel(unProfessionnel.getTel(), unProfessionnel.getEmail());
					
					/* NE FONCTIONNE PAS (problème d'affichage)
					Object ligne[] = {
							unProfessionnel.getIdclient(),
							unProfessionnel.getNom(),
							unProfessionnel.getTel(),
							unProfessionnel.getEmail(),
							unProfessionnel.getEtat(),
							unProfessionnel.getRole(),
							unProfessionnel.getType()
					};
					unTableau.ajouterLigne(ligne);
					unTableau.fireTableDataChanged();
					*/
					
					JOptionPane.showMessageDialog(this, "Insertion du professionnel réussi !");
					this.viderChamps();
					
					// Déconnexion, puis on se reconnecte pour afficher les données correctes
					Filelec.closeVueGenerale();
					Filelec.setVisibleVueConnexion(true);
				} else {
					JOptionPane.showMessageDialog(this, "Echec de l'insertion !");
				}
			}
		} else if (e.getSource() == this.btAjouter && e.getActionCommand().equalsIgnoreCase("Modifier")) {
			if (this.radioParticulier.isSelected()) {
				Particulier unParticulier = this.saisirParticulier();
				if (unParticulier != null) {
					int numLigne = this.uneTable.getSelectedRow();
					int idclient = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
					unParticulier.setIdclient(idclient);
					Modele.updateParticulier(unParticulier);
					JOptionPane.showMessageDialog(this, "Modification des informations du particulier effectuée !");
					
					Object ligne[] = {
						unParticulier.getIdclient(),
						unParticulier.getNom(),
						unParticulier.getTel(),
						unParticulier.getEmail(),
						unParticulier.getEtat(),
						unParticulier.getRole(),
						unParticulier.getType()
					};
					unTableau.modifierLigne(numLigne, ligne);
					this.viderChamps();
					this.btAjouter.setText("Ajouter");
				} else {
					JOptionPane.showMessageDialog(this, "Echec de la modification !");
				}
			} else if (this.radioProfessionnel.isSelected()) {
				Professionnel unProfessionnel = this.saisirProfessionnel();
				if (unProfessionnel != null) {
					int numLigne = this.uneTable.getSelectedRow();
					int idclient = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
					unProfessionnel.setIdclient(idclient);
					Modele.updateProfessionnel(unProfessionnel);
					JOptionPane.showMessageDialog(this, "Modification des informations du professionnel effectuée !");					
					Object ligne[] = {
							unProfessionnel.getIdclient(),
							unProfessionnel.getNom(),
							unProfessionnel.getTel(),
							unProfessionnel.getEmail(),
							unProfessionnel.getEtat(),
							unProfessionnel.getRole(),
							unProfessionnel.getType()
					};
					unTableau.modifierLigne(numLigne, ligne);
					this.viderChamps();
					this.btAjouter.setText("Ajouter");
				} else {
					JOptionPane.showMessageDialog(this, "Echec de la modification !");
				}
			}
		} else if (e.getSource() == this.radioProfessionnel) {
			this.radioParticulier.setSelected(false);
			this.radioProfessionnel.setSelected(true);
			this.panelForm2.setVisible(false);
			this.panelForm3.setVisible(true);
			this.viderChamps();
		} else if (e.getSource() == this.radioParticulier) {
			this.radioParticulier.setSelected(true);
			this.radioProfessionnel.setSelected(false);
			this.panelForm2.setVisible(true);
			this.panelForm3.setVisible(false);
			this.viderChamps();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
