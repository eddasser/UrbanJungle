package client.command;

import java.util.ArrayList;

import client.JeuPanel;
import client.ServerListener;

import common.Commande;
import common.Translator;

/**
 * @author omar
 */
public class ClientCommandPropositionDeSauvegardePartie extends ClientCommand{
	
	@Override
	public void execute(ServerListener _server){
		JeuPanel jeu = _server.getJeu();
		
		ArrayList<String> choixPossible = new ArrayList<String>();
		choixPossible.add(Translator.translate("oui"));
		choixPossible.add(Translator.translate("non"));
		int choix = jeu.getChoixSaisie(choixPossible,
				Translator.translate("Vous êtes tout seul sur la partie. Souhaitez-vous sauvegarder la partie avant de quitter ?"));
		
		boolean sauvegarder = false;
		if (choix == 0){
			sauvegarder = true;
		}
		// on envoi la reponse au serveur
		Object[] args = { Commande.PROPOSER_SAUVEGARDE_PARTIE,sauvegarder };
		_server.sendCommand(args);
		_server.deconnexion();
		
		JeuPanel.getEcranJeu().cacherPlateau();
		jeu.chargerEcranChoixTypePartie();
		jeu.getClient().update();
	}
	
}
