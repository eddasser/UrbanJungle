package client.command;

import client.JeuPanel;
import client.ServerListener;

/**
 * @author omar
 */
public class ClientCommandDebutJeu extends ClientCommand{
	
	@Override
	public void execute(ServerListener _server){
		// recuperer la position du QG
		
		JeuPanel jeu = _server.getJeu();
		jeu.chargerEcranJeu();
	}
	
}
