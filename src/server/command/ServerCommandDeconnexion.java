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
public class ServerCommandDeconnexion extends ServerCommand{
	
	@Override
	public void execute(ClientListener _client){
		Server server = _client.getServer();
		// on supprime d'abord le joueur de la liste du server
		Joueur joueur = server.getJoueur(_client.getSocket());
		server.remove(joueur);
		
		
		Partie partie = server.getPartieWhereJoueur(joueur);
		if (partie != null){
			// on supprime le joueur de la partie dans laquelle il était en train de jouer
			// et on notifie les autres participant (voir on supprime la partie s'il n'y a plus personne dessus)
			// partie.getListeParticipants().remove(joueur);
			
			Object[] args = new Object[3];
			args[0] = Commande.DECONNEXION_UN_JOUEUR;
			args[1] = joueur;
			
			ArrayList<Joueur> joueurs = partie.getListeParticipants();// joueurs restant
			int nbJoueurRestant = joueurs.size() - 1;
			switch(nbJoueurRestant){
				case 0:
					// s'il n' y a plus personne on supprime la partie et le joueur :
					// c'est le cas par exemple lorsque l'on a créé une partie et que personne ne l'a rejointe
					partie.getListeParticipants().remove(joueur);
					server.remove(partie);
					break;
				
				case 1:
					// cas où on joue a 2 et qu'une des deux personnes s'en va
					// on lui dit qu'il ne peut plus continuer seul et on lui propose de sauvegarder la partie
					args[2] = Commande.PROPOSER_SAUVEGARDE_PARTIE;
					if (joueurs.get(0).equals(joueur)){
						joueurs.get(1).send(args);
					}else{
						joueurs.get(0).send(args);
					}
					break;
				
				default:
					// sinon, on notifie tous les autres joueurs
					// et on propose au joueur admin (le nouvel admin) de choisir une action :
					// soit continuer la partie (sans le joueur)
					// soit sauvegarder la partie
					// soit quitter la partie
					Joueur admin = partie.getJoueurAdminApresDeconnexion(joueur);
					for (int i = 0 ; i < joueurs.size() ; i++){
						Joueur joueurCourant = joueurs.get(i);
						if (!joueurCourant.equals(joueur)){// on n'envois pas au joueur qui vient de se deconnecter
							if (joueurCourant.equals(admin)){
								// on envoi a l'admin la commande de proposition
								args[2] = Commande.PROPOSER_SAUVEGARDE_OU_CONTINUER_PARTIE;
							}else{
								// et on envoi aux autres joueurs la commande de mise en pause
								args[2] = Commande.MISE_EN_PAUSE;
							}
							joueurCourant.send(args);
						}
					}
					break;
			}
		}
	}
	
}
