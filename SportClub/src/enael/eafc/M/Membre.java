package enael.eafc.M;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Membre implements Comparable<Membre>{
	/*
	 * Chaque Member possède un numéro de membre (String), un nom, un prénom et une date de naissance (LocalDate).
	 */
	private static int compteurId=1;
	private String numeroMembre ;
	private String nom;
	private String prenom;
	private LocalDate naissance;
	
	public Membre(String nom, String prenom, String dateNaissance) {
		this.nom=nom;
		this.prenom=prenom;
		this.naissance=transformStringToLocalDate(dateNaissance);
		attribuerId();		
	}
	
	private LocalDate transformStringToLocalDate(String date) {
		/*
		 * https://docs.oracle.com/javase/10/docs/api/java/time/format/DateTimeFormatter.html
		 * il y a un paragraphe avec une conversion complete, je la reprend.
		 */
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		/*
		 * l'utilisateur du programe devra entré la date par un string avec 
		 *     le jour en nombre/slash/
		 *     le mois en nombre/slash/
		 *     l'année en nombre, je cherche comment presenter un calendrier ca serait mieux.
		 */
		LocalDate dateReturn = LocalDate.parse(date, dtf);
		return dateReturn;
	}
	
	private void attribuerId() {
		//je format le matricule du mebre en "MBR-0000"
		//car si je met un int qui commence par zero ca sera un hexadecimal.
		String concat = "";
		String idString = compteurId+"";
		for(int i=4;i>=0;i--) {
			//je boucle sur une longeur de 4 non compris, mais le zero compris. en négatif
			if(i==idString.length()) {
				concat+=idString; //concataine le nombre au string former 
				break;//sors de la loop				
			}
			else {
				concat=concat+"0";
			}			
		}
		this.numeroMembre="MBR-"+concat;
		compteurId++;
	}
	//trier par défault par numero de matricule du membre.
		@Override
		public int compareTo(Membre o) {
			// TODO Auto-generated method stub
			return this.numeroMembre.compareTo(o.numeroMembre);
		}
    ///////////////////////////////////////////
	public String getNumeroMembre() {
		return numeroMembre;
	}
	public String getNom() {
		return nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public LocalDate getNaissance() {
		return naissance;
	}
	@Override
	public String toString() {
		return "Membre [numeroMembre=" + numeroMembre + ", nom=" + nom + ", prenom=" + prenom + ", naissance="+ naissance + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(naissance, nom, numeroMembre, prenom);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Membre other = (Membre) obj;
		return Objects.equals(naissance, other.naissance) && Objects.equals(nom, other.nom)
				&& Objects.equals(numeroMembre, other.numeroMembre) && Objects.equals(prenom, other.prenom);
	}
}
