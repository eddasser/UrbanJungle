package common.partie.unite;

import common.Constante;
import common.ElementPlateau;
import common.partie.plateau.Case;

/**
 * @author omar
 */
public class Unite extends ElementPlateau{
	private int deplacementRestant;// nombre de case que l'unite peut encore parcourrir
	
	public Unite(TypeUnite type,Case position){
		super(type,position);
		deplacementRestant = type.getVitesse(0);
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
}
