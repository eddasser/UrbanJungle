package common.command.server;

import server.ClientListener;
import server.DBConnexion;

import common.command.ClientCommand;
import common.command.ServerCommand;
import common.command.client.ClientCommandPing;


/**
 * @author omar
 */
public class ServerCommandPing extends ServerCommand{
	private static final long serialVersionUID = 1L;
	
	@Override
	public void execute(ClientListener _client){
		// le client envoi un ping pour verifier si le serveur est disponible
		// on lui renvoi une chaine qui aparaitra en mode debug coté client
		ClientCommand com = new ClientCommandPing(DBConnexion.isDataBaseAccessible());
		_client.send(com);
	}
}
