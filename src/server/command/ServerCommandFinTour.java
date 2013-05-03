package server.command;

import java.util.ArrayList;

import server.ClientListener;
import server.Server;

import common.Commande;
import common.Joueur;
import common.Partie;

/**
 * @author omar
 */
public class ServerCommandFinTour extends ServerCommand{
	
	@Override
	public void execute(ClientListener _client){
		Server server = _client.getServer();
		Joueur joueur = server.getJoueur(_client.getSocket());
		
		// recuperation de l'ancienne partie sur laquelle se trouve les sockets vers les joueurs
		// les sockets ont en effet été supprimée lors de la serialization
		Partie partie = server.getPartieWhereJoueur(joueur);
		// recuperation de la partie envoyé par le client
		Partie newPartie = (Partie)arguments[0];
		
		// on passe au tour suivant
		partie.passerTour();
		newPartie.passerTour();
		
		// notification de tous les joueurs
		ArrayList<Joueur> joueurs = newPartie.getListeParticipants();
		for (int i = 0 ; i < joueurs.size() ; i++){
			Joueur joueurCourant = joueurs.get(i);
			
			boolean isSonTour = false;
			if (joueurCourant.equals(newPartie.getJoueurCourant())){
				isSonTour = true;
			}
			
			Object[] args = { Commande.UPDATE_TOUR,newPartie,joueurCourant,isSonTour };
			// recuperation de la socket a partir de l'ancienne partie
			ClientListener clientListener = partie.getListeParticipants().get(i).getClientListener();
			clientListener.send(args);
		}
		// TODO: Mettre a jour la partie présente sur le serveur avec les nouvelles informations sur les joueurs
		/**/
	}
}
