package client.command;

import client.ServerListener;

/**
 * @author omar
 */
public class ClientCommandRejoindrePartie extends ClientCommand{
	
	@Override
	public void execute(ServerListener _server){
		boolean rejoint = (boolean)arguments[0];
		String message_erreur = (String)arguments[1];
		
		System.out.println("Rejoindre partie : " + rejoint + " ( " + message_erreur + ")");
	}
	
}
