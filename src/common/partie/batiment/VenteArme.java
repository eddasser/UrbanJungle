package common.partie.batiment;

import common.partie.plateau.Case;

/**
 * @author omar
 */
public class VenteArme extends Commerce{
	
	/**
	 * @param revenuBase
	 * @param pointsVieBase
	 * @param prix
	 */
	public VenteArme(Case position){
		super(TypeBatiment.VENTE_ARME,500,500,5000, position);
	}
	
}
