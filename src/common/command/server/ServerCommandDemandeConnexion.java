package common.command.server;

import server.ClientListener;
import server.DBConnexion;
import server.Server;

import common.Joueur;
import common.command.ServerCommand;
import common.command.client.ClientCommandDemandeConnexion;

/**
 * @author omar
 */
public class ServerCommandDemandeConnexion extends ServerCommand{
	private static final long serialVersionUID = 1L;
	
	private String login;
	private String password;
	
	public ServerCommandDemandeConnexion(String _login,String _password){
		super();
		login = _login;
		password = _password;
	}
	
	@Override
	public void execute(ClientListener _client){
		// le client demande au serveur a se connecter
		boolean resultatVerif = DBConnexion.verificationIdentifiantsConnection(login,password);
		
		if (resultatVerif){
			// on ajoute le joueur a la liste des joueurs connectés au serveur
			Server.add(new Joueur(_client.getSocket(),login,password));
		}
		
		ClientCommandDemandeConnexion com = new ClientCommandDemandeConnexion(resultatVerif);
		_client.send(com);
	}
	
}
