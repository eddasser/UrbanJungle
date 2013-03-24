package common.partie.batiment;

/**
 * @author omar
 */
public abstract class Batiment{
	protected int revenuBase;
	protected int pointsVieBase;
	
	protected int niveau;
	protected int pointsVie;
	
	public Batiment(int revenuBase,int pointsVieBase){
		super();
		this.revenuBase = revenuBase;
		this.pointsVieBase = pointsVieBase;
		niveau = 1;
		pointsVie = pointsVieBase;
	}
	
	public int getRevenuBase(){
		return revenuBase;
	}
	
	public int getPointsVieBase(){
		return pointsVieBase;
	}
	
	public int getNiveau(){
		return niveau;
	}
	
	public int getPointsVie(){
		return pointsVie;
	}
	
	public void setRevenuBase(int revenuBase){
		this.revenuBase = revenuBase;
	}
	
	public void setPointsVieBase(int pointsVieBase){
		this.pointsVieBase = pointsVieBase;
	}
	
	public void setNiveau(int niveau){
		this.niveau = niveau;
	}
	
	public void setPointsVie(int pointsVie){
		this.pointsVie = pointsVie;
	}
	
}
