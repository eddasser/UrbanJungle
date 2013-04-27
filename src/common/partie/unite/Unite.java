package common.partie.unite;

import java.io.Serializable;

import common.Constante;
import common.partie.plateau.Case;

/**
 * @author omar
 */
public class Unite implements Serializable{
	
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	protected int niveau;// niveau courant de l'unité, agit comme un facteur
	protected int pointsVie;// point de vie actuel de l'unité
	
	protected Case position; // Position actuelle de l'unite
	protected TypeUnite type;// type de l'unite
	
	public Unite(TypeUnite type,Case position){
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
	
	public Case getPosition(){
		return position;
	}
	
	public TypeUnite getType(){
		return type;
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
	
}
