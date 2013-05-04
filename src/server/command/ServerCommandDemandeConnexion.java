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
		boolean loginEtPasswordCorrect = DBConnexion.verificationIdentifiantsConnection(login,password);
		boolean dejaConnecte = false;
		
		if (loginEtPasswordCorrect){
			if (!server.isDejaConnecte(login,password)){
				// on ajoute le joueur a la liste des joueurs connectés au serveur
				server.add(new Joueur(_client,login,password));
			}else{
				dejaConnecte = true;
			}
		}
		
		Object[] args = new Object[3];
		args[0] = Commande.DEMANDE_CONNEXION;
		args[1] = loginEtPasswordCorrect;
		args[2] = dejaConnecte;
		_client.send(args);
	}
	
}
