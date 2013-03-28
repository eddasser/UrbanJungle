package server.command;

import server.ClientListener;


/*
 * interface representant les commandes qui sont 
 * créé puis envoyé par le client
 * et executé sur le server
 */
public abstract class ServerCommand{
	protected String[] arguments = new String[0];
	
	public abstract void execute(ClientListener _client);
	
	public String[] getArguments(){
		return arguments;
	}
	
	public void setArguments(String[] arguments){
		this.arguments = arguments;
	}
}
