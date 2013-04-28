package common.partie.batiment;

import common.Constante;
import common.ElementPlateau;
import common.partie.plateau.Case;

/**
 * @author omar
 */
public class Batiment extends ElementPlateau{
	
	public Batiment(TypeBatiment type,Case position){
		super(type,position);
	}
	
	@Override
	public Case getCentre(){
		int x = position.getX();
		int y = position.getY();
		Case centre = new Case(x + Constante.LARGEUR_CASE,y + Constante.HAUTEUR_CASE);
		return centre;
	}
	
}
