package client.command;

import client.JeuPanel;
import client.ServerListener;
import client.view.jeu.EcranJeu;

import common.Joueur;
import common.Partie;

/**
 * @author omar
 */
public class ClientCommandJoueurPerdu extends ClientCommand{
	
	@Override
	public void execute(ServerListener _server){
		// recuperer la partie (position des QG, ...) et de
		Partie partie = (Partie)arguments[0];
		Joueur joueur = (Joueur)arguments[1];
		boolean isMoiQuiAPerdu = (boolean)arguments[2];
		
		JeuPanel jeu = _server.getJeu();
		jeu.getClient().setPartie(partie);
		jeu.getClient().setJoueur(joueur);
		jeu.getClient().update();
		
		EcranJeu ej = JeuPanel.getEcranJeu();
		if (isMoiQuiAPerdu){
			ej.getEcranFinPartie().setTextPartiePerdu();
			ej.afficherEcranFinPartie();
		}else if (partie.getNbJoueurActuellement() == 1){
			// le joueur a gagne
			ej.getEcranFinPartie().setTextPartieGagne();
			ej.afficherEcranFinPartie();
		}
		jeu.getClient().update();
		ej.update();
		
		jeu.getTchatFrame().dispose();
	}
	
}
