package enael.eafc.Design;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import enael.eafc.M.*; //j'importe tout mes classe du mon package model

public class WindowFrame extends JFrame {
	//envie d'utiliser le singletone pour etre sur d'avoir qu'une seul fenetre. mais pas sur.
	//j'ai besoin de ranger des valeur dans des bool pour savoir mettre les listner au bon moment.
	//private boolean choixAction = false;
	//private boolean choixAffichage = false;
	/*
	 * https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/GridLayoutDemoProject/src/layout/GridLayoutDemo.java
	 */	
	private Club fcJosaphat = new Club();
	//Set<Membre> getMembre = new TreeSet<Membre>(); // pour recuperer les membre apres rafraichissment
	/*
	 * j'ai decouvert dans le sous menu eclipse 
	 * qu'il pouvais construire une liste avec un tableau d'objet. grace a toArray on poura parser le Set en tableau.
	 * Mais le tableau n'est pas flexible comme la list, alors il fallait chercher plus lois avaec ModelList
	 * https://docs.oracle.com/javase/8/docs/api/javax/swing/DefaultListModel.html#add-int-E-
	 * ici il dise qu'on peut mettre un <element> ce qui sous entend n'importe quel objet
	 * il faut rafraichir la liste pour qu'a chaque ajout ou retrait la liste soit a jour.
	 */
	// 
	DefaultListModel<Membre> dlmMembre = new DefaultListModel<>();
	JList<Membre> membresClub = new JList<Membre>(dlmMembre);
	
	public WindowFrame() {
		wFInit();
	}
	
