package client.command;

import client.JeuPanel;
import client.ServerListener;

import common.Joueur;
import common.Partie;
import common.Translator;

/**
 * @author omar
 */
public class ClientCommandSortiePause extends ClientCommand{
	
	@Override
	public void execute(ServerListener _server){
		JeuPanel jeu = _server.getJeu();
		jeu.notificationJoueur(Translator.translate("LaPartieContinueSansLeJoueur"));
		
		Partie partie = (Partie)arguments[0];
		Joueur joueur = (Joueur)arguments[1];
		
		jeu.getClient().setPartie(partie);
		jeu.getClient().setJoueur(joueur);
		
		jeu.chargerEcranJeu();
		
		JeuPanel.getEcranJeu().cacherEcranAttenteDecisionAdmin();
		
		if (joueur.equals(partie.getJoueurCourant())){
			// s'il s'agit du joueur courant on lui affiche le plateu
			JeuPanel.getEcranJeu().cacherEcranAttente();
		}else{
			JeuPanel.getEcranJeu().afficherEcranAttente();
		}
		
		jeu.getClient().update();
		JeuPanel.getEcranJeu().update();
	}
	
	
}
