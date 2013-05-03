package common.ia;

import common.Constante;
import common.ElementPlateau;
import common.Joueur;
import common.Partie;
import common.partie.batiment.Batiment;
import common.partie.batiment.TypeBatiment;
import common.partie.plateau.Case;
import common.partie.unite.TypeUnite;
import common.partie.unite.Unite;

/** cette classe va permettre de representer les axe d'expension de l'ia
 * C.A.D des directions ou l'IA va envoyer des batisseurs pour construire des batiments 
 * @author Florent
 */
public class AxeExpansion {

	private Partie partie;
	private Joueur iA;
	private String direction;
	
	private Unite constructeur;
	
	
	public AxeExpansion(Partie partie, Joueur iA, Batiment batimentOrigine,String direction) {
		super();
		this.partie = partie;
		this.iA = iA;
		this.direction = direction;
		
		// on ajoute le constructeur attaché a l'axe
		
		if (direction.equals("gauche")){ //axe d'expansion gauche
			constructeur = new Unite(TypeUnite.CONSTRUCTEUR, iA.getNiveau(TypeUnite.CONSTRUCTEUR), partie.getPlateau().getCasePlusProche(batimentOrigine.getPosition().getX()-(2*Constante.LARGEUR_CASE)+5, batimentOrigine.getPosition().getY()+(2*Constante.HAUTEUR_CASE)+5));
		}else if (direction.equals("bas")){ //axe d'expansion droit
			constructeur = new Unite(TypeUnite.CONSTRUCTEUR, iA.getNiveau(TypeUnite.CONSTRUCTEUR), partie.getPlateau().getCasePlusProche(batimentOrigine.getPosition().getX()-(1*Constante.LARGEUR_CASE)+5, batimentOrigine.getPosition().getY()+(2*Constante.HAUTEUR_CASE)+5));
		}
		
		iA.ajouterUnite(constructeur);
		
		iA.decrementArgent(TypeUnite.CONSTRUCTEUR.getPrix(iA.getNiveau(TypeUnite.CONSTRUCTEUR))*2);
	}
	
	public void executeTour(){
		
		if (direction.equals("gauche")){
			
			
			int xConstruction = (constructeur.getPosition().getX())+5;
			int yConstruction = (constructeur.getPosition().getY()- 2*Constante.HAUTEUR_CASE)+5;
			
			Case positionNouveauBatiment = partie.getPlateau().getCasePlusProche(xConstruction, yConstruction);
			
			ElementPlateau elementSurCaseCible = partie.peutConstruireBatimentPosition(positionNouveauBatiment);
			boolean pasEnBordDeMap = partie.pasEnBordDeMap(positionNouveauBatiment);
			
			if (elementSurCaseCible == null && pasEnBordDeMap){ //si on peux construire
				Batiment nouveauBatiment = new Batiment(TypeBatiment.GARAGE, iA.getNiveau(TypeBatiment.GARAGE), positionNouveauBatiment);
				iA.ajouterBatiment(nouveauBatiment);		
				
				int montant = TypeBatiment.GARAGE.getPrix(iA.getNiveau(TypeBatiment.GARAGE));
				iA.decrementArgent(montant);
			}
			
			if ( !tenterDeplacerUnite("gauche")){ // on tente de deplacer a gauche et si sa marche pas ...
				if ( !tenterDeplacerUnite("bas")){ // ... on tente vers le bas et si sa marche pas non plus... 
					if ( !tenterDeplacerUnite("droite")){ // ... on tente vers la droite ...
						tenterDeplacerUnite("haut"); // ... et en dernier recours vers le haut
					}
				}
			}
			
		}else if (direction.equals("bas")){
			
			int xConstruction = (constructeur.getPosition().getX()+Constante.LARGEUR_CASE)+5;
			int yConstruction = (constructeur.getPosition().getY())+5;
			
			Case positionNouveauBatiment = partie.getPlateau().getCasePlusProche(xConstruction, yConstruction);
			
			ElementPlateau elementSurCaseCible = partie.peutConstruireBatimentPosition(positionNouveauBatiment);
			
			if (elementSurCaseCible == null){ //si on peux construire
				
				Batiment nouveauBatiment = new Batiment(TypeBatiment.GARAGE, iA.getNiveau(TypeBatiment.GARAGE), positionNouveauBatiment);
				iA.ajouterBatiment(nouveauBatiment);
				((JoueurIAHasard) iA).getListeAxeAttaque().add(new AxeAttaque(partie, iA, nouveauBatiment ));// on cree un axe d'attaque a partir de ce nouveau batiment
				
				int montant = TypeBatiment.GARAGE.getPrix(iA.getNiveau(TypeBatiment.GARAGE));
				iA.decrementArgent(montant);
			}
			
			if ( !tenterDeplacerUnite("bas")){ // on tente de deplacer a gauche et si sa marche pas ...
				if ( !tenterDeplacerUnite("gauche")){ // ... on tente vers le bas et si sa marche pas non plus... 
					if ( !tenterDeplacerUnite("haut")){ // ... on tente vers la droite ...
						tenterDeplacerUnite("droite"); // ... et en dernier recours vers le haut
					}
				}
			}
		}
	}

