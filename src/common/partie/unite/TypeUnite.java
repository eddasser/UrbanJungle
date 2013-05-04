package common.partie.unite;

import java.io.Serializable;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import common.Translator;
import common.TypeElementPlateau;


/**
 * @author omar
 */
public enum TypeUnite implements TypeElementPlateau,Serializable{
	PETITE_FRAPPE, HOMME_MAIN, CAID, BASTONNEUR, CONSTRUCTEUR;
	
	/*
	 * Il s'agit des icons qui sont affichée lorsque l'on clic sur le menu "unité", les icones sont rectangulaire et plus grande
	 */
	private static Icon ICON_PETITE_FRAPPE = new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranJeu/PETITE_FRAPPE.png");
	private static Icon ICON_HOMME_MAIN = new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranJeu/HOMME_MAIN.png");
	private static Icon ICON_CAID = new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranJeu/CAID.png");
	private static Icon ICON_BASTONNEUR = new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranJeu/BASTONNEUR.png");
	private static Icon ICON_CONSTRUCTEUR = new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranJeu/CONSTRUCTEUR.png");
	/*
	 * Il s'agit ici des icons qui sont affichée sur le plateau (elle sont plus petite et sont carrée)
	 */
	private static Icon ICON_PETITE_FRAPPE_MIN = new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranJeu/PETITE_FRAPPE_MIN.png");
	private static Icon ICON_HOMME_MAIN_MIN = new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranJeu/HOMME_MAIN_MIN.png");
	private static Icon ICON_CAID_MIN = new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranJeu/CAID_MIN.png");
	private static Icon ICON_BASTONNEUR_MIN = new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranJeu/BASTONNEUR_MIN.png");
	private static Icon ICON_CONSTRUCTEUR_MIN = new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranJeu/CONSTRUCTEUR_MIN.png");
	
	public Icon getIcon(){
		Icon icon = null;
		switch(this){
			case PETITE_FRAPPE:
				icon = ICON_PETITE_FRAPPE;
				break;
			
			case HOMME_MAIN:
				icon = ICON_HOMME_MAIN;
				break;
			
			case CAID:
				icon = ICON_CAID;
				break;
			
			case BASTONNEUR:
				icon = ICON_BASTONNEUR;
				break;
			
			case CONSTRUCTEUR:
				icon = ICON_CONSTRUCTEUR;
				break;
		}
		return icon;
	}
	
	public Icon getIconMin(){
		Icon icon = null;
		switch(this){
			case PETITE_FRAPPE:
				icon = ICON_PETITE_FRAPPE_MIN;
				break;
			
			case HOMME_MAIN:
				icon = ICON_HOMME_MAIN_MIN;
				break;
			
			case CAID:
				icon = ICON_CAID_MIN;
				break;
			
			case BASTONNEUR:
				icon = ICON_BASTONNEUR_MIN;
				break;
			
			case CONSTRUCTEUR:
				icon = ICON_CONSTRUCTEUR_MIN;
				break;
		}
		return icon;
	}
	
	
	public int getNiveauBase(){
		return 0;
	}
	
	public int getPointDeVie(int niveau){
		int ptsVie = 0;
		
		switch(this){
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
	
	
	public int getPointAttaque(int niveau){
		int ptsAttaque = 0;
		
		switch(this){
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
	
	public int getSalaire(int niveau){
		int salaire = 0;
		
		switch(this){
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
	
	public int getVitesse(int niveau){
		int vitesse = 0;
		
		switch(this){
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
	
	public int getMontantLevelUp(int niveau){
		int montant = 0;
		
		switch(this){
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
	
	
	public int getPrix(int niveau){
		int prix = -1;
		
		switch(this){
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
