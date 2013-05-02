package client.command;

import client.JeuPanel;
import client.ServerListener;

import common.Translator;

/**
 * @author omar
 */
public class ClientCommandSortiePause extends ClientCommand{
	
	@Override
	public void execute(ServerListener _server){
		JeuPanel jeu = _server.getJeu();
		jeu.notificationJoueur(Translator.translate("LaPartieContinueSansLeJoueur"));
		// TODO: implementer la mise en pause (en attendant le choix de l'admin)
	}
	
	
}
