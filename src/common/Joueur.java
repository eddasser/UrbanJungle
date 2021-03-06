package common;

import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import server.ClientListener;

import common.partie.batiment.Batiment;
import common.partie.batiment.TypeBatiment;
import common.partie.plateau.Case;
import common.partie.unite.TypeUnite;
import common.partie.unite.Unite;

public class Joueur implements Serializable{
	
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	// info pour partie réseau
	private transient ClientListener clientListener; // socket a utiliser pour contacter le client
	private String login;
	private transient String password;
	
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
	
	/*
	 * methode qui permet d'initialiser le joueur courant à partir des informations du joueur en parametre (seule la socket n'est pas reprise)
	 */
	public void initialiserJoueur(Joueur j){
		unites = j.unites;
		batiments = j.batiments;
		argent = j.argent;
		niveaux = j.niveaux;
		// login = j.login;
		// password = j.password;
	}
	
	/*
	 * incremente le niveau pour le type donnée du joueur
	 * et retourne la difference de point de vie (entre l'ancien niveau et le nouveau niveau)
	 */
	private int incrementeNiveau(TypeElementPlateau type,int nb){
		// recuperation de l'ancien niveau de point de vie
		int ancienPtsVieMax = type.getPointDeVie(niveaux.get(type));
		
		// incrementation du niveau
		int niveau = niveaux.get(type) + nb;
		niveaux.put(type,niveau);
		
		// recuperation du nouveau niveau de point de vie
		int nouveauPtsVieMax = type.getPointDeVie(niveaux.get(type));
		
		return nouveauPtsVieMax - ancienPtsVieMax;
	}
	
	public void incrementeNiveauBatiment(TypeBatiment type){
		incrementeNiveauBatiment(type,1);
	}
	
	public void incrementeNiveauBatiment(TypeBatiment type,int nb){
		int differencePtsVie = incrementeNiveau(type,nb);
		
		// incrementer les pts de vie des batiments avec la difference entre niveau
		for (int i = 0 ; i < batiments.size() ; i++){
			Batiment bat = batiments.get(i);
			if (bat.getType() == type){
				bat.incrementePointsVie(differencePtsVie);
			}
		}
	}
	
	public void incrementeNiveauUnite(TypeUnite type){
		incrementeNiveauUnite(type,1);
	}
	
	public void incrementeNiveauUnite(TypeUnite type,int nb){
		int differencePtsVie = incrementeNiveau(type,nb);
		// incrmente les pts de vie des unités avec la difference entre niveau
		for (int i = 0 ; i < unites.size() ; i++){
			Unite unite = unites.get(i);
			if (unite.getType() == type){
				unite.incrementePointsVie(differencePtsVie);
			}
		}
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
		if (clientListener != null && clientListener.getSocket() != null && !clientListener.getSocket().isClosed()){
			return clientListener.getSocket();
		}else{
			return null;
		}
	}
	
	public ClientListener getClientListener(){
		return clientListener;
	}
	
	public void setClientListener(ClientListener clientListener){
		this.clientListener = clientListener;
	}
	
