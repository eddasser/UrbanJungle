package client.command;

import client.JeuPanel;
import client.ServerListener;

import common.Translator;

/**
 * @author omar
 */
public class ClientCommandNotifierPartieSauvegardee extends ClientCommand{
	
	@Override
	public void execute(ServerListener _server){
		JeuPanel jeu = _server.getJeu();
		jeu.notificationJoueur(Translator.translate("LaPartieAEteSauvegardee"));
		_server.deconnexion();
		JeuPanel.getEcranJeu().cacherPlateau();
		JeuPanel.getEcranJeu().cacherEcranAttenteDecisionAdmin();
		jeu.chargerEcranChoixTypePartie();
		jeu.getClient().update();
	}
	
	
}
