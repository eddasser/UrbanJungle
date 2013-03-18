package client.command;

import client.JeuPanel;
import client.ServerListener;

import common.Translator;

/**
 * @author omar
 */
public class ClientCommandDemandeConnexion extends ClientCommand{
	
	@Override
	public void execute(ServerListener _server){
		boolean connexionAccepted = new Boolean(arguments[0]);
		
		JeuPanel jeu = _server.getJeu();
		// reponse du serveur a la tentative de connection du client
		if (!connexionAccepted){
			jeu.notificationJoueur(Translator.translate("identifiantsIncorrects"));
		}else{
			jeu.chargerEcranMenuMultijoueur();
		}
	}
	
}
