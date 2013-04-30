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
		Partie partie = server.getPartieWhereJoueur(joueur);// recuperation de l'ancienne partie
		
		partie.passerTour();// on increment le joueur suivant
		
		Partie newPartie = (Partie)arguments[0];// recuperation de la partie envoyé par le client
		newPartie.passerTour();
		// MAJ des unites, des batiments, des niveaux du joueur
		
		/*
		 * notification de tous les joueurs
		 */
		ArrayList<Joueur> joueurs = partie.getListeParticipants();
		for (int i = 0 ; i < joueurs.size() ; i++){
			boolean isSonTour = false;
			if (joueurs.get(i).equals(partie.getJoueurCourant())){
				isSonTour = true;
			}
			Object[] args = { Commande.UPDATE_TOUR,newPartie,newPartie.getListeParticipants().get(i),isSonTour };
			joueurs.get(i).send(args);
		}
	}
	
}
