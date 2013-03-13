package common.command.server;

import server.ClientListener;
import server.DBConnexion;
import server.Server;

import common.Joueur;
import common.command.ServerCommand;
import common.command.client.ClientCommandCreationCompte;

/**
 * @author omar
 */
public class ServerCommandCreationCompte extends ServerCommand{
	private static final long serialVersionUID = 1L;
	
	private String login;
	private String password;
	
	public ServerCommandCreationCompte(String _login,String _password){
		login = _login;
		password = _password;
	}
	
	
	@Override
	public void execute(ClientListener _client){
		// le client demande au serveur a creer un compte
		boolean resultatVerif = DBConnexion.creationDeCompte(login,password);
		
		if (resultatVerif){
			// on ajoute le joueur a la liste des joueurs connectés au serveur
			Server.add(new Joueur(_client.getSocket(),login,password));
		}
		
		ClientCommandCreationCompte com = new ClientCommandCreationCompte(resultatVerif);
		_client.send(com);
	}
	
}
