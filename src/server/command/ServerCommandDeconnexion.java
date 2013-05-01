package server.command;

import server.ClientListener;
import server.Server;

import common.Joueur;
import common.Partie;

/**
 * @author omar
 */
public class ServerCommandDeconnexion extends ServerCommand{
	
	@Override
	public void execute(ClientListener _client){
		Server server = _client.getServer();
		Joueur joueur = server.getJoueur(_client.getSocket());
		server.remove(joueur);
		
		
		Partie partie = server.getPartieWhereJoueur(joueur);
		if (partie != null){
			// on supprime le joueur de la partie dans laquelle il était en train de jouer
			// et on notifie les autres participant (voir on supprime la partie s'il n'y a plus personne dessus)
			partie.getListeParticipants().remove(joueur);
			if (partie.getNbJoueurActuellement() == 0){
				server.remove(partie);
			}else{
				// notifier autres joueurs
				// TODO: notifier autres joueurs du depart d'un joueur
			}
		}
	}
	
}
