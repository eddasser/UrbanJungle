package server.command;

import server.ClientListener;
import server.DBConnexion;
import server.Server;

import common.Constante;
import common.Joueur;

/**
 * @author omar
 */
public class ServerCommandDemandeConnexion extends ServerCommand{
	@Override
	public void execute(ClientListener _client){
		String login = arguments[0];
		String password = arguments[1];
		
		// le client demande au serveur a se connecter
		boolean resultatVerif = DBConnexion.verificationIdentifiantsConnection(login,password);
		
		if (resultatVerif){
			// on ajoute le joueur a la liste des joueurs connectés au serveur
			Server.add(new Joueur(_client.getSocket(),login,password));
		}
		
		String[] args = { Constante.COMMANDE_DEMANDE_CONNEXION,Boolean.toString(resultatVerif) };
		_client.send(args);
	}
	
}
