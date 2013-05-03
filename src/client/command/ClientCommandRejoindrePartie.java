package client.command;

import client.JeuPanel;
import client.ServerListener;

import common.Translator;

/**
 * @author omar
 */
public class ClientCommandRejoindrePartie extends ClientCommand{
	
	@Override
	public void execute(ServerListener _server){
		JeuPanel jeu = _server.getJeu();
		
		boolean rejoint = (boolean)arguments[0];
		String message_erreur = (String)arguments[1];
		
		System.out.println("Rejoindre partie : " + rejoint + " ( " + message_erreur + ")"); 
		
		if (!rejoint){
			jeu.notificationJoueur(Translator.translate("rejoindrePartieImpossible"));
		}else{
			// jeu.notificationJoueur(Translator.translate("rejoindrePartieReussie"));
			jeu.chargerEcranAttenteJoueur();
		}
	}
	
}
