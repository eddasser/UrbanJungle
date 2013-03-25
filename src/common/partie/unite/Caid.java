package common.partie.unite;

import common.partie.plateau.Case;

/**
 * @author omar
 */
public class Caid extends Combatant{
	
	/**
	 * @param pointsVieBase
	 * @param pointsAttaqueBase
	 * @param salaireBase
	 * @param vitesseBase
	 */
	public Caid(Case position){
		super(200,20,40,3, position);
	}
	
}
