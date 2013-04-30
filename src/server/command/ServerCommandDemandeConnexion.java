package server.command;

import server.ClientListener;
import server.DBConnexion;
import server.Server;

import common.Commande;
import common.Joueur;

/**
 * @author omar
 */
public class ServerCommandDemandeConnexion extends ServerCommand{
	@Override
	public void execute(ClientListener _client){
		String login = (String)arguments[0];
		String password = (String)arguments[1];
		
		Server server = _client.getServer();
		
		
		// le client demande au serveur a se connecter
		boolean resultatVerif = DBConnexion.verificationIdentifiantsConnection(login,password);
		
		if (resultatVerif){
			// on ajoute le joueur a la liste des joueurs connectés au serveur
			server.add(new Joueur(_client,login,password));
		}
		
		Object[] args = { Commande.DEMANDE_CONNEXION,resultatVerif };
		_client.send(args);
	}
	
}
