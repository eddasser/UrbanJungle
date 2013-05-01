package client.command;

import client.JeuPanel;
import client.ServerListener;

import common.Translator;

/**
 * @author omar
 */
public class ClientCommandCreationCompte extends ClientCommand{
	
	@Override
	public void execute(ServerListener _server){
		boolean creationAccepted = (boolean)arguments[0];
		
		JeuPanel jeu = _server.getJeu();
		// reponse du serveur a la tentative de creation du client ( 1 pour ok, 0 pour refusé )
		if (!creationAccepted){
			jeu.notificationJoueur(Translator.translate("loginDejaExistant"));
		}else{
			jeu.notificationJoueur("compteCree");
			jeu.chargerEcranMenuMultijoueur();
		}
	}
	
	
}
