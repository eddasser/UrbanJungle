package server.command;

import java.util.ArrayList;

import server.ClientListener;
import server.Server;

import common.Etat;
import common.Joueur;
import common.Partie;

/**
 * @author omar
 */
public class ServerCommandPropositionDeSauvegardePartie extends ServerCommand{
	
	@Override
	public void execute(ClientListener _client){
		Server server = _client.getServer();
		Joueur joueur = server.getJoueur(_client.getSocket());
		
		// recuperation de la partie sur laquelle se trouve les joueurs
		Partie partie = server.getPartieWhereJoueur(joueur);
		
		boolean sauvegarder = (boolean)arguments[0];
		
		// d'abord on suppression de tous les joueurs de la liste du serveur
		ArrayList<Joueur> joueurs = partie.getListeParticipants();
		for (int i = 0 ; i < joueurs.size() ; i++){
			server.remove(joueurs.get(i));
			joueurs.get(i).getClientListener().deconnexion();
		}
		// puis on suppression de la partie en elle-meme
		server.remove(partie);
		
		if (sauvegarder){
			// on recupere la partie a sauvegarde (tel que l'admin l'a connait)
			Partie partieASauvegarder = (Partie)arguments[1];
			
			// on met a jour le nombre de joueur requis pr la partie
			partieASauvegarder.setNbJoueurRequis(partieASauvegarder.getNbJoueurActuellement());
			
			// et changement d'etat de la partie
			partieASauvegarder.setEtatDeLaPartie(Etat.SAUVEGARDEE);
			
			// puis on l'ajoute a la liste du server
			server.add(partieASauvegarder);
		}
		/**/
	}
}
