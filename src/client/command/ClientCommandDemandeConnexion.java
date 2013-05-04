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
		boolean loginEtPasswordCorrect = (boolean)arguments[0];
		boolean isDejaConnected = (boolean)arguments[1];
		
		JeuPanel jeu = _server.getJeu();
		// reponse du serveur a la tentative de connection du client
		if (!loginEtPasswordCorrect){
			jeu.notificationJoueur(Translator.translate("identifiantsIncorrects"));
		}else if (isDejaConnected){
			jeu.notificationJoueur(Translator.translate("utilisateurDejaConnecte"));
		}else{
			jeu.chargerEcranMenuMultijoueur();
		}
	}
	
}
