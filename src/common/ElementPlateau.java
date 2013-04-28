package common;

import common.partie.plateau.Case;

/**
 * @author omar
 */
public abstract class ElementPlateau{
	protected int pointsVie;
	
	protected TypeElementPlateau type;
	protected Case position;
	
	public ElementPlateau(TypeElementPlateau type,Case position){
		super();
		this.type = type;
		this.position = position;
		pointsVie = type.getPointDeVie(0);
	}
	
	
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
