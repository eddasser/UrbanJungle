package common.partie.batiment;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import common.Translator;
import common.TypeElementPlateau;

/**
 * @author omar
 */
public enum TypeBatiment implements TypeElementPlateau{
	QG, GARAGE, VENTE_DROGUE, VENTE_ALCOOL, VENTE_ARME, BOOKMAKER;
	
	/*
	 * Il s'agit des icons qui sont affichée lorsque l'on clic sur le menu "batiment", les icones sont rectangulaire et plus grande
	 */
	private static Icon ICON_QG = new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranJeu/QG.png");
	private static Icon ICON_GARAGE = new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranJeu/GARAGE.png");
	private static Icon ICON_VENTE_DROGUE = new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranJeu/VENTE_DROGUE.png");
	private static Icon ICON_VENTE_ALCOOL = new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranJeu/VENTE_ALCOOL.png");
	private static Icon ICON_VENTE_ARME = new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranJeu/VENTE_ARME.png");
	private static Icon ICON_BOOKMAKER = new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranJeu/BOOKMAKER.png");
	
	/*
	 * Il s'agit ici des icons qui sont affichée sur le plateau (elle sont plus petite et sont carrée)
	 */
	private static Icon ICON_QG_MIN = new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranJeu/QG_MIN.png");
	private static Icon ICON_GARAGE_MIN = new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranJeu/GARAGE_MIN.png");
	private static Icon ICON_VENTE_DROGUE_MIN = new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranJeu/VENTE_DROGUE_MIN.png");
	private static Icon ICON_VENTE_ALCOOL_MIN = new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranJeu/VENTE_ALCOOL_MIN.png");
	private static Icon ICON_VENTE_ARME_MIN = new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranJeu/VENTE_ARME_MIN.png");
	private static Icon ICON_BOOKMAKER_MIN = new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranJeu/BOOKMAKER_MIN.png");
	
	
	public Icon getIcon(){
		Icon icon = null;
		switch(this){
			case QG:
				icon = ICON_QG;
				break;
			
			case VENTE_DROGUE:
				icon = ICON_VENTE_DROGUE;
				break;
			
			case VENTE_ALCOOL:
				icon = ICON_VENTE_ALCOOL;
				break;
			
			case BOOKMAKER:
				icon = ICON_BOOKMAKER;
				break;
			
			case VENTE_ARME:
				icon = ICON_VENTE_ARME;
				break;
			
			case GARAGE:
				icon = ICON_GARAGE;
				break;
		}
		return icon;
	}
	
	
	public Icon getIconMin(){
		Icon icon = null;
		switch(this){
			case QG:
				icon = ICON_QG_MIN;
				break;
			
			case VENTE_DROGUE:
				icon = ICON_VENTE_DROGUE_MIN;
				break;
			
			case VENTE_ALCOOL:
				icon = ICON_VENTE_ALCOOL_MIN;
				break;
			
			case BOOKMAKER:
				icon = ICON_BOOKMAKER_MIN;
				break;
			
			case VENTE_ARME:
				icon = ICON_VENTE_ARME_MIN;
				break;
			
			case GARAGE:
				icon = ICON_GARAGE_MIN;
				break;
		}
		return icon;
	}
	
	public int getNiveauBase(){
		return 0;
	}
	
	public int getRevenu(int niveau){
		int revenu = 0;
		
		switch(this){
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
	
	
	public int getPointDeVie(int niveau){
		int ptsVie = 0;
		
		switch(this){
			case QG:
				ptsVie = 1000;
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
	
	public int getPrix(int niveau){
		int prix = -1;
		
		switch(this){
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
	
	public int getMontantLevelUp(int niveau){
		int montant = 0;
		
		switch(this){
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
