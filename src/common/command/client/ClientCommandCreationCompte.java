package common.command.client;

import client.JeuPanel;

import common.Translator;
import common.command.ClientCommand;

/**
 * @author omar
 */
public class ClientCommandCreationCompte extends ClientCommand{
	private static final long serialVersionUID = 1L;
	private boolean creationAccepted;
	
	public ClientCommandCreationCompte(boolean creationAccepted){
		this.creationAccepted = creationAccepted;
	}
	
	@Override
	public void execute(JeuPanel _jeu){
		// reponse du serveur a la tentative de creation du client ( 1 pour ok, 0 pour refus� )
		if (!creationAccepted){
			_jeu.notificationJoueur(Translator.translate("loginDejaExistant"));
		}else{
			_jeu.notificationJoueur("compteCree");
			_jeu.chargerEcranMenuMultijoueur();
		}
	}
	
}
