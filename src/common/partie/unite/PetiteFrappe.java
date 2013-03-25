package common.partie.unite;

import common.partie.plateau.Case;

/**
 * @author omar
 */
public class PetiteFrappe extends Combatant{
	
	/**
	 * @param pointsVieBase
	 * @param pointsAttaqueBase
	 * @param salaireBase
	 * @param vitesseBase
	 */
	public PetiteFrappe(Case position){
		super(100,10,20,1, position);
	}
	
}
