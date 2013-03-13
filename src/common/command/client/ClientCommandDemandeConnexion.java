package common.command.client;

import client.JeuPanel;

import common.Translator;
import common.command.ClientCommand;

/**
 * @author omar
 */
public class ClientCommandDemandeConnexion extends ClientCommand{
	private static final long serialVersionUID = 1L;
	private boolean connexionAccepted;
	
	public ClientCommandDemandeConnexion(boolean connexionAccepted){
		this.connexionAccepted = connexionAccepted;
	}
	
	@Override
	public void execute(JeuPanel _jeu){
		// reponse du serveur a la tentative de connection du client
		if (!connexionAccepted){
			_jeu.notificationJoueur(Translator.translate("identifiantsIncorrects"));
		}else{
			_jeu.chargerEcranMenuMultijoueur();
		}
	}
	
}
