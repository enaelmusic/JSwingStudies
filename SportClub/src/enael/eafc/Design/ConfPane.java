package enael.eafc.Design;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ConfPane {
	private ChoixOptionRetraitAjout rORa;
	
	public ConfPane(ChoixOptionRetraitAjout rORa) {
		this.rORa = rORa;
	}
	
	protected void configPannelNord(JPanel nordPanel, JPanel centrePan) {
		
		JLabel choisirOption = new JLabel("Choisissez une des deux options");
		JButton ajouterMembre= new JButton("Ajouter un membre");
		JButton retirerMembre = new JButton("Retirer un membre");
		nordPanel.add(choisirOption);
		nordPanel.add(ajouterMembre);
		nordPanel.add(retirerMembre);
		
		ajouterMembre.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				configCentre(centrePan,true); //si ce boutton est appuyer ca deviens ajout
			}
		});
		retirerMembre.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				configCentre(centrePan, false); //retrait membre
			}
		});
	}
	private void configCentre(JPanel centrePan, boolean choixAction) {
		/*
		 * https://docs.oracle.com/javase/tutorial/uiswing/layout/grid.html
		 * je vais utiliser un grid de 2 colones qui correspond a la variable déclaré dans les attribut GridLayout experimentLayout
		 */
		//completement arbitraire, si le choix est en vrai c'est qu'il voudra ajouter un membre.
		if(choixAction) {
			JOptionPane.showMessageDialog(null, "Ajouter un membre");
			rORa.configAjout(centrePan);		
		}
		//sinon il decide de faire un retrait
		else {
			/*
			 * https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html#button
			 * tres cool
			 * donc le bouton par default il choisi de supprimer en selectionnant dans la liste,
			 * sinon il sera diriger vers le centre avec la saisie de matricule
			 */
			Object[] options = {"Laisse moi choisir dans la liste","Je saisie le matricule"};
			//si je comprend bien il range dans un int l'option choisi correcpondant au tableau options
			int n = JOptionPane.showOptionDialog(centrePan,
				    "Comment voulez vous le retirer?",
				    "Retirer un Membre",
				    JOptionPane.YES_NO_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
				    null,     //do not use a custom Icon
				    options,  //the titles of buttons
				    options[0]); //default button title
			rORa.configRetrait(centrePan,n); //si n vaut 0 ca sera le premier bouton
		}		
	}
}
