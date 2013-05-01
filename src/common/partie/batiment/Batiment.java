package common.partie.batiment;

import java.io.Serializable;

import common.Constante;
import common.ElementPlateau;
import common.partie.plateau.Case;


public class Batiment extends ElementPlateau implements Serializable{
	
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	
	public Batiment(TypeBatiment type,int niveau,Case position){
		super(type,niveau,position);
	}
	
	@Override
	public Case getCentre(){
		int x = position.getX();
		int y = position.getY();
		Case centre = new Case(x + Constante.LARGEUR_CASE,y + Constante.HAUTEUR_CASE);
		return centre;
	}
	
}
