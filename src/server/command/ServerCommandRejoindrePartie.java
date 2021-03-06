package server.command;

import server.ClientListener;
import server.Server;

import common.Commande;
import common.Etat;
import common.Joueur;
import common.Partie;
import common.Translator;

/**
 * @author omar
 */
public class ServerCommandRejoindrePartie extends ServerCommand{
	
	@Override
	public void execute(ClientListener _client){
		Server server = _client.getServer();
		
		int idPartie = (int)arguments[0];
		String password = (String)arguments[1];
		
		Joueur joueur = server.getJoueur(_client.getSocket());
		Partie partie = server.getParte(idPartie);
		
		
		boolean rejoint = false;
		String message_erreur = "";
		
		Etat etat = partie.getEtatDeLaPartie();
		if (etat == Etat.EN_ATTENTE_JOUEUR || etat == Etat.SAUVEGARDEE){
			if (partie.getPassword().equals(password)){
				if (partie.addJoueur(joueur)){
					rejoint = true;
				}else{
					// erreur deja trop de joueur
					message_erreur = Translator.translate("nbJoueurMaximumDejaAtteint");
				}
			}else{
				// erreur mauvais mot de passe
				message_erreur = Translator.translate("motDePasseIncorrect");
			}
		}else{
			// erreur partie commencee
			message_erreur = Translator.translate("partieDejaCommencee");
		}
		
		Object[] args = { Commande.REJOINDRE_PARTIE,rejoint,message_erreur };
		_client.send(args);
		
		if (rejoint && !partie.placeDisponible()){
			// dans le cas ou tout les sjoueurs ont rejoint la partie, on demarre le jeu
			partie.setEtatDeLaPartie(Etat.COMMENCEE);
			partie.initialiserPartie();
			partie.notifierDebutJeu();
		}
	}
	
}
