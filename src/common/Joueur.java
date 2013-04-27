package common;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import server.ClientListener;

import common.partie.batiment.Batiment;
import common.partie.batiment.TypeBatiment;
import common.partie.plateau.Case;
import common.partie.unite.TypeUnite;
import common.partie.unite.Unite;

public class Joueur{
	// info pour partie réseau
	private ClientListener clientListener; // socket a utiliser pour contacter le client
	private String login;
	private String password;
	
	// info pour les unites et batiments que le joueur possede
	private ArrayList<Unite> unites;
	private ArrayList<Batiment> batiments;
	
	// niveau pour les types de batiments et unités pour le joueur courant
	private HashMap<TypeBatiment,Integer> niveauBatiment;
	private HashMap<TypeUnite,Integer> niveauUnite;
	
	private int argent;
	
	public Joueur(){
		unites = new ArrayList<Unite>();
		batiments = new ArrayList<Batiment>();
		argent = Constante.ARGENT_DEPART;
		
		niveauBatiment = new HashMap<TypeBatiment,Integer>();
		for (TypeBatiment type : TypeBatiment.values()){
			niveauBatiment.put(type,type.getNiveauBase());
		}
		
		niveauUnite = new HashMap<TypeUnite,Integer>();
		for (TypeUnite type : TypeUnite.values()){
			niveauUnite.put(type,type.getNiveauBase());
		}
	}
	
	// constructeur appelé par le serveur
	public Joueur(ClientListener _clientListener,String _login,String _password){
		this();
		clientListener = _clientListener;
		login = _login;
		password = _password;
	}
	
	public void incrementeNiveauBatiment(TypeBatiment type){
		int niveau = niveauBatiment.get(type) + 1;
		niveauBatiment.put(type,niveau);
	}
	
	public void incrementeNiveauUnite(TypeUnite type){
		int niveau = niveauUnite.get(type) + 1;
		niveauUnite.put(type,niveau);
	}
	
	public void incrementArgent(int montant){
		argent += montant;
	}
	
	public void decrementArgent(int montant){
		argent -= montant;
	}
	
	public Case getPositionQG(){
		Case position = null;
		for (int i = 0 ; position == null && i < batiments.size() ; i++){
			if (batiments.get(i).getType() == TypeBatiment.QG){
				position = batiments.get(i).getPosition();
			}
		}
		return position;
	}
	
	public int getNiveauBatiment(TypeBatiment type){
		return niveauBatiment.get(type);
	}
	
	public int getNiveauUnite(TypeUnite type){
		return niveauUnite.get(type);
	}
	
	public Socket getSocket(){
		return clientListener.getSocket();
	}
	
	public void send(String[] args){
		clientListener.send(args);
	}
	
	@Override
	public String toString(){
		return "\nlogin : " + login + "\nmot de passe : " + password;
	}
	
	public ArrayList<Unite> getUnites(){
		return unites;
	}
	
	public void setUnites(ArrayList<Unite> unites){
		this.unites = unites;
	}
	
	public ArrayList<Batiment> getBatiments(){
		return batiments;
	}
	
	public int getArgent(){
		return argent;
	}
	
	public void setArgent(int argent){
		this.argent = argent;
	}
	
	public void setBatiments(ArrayList<Batiment> batiments){
		this.batiments = batiments;
	}
	
	public void ajouterBatiment(Batiment b){
		batiments.add(b);
	}
	
	public void ajouterUnite(Unite u){
		unites.add(u);
	}
	
	/*
	 * retourne l'unite présente sur la case ou NULL
	 */
	public Unite getUniteSurCase(Case position){
		Unite unite = null;
		
		for (int i = 0 ; unite == null && i < unites.size() ; i++){
			Unite unite_courante = unites.get(i);
			if (unite_courante.getPosition().equals(position)){
				unite = unite_courante;
			}
		}
		
		return unite;
	}
	
	/*
	 * retourne true s'il y a une unite du joueur sur cette position
	 */
	public boolean presenceDeUnitePosition(Case position){
		boolean presenceUnite = false;
		
		for (int i = 0 ; !presenceUnite && i < unites.size() ; i++){
			Unite unite = unites.get(i);
			Case positionUnite = unite.getPosition();
			if (positionUnite.getX() >= position.getX() && positionUnite.getX() <= (position.getX() + 2 * Constante.LARGEUR_CASE)){
				if (positionUnite.getY() >= position.getY() && positionUnite.getY() <= (position.getY() + 2 * Constante.HAUTEUR_CASE)){
					presenceUnite = true;
				}
			}
		}
		return presenceUnite;
	}
	
	/*
	 * retourne true s'il y a un batiment du joueur sur cette position
	 */
	public boolean presenceDeBatimentPosition(Case position){
		boolean presenceBatiment = false;
		
		for (int i = 0 ; !presenceBatiment && i < batiments.size() ; i++){
			Batiment bat = batiments.get(i);
			Case positionBat = bat.getPosition();
			if (position.getX() < (positionBat.getX() + 2 * Constante.LARGEUR_CASE)){
				if (position.getY() < (positionBat.getY() + 2 * Constante.HAUTEUR_CASE)){
					presenceBatiment = true;
				}
			}
		}
		return presenceBatiment;
	}
	
	
	public boolean aUniteConstructionProche(Case position){
		boolean aUniteConstructeur = false;
		
		for (int i = 0 ; i < unites.size() ; i++){
			Unite unite = unites.get(i);
			if (unite.getType() == TypeUnite.CONSTRUCTEUR){
				// on calcul la distance entre l'unite et l'endroit où il veux construire
				double distanceUnite = position.getDistance(unite.getPosition());
				// on convertit la distance en nombre de cases
				distanceUnite /= Constante.LARGEUR_CASE;
				if (distanceUnite <= Constante.NB_CASES_DISTANCE_AVEC_UNITE_CONSTRUCTEUR_AUTORISE_POUR_CONSTRUCTION_BATIMENT){
					aUniteConstructeur = true;
				}
			}
		}
		
		return aUniteConstructeur;
	}
}
