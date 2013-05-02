package server.command;

import java.util.ArrayList;

import server.ClientListener;
import server.Server;

import common.Commande;
import common.Etat;
import common.Joueur;
import common.Partie;

/**
 * @author omar
 */
public class ServerCommandPropositionDeSauvegardeOuContinuationPartie extends ServerCommand{
	
	@Override
	public void execute(ClientListener _client){
		Server server = _client.getServer();
		Joueur joueur = server.getJoueur(_client.getSocket());
		
		// recuperation de la partie sur laquelle se trouve les joueurs
		Partie partie = server.getPartieWhereJoueur(joueur);
		
		boolean continuer = (boolean)arguments[0];
		if (continuer){
			// on supprime seulement le joueur qui s'est deconnecte
			Partie newPartie = (Partie)arguments[1];
			
			// suppression du joueur a la fois sur la partie sur le serveur et sur la partie recu du joueur admin
			for (int i = 0 ; i < partie.getListeParticipants().size() ; i++){
				if (partie.getListeParticipants().get(i).getSocket() == null){
					partie.getListeParticipants().remove(i);
					newPartie.getListeParticipants().remove(i);
				}
			}
			
			// TODO: traiter le cas ou c'est au joueur a qui a la main (a qui c'est le tour) qui se deconnecte
			
			// puis on notifier a tous les joueurs qu'ils peuvent reprendre la partie
			ArrayList<Joueur> joueurs = partie.getListeParticipants();
			for (int i = 0 ; i < joueurs.size() ; i++){
				Joueur joueurCourant = joueurs.get(i);
				Object[] args = { Commande.SORTIE_PAUSE,newPartie,newPartie.getListeParticipants().get(i) };
				joueurCourant.send(args);
			}
		}else{
			// suppression de tous les joueurs de la liste du serveur apres les avoirs prevenu que la partie aller etre sauvegardee
			// et changement d'etat de la partie
			ArrayList<Joueur> joueurs = partie.getListeParticipants();
			for (int i = 0 ; i < joueurs.size() ; i++){
				// prevenir le joueur que l'administrateur a choisi de sauvegarder la partie
				Object[] args = { Commande.NOTIFIER_JOUEUR_PARTIE_SAUVEGARDEE };
				if (joueurs.get(i).getSocket() != null){
					// on verifie qu'il s'agit pas bien sur du joueur qui vient de se deconnecter
					joueurs.get(i).send(args);
				}
				ClientListener clientListener = joueurs.get(i).getClientListener();
				clientListener.deconnexion();
				
				server.remove(joueurs.get(i));
			}
			server.remove(partie);
			
			
			// TODO:implementer ICI la sauvegarde de la partie au niveau du serveur (sous forme de fichier)
			
			// on recupere la partie a sauvegarde (tel que l'admin l'a connait)
			Partie partieASauvegarder = (Partie)arguments[1];
			
			// et changement d'etat de la partie
			partieASauvegarder.setEtatDeLaPartie(Etat.SAUVEGARDEE);
			
			// puis on l'ajoute a la liste du server
			server.add(partieASauvegarder);
		}
		
		
	}
}
