package common.partie.unite;

import java.io.Serializable;

import common.Constante;
import common.ElementPlateau;
import common.partie.plateau.Case;

/**
 * @author omar
 */
public class Unite extends ElementPlateau implements Serializable{
	
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	private int deplacementRestant;// nombre de case que l'unite peut encore parcourrir
	
	public Unite(TypeUnite type,int niveau,Case position){
		super(type,niveau,position);
		deplacementRestant = type.getVitesse(niveau);
	}
	
	@Override
	public Case getCentre(){
		int x = position.getX();
		int y = position.getY();
		Case centre = new Case(x + Constante.LARGEUR_CASE / 2,y + Constante.HAUTEUR_CASE / 2);
		return centre;
	}
	
	public int getDeplacementRestant(){
		return deplacementRestant;
	}
	
	public void setDeplacementRestant(int deplacementRestant){
		this.deplacementRestant = deplacementRestant;
	}
	
	public boolean deplacementPossibleVersPosition(int x,int y){
		boolean deplacementPossible = false;
		
		// on part de la position centrale de la case ou se trouve l'unite
		Case positionCentre = new Case(position.getX() + Constante.LARGEUR_CASE / 2,position.getY() + Constante.HAUTEUR_CASE / 2);
		
		// on calcul la distance maximal que peut parcourrir l'unite (on ajoute une marge = Constante.LARGEUR_CASE / 2 car on par du centre
		double dMax = deplacementRestant * Constante.LARGEUR_CASE + Constante.LARGEUR_CASE / 2;
		
		// on calcul la distance total a parcourir
		double dTotal = positionCentre.getDistance(x,y);
		
		if (dTotal <= dMax){
			deplacementPossible = true;
		}
		
		return deplacementPossible;
	}
	
	public void decrementDeplacementRestant(int nbCases){
		deplacementRestant -= nbCases;
	}
	
	// methode deplacement possible avec calcul plus precis pour les deplacement en ligne et colonne
	public boolean peutBougerVers(int x, int y, char direction) {
		
		boolean deplacementPossible = false;

		// on calcul la distance maximal que peut parcourrir l'unite 
		double dMax;
		if ( direction == 'h' ){//deplacement horizontal, calcul basé sur les x
			dMax = deplacementRestant * Constante.LARGEUR_CASE;
		}else{//deplacement vertical, calcul basé sur les y
			dMax = deplacementRestant * Constante.HAUTEUR_CASE;
		}
		
		int dTotal;
		// on calcul la distance total a parcourir
		if ( direction == 'h' ){//deplacement horizontal, calcul basé sur les x
			dTotal = position.getX() - x;
		}else{//deplacement vertical, calcul basé sur les y
			dTotal = position.getY() - y;
		}
		if (dTotal < 0){dTotal = (-1)*dTotal;} //valeur absolu
			
		
		if (dTotal <= dMax){
			deplacementPossible = true;
		}
		
		return deplacementPossible;
	}
}
