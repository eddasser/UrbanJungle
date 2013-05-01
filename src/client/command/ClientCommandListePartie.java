package client.command;

import client.ServerListener;
import client.view.EcranMenuMultijoueur;


/**
 * @author omar
 */
public class ClientCommandListePartie extends ClientCommand{
	
	@Override
	public void execute(ServerListener _server){
		EcranMenuMultijoueur ecranMenuMultijoueur = _server.getJeu().getEcranMenuMultijoueur();
		
		String[] listeParties = new String[arguments.length];
		System.arraycopy(arguments,0,listeParties,0,arguments.length);
		
		ecranMenuMultijoueur.setParties(listeParties);
	}
}
