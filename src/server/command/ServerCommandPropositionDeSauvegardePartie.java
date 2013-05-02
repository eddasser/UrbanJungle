package server.command;

import java.io.IOException;
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
		if (sauvegarder){
			// TODO:implementer ICI la sauvegarde de la partie au niveau du serveur
			
			// suppression de tous les joueurs de la liste du serveur
			// et changement d'etat de la partie
			ArrayList<Joueur> joueurs = partie.getListeParticipants();
			for (int i = 0 ; i < joueurs.size() ; i++){
				server.remove(joueurs.get(i));
				try{
					joueurs.get(i).getSocket().close();
				}catch (IOException e){
					e.printStackTrace();
				}
			}
			partie.setEtatDeLaPartie(Etat.SAUVEGARDEE);
		}else{
			// suppression de tous les joueurs de la liste du serveur
			// puis suppression de la partie en elle-meme
			ArrayList<Joueur> joueurs = partie.getListeParticipants();
			for (int i = 0 ; i < joueurs.size() ; i++){
				server.remove(joueurs.get(i));
			}
			server.remove(partie);
		}
		/**/
	}
}
