package common.partie.batiment;

import common.partie.plateau.Case;

/**
 * @author omar
 */
public abstract class Batiment{
	protected int niveau;
	protected int pointsVie;
	
	protected TypeBatiment type;
	protected Case position;
	
	public Batiment(TypeBatiment type,Case position){
		super();
		this.type = type;
		this.position = position;
		niveau = TypeBatiment.getNiveauBase(type);
		pointsVie = TypeBatiment.getPointDeVie(type,niveau);
	}
	
	public int getNiveau(){
		return niveau;
	}
	
	public int getPointsVie(){
		return pointsVie;
	}
	
	public TypeBatiment getType(){
		return type;
	}
	
	public void setNiveau(int niveau){
		this.niveau = niveau;
	}
	
	public void setPointsVie(int pointsVie){
		this.pointsVie = pointsVie;
	}
	
	public void setType(TypeBatiment type){
		this.type = type;
	}
	
	public Case getPosition(){
		return position;
	}
	
	public void setPosition(Case position){
		this.position = position;
	}
}
