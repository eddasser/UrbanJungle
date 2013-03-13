package common.command.client;

import client.JeuPanel;

import common.command.ClientCommand;

/**
 * @author omar
 */
public class ClientCommandPing extends ClientCommand{
	private static final long serialVersionUID = 1L;
	private boolean databaseConnected;
	
	public ClientCommandPing(boolean bdd_connected){
		databaseConnected = bdd_connected;
	}
	
	@Override
	public void execute(JeuPanel _jeu){
		// reponse du serveur a la tentative de ping contenant le port attribuée au client par le serveur
		_jeu.setAccesServeur(databaseConnected);
		_jeu.chargerEcranResultatTentativeConnection();
	}
	
}
