package server.command;

import server.ClientListener;


/**
 * @author omar
 */
public class ServerCommandListePartie extends ServerCommand{
	
	@Override
	public void execute(ClientListener _client){
		// le client demande au serveur a recuperer la liste des parties crées
		
		// _client.send(Server.getParties());// on envoie la liste des parties
	}
	
}
