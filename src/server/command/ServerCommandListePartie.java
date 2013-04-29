package server.command;

import java.util.ArrayList;

import server.ClientListener;
import server.Server;

import common.Constante;
import common.Partie;


/**
 * @author omar
 */
public class ServerCommandListePartie extends ServerCommand{
	
	@Override
	public void execute(ClientListener _client){
		Server server = _client.getServer();
		
		// le client demande au serveur a recuperer la liste des parties crées
		ArrayList<Partie> parties = server.getParties();
		String[] args = new String[parties.size() + 1];
		
		args[0] = Constante.COMMANDE_LISTE_PARTIES;
		
		for (int i = 0 ; i < parties.size() ; i++){
			Partie partie = parties.get(i);
			
			StringBuffer sb = new StringBuffer();
			
			sb.append(Integer.toString(i));
			sb.append(Constante.MESSAGE_SEPARATOR);
			sb.append(partie.getNomPartie());
			sb.append(Constante.MESSAGE_SEPARATOR);
			sb.append(Integer.toString(partie.getNbJoueur()));
			sb.append(Constante.MESSAGE_SEPARATOR);
			sb.append(partie.getEtatDeLaPartie());
			sb.append(Constante.MESSAGE_SEPARATOR);
			sb.append(Boolean.toString(partie.necessitePassword()));
			
			args[i + 1] = sb.toString();
		}
		
		_client.send(args);
	}
}
