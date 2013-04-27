package common.partie.unite;

import common.Constante;
import common.partie.plateau.Case;

/**
 * @author omar
 */
public class Unite{
	protected int niveau;// niveau courant de l'unité, agit comme un facteur
	protected int pointsVie;// point de vie actuel de l'unité
	
	protected Case position; // Position actuelle de l'unite
	protected TypeUnite type;// type de l'unite
	private int deplacementRestant;// nombre de case que l'unite peut encore parcourrir
	
	public Unite(TypeUnite type,Case position){
		super();
		this.type = type;
		this.position = position;
		niveau = type.getNiveauBase();
		pointsVie = type.getPointDeVie(niveau);
		deplacementRestant = type.getVitesse(niveau);
	}
	
	public int getNiveau(){
		return niveau;
	}
	
	public int getPointsVie(){
		return pointsVie;
	}
	
	public Case getPosition(){
		return position;
	}
	
	public TypeUnite getType(){
		return type;
	}
	
	public int getDeplacementRestant(){
		return deplacementRestant;
	}
	
	public void setDeplacementRestant(int deplacementRestant){
		this.deplacementRestant = deplacementRestant;
	}
	
	public void setNiveau(int niveau){
		this.niveau = niveau;
	}
	
	public void setPointsVie(int pointsVie){
		this.pointsVie = pointsVie;
	}
	
	public void setPosition(Case position){
		this.position = position;
	}
	
	public void setType(TypeUnite type){
		this.type = type;
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
