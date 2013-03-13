package common.command;

import java.io.Serializable;

import client.JeuPanel;


/*
 * interface representant les commandes qui sont 
 * créé puis envoyé par le serveur
 * et executé sur le client
 */
public abstract class ClientCommand implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public abstract void execute(JeuPanel _jeu);
}
