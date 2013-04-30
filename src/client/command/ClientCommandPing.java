package client.command;

import client.JeuPanel;
import client.ServerListener;


/**
 * @author omar
 */
public class ClientCommandPing extends ClientCommand{
	
	@Override
	public void execute(ServerListener _server){
		boolean databaseConnected = (boolean)arguments[0];
		
		JeuPanel jeu = _server.getJeu();
		// reponse du serveur a la tentative de ping contenant le port attribuée au client par le serveur
		jeu.setAccesServeur(databaseConnected);
		jeu.chargerEcranResultatTentativeConnection();
	}
}
