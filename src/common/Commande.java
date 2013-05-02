package common;

import java.io.Serializable;


/**
 * @author omar
 */
public enum Commande implements Serializable{
	PING, // pour la verification de la prensence du server
	CREATION_COMPTE, // pr la demande de création d'un compte
	DEMANDE_CONNEXION, // pr la demande de deconnexion
	LISTE_PARTIES, // pr la récuperation de la liste des parties
	REJOINDRE_PARTIE, // pr la demnade pr rejoindre une partie sur le server
	REPRENDRE_PARTIE, // pr la demande de reprise d'une partie préalablement sauvegardée
	ERROR, // commande par défaut d'erreur
	CREATION_PARTIE, // pr la demande de création d'une partie
	DEBUT_JEU, // lorsque le serveur notifie tous les joueurs que la partie peut commencer
	DECONNEXION, // utilisé lorsqu'un joueur prévient le server qu'il se deconnect
	FIN_TOUR, // utilisé par les clients pr indiquer qu'ils ont finit leur tour
	UPDATE_TOUR, // utilisé par le server pr envoyer aux joueurs la nouvelle partie
	DECONNEXION_UN_JOUEUR, // envoyé par le serveur au client pr les notifiers du départ d'un joueur
	
	// envoyé par le serveur au joueur admin de la partie
	PROPOSER_SAUVEGARDE_PARTIE, PROPOSER_SAUVEGARDE_OU_CONTINUER_PARTIE, MISE_EN_PAUSE, SORTIE_PAUSE, NOTIFIER_JOUEUR_PARTIE_SAUVEGARDEE
}
