package common.partie.batiment;

import common.partie.plateau.Case;

/**
 * @author omar
 */
public class Bookmaker extends Commerce{
	
	/**
	 * @param revenuBase
	 * @param pointsVieBase
	 * @param prix
	 */
	public Bookmaker(Case position){
		super(TypeBatiment.BOOKMAKER,400,200,2000, position);
	}
	
}
