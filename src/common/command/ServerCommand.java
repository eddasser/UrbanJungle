package common.command;

import java.io.Serializable;

import server.ClientListener;

/*
 * interface representant les commandes qui sont 
 * créé puis envoyé par le client
 * et executé sur le server
 */
public abstract class ServerCommand implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public abstract void execute(ClientListener _client);
}
