package server.command;

import java.io.IOException;
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
		if (!continuer){
			// TODO:implementer ICI la sauvegarde de la partie au niveau du serveur
			
			// suppression de tous les joueurs de la liste du serveur apres les avoirs prevenu que la partie aller etre sauvegardee
			// et changement d'etat de la partie
			ArrayList<Joueur> joueurs = partie.getListeParticipants();
			for (int i = 0 ; i < joueurs.size() ; i++){
				// prevenir le joueur que l'administrateur a choisi de sauvegarder la partie
				Object[] args = { Commande.NOTIFIER_JOUEUR_PARTIE_SAUVEGARDEE };
				joueurs.get(i).send(args);
				
				server.remove(joueurs.get(i));
				try{
					joueurs.get(i).getSocket().close();
				}catch (IOException e){
					e.printStackTrace();
				}
			}
			partie.setEtatDeLaPartie(Etat.SAUVEGARDEE);
		}else{
			// on supprime seulement le joueur qui s'est deconnecte
			Joueur joueurASupprimer = (Joueur)arguments[1];
			ArrayList<Joueur> joueurs = partie.getListeParticipants();
			joueurs.remove(joueurASupprimer);
			server.remove(joueurASupprimer);
			server.remove(partie);
			// puis on notifier a tous les joueurs qu'ils peuvent reprendre la partie
			for (int i = 0 ; i < joueurs.size() ; i++){
				Object[] args = { Commande.SORTIE_PAUSE };
				joueurs.get(i).send(args);
			}
		}
		
		
	}
}
