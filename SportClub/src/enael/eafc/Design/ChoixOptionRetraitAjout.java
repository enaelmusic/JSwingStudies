package enael.eafc.Design;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import enael.eafc.M.Club;
import enael.eafc.M.Membre;

public class ChoixOptionRetraitAjout {
	
	private Club fcJosaphat;
	private JList<Membre> membresClub;
	private DefaultListModel<Membre> dlmMembre;
	
	public ChoixOptionRetraitAjout(Club fcJosaphat, JList<Membre> membresClub, DefaultListModel<Membre> dlmMembre) {
		this.fcJosaphat = fcJosaphat;
		this.membresClub = membresClub;
		this.dlmMembre = dlmMembre;
	}
	
	protected void configAjout(JPanel centrePanAjout) {
		centrePanAjout.removeAll();
		JLabel nomLabel = new JLabel("Saisir nom :");
		JTextField nomText = new JTextField("Nom");
		JLabel prenomL = new JLabel("Saisir prenom :");
		JTextField prenomT = new JTextField("Prenom");
		JLabel naissanceL = new JLabel("Saisir date de naissance(exemple : 29/12/1988) :");
		JTextField naissanceT = new JTextField("Date");
		JButton confirmerAjout = new JButton("Confirmer");
		centrePanAjout.add(nomLabel);
		centrePanAjout.add(nomText);
		centrePanAjout.add(prenomL);
		centrePanAjout.add(prenomT);
		centrePanAjout.add(naissanceL);
		centrePanAjout.add(naissanceT);
		centrePanAjout.add(confirmerAjout);
		centrePanAjout.repaint(); //j'essaie de retirer le bug affichage fantome
		confirmerAjout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fcJosaphat.addMembre(nomText.getText(), prenomT.getText(), naissanceT.getText());
				rafraichirList(fcJosaphat.returnMembreTrier(true));
				JOptionPane.showMessageDialog(null, "Vous avez rajouter un nouveaux Membre");	
				nomText.setText("");
				prenomT.setText("");
				naissanceT.setText("exemple : 29/12/1988");
			}
		});		
	}
	
	protected void configRetrait(JPanel centrePanRetrait , int choix) {
		if(choix==0) {
			centrePanRetrait.removeAll();// on clean le panel central
			JButton selectM = new JButton("Selectionner");
			ButtonGroup choixtriage = new ButtonGroup();
			JRadioButton trieId = new JRadioButton("Trier Par ID");
			JRadioButton trizNom = new JRadioButton("Trier par nom,Prenom");
			/*
			 * Action listener sur jradiobutton
			 * https://stackoverflow.com/questions/17653116/action-listener-for-multiple-radio-buttons
			 */
			trieId.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					rafraichirList(fcJosaphat.returnMembreTrier(true));	// a cause de actionlistener j'ai du deplacer le getMembre dans les attribut de classe.		
					trieId.setEnabled(false);
					trizNom.setEnabled(true);
				}
			});
			trizNom.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					rafraichirList(fcJosaphat.returnMembreTrier(false));	
					trieId.setEnabled(true);
					trizNom.setEnabled(false);
				}
			});
			selectM.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Membre m = membresClub.getSelectedValue(); //je demande a la JList de me renvoiyer sa selection
					// TODO Auto-generated method stub
					fcJosaphat.removeMembre(m);
					JOptionPane.showMessageDialog(null, "Vous avez supprimer le membre : " +m.getNumeroMembre());
					rafraichirList(fcJosaphat.returnMembreTrier(true));
					
				}
			});
			choixtriage.add(trizNom);
			choixtriage.add(trieId);
			centrePanRetrait.add(new JScrollPane(membresClub));
			centrePanRetrait.add(selectM);			
			centrePanRetrait.add(trizNom);
			centrePanRetrait.add(trieId);
			
		}
		else {
			centrePanRetrait.removeAll();
			JLabel saisirMatricule = new JLabel("Saisir numero de matricule :");
			JTextField matriculeSaisie = new JTextField("MBR-0000");
			JButton confirmer = new JButton("Confirmer");
			confirmer.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String matricul = matriculeSaisie.getText();
					fcJosaphat.removeMembre(matricul);
					rafraichirList(fcJosaphat.returnMembreTrier(true));
					JOptionPane.showMessageDialog(null, "Vous avez suprimmez le membre "+matricul);
					matriculeSaisie.setText("exemple : MBR-0000");
				}
			});
			centrePanRetrait.add(saisirMatricule);
			centrePanRetrait.add(matriculeSaisie);
			centrePanRetrait.add(confirmer);			
		}
	}
	
	private void rafraichirList(Set<Membre> membreclub) {
		dlmMembre.clear();//efface le puis remplis a nouveaux.
		dlmMembre.addAll(membreclub);
//		for(Membre m : membreclub) {
//			dlmMembre.addElement(m);
//		}	
	}
}
