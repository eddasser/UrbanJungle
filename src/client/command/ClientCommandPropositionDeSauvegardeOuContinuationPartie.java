package client.command;

import java.util.ArrayList;

import client.JeuPanel;
import client.ServerListener;

import common.Commande;
import common.Joueur;
import common.Partie;
import common.Translator;

/**
 * @author omar
 */
public class ClientCommandPropositionDeSauvegardeOuContinuationPartie extends ClientCommand{
	
	@Override
	public void execute(ServerListener _server){
		JeuPanel jeu = _server.getJeu();
		
		Partie partie = jeu.getClient().getPartie();
		Joueur joueur = (Joueur)arguments[0];// joueur qui s'est deconnecté
		
		ArrayList<String> choixPossible = new ArrayList<String>();
		choixPossible.add(Translator.translate("ContinuerLaPartie"));
		choixPossible.add(Translator.translate("SauvegaderLaPartie"));
		int choix = jeu.getChoixSaisie(choixPossible,
				Translator.translate("Le joueur " + joueur.getLogin() + " s'est deconnecter. Que souhaitez-vous faire ?"));
		
		boolean continuer = true;
		if (choix == 1){
			continuer = false;
		}
		
		// on envoi la reponse au serveur
		Object[] args = { Commande.PROPOSER_SAUVEGARDE_OU_CONTINUER_PARTIE,continuer,partie };
		_server.sendCommand(args);
		
		if (!continuer){
			_server.deconnexion();
			JeuPanel.getEcranJeu().cacherPlateau();
			jeu.chargerEcranChoixTypePartie();
			jeu.getClient().update();
		}
		
	}
	
	
}
