package server.command;

import server.ClientListener;


/*
 * interface representant les commandes qui sont 
 * créé puis envoyé par le client
 * et executé sur le server
 */
public abstract class ServerCommand{
	protected Object[] arguments = new Object[0];
	
	public abstract void execute(ClientListener _client);
	
	public Object[] getArguments(){
		return arguments;
	}
	
	public void setArguments(Object[] arguments){
		this.arguments = arguments;
	}
}
