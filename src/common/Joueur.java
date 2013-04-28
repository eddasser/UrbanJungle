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
	private HashMap<TypeElementPlateau,Integer> niveaux;
	
	private int argent;
	
	public Joueur(){
		unites = new ArrayList<Unite>();
		batiments = new ArrayList<Batiment>();
		argent = Constante.ARGENT_DEPART;
		
		niveaux = new HashMap<TypeElementPlateau,Integer>();
		for (TypeBatiment type : TypeBatiment.values()){
			niveaux.put(type,type.getNiveauBase());
		}
		for (TypeUnite type : TypeUnite.values()){
			niveaux.put(type,type.getNiveauBase());
		}
	}
	
	// constructeur appelé par le serveur
	public Joueur(ClientListener _clientListener,String _login,String _password){
		this();
		clientListener = _clientListener;
		login = _login;
		password = _password;
	}
	
	public void incrementeNiveau(TypeElementPlateau type){
		int niveau = niveaux.get(type) + 1;
		niveaux.put(type,niveau);
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
	
	public int getNiveau(TypeElementPlateau type){
		return niveaux.get(type);
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
	 * retourne l'unite présente sur la case ou NULL
	 */
	public Batiment getBatimentSurCase(Case position){
		Batiment batiment = null;
		
		for (int i = 0 ; batiment == null && i < batiments.size() ; i++){
			Batiment batiment_courante = batiments.get(i);
			Case postionCentreBatiment = new Case(batiment_courante.getPosition().getX() + Constante.LARGEUR_CASE,batiment_courante
					.getPosition().getY() + Constante.HAUTEUR_CASE);
			if (postionCentreBatiment.getDistance(position) <= Constante.LARGEUR_CASE){
				batiment = batiment_courante;
			}
		}
		
		return batiment;
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
	
	/*
	 * methode qui verifie la présence d'une unité de construction à proximité de la position en parametre
	 * (pour construire un batiment, il faut un "constructeur" a proximité)
	 */
	public boolean aUniteConstructionProche(Case position){
		boolean aUniteConstructeur = false;
		
		Case centreBatiment = new Case(position.getX() + Constante.LARGEUR_CASE,position.getY() + Constante.HAUTEUR_CASE);
		
		for (int i = 0 ; !aUniteConstructeur && i < unites.size() ; i++){
			Unite unite = unites.get(i);
			if (unite.getType() == TypeUnite.CONSTRUCTEUR){
				Case centreUnite = unite.getCentre();
				// on calcul la distance entre l'unite et l'endroit où il veux construire
				double distanceUnite = centreBatiment.getDistance(centreUnite);
				// on convertit la distance en nombre de cases
				distanceUnite /= Constante.LARGEUR_CASE;
				if (distanceUnite <= Constante.NB_CASES_DISTANCE_AVEC_UNITE_CONSTRUCTEUR_AUTORISE_POUR_CONSTRUCTION_BATIMENT){
					aUniteConstructeur = true;
				}
			}
		}
		return aUniteConstructeur;
	}
	
	
	/*
	 * retourne true s'il y a un batiment du joueur a proximité de la position
	 * (il est nécéssaire d'avoir un batiment a proximité pour pouvoir créer une unité)
	 */
	public boolean presenceDeBatimentAProximitePosition(Case position){
		boolean presenceBatiment = false;
		
		for (int i = 0 ; !presenceBatiment && i < batiments.size() ; i++){
			Batiment bat = batiments.get(i);
			Case centreBatiment = bat.getCentre();
			Case centrePosition = new Case(position.getX() + Constante.LARGEUR_CASE / 2,position.getY() + Constante.HAUTEUR_CASE / 2);
			// on calcul la distance entre le batiment et l'endroit où il veux construire son unité
			// on calcul bien sur la distance a partir du centre du batiment
			double distance = centreBatiment.getDistance(centrePosition);
			// on convertit la distance en nombre de cases
			distance /= Constante.LARGEUR_CASE;
			if (distance <= Constante.NB_CASES_DISTANCE_AVEC_BATIMENT_AUTORISE_POUR_CREATION_UNITE){
				presenceBatiment = true;
			}
		}
		return presenceBatiment;
	}
	
	/*
	 * cette methode est appelé a chaque tour
	 * elle met à jour l'argent et le deplacement des unités
	 */
	public void majTour(){
		majArgentTour();
		majDeplacementUniteTour();
	}
	
	/*
	 * cette methode est appelé a chaque tour
	 * elle met a jour le montant d'argent du joueur :
	 * augmente en fonction des batiments (revenu) qu'il a 
	 * et diminue en fonction des unités (salaire) qu'il a
	 */
	private void majArgentTour(){
		int montant = 0;
		for (int i = 0 ; i < batiments.size() ; i++){
			TypeBatiment type = (TypeBatiment)batiments.get(i).getType();
			montant += type.getRevenu(getNiveau(type));
		}
		for (int i = 0 ; i < unites.size() ; i++){
			TypeUnite type = (TypeUnite)unites.get(i).getType();
			montant -= type.getSalaire(getNiveau(type));
		}
		argent += montant;
	}
	
	/*
	 * cette methode est appelé a chaque tour
	 * elle reinitialise la capacite de deplacement de toutes les unités
	 */
	private void majDeplacementUniteTour(){
		for (int i = 0 ; i < unites.size() ; i++){
			Unite unite = unites.get(i);
			TypeUnite type = (TypeUnite)unite.getType();
			unite.setDeplacementRestant(type.getVitesse(getNiveau(type)));
		}
	}
}
