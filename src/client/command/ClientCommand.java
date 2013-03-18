package client.command;

import client.ServerListener;


/*
 * interface representant les commandes qui sont 
 * créé puis envoyé par le serveur
 * et executé sur le client
 */
public abstract class ClientCommand{
	protected String[] arguments;
	
	public abstract void execute(ServerListener _server);
	
	public String[] getArguments(){
		return arguments;
	}
	
	public void setArguments(String[] arguments){
		this.arguments = arguments;
	}
	
}
