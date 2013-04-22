package common.partie.unite;


/**
 * @author omar
 */
public enum TypeUnite{
	PETITE_FRAPPE, HOMME_MAIN, CAID, BASTONNEUR, CONSTRUCTEUR;
	
	public static int getNiveauBase(TypeUnite type){
		return 1;
	}
	
	public static int getPointDeVieBase(TypeUnite type){
		int ptsVie = 0;
		
		switch(type){
			case PETITE_FRAPPE:
				ptsVie = 100;
				break;
			
			case HOMME_MAIN:
				ptsVie = 150;
				break;
			
			case CAID:
				ptsVie = 200;
				break;
			
			case BASTONNEUR:
				ptsVie = 150;
				break;
			
			case CONSTRUCTEUR:
				ptsVie = 50;
				break;
		}
		
		return ptsVie;
	}
	
	
	public static int getPointAttaqueBase(TypeUnite type){
		int ptsAttaque = 0;
		
		switch(type){
			case PETITE_FRAPPE:
				ptsAttaque = 10;
				break;
			
			case HOMME_MAIN:
				ptsAttaque = 15;
				break;
			
			case CAID:
				ptsAttaque = 20;
				break;
			
			case BASTONNEUR:
				ptsAttaque = 15;
				break;
			
			case CONSTRUCTEUR:
				ptsAttaque = 0;
				break;
		}
		
		return ptsAttaque;
	}
	
	public static int getSalaireBase(TypeUnite type){
		int salaire = 0;
		
		switch(type){
			case PETITE_FRAPPE:
				salaire = 20;
				break;
			
			case HOMME_MAIN:
				salaire = 20;
				break;
			
			case CAID:
				salaire = 40;
				break;
			
			case BASTONNEUR:
				salaire = 20;
				break;
			
			case CONSTRUCTEUR:
				salaire = 10;
				break;
		}
		
		return salaire;
	}
	
	public static int getVitesseBase(TypeUnite type){
		int vitesse = 0;
		
		switch(type){
			case PETITE_FRAPPE:
				vitesse = 1;
				break;
			
			case HOMME_MAIN:
				vitesse = 2;
				break;
			
			case CAID:
				vitesse = 3;
				break;
			
			case BASTONNEUR:
				vitesse = 2;
				break;
			
			case CONSTRUCTEUR:
				vitesse = 2;
				break;
		}
		
		return vitesse;
	}
	
	public static int getMontantLevelUpBase(TypeUnite type){
		int montant = 0;
		
		switch(type){
			case PETITE_FRAPPE:
				montant = 100;
				break;
			
			case HOMME_MAIN:
				montant = 100;
				break;
			
			case CAID:
				montant = 100;
				break;
			
			case BASTONNEUR:
				montant = 100;
				break;
			
			case CONSTRUCTEUR:
				montant = 100;
				break;
		}
		
		return montant;
	}
}
