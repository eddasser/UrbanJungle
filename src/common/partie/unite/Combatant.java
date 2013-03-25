package common.partie.unite;

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
	public Combatant(int pointsVieBase,int pointsAttaqueBase,int salaireBase,int vitesseBase){
		super(pointsVieBase,pointsAttaqueBase,salaireBase,vitesseBase);
	}
	
}
