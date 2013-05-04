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
public class ServerCommandJoueurPerdu extends ServerCommand{
	
	@Override
	public void execute(ClientListener _client){
		Server server = _client.getServer();
		Joueur joueur = server.getJoueur(_client.getSocket());
		
		// recuperation de l'ancienne partie sur laquelle se trouve les sockets vers les joueurs
		// les sockets ont en effet été supprimée lors de la serialization
		Partie partie = server.getPartieWhereJoueur(joueur);
		// recuperation de la partie envoyé par le client
		Partie newPartie = (Partie)arguments[0];
		// on recupere le joueur ayant perdu son QG
		Joueur joueurPerdant = (Joueur)arguments[1];
		
		// suppression du joueur de la partie
		int ind = newPartie.getListeParticipants().indexOf(joueurPerdant);
		newPartie.getListeParticipants().remove(ind);
		Object[] args = { Commande.JOUEUR_PERDU,newPartie,joueurPerdant,true };
		joueurPerdant = partie.getListeParticipants().remove(ind);
		joueurPerdant.send(args);
		server.remove(joueurPerdant);
		joueurPerdant.getClientListener().deconnexion();
		
		// notification de tous les joueurs
		ArrayList<Joueur> joueurs = newPartie.getListeParticipants();
		for (int i = 0 ; i < joueurs.size() ; i++){
			Joueur joueurCourant = joueurs.get(i);
			
			args[0] = Commande.JOUEUR_PERDU;
			args[1] = newPartie;
			args[2] = joueurCourant;
			args[3] = false;
			
			ClientListener clientListener = partie.getListeParticipants().get(i).getClientListener();
			clientListener.send(args);
		}
		
		if (joueurs.size() == 1){
			// il ne reste plus qu'un seule joueur, il gagne donc la partie
			Joueur joue = partie.getListeParticipants().get(0);
			args[0] = Commande.JOUEUR_GAGNANT;
			
			joue.send(args);
			server.remove(joue);
			joue.getClientListener().deconnexion();
			
			server.remove(partie);
		}
		
	}
}
