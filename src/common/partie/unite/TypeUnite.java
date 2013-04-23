package common.partie.unite;

import java.awt.Color;


/**
 * @author omar
 */
public enum TypeUnite{
	PETITE_FRAPPE, HOMME_MAIN, CAID, BASTONNEUR, CONSTRUCTEUR;
	
	public static int getNiveauBase(TypeUnite type){
		return 0;
	}
	
	public static Color getColor(TypeUnite type){
		Color color = null;
		
		switch(type){
			case PETITE_FRAPPE:
				color = Color.lightGray;
				break;
			
			case HOMME_MAIN:
				color = Color.darkGray;
				break;
			
			case CAID:
				color = Color.black;
				break;
			
			case BASTONNEUR:
				color = Color.yellow;
				break;
			
			case CONSTRUCTEUR:
				color = Color.green;
				break;
		}
		
		return color;
	}
	
	public static int getPointDeVie(TypeUnite type,int niveau){
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
		
		ptsVie += ptsVie * niveau / 10;
		
		return ptsVie;
	}
	
	
	public static int getPointAttaque(TypeUnite type,int niveau){
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
		
		ptsAttaque += ptsAttaque * niveau / 10;
		
		return ptsAttaque;
	}
	
	public static int getSalaire(TypeUnite type,int niveau){
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
		
		salaire += salaire * niveau / 10;
		
		return salaire;
	}
	
	public static int getVitesse(TypeUnite type,int niveau){
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
		
		vitesse += vitesse * niveau / 10;
		
		return vitesse;
	}
	
	public static int getMontantLevelUp(TypeUnite type,int niveau){
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
		
		montant += montant * niveau / 10;
		
		return montant;
	}
	
	
	public static int getPrix(TypeUnite type,int niveau){
		int prix = -1;
		
		switch(type){
			case PETITE_FRAPPE:
				prix = 100;
				break;
			
			case HOMME_MAIN:
				prix = 100;
				break;
			
			case CAID:
				prix = 100;
				break;
			
			case BASTONNEUR:
				prix = 100;
				break;
			
			case CONSTRUCTEUR:
				prix = 100;
				break;
		}
		
		prix += prix * niveau / 10;
		
		return prix;
	}
}
