package server.command;

import server.ClientListener;
import server.Server;

import common.Commande;
import common.Joueur;
import common.Partie;

/**
 * @author omar
 */
public class ServerCommandCreationPartie extends ServerCommand{
	
	@Override
	public void execute(ClientListener _client){
		String nomPartie = (String)arguments[0];
		String passwordPartie = (String)arguments[1];
		int nbJoueur = (int)arguments[2];
		
		Server server = _client.getServer();
		
		Joueur joueur = server.getJoueur(_client.getSocket());
		Partie partie = new Partie(nomPartie,nbJoueur,passwordPartie,false);
		
		partie.addJoueur(joueur);
		partie.setJoueurCourant(joueur); // Le createur jouera en premier
		
		server.add(partie);
		
		Boolean creationOk = true;// on part du principe ou il n'y a aucune limitation pour la création d'une partie
		
		Object[] args = { Commande.CREATION_PARTIE,creationOk };
		_client.send(args);
	}
	
}
