package server.command;

import server.ClientListener;


/**
 * @author omar
 */
public class ServerCommandError extends ServerCommand{
	
	@Override
	public void execute(ClientListener _client){
		System.out.println("COMMAND SERVER INCONNU. Arguments recu : ");
		for (int i = 0 ; i < arguments.length ; i++){
			System.out.println(arguments[i]);
		}
	}
	
}
