package client.command;

import client.JeuPanel;
import client.ServerListener;

/**
 * @author omar
 */
public class ClientCommandMiseEnPausePartie extends ClientCommand{
	
	@Override
	public void execute(ServerListener _server){
		JeuPanel jeu = _server.getJeu();
		
		// TODO: implementer la mise en pause (en attendant le choix de l'admin)
	}
	
	
}
