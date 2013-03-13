package common.command.server;

import server.ClientListener;

import common.command.ServerCommand;

/**
 * @author omar
 */
public class ServerCommandListePartie extends ServerCommand{
	private static final long serialVersionUID = 1L;
	
	@Override
	public void execute(ClientListener _client){
		// le client demande au serveur a recuperer la liste des parties crées
		
		// _client.send(Server.getParties());// on envoie la liste des parties
	}
	
}
