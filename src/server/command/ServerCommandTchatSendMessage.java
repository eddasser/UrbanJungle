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
public class ServerCommandTchatSendMessage extends ServerCommand{
	
	@Override
	public void execute(ClientListener _client){
		Server server = _client.getServer();
		Joueur joueur = server.getJoueur(_client.getSocket());
		
		Partie partie = server.getPartieWhereJoueur(joueur);
		// recuperation du message qu'il veut envoyer
		String message = (String)arguments[0];
		
		Object[] args = { Commande.TCHAT_SEND_MESSAGE,joueur,message };
		
		// notification de tous les joueurs de la partie
		ArrayList<Joueur> joueurs = partie.getListeParticipants();
		for (int i = 0 ; i < joueurs.size() ; i++){
			Joueur joueurCourant = joueurs.get(i);
			joueurCourant.send(args);
		}
		
	}
}
