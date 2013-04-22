package common.partie.batiment;

/**
 * @author omar
 */
public enum TypeBatiment{
	QG, GARAGE, VENTE_DROGUE, VENTE_ALCOOL, VENTE_ARME, BOOKMAKER;
	
	public static int getNiveauBase(TypeBatiment type){
		return 1;
	}
	
	public static int getRevenu(TypeBatiment type,int niveau){
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
		
		revenu += revenu * niveau / 10;
		
		return revenu;
	}
	
	
	public static int getPointDeVie(TypeBatiment type,int niveau){
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
		
		ptsVie += ptsVie * niveau / 10;
		
		return ptsVie;
	}
	
	public static int getPrix(TypeBatiment type,int niveau){
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
		
		prix += prix * niveau / 10;
		
		return prix;
	}
	
	public static int getMontantLevelUp(TypeBatiment type,int niveau){
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
		
		montant += montant * niveau / 10;
		
		return montant;
	}
	
}
