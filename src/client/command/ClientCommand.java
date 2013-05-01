package client.command;

import client.ServerListener;


/*
 * interface representant les commandes qui sont 
 * créé puis envoyé par le serveur
 * et executé sur le client
 */
public abstract class ClientCommand{
	protected Object[] arguments = new String[0];
	
	public abstract void execute(ServerListener _server);
	
	public Object[] getArguments(){
		return arguments;
	}
	
	public void setArguments(Object[] arguments){
		this.arguments = arguments;
	}
	
}
