package common;

import java.io.Serializable;

import common.partie.plateau.Case;

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
	
}
