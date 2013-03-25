package common.partie.batiment;

import common.partie.plateau.Case;

/**
 * @author omar
 */
public abstract class Commerce extends Batiment{
	protected int prix;
	
	/**
	 * @param revenuBase
	 * @param pointsVieBase
	 * @param prix
	 */
	public Commerce(TypeBatiment type,int revenuBase,int pointsVieBase,int prix, Case position){
		super(type,revenuBase,pointsVieBase, position);
		this.prix = prix;
	}
	
	public int getPrix(){
		return prix;
	}
	
	public void setPrix(int prix){
		this.prix = prix;
	}
	
}
