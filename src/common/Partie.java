package common;

import java.io.Serializable;
import java.util.ArrayList;

import common.partie.batiment.Batiment;
import common.partie.batiment.TypeBatiment;
import common.partie.plateau.Case;
import common.partie.plateau.Plateau;
import common.partie.unite.Unite;


public class Partie implements Serializable{
	
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	private String nomPartie; // nom donné a la parti par son créateur
	private int nbJoueur; // nb de joueur requis pour cette partie
	private ArrayList<Joueur> listeParticipants; // liste des joueurs participants a cette partie
	private int indiceJoueurCourant; // indice dans la liste du Joueur qui joue a un instant donne (change a chaque tour)
	private Etat etatDeLaPartie; // etat de la partie possible : "en cours" ou "en attente"
	private String password;// password de la partie, si null alors la partie est public sinon elle est privé
	private Plateau plateau;// plateau de jeu
	private boolean solo;// indique si la partie est en solo (vs IA) ou pas
	
	public Partie(){
		nomPartie = "";
		nbJoueur = 0;
		indiceJoueurCourant = 0;
		listeParticipants = new ArrayList<Joueur>();
		plateau = new Plateau();
		etatDeLaPartie = Etat.EN_ATTENTE_JOUEUR;
		solo = false;
	}
	
	public Partie(String nomPartieParam,int nbJoueurParam,String passwordParam,boolean solo){
		this();
		nomPartie = nomPartieParam;
		nbJoueur = nbJoueurParam;
		password = passwordParam;
		this.solo = solo;
	}
	
	/*
	 * methode qui permet d'initialiser la partie courante à partir des informations de la partie en parametre
	 */
	public void initialiserPartie(Partie partie){
		nomPartie = partie.nomPartie;
		nbJoueur = partie.nbJoueur;
		listeParticipants = partie.listeParticipants;
		indiceJoueurCourant = partie.indiceJoueurCourant;
		etatDeLaPartie = partie.etatDeLaPartie;
		password = partie.password;
		plateau = partie.plateau;
		solo = partie.solo;
	}
	
	public void initialiserPartie(){
		// methode qui va creer les QG et donner au joueur leur argent de depart et désigner un joueur de départ
		ArrayList<Case> cases = plateau.getCases();
		for (int i = 0 ; i < listeParticipants.size() ; i++){
			Batiment qg = new Batiment(TypeBatiment.QG,TypeBatiment.QG.getNiveauBase(),cases.get(getPositionQG(i)));
			listeParticipants.get(i).ajouterBatiment(qg);
		}
		int random = (int)(Math.random() * (listeParticipants.size() - 1));
		indiceJoueurCourant = random;
	}
	
	public void setIndiceJoueurCourant(int ind){
		indiceJoueurCourant = 0;
	}
	
	private int getPositionQG(int niemeJoueur){
		int position = 0;
		switch(niemeJoueur){
			case 0:
				position = Constante.NB_CASES_LARGEUR_PLATEAU * Constante.NB_CASES_HAUTEUR_PLATEAU - 2 * Constante.NB_CASES_LARGEUR_PLATEAU;
				break;
			case 1:
				position = Constante.NB_CASES_LARGEUR_PLATEAU - 2;
				break;
			case 2:
				position = 0;
				break;
			case 3:
				position = Constante.NB_CASES_LARGEUR_PLATEAU * Constante.NB_CASES_HAUTEUR_PLATEAU - Constante.NB_CASES_LARGEUR_PLATEAU - 2;
				break;
			default:
				position = random(0,Constante.NB_CASES_HAUTEUR_PLATEAU * Constante.NB_CASES_LARGEUR_PLATEAU - 1);
				break;
		}
		return position;
	}
	
	private int random(int min,int max){
		return min + (int)(Math.random() * ((max - min) + 1));
	}
	
	public void notifierDebutJeu(){
		Joueur joueurCourant = getJoueurCourant();
		for (int i = 0 ; i < listeParticipants.size() ; i++){
			Joueur joueur = listeParticipants.get(i);
			boolean is_courant = joueur.equals(joueurCourant);
			
			Object[] args = { Commande.DEBUT_JEU,this,joueur,is_courant };
			joueur.send(args);
		}
	}
	
