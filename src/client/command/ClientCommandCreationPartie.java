package client.command;

import client.JeuPanel;
import client.ServerListener;

import common.Translator;

/**
 * @author omar
 */
public class ClientCommandCreationPartie extends ClientCommand{
	
	@Override
	public void execute(ServerListener _server){
		boolean creationAccepted = new Boolean(arguments[0]);
		
		JeuPanel jeu = _server.getJeu();
		// reponse du serveur a la tentative de creation de partie ( 1 pour ok, 0 pour refusé )
		if (!creationAccepted){
			jeu.notificationJoueur(Translator.translate("creationPartieImpossible"));
		}else{
			jeu.notificationJoueur(Translator.translate("creationPartieReussie"));
			jeu.chargerEcranAttenteJoueur();
			jeu.chargerEcranMenuMultijoueur();
		}
	}
	
	
}
