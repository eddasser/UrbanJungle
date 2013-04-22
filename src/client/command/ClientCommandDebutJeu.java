package client.command;

import client.JeuPanel;
import client.ServerListener;
import client.view.jeu.EcranJeu;

/**
 * @author omar
 */
public class ClientCommandDebutJeu extends ClientCommand{
	
	@Override
	public void execute(ServerListener _server){
		// recuperer la position du QG
		
		JeuPanel jeu = _server.getJeu();
		jeu.chargerEcranJeu();
		
		EcranJeu ej = JeuPanel.getEcranJeu();
		ej.afficherEcranAttente();
	}
	
}