	public String getLogin(){
		return login;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void send(Object[] args){
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
	public ElementPlateau presenceDeUnitePosition(Case position){
		ElementPlateau elementPlateau = null;
		
		for (int i = 0 ; elementPlateau == null && i < unites.size() ; i++){
			Unite unite = unites.get(i);
			Case positionUnite = unite.getPosition();
			
			if (position.equals(positionUnite)){
				elementPlateau = unite;
			}
		}
		return elementPlateau;
	}
	
	/*
	 * retourne true s'il y a un batiment du joueur sur cette position
	 */
	public ElementPlateau presenceDeBatimentPosition(Case position){
		ElementPlateau presenceBatiment = null;
		
		int Xmin = position.getX() - Constante.LARGEUR_CASE;
		int Xmax = position.getX() + Constante.LARGEUR_CASE;
		
		int Ymin = position.getY() - Constante.HAUTEUR_CASE;
		int Ymax = position.getY() + Constante.HAUTEUR_CASE;
		
		for (int i = 0 ; presenceBatiment == null && i < batiments.size() ; i++){
			Batiment bat = batiments.get(i);
			Case positionBat = bat.getPosition();
			
			if (positionBat.getX() >= Xmin && positionBat.getX() < Xmax && positionBat.getY() >= Ymin && positionBat.getY() < Ymax){
				presenceBatiment = bat;
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
		
		// un batiment peut etre contruit si au moin un constructeur est present dans un case situé sur X case autour du batiments ( X
		// correspond a la constante de distance definie dans le fichier constante)
		// je decoupe les cases autour du batiment en zone et je vérifie si oil existe un constructeur dans ces zones
		
		// zone nord
		int nord_X_MIN = (centreBatiment.getX() - Constante.LARGEUR_CASE)
				- (Constante.LARGEUR_CASE * (int)Constante.NB_CASES_DISTANCE_AVEC_UNITE_CONSTRUCTEUR_AUTORISE_POUR_CONSTRUCTION_BATIMENT);
		int nord_X_MAX = (centreBatiment.getX() + Constante.LARGEUR_CASE)
				+ (Constante.LARGEUR_CASE * (int)Constante.NB_CASES_DISTANCE_AVEC_UNITE_CONSTRUCTEUR_AUTORISE_POUR_CONSTRUCTION_BATIMENT);
		
		int nord_Y_MIN = (centreBatiment.getY() - Constante.HAUTEUR_CASE)
				- (Constante.HAUTEUR_CASE * (int)Constante.NB_CASES_DISTANCE_AVEC_UNITE_CONSTRUCTEUR_AUTORISE_POUR_CONSTRUCTION_BATIMENT);
		int nord_Y_MAX = (centreBatiment.getY() - Constante.HAUTEUR_CASE);
		
		
		// zone sud
		int sud_X_MIN = nord_X_MIN;
		int sud_X_MAX = nord_X_MAX;
		
		int sud_Y_MIN = (centreBatiment.getY() + Constante.HAUTEUR_CASE);
		int sud_Y_MAX = (centreBatiment.getY() + Constante.HAUTEUR_CASE)
				+ (Constante.HAUTEUR_CASE * (int)Constante.NB_CASES_DISTANCE_AVEC_UNITE_CONSTRUCTEUR_AUTORISE_POUR_CONSTRUCTION_BATIMENT);
		
		
		// zone ouest
		int ouest_X_MIN = (centreBatiment.getX() - Constante.LARGEUR_CASE)
				- (Constante.LARGEUR_CASE * (int)Constante.NB_CASES_DISTANCE_AVEC_UNITE_CONSTRUCTEUR_AUTORISE_POUR_CONSTRUCTION_BATIMENT);
		int ouest_X_MAX = (centreBatiment.getX() - Constante.LARGEUR_CASE);
		
		int ouest_Y_MIN = (centreBatiment.getY() - Constante.HAUTEUR_CASE)
				- (Constante.HAUTEUR_CASE * (int)Constante.NB_CASES_DISTANCE_AVEC_UNITE_CONSTRUCTEUR_AUTORISE_POUR_CONSTRUCTION_BATIMENT);
		int ouest_Y_MAX = (centreBatiment.getY() + Constante.HAUTEUR_CASE)
				+ (Constante.HAUTEUR_CASE * (int)Constante.NB_CASES_DISTANCE_AVEC_UNITE_CONSTRUCTEUR_AUTORISE_POUR_CONSTRUCTION_BATIMENT);
		
		
		// zone est
		
		int est_X_MIN = (centreBatiment.getX() + Constante.LARGEUR_CASE);
		int est_X_MAX = (centreBatiment.getX() + Constante.LARGEUR_CASE)
				+ (Constante.LARGEUR_CASE * (int)Constante.NB_CASES_DISTANCE_AVEC_UNITE_CONSTRUCTEUR_AUTORISE_POUR_CONSTRUCTION_BATIMENT);
		
		int est_Y_MIN = ouest_Y_MIN;
		int est_Y_MAX = ouest_Y_MAX;
		
		
		for (int i = 0 ; !aUniteConstructeur && i < unites.size() ; i++){
			Unite unite = unites.get(i);
			if (unite.getType() == TypeUnite.CONSTRUCTEUR){ // si l'unite est un constructeur
			
				int uniteX = unite.getPosition().getX();
				int uniteY = unite.getPosition().getY();
				
				// si un constructeur se situe dans une des 4 zone
				if (uniteX >= nord_X_MIN && uniteX < nord_X_MAX && uniteY >= nord_Y_MIN && uniteY < nord_Y_MAX || // nord
						uniteX >= sud_X_MIN && uniteX < sud_X_MAX && uniteY >= sud_Y_MIN && uniteY < sud_Y_MAX || // sud
						uniteX >= ouest_X_MIN && uniteX < ouest_X_MAX && uniteY >= ouest_Y_MIN && uniteY < ouest_Y_MAX || // ouest
						uniteX >= est_X_MIN && uniteX < est_X_MAX && uniteY >= est_Y_MIN && uniteY < est_Y_MAX){ // est
				
					aUniteConstructeur = true;
				}
				/**
				 * ancien code, mauvais calcul, fonction retourne un mauvais resultat souvant et empeche la construction alors qu'elle
				 * devrait pas Case centreUnite = unite.getCentre(); // on calcul la distance entre l'unite et l'endroit où il veux
				 * construire double distanceUnite = centreBatiment.getDistance(centreUnite); // on convertit la distance en nombre de cases
				 * distanceUnite /= Constante.LARGEUR_CASE; if (distanceUnite <=
				 * Constante.NB_CASES_DISTANCE_AVEC_UNITE_CONSTRUCTEUR_AUTORISE_POUR_CONSTRUCTION_BATIMENT){ aUniteConstructeur = true; }
				 */
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
		
		int centreBatimentX = 0;
		int centreBatimentY = 0;
		int borneInfX = 0;
		int borneInfY = 0;
		int borneSupX = 0;
		int borneSupY = 0;
		
		for (int i = 0 ; !presenceBatiment && i < batiments.size() ; i++){
			Batiment bat = batiments.get(i);
			
			centreBatimentX = bat.getCentre().getX();
			centreBatimentY = bat.getCentre().getY();
			
			borneInfX = centreBatimentX
					- (Constante.LARGEUR_CASE + (Constante.LARGEUR_CASE * (int)Constante.NB_CASES_DISTANCE_AVEC_BATIMENT_AUTORISE_POUR_CREATION_UNITE));
			borneInfY = centreBatimentY
					- (Constante.HAUTEUR_CASE + (Constante.HAUTEUR_CASE * (int)Constante.NB_CASES_DISTANCE_AVEC_BATIMENT_AUTORISE_POUR_CREATION_UNITE));
			
			borneSupX = centreBatimentX
					+ (Constante.LARGEUR_CASE + (Constante.LARGEUR_CASE * (int)Constante.NB_CASES_DISTANCE_AVEC_BATIMENT_AUTORISE_POUR_CREATION_UNITE));
			borneSupY = centreBatimentY
					+ (Constante.HAUTEUR_CASE + (Constante.HAUTEUR_CASE * (int)Constante.NB_CASES_DISTANCE_AVEC_BATIMENT_AUTORISE_POUR_CREATION_UNITE));
			
			if (position.getX() >= borneInfX && position.getX() < borneSupX && position.getY() >= borneInfY && position.getY() < borneSupY){
				presenceBatiment = true;
			}
			
			/**
			 * ancien code, problématique car calcul des diagonal faux et placements des unités qui bug du coup Case centrePosition = new
			 * Case(position.getX() + Constante.LARGEUR_CASE / 2,position.getY() + Constante.HAUTEUR_CASE / 2); // on calcul la distance
			 * entre le batiment et l'endroit où il veux construire son unité // on calcul bien sur la distance a partir du centre du
			 * batiment double distance = centreBatiment.getDistance(centrePosition); // on convertit la distance en nombre de cases
			 * distance /= Constante.LARGEUR_CASE; if (distance <= Constante.NB_CASES_DISTANCE_AVEC_BATIMENT_AUTORISE_POUR_CREATION_UNITE){
			 * presenceBatiment = true; }
			 */
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
	
	/** cette methode permet de savoir si on a relaché l'unité sur un elemnt du plateau qui apartient au joueur ou pas */
	public boolean caseOccupeParElementJoueur(Case position){
		boolean res = false; // verifie que la case est pas deja occupé par un batiment ou une unité d'un des joueurs
		
		ElementPlateau batiment = presenceDeBatimentPosition(position);
		ElementPlateau unite = presenceDeUnitePosition(position);
		
		// si un batiment ou une unité du joueur est deja sur la case
		if (batiment != null || unite != null){
			res = true;
		}
		
		return res;
	}
	
}
