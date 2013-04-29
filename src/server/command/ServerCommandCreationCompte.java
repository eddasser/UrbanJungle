package server.command;

import server.ClientListener;
import server.DBConnexion;
import server.Server;

import common.Constante;
import common.Joueur;

/**
 * @author omar
 */
public class ServerCommandCreationCompte extends ServerCommand{
	
	@Override
	public void execute(ClientListener _client){
		String login = arguments[0];
		String password = arguments[1];
		
		// le client demande au serveur a creer un compte
		boolean resultatVerif = DBConnexion.creationDeCompte(login,password);
		
		if (resultatVerif){
			// on ajoute le joueur a la liste des joueurs connectés au serveur
			Server server = _client.getServer();
			server.add(new Joueur(_client,login,password));
		}
		String[] args = { Constante.COMMANDE_CREATION_COMPTE,Boolean.toString(resultatVerif) };
		_client.send(args);
	}
	
}
