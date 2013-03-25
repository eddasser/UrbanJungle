package common.partie.unite;

import common.partie.plateau.Case;

/**
 * @author omar
 */
public abstract class Combatant extends Unite{
	
	/**
	 * @param pointsVieBase
	 * @param pointsAttaqueBase
	 * @param salaireBase
	 * @param vitesseBase
	 */
	public Combatant(int pointsVieBase,int pointsAttaqueBase,int salaireBase,int vitesseBase, Case position){
		super(pointsVieBase,pointsAttaqueBase,salaireBase,vitesseBase, position);
	}
	
}
