package common.partie.batiment;

import common.partie.plateau.Case;

/**
 * @author omar
 */
public class VenteDrogue extends Commerce{
	
	/**
	 * @param revenuBase
	 * @param pointsVieBase
	 * @param prix
	 */
	public VenteDrogue(Case position){
		super(TypeBatiment.VENTE_DROGUE,200,100,1000, position);
	}
	
}
