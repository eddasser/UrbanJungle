package common.partie.batiment;

import common.partie.plateau.Case;

/**
 * @author omar
 */
public class QG extends Batiment{
	
	/**
	 * @param revenuBase
	 * @param pointsVieBase
	 */
	public QG(Case position){
		super(TypeBatiment.QG,1000,1000, position);
	}
	
}
