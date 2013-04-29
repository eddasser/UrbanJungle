package server.command;

import server.ClientListener;
import server.Server;

import common.Constante;
import common.Joueur;
import common.Partie;

/**
 * @author omar
 */
public class ServerCommandCreationPartie extends ServerCommand{
	
	@Override
	public void execute(ClientListener _client){
		String nomPartie = arguments[0];
		String passwordPartie = arguments[1];
		int nbJoueur = Integer.parseInt(arguments[2]);
		
		Server server = _client.getServer();
		
		Joueur joueur = Server.getJoueur(_client.getSocket());
		Partie partie = new Partie(nomPartie,nbJoueur,passwordPartie,false);

		partie.addJoueur(joueur);
		partie.setJoueurCourant(joueur); // Le createur jouera en premier

		server.add(partie);
		
		Boolean creationOk = true;// on part du principe ou il n'y a aucune limitation pour la création d'une partie
		
		String[] args = { Constante.COMMANDE_CREATION_PARTIE,Boolean.toString(creationOk) };
		_client.send(args);
	}
	
}
