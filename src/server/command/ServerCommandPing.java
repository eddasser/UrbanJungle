package server.command;

import server.ClientListener;
import server.DBConnexion;

import common.Commande;


/**
 * @author omar
 */
public class ServerCommandPing extends ServerCommand{
	
	@Override
	public void execute(ClientListener _client){
		// le client envoi un ping pour verifier si le serveur est disponible
		// on lui renvoi une chaine qui aparaitra en mode debug coté client
		Object[] args = { Commande.PING,DBConnexion.isDataBaseAccessible() };
		_client.send(args);
	}
	
}
