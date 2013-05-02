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
		
		/*
		// recuperation de l'ancienne partie sur laquelle se trouve les sockets vers les joueurs
		// les sockets ont en effet été supprimée lors de la serialization
		Partie partie = server.getPartieWhereJoueur(joueur);
		// recuperation de la partie envoyé par le client
		Partie newPartie = (Partie)arguments[0];
		
		// on passe au tour suivant
		partie.passerTour();
		// partie.setPlateau(newPartie.getPlateau());
		
		// notification de tous les joueurs
		ArrayList<Joueur> joueurs = partie.getListeParticipants();
		for (int i = 0 ; i < joueurs.size() ; i++){
			Joueur joueurCourant = joueurs.get(i);
			
			// maj des elements (unites, batiments, niveaux et argent)
			Joueur newJoueurCourant = newPartie.getListeParticipants().get(i);
			joueurCourant.initialiserJoueur(newJoueurCourant);
			System.out.println(joueurCourant.getLogin() + " : " + newJoueurCourant.getLogin());
			
			// on verifie s'il s'agit ou non de son tour de jouer
			boolean isSonTour = false;
			if (joueurCourant.equals(partie.getJoueurCourant())){
				isSonTour = true;
				System.out.println("C'EST AU TOUR DE : " + joueurCourant.getLogin());
			}
			
			// on notifie le joueur
			Object[] args = { Commande.UPDATE_TOUR,partie,joueurCourant,isSonTour };
			joueurCourant.send(args);
		}
		// TODO: Mettre a jour la partie présente sur le serveur avec les nouvelles informations sur les joueurs
		
		/**/
		
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
