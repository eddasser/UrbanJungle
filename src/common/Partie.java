package common;

import java.io.Serializable;
import java.util.ArrayList;


public class Partie implements Serializable{
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	
	private String nomPartie; // nom donné a la parti par son créateur
	private int nbJoueur; // nb de joueur requis pour cette partie
	private ArrayList<Joueur> listeParticipants; // liste des joueurs participants a cette partie
	private Etat etatDeLaPartie; // etat de la partie possible : "en cours" ou "en attente"
	private String password;// password de la partie, si null alors la partie est public sinon elle est privé
	
	
	public Partie(String nomPartieParam,int nbJoueurParam,String passwordParam){
		nomPartie = nomPartieParam;
		nbJoueur = nbJoueurParam;
		
		listeParticipants = new ArrayList<Joueur>();
		etatDeLaPartie = Etat.EN_ATTENTE_JOUEUR;
		password = passwordParam;
		
	}
	
	public boolean necessitePassword(){
		boolean necessitePassword = true;
		
		if (password.equals("")){
			necessitePassword = false;
		}
		
		return necessitePassword;
	}
	
	@Override
	public String toString(){
		return nomPartie + "\nNombre de joueur : " + nbJoueur + "\netat de la partie : " + etatDeLaPartie + "\npassword : " + password;
	}
	
	public String getNomPartie(){
		return nomPartie;
	}
	
	public void setNomPartie(String nomPartie){
		this.nomPartie = nomPartie;
	}
	
	public int getNbJoueur(){
		return nbJoueur;
	}
	
	public void setNbJoueur(int nbJoueur){
		this.nbJoueur = nbJoueur;
	}
	
	public ArrayList<Joueur> getListeParticipants(){
		return listeParticipants;
	}
	
	public void setListeParticipants(ArrayList<Joueur> listeParticipants){
		this.listeParticipants = listeParticipants;
	}
	
	public Etat getEtatDeLaPartie(){
		return etatDeLaPartie;
	}
	
	public void setEtatDeLaPartie(Etat etatDeLaPartie){
		this.etatDeLaPartie = etatDeLaPartie;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public Joueur getJoueurAdmin(){
		return listeParticipants.get(0);
	}
	
	public void setJoueurAdmin(Joueur joueurAdmin){
		listeParticipants.set(0,joueurAdmin);
	}
}
