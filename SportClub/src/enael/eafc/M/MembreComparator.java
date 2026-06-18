package enael.eafc.M;

import java.util.Comparator;

public class MembreComparator implements Comparator<Membre>{

	@Override
	public int compare(Membre o1, Membre o2) {
		// TODO Auto-generated method stub
		//si le meme nom par prenom, sinon par nom
		if(o1.getNom().equals(o2.getNom())){
			return o1.getPrenom().compareTo(o2.getPrenom());
		}
		else {
			return o1.getNom().compareTo(o2.getNom());
		}
	}

}
