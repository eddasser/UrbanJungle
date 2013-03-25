package common.partie.batiment;

import common.partie.plateau.Case;

/**
 * @author omar
 */
public abstract class Batiment{
	protected int revenuBase;
	protected int pointsVieBase;
	
	protected int niveau;
	protected int pointsVie;
	
	protected TypeBatiment type;
	protected Case position;

	public Batiment(TypeBatiment type,int revenuBase,int pointsVieBase, Case position){
		super();
		this.type = type;
		this.revenuBase = revenuBase;
		this.pointsVieBase = pointsVieBase;
		niveau = 1;
		pointsVie = pointsVieBase;
		this.position = position;
	}
	
	public int getRevenuBase(){
		return revenuBase;
	}
	
	public int getPointsVieBase(){
		return pointsVieBase;
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
	
	public void setRevenuBase(int revenuBase){
		this.revenuBase = revenuBase;
	}
	
	public void setPointsVieBase(int pointsVieBase){
		this.pointsVieBase = pointsVieBase;
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
	
	public Case getPosition() {
		return position;
	}

	public void setPosition(Case position) {
		this.position = position;
	}
}