	public boolean placeDisponible(){
		boolean placeDisponible = false;
		
		if (listeParticipants.size() < nbJoueur){
			placeDisponible = true;
		}else if (etatDeLaPartie == Etat.SAUVEGARDEE){
			for (int i = 0 ; !placeDisponible && i < listeParticipants.size() ; i++){
				Joueur joueur = listeParticipants.get(i);
				if (joueur.getSocket() == null){
					placeDisponible = true;
				}
			}
		}
		
		return placeDisponible;
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
	
	
	public synchronized boolean addJoueur(Joueur joueur){
		boolean add = false;
		if (placeDisponible()){
			int i = listeParticipants.size();
			if (etatDeLaPartie == Etat.SAUVEGARDEE){
				// dans le cas d'une partie sauvegardee, on met seulement a jour la socket vers le joueur
				boolean ajoute = false;
				for (i = 0 ; !ajoute && i < listeParticipants.size() ; i++){
					Joueur joueurCourant = listeParticipants.get(i);
					if (joueurCourant.getSocket() == null){
						joueur.initialiserJoueur(joueurCourant);
						listeParticipants.remove(joueurCourant);
						ajoute = true;
					}
				}
				if (i > listeParticipants.size()) i--;
			}
			listeParticipants.add(i,joueur);
			add = true;
		}
		return add;
	}
	
	public String getNomPartie(){
		return nomPartie;
	}
	
	public void setNomPartie(String nomPartie){
		this.nomPartie = nomPartie;
	}
	
	public int getNbJoueurRequis(){
		return nbJoueur;
	}
	
	public int getNbJoueurActuellement(){
		return listeParticipants.size();
	}
	
	public void setNbJoueurRequis(int nbJoueur){
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
	
	/*
	 *  retourne le nouvel admin apres la deconnexion du joueur en parametre
	 */
	public Joueur getJoueurAdminApresDeconnexion(Joueur joueur){
		if (joueur.equals(getJoueurAdmin())){
			// si le joueur qui s'est deconnecter était l'admin , on retourne le joueur suivant
			return listeParticipants.get(1);
		}else{
			return listeParticipants.get(0);
		}
	}
	
	public void setJoueurAdmin(Joueur joueurAdmin){
		listeParticipants.set(0,joueurAdmin);
	}
	
	public Joueur getJoueurCourant(){
		if (indiceJoueurCourant < listeParticipants.size()){
			return listeParticipants.get(indiceJoueurCourant);
		}else{
			return null;
		}
	}
	
	public void setJoueurCourant(Joueur joueurCourant){
		indiceJoueurCourant = listeParticipants.indexOf(joueurCourant);
	}
	
	public Plateau getPlateau(){
		return plateau;
	}
	
	public boolean isSolo(){
		return solo;
	}
	
	public void setSolo(boolean solo){
		this.solo = solo;
	}
	
	public void setPlateau(Plateau plateau){
		this.plateau = plateau;
	}
	
	public ElementPlateau peutConstruireBatimentPosition(Case position){
		ElementPlateau elementSurCase = null;
		
		// on verifie d'abord qu'il n'y a pas d'autres batiment d'aucun joueur a cette position
		for (int i = 0 ; elementSurCase == null && i < listeParticipants.size() ; i++){
			elementSurCase = listeParticipants.get(i).presenceDeBatimentPosition(position);
		}
		
		// on verifie ensuite qu'il n'y a aucune unité à cette position
		if (elementSurCase == null){
			for (int i = 0 ; elementSurCase == null && i < listeParticipants.size() ; i++){
				elementSurCase = listeParticipants.get(i).presenceDeUnitePosition(position);
			}
		}
		
		return elementSurCase;
	}
	
	public ElementPlateau elementSurCase(Case position){
		ElementPlateau res = null;
		
		res = peutConstruireBatimentPosition(position); // verifie que la case est pas deja occupé par un batiment ou une unité d'un des
														// joueurs
		return res;
	}
	
	public void passerTour(){
		indiceJoueurCourant++;
		if (indiceJoueurCourant >= listeParticipants.size()){
			indiceJoueurCourant = 0;
			
			for (int i = 0 ; i < listeParticipants.size() ; i++){
				listeParticipants.get(i).majTour();
			}
		}
	}
	
	
	/** retourne le proprietaire de l'element en parametre */
	public Joueur proprietaireElement(ElementPlateau element){
		Joueur proprio = null;
		
		proprio = getProprioBatiment(element);
		
		if (proprio == null){
			proprio = getProprioUnite(element);
		}
		
		return proprio;
	}
	
	/*
	 * retourne l'unite présente sur la case ou NULL
	 */
	public Joueur getProprioUnite(ElementPlateau element){
		Joueur proprio = null;
		
		for (Joueur joueurTmp : listeParticipants){ // pour chaque joueur
			if (proprio == null){
				for (int i = 0 ; i < joueurTmp.getUnites().size() ; i++){
					Unite unite_courante = joueurTmp.getUnites().get(i);
					if (unite_courante.equals(element)){
						proprio = joueurTmp;
						break;
					}
				}
			}
		}
		return proprio;
	}
	
	/*
	 * retourne l'unite présente sur la case ou NULL
	 */
	public Joueur getProprioBatiment(ElementPlateau element){
		Joueur proprio = null;
		
		for (Joueur joueurTmp : listeParticipants){ // pour chaque joueur
			if (proprio == null){
				for (int i = 0 ; i < joueurTmp.getBatiments().size() ; i++){
					Batiment batiment_courant = joueurTmp.getBatiments().get(i);
					if (batiment_courant.equals(element)){
						proprio = joueurTmp;
						break;
					}
				}
			}
		}
		return proprio;
	}
	
	public boolean pasEnBordDeMap(Case positionNouveauBatiment){
		boolean res = true;
		
		int xMAXAutorise = (Constante.NB_CASES_LARGEUR_PLATEAU - 1) * Constante.LARGEUR_CASE;
		int yMAXAutorise = (Constante.NB_CASES_HAUTEUR_PLATEAU - 1) * Constante.HAUTEUR_CASE;
		
		if (positionNouveauBatiment.getX() >= xMAXAutorise || positionNouveauBatiment.getY() >= yMAXAutorise){
			res = false;
		}
		
		return res;
	}
	
	public void detruireElement(ElementPlateau elementEnnemi){
		Joueur proprio;
		
		proprio = getProprioBatiment(elementEnnemi);
		
		if (proprio == null){
			proprio = getProprioUnite(elementEnnemi);
			proprio.getUnites().remove(elementEnnemi);
		}else{
			proprio.getBatiments().remove(elementEnnemi);
		}
		
	}
	
}