package client.command;

import client.ServerListener;


/**
 * @author omar
 */
public class ClientCommandError extends ClientCommand{
	
	@Override
	public void execute(ServerListener _server){
		System.out.println("COMMAND CLIENT INCONNU. Arguments recu : ");
		for (int i = 0 ; i < arguments.length ; i++){
			System.out.println(arguments[i]);
		}
	}
}
