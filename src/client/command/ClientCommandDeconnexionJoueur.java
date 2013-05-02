package client.command;

import client.JeuPanel;
import client.ServerListener;

import common.Commande;
import common.Joueur;
import common.Partie;

/**
 * @author omar
 */
public class ClientCommandDeconnexionJoueur extends ClientCommand{
	
	@Override
	public void execute(ServerListener _server){
		JeuPanel jeu = _server.getJeu();
		Joueur joueur = (Joueur)arguments[0];// joueur qui s'est deconnecté
		
		Partie partie = jeu.getClient().getPartie();
		partie.getListeParticipants().remove(joueur);// on supprime le joueur de la partie
		
		// System.out.println("deconnexion d'un joueur de la partie : " + joueur.getLogin() + " : " + partie.getNbJoueurActuellement());
		
		
		ClientCommand commande = ClientCommandFactory.getCommand((Commande)arguments[1]);
		commande.setArguments(arguments);
		commande.execute(_server);
	}
	
}
