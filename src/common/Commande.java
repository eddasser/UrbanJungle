package common;

import java.io.Serializable;


/**
 * @author omar
 */
public enum Commande implements Serializable{
	PING, CREATION_COMPTE, DEMANDE_CONNEXION, LISTE_PARTIES, REJOINDRE_PARTIE, REPRENDRE_PARTIE, ERROR, CREATION_PARTIE, DEBUT_JEU, DECONNEXION;
}
