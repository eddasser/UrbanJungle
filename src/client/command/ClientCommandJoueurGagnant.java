package client.command;

import client.JeuPanel;
import client.ServerListener;
import client.view.jeu.EcranJeu;

import common.Joueur;
import common.Partie;

/**
 * @author omar
 */
public class ClientCommandJoueurGagnant extends ClientCommand{
	
	@Override
	public void execute(ServerListener _server){
		// recuperer la partie (position des QG, ...) et de
		Partie partie = (Partie)arguments[0];
		Joueur joueur = (Joueur)arguments[1];
		
		JeuPanel jeu = _server.getJeu();
		jeu.getClient().setPartie(partie);
		jeu.getClient().setJoueur(joueur);
		jeu.getClient().update();
		
		EcranJeu ej = JeuPanel.getEcranJeu();
		ej.getEcranFinPartie().setTextPartieGagne();
		ej.afficherEcranFinPartie();
		jeu.getClient().update();
		ej.update();
	}
	
}
