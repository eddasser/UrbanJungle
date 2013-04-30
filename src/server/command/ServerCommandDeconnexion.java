package server.command;

import server.ClientListener;
import server.Server;

import common.Joueur;

/**
 * @author omar
 */
public class ServerCommandDeconnexion extends ServerCommand{
	
	@Override
	public void execute(ClientListener _client){
		Server server = _client.getServer();
		Joueur joueur = server.getJoueur(_client.getSocket());
		server.remove(joueur);
	}
	
}
