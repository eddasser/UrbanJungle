package client.command;

import client.ServerListener;

import common.Commande;

/**
 * @author omar
 */
public class ClientCommandDeconnexionJoueur extends ClientCommand{
	
	@Override
	public void execute(ServerListener _server){
		// JeuPanel jeu = _server.getJeu();
		
		
		ClientCommand commande = ClientCommandFactory.getCommand((Commande)arguments[1]);
		commande.setArguments(arguments);
		commande.execute(_server);
	}
	
}
