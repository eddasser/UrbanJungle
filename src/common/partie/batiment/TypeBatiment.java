package common.partie.batiment;

/**
 * @author omar
 */
public enum TypeBatiment{
	QG, GARAGE, VENTE_DROGUE, VENTE_ALCOOL, VENTE_ARME, BOOKMAKER;
	
	public static int getNiveauBase(TypeBatiment type){
		return 1;
	}
	
	public static int getRevenuBase(TypeBatiment type){
		int revenu = 0;
		
		switch(type){
			case QG:
				revenu = 1000;
				break;
			
			case VENTE_DROGUE:
				revenu = 200;
				break;
			
			case VENTE_ALCOOL:
				revenu = 200;
				break;
			
			case BOOKMAKER:
				revenu = 400;
				break;
			
			case VENTE_ARME:
				revenu = 500;
				break;
			
			case GARAGE:
				revenu = 300;
				break;
		}
		
		return revenu;
	}
	
	
	public static int getPointDeVieBase(TypeBatiment type){
		int ptsVie = 0;
		
		switch(type){
			case QG:
				ptsVie = 10000;
				break;
			
			case VENTE_DROGUE:
				ptsVie = 100;
				break;
			
			case VENTE_ALCOOL:
				ptsVie = 100;
				break;
			
			case BOOKMAKER:
				ptsVie = 200;
				break;
			
			case VENTE_ARME:
				ptsVie = 500;
				break;
			
			case GARAGE:
				ptsVie = 100;
				break;
		}
		
		return ptsVie;
	}
	
	public static int getPrixBase(TypeBatiment type){
		int prix = -1;
		
		switch(type){
			case VENTE_DROGUE:
				prix = 1000;
				break;
			
			case VENTE_ALCOOL:
				prix = 1000;
				break;
			
			case BOOKMAKER:
				prix = 2000;
				break;
			
			case VENTE_ARME:
				prix = 5000;
				break;
			
			case GARAGE:
				prix = 1000;
				break;
		}
		
		return prix;
	}
	
	public static int getMontantLevelUp(TypeBatiment type){
		int montant = 0;
		
		switch(type){
			case QG:
				montant = 1000;
				break;
			
			case VENTE_DROGUE:
				montant = 200;
				break;
			
			case VENTE_ALCOOL:
				montant = 200;
				break;
			
			case BOOKMAKER:
				montant = 400;
				break;
			
			case VENTE_ARME:
				montant = 500;
				break;
			
			case GARAGE:
				montant = 200;
				break;
		}
		
		return montant;
	}
	
}
