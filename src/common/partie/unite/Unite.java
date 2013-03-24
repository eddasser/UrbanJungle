package common.partie.unite;

/**
 * @author omar
 */
public abstract class Unite{
	protected int pointsVieBase;
	protected int pointsAttaqueBase;
	protected int salaireBase;
	protected int vitesseBase;
	
	protected int niveau;// niveau courant de l'unité, agit comme un facteur
	protected int pointsVie;// point de vie actuel de l'unité
	
	
	public Unite(int pointsVieBase,int pointsAttaqueBase,int salaireBase,int vitesseBase){
		super();
		this.pointsVieBase = pointsVieBase;
		this.pointsAttaqueBase = pointsAttaqueBase;
		this.salaireBase = salaireBase;
		this.vitesseBase = vitesseBase;
		niveau = 1;
		pointsVie = pointsVieBase;
	}
	
	public int getPointsVieBase(){
		return pointsVieBase;
	}
	
	public int getPointsAttaqueBase(){
		return pointsAttaqueBase;
	}
	
	public int getSalaireBase(){
		return salaireBase;
	}
	
	public int getVitesseBase(){
		return vitesseBase;
	}
	
	public int getNiveau(){
		return niveau;
	}
	
	public int getPointsVie(){
		return pointsVie;
	}
	
	public void setPointsVieBase(int pointsVieBase){
		this.pointsVieBase = pointsVieBase;
	}
	
	public void setPointsAttaqueBase(int pointsAttaqueBase){
		this.pointsAttaqueBase = pointsAttaqueBase;
	}
	
	public void setSalaireBase(int salaireBase){
		this.salaireBase = salaireBase;
	}
	
	public void setVitesseBase(int vitesseBase){
		this.vitesseBase = vitesseBase;
	}
	
	public void setNiveau(int niveau){
		this.niveau = niveau;
	}
	
	public void setPointsVie(int pointsVie){
		this.pointsVie = pointsVie;
	}
}
