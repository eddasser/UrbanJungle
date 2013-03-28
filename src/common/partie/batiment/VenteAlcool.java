package common.partie.batiment;

import common.partie.plateau.Case;

/**
 * @author omar
 */
public class VenteAlcool extends Commerce{
	
	/**
	 * @param revenuBase
	 * @param pointsVieBase
	 * @param prix
	 */
	public VenteAlcool(Case position){
		super(TypeBatiment.VENTE_ALCOOL,200,100,1000, position);
	}
	
}
