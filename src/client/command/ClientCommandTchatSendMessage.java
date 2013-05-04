package client.command;

import client.JeuPanel;
import client.ServerListener;

import common.Joueur;

/**
 * @author omar
 */
public class ClientCommandTchatSendMessage extends ClientCommand{
	
	@Override
	public void execute(ServerListener _server){
		Joueur joueur = (Joueur)arguments[0];// emetteur du message
		String message = (String)arguments[1];
		
		JeuPanel jeu = _server.getJeu();
		jeu.getTchatFrame().newMessage(joueur,message);
		
	}
	
}
