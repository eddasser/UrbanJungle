package client.command;

import java.util.ArrayList;

import client.JeuPanel;
import client.ServerListener;

import common.Commande;
import common.Partie;
import common.Translator;

/**
 * @author omar
 */
public class ClientCommandPropositionDeSauvegardePartie extends ClientCommand{
	
	@Override
	public void execute(ServerListener _server){
		JeuPanel jeu = _server.getJeu();
		
		// Joueur joueur = (Joueur)arguments[0];// joueur qui s'est deconnecté
		// Partie partie = jeu.getClient().getPartie();
		// partie.getListeParticipants().remove(joueur);// on supprime le joueur de la partie
		
		ArrayList<String> choixPossible = new ArrayList<String>();
		choixPossible.add(Translator.translate("oui"));
		choixPossible.add(Translator.translate("non"));
		int choix = jeu.getChoixSaisie(choixPossible,
				Translator.translate("Vous êtes tout seul sur la partie. Souhaitez-vous sauvegarder la partie avant de quitter ?"));
		
		boolean sauvegarder = false;
		
		Partie partie = null;
		if (choix == 0){
			// s'il choisi de sauvegarder la partie, on envoi celle-ci pour qu'il la sauvegarde
			sauvegarder = true;
			partie = jeu.getClient().getPartie();
		}
		// on envoi la reponse au serveur
		Object[] args = { Commande.PROPOSER_SAUVEGARDE_PARTIE,sauvegarder,partie };
		_server.sendCommand(args);
		_server.deconnexion();
		
		JeuPanel.getEcranJeu().cacherPlateau();
		jeu.chargerEcranChoixTypePartie();
		jeu.getClient().update();
	}
	
}
