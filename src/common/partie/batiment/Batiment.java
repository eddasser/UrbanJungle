package common.partie.batiment;

import java.io.Serializable;

import common.Constante;
import common.partie.plateau.Case;

/**
 * @author omar
 */
public class Batiment implements Serializable{
	
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	protected int niveau;
	protected int pointsVie;
	
	protected TypeBatiment type;
	protected Case position;
	
	public Batiment(TypeBatiment type,Case position){
		super();
		this.type = type;
		this.position = position;
		niveau = type.getNiveauBase();
		pointsVie = type.getPointDeVie(niveau);
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
