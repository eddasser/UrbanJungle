package common;

import java.io.Serializable;

/**
 * @author omar different etats possible pour une partie
 */
public enum Etat implements Serializable{
	SAUVEGARDEE, EN_ATTENTE_JOUEUR, COMMENCEE;
	
	public static Etat get(String _etat){
		for (Etat etat : Etat.values()){
			if (etat.toString().equals(_etat)){
				return etat;
			}
		}
		return EN_ATTENTE_JOUEUR;
	}
}