	// cette methode deplace les constructeurs si c'est possible en tentant toutes les direction, sinon le constructeur ne bouge pas
	private boolean tenterDeplacerUnite(String direction) {
		
		boolean res = false;
		Case nouvellePositionConstructeur;
		
		if (direction.equals("gauche")){
			nouvellePositionConstructeur = partie.getPlateau().getCasePlusProche((constructeur.getPosition().getX()-constructeur.getDeplacementRestant()*Constante.LARGEUR_CASE)+5,(constructeur.getPosition().getY())+5);
//			if (nouvellePositionConstructeur != constructeur.getPosition()){ //evite que le constructeur reste bloqué dans un coin
				if (constructeur.peutBougerVers(nouvellePositionConstructeur.getX(),nouvellePositionConstructeur.getY(),'h')){
					constructeur.setPosition(nouvellePositionConstructeur);
					res =true;
				}
//			}
		}
		else if (direction.equals("bas")){
			nouvellePositionConstructeur = partie.getPlateau().getCasePlusProche((constructeur.getPosition().getX())+5,(constructeur.getPosition().getY()+constructeur.getDeplacementRestant()*Constante.HAUTEUR_CASE)+5);
//			if (nouvellePositionConstructeur != constructeur.getPosition()){ //evite que le constructeur reste bloqué dans un coin
				if (constructeur.peutBougerVers(nouvellePositionConstructeur.getX(),nouvellePositionConstructeur.getY(),'v')){
					constructeur.setPosition(nouvellePositionConstructeur);
					res =true;
				}
//			}	
		}
		else if (direction.equals("droite")){
			nouvellePositionConstructeur = partie.getPlateau().getCasePlusProche((constructeur.getPosition().getX()+constructeur.getDeplacementRestant()*Constante.LARGEUR_CASE)+5,(constructeur.getPosition().getY())+5);
//			if (nouvellePositionConstructeur != constructeur.getPosition()){ //evite que le constructeur reste bloqué dans un coin
				if (constructeur.peutBougerVers(nouvellePositionConstructeur.getX(),nouvellePositionConstructeur.getY(),'h')){
					constructeur.setPosition(nouvellePositionConstructeur);
					res =true;
				}
//			}			
		}
		else if (direction.equals("haut")){
			nouvellePositionConstructeur = partie.getPlateau().getCasePlusProche((constructeur.getPosition().getX())+5,(constructeur.getPosition().getY()-constructeur.getDeplacementRestant()*Constante.HAUTEUR_CASE)+5);
//			if (nouvellePositionConstructeur != constructeur.getPosition()){ //evite que le constructeur reste bloqué dans un coin
				if (constructeur.peutBougerVers(nouvellePositionConstructeur.getX(),nouvellePositionConstructeur.getY(),'v')){
					constructeur.setPosition(nouvellePositionConstructeur);
					res =true;
				}
//			}	
		}
		
		return res;
	}
}
