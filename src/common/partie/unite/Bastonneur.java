package common.partie.unite;

import common.partie.plateau.Case;

/**
 * @author omar
 */
public class Bastonneur extends Combatant{
	
	/**
	 * @param pointsVieBase
	 * @param pointsAttaqueBase
	 * @param salaireBase
	 * @param vitesseBase
	 */
	public Bastonneur(Case position){
		super(150,15,20,2, position);
	}
	
}
