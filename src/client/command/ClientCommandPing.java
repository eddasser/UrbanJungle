package client.command;

import client.JeuPanel;
import client.ServerListener;


/**
 * @author omar
 */
public class ClientCommandPing extends ClientCommand{
	
	@Override
	public void execute(ServerListener _server){
		// boolean databaseConnected = (boolean)arguments[0];
		// System.out.println(databaseConnected);
		JeuPanel jeu = _server.getJeu();
		jeu.chargerEcranResultatTentativeConnection();
	}
}
