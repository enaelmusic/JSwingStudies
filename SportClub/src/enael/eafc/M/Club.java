package enael.eafc.M;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Club {
	private String name="FC-Josaphat";
	private String descrClub = "Un club mythique et légendaire de schaerbeek, multidisciplinaire, participants a plusieur evenement nationaux en Belgique.";
	private Set<Membre> membres = new TreeSet<Membre>();
	
	public void addMembre(String nom, String prenom, String dateNaissance) {
		membres.add(new Membre(nom,prenom,dateNaissance));
	}
	
	public void removeMembre(Membre m) {
		membres.remove(m);
	}
	// overloaded methode : deux methode de meme nom avec des parametre formel different.
	public void removeMembre(String numeroMatricule) {
		for(Membre m : membres) {
			if(m.getNumeroMembre().equals(numeroMatricule)) {
				membres.remove(m);
				break; //sors de la loop plus besoin de chercher
			}
		}
	}
	//une methode pour afficher par id
	public void afficherMembre(boolean choixAffichage) {
		if(choixAffichage) {
			//si c'est vrai le club affichera par Id, c'est arbitraire de ma part
			for(Membre m : membres) {
				System.out.println(m.toString());
			}
		}
		else {
			TreeSet<Membre> membresParNom = new TreeSet<Membre>(new MembreComparator()); //j'instancie un nouveaux treeset avec le comparator
			membresParNom.addAll(membres);
			for(Membre m : membresParNom) {
				System.out.println(m);
			}		
		}
	}
	//mon getListe par rapport a un choix de triage
		public Set<Membre> returnMembreTrier (boolean choixAffichage) {
			if(choixAffichage) {
				//si c'est vrai le club affichera par Id, c'est arbitraire de ma part
				return membres;
			}
			else {
				Set<Membre> membresParNom = new TreeSet<Membre>(new MembreComparator()); //j'instancie un nouveaux treeset avec le comparator
				membresParNom.addAll(membres);
				return 	membresParNom;	
			}
		}

	public Set<Membre> getMembres() {
		return membres;
	}

	public String getName() {
		return name;
	}

	public String getDescrClub() {
		return descrClub;
	}

	@Override
	public String toString() {
		return "Club [name=" + name + ", descrClub=" + descrClub + ", membres=" + membres + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(descrClub, membres, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Club other = (Club) obj;
		return Objects.equals(descrClub, other.descrClub) && Objects.equals(membres, other.membres)
				&& Objects.equals(name, other.name);
	}
	
	
	
}