	private void wFInit() {
		addCarte();
		this.setSize(400,400);
		this.setLocationRelativeTo(null); //centre la fenetre
		this.setTitle(fcJosaphat.getName()+"Gestionnaire des Membre");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
	}
	private void addCarte() {
		//il y aucune logique, c'est arbitraire de ma part
		JPanel main = new JPanel(new BorderLayout()); // je creer ici le JPanel Main
		JPanel pNord = new JPanel(new GridLayout(1,3)); //une ligne trois colone
		JPanel pCentre = new JPanel( new GridLayout(0,2));
		JPanel pSud= new JPanel();
		ConfPane confPane  = new ConfPane(new ChoixOptionRetraitAjout(fcJosaphat, membresClub, dlmMembre));
		confPane.configPannelNord(pNord,pCentre);
		//pCentre.setBackground(new Color(255,255,255,20));
		main.add(pNord, BorderLayout.NORTH);
		main.add(pCentre, BorderLayout.CENTER);
		this.setContentPane(main);
	}
//	private void configPannelNord(JPanel nordPanel, JPanel centrePan) {
//		JLabel choisirOption = new JLabel("Choisissez une des deux options");
//		JButton ajouterMembre= new JButton("Ajouter un membre");
//		JButton retirerMembre = new JButton("Retirer un membre");
//		
//		nordPanel.add(choisirOption);
//		nordPanel.add(ajouterMembre);
//		nordPanel.add(retirerMembre);
//		
//		ajouterMembre.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				configCentre(centrePan,true); //si ce boutton est appuyer ca deviens ajout
//			}
//		});
//		retirerMembre.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				configCentre(centrePan, false); //retrait membre
//			}
//		});
//	}
//	private void configCentre(JPanel centrePan, boolean choixAction) {
//		/*
//		 * https://docs.oracle.com/javase/tutorial/uiswing/layout/grid.html
//		 * je vais utiliser un grid de 2 colones qui correspond a la variable déclaré dans les attribut GridLayout experimentLayout
//		 */
//		//completement arbitraire, si le choix est en vrai c'est qu'il voudra ajouter un membre.
//		if(choixAction) {
//			JOptionPane.showMessageDialog(null, "Ajouter un membre");
//			configAjout(centrePan);		
//		}
//		//sinon il decide de faire un retrait
//		else {
//			/*
//			 * https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html#button
//			 * tres cool
//			 */
//			Object[] options = {"Laisse moi choisir dans la liste","Je saisie le matricule"};
//			//si je comprend bien il range dans un int l'option choisi correcpondant au tableau options
//			int n = JOptionPane.showOptionDialog(centrePan,
//				    "Comment voulez vous le retirer?",
//				    "Retirer un Membre",
//				    JOptionPane.YES_NO_OPTION,
//				    JOptionPane.QUESTION_MESSAGE,
//				    null,     //do not use a custom Icon
//				    options,  //the titles of buttons
//				    options[0]); //default button title
//			configRetrait(centrePan,n); //si n vaut 0 ca sera le premier bouton
//		}		
//	}
	
//	private void configAjout(JPanel centrePanAjout) {
//		centrePanAjout.removeAll();
//		JLabel nomLabel = new JLabel("Saisir nom :");
//		JTextField nomText = new JTextField("Nom");
//		JLabel prenomL = new JLabel("Saisir prenom :");
//		JTextField prenomT = new JTextField("Prenom");
//		JLabel naissanceL = new JLabel("Saisir date de naissance(exemple : 29/12/1988) :");
//		JTextField naissanceT = new JTextField("Date");
//		JButton confirmerAjout = new JButton("Confirmer");
//		centrePanAjout.add(nomLabel);
//		centrePanAjout.add(nomText);
//		centrePanAjout.add(prenomL);
//		centrePanAjout.add(prenomT);
//		centrePanAjout.add(naissanceL);
//		centrePanAjout.add(naissanceT);
//		centrePanAjout.add(confirmerAjout);
//		centrePanAjout.repaint();
//		confirmerAjout.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				fcJosaphat.addMembre(nomText.getText(), prenomT.getText(), naissanceT.getText());
//				rafraichirList(fcJosaphat.returnMembreTrier(true));
//				JOptionPane.showMessageDialog(null, "Vous avez rajouter un nouveaux Membre");	
//				nomText.setText("");
//				prenomT.setText("");
//				naissanceT.setText("exemple : 29/12/1988");
//			}
//		});
//		
//	}
//	
//	private void configRetrait(JPanel centrePanRetrait , int choix) {
//		if(choix==0) {
//			centrePanRetrait.removeAll();// on clean le panel central
//			JButton selectM = new JButton("Selectionner");
//			ButtonGroup choixtriage = new ButtonGroup();
//			JRadioButton trieId = new JRadioButton("Trier Par ID");
//			JRadioButton trizNom = new JRadioButton("Trier par nom,Prenom");
//			/*
//			 * Action listener sur jradiobutton
//			 * https://stackoverflow.com/questions/17653116/action-listener-for-multiple-radio-buttons
//			 */
//			trieId.addActionListener(new ActionListener() {
//				
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					// TODO Auto-generated method stub
//					rafraichirList(fcJosaphat.returnMembreTrier(true));	// a cause de actionlistener j'ai du deplacer le getMembre dans les attribut de classe.		
//
//					trieId.setEnabled(false);
//					trizNom.setEnabled(true);
//				}
//			});
//			trizNom.addActionListener(new ActionListener() {
//				
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					rafraichirList(fcJosaphat.returnMembreTrier(false));	
//
//					trieId.setEnabled(true);
//					trizNom.setEnabled(false);
//				}
//			});
//			selectM.addActionListener(new ActionListener() {
//				
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					Membre m = membresClub.getSelectedValue(); //je demande a la JList de me renvoiyer sa selection
//					// TODO Auto-generated method stub
//					fcJosaphat.removeMembre(m);
//					JOptionPane.showMessageDialog(null, "Vous avez supprimer le membre : " +m.getNumeroMembre());
//					rafraichirList(fcJosaphat.returnMembreTrier(true));
//					
//				}
//			});
//			choixtriage.add(trizNom);
//			choixtriage.add(trieId);
//			centrePanRetrait.add(new JScrollPane(membresClub));
//			centrePanRetrait.add(selectM);			
//			centrePanRetrait.add(trizNom);
//			centrePanRetrait.add(trieId);
//			
//		}
//		else {
//			centrePanRetrait.removeAll();
//			JLabel saisirMatricule = new JLabel("Saisir numero de matricule :");
//			JTextField matriculeSaisie = new JTextField("MBR-0000");
//			
//			JButton confirmer = new JButton("Confirmer");
//			confirmer.addActionListener(new ActionListener() {
//				
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					// TODO Auto-generated method stub
//					String matricul = matriculeSaisie.getText();
//					fcJosaphat.removeMembre(matricul);
//					rafraichirList(fcJosaphat.returnMembreTrier(true));
//					JOptionPane.showMessageDialog(null, "Vous avez suprimmez le membre "+matricul);
//					matriculeSaisie.setText("exemple : MBR-0000");
//				}
//			});
//			
//			centrePanRetrait.add(saisirMatricule);
//			centrePanRetrait.add(matriculeSaisie);
//			centrePanRetrait.add(confirmer);			
//		}
//	}
//	
//	private void rafraichirList(Set<Membre> membreclub) {
//		dlmMembre.clear();//efface le puis remplis a nouveaux.
//		for(Membre m : membreclub) {
//			dlmMembre.addElement(m);
//		}	
//	}
}
