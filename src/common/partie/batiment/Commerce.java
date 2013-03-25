package common.partie.batiment;

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
	public Commerce(TypeBatiment type,int revenuBase,int pointsVieBase,int prix){
		super(type,revenuBase,pointsVieBase);
		this.prix = prix;
	}
	
	public int getPrix(){
		return prix;
	}
	
	public void setPrix(int prix){
		this.prix = prix;
	}
	
}
