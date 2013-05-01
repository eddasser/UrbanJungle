package common;

import java.io.Serializable;

import client.JeuPanel;

import common.partie.plateau.Case;
import common.partie.unite.Unite;

/**
 * @author omar
 */
public abstract class ElementPlateau implements Serializable{
	
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;

	protected int pointsVie;
	
	protected TypeElementPlateau type;
	protected Case position;
	
	public ElementPlateau(TypeElementPlateau type,Case position){
		super();
		this.type = type;
		this.position = position;
		pointsVie = type.getPointDeVie(0);
	}
	
	/*
	 *  retourne la position centrale de l'element
	 */
	public abstract Case getCentre();
	
	public int getPointsVie(){
		return pointsVie;
	}
	
	public TypeElementPlateau getType(){
		return type;
	}
	
	public Case getPosition(){
		return position;
	}
	
	
	public void setPointsVie(int pointsVie){
		this.pointsVie = pointsVie;
	}
	
	public void setType(TypeElementPlateau type){
		this.type = type;
	}
	
	public void setPosition(Case position){
		this.position = position;
	}
	
	/** cette methode permet de simuler une attaque sur l'element par l'unité en parametre
	 * 
	 * @param uniteEnDeplacement, l'unité qui attaque
	 * @return estDetruit, boolean qui signale si l'unité est detruite ou pas
	 */
	public boolean attaque(Unite uniteEnDeplacement){
		boolean estDetruit = false;
		
		int puissanceAttaquant = JeuPanel.getPuissanceAttaque(uniteEnDeplacement);
		
		int nouveauPDV = pointsVie - puissanceAttaquant;
		
		if (nouveauPDV <= 0){
			nouveauPDV = 0;
			estDetruit = true;
		}
		
		setPointsVie(nouveauPDV);

		return estDetruit;
	}
}
