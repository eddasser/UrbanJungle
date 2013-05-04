package client.command;

import java.util.Arrays;
import java.util.HashMap;

import common.Commande;

/**
 * @author omar
 */
public class ClientCommandFactory{
	private final static ClientCommandFactory factory = new ClientCommandFactory();
	
	private HashMap<Commande,ClientCommand> commandes = new HashMap<Commande,ClientCommand>();
	
	private ClientCommandFactory(){
		commandes.put(Commande.CREATION_COMPTE,new ClientCommandCreationCompte());
		commandes.put(Commande.DEMANDE_CONNEXION,new ClientCommandDemandeConnexion());
		commandes.put(Commande.LISTE_PARTIES,new ClientCommandListePartie());
		commandes.put(Commande.PING,new ClientCommandPing());
		commandes.put(Commande.ERROR,new ClientCommandError());
		commandes.put(Commande.REJOINDRE_PARTIE,new ClientCommandRejoindrePartie());
		commandes.put(Commande.REPRENDRE_PARTIE,new ClientCommandRejoindrePartie());
		commandes.put(Commande.CREATION_PARTIE,new ClientCommandCreationPartie());
		commandes.put(Commande.DEBUT_JEU,new ClientCommandDebutJeu());
		commandes.put(Commande.UPDATE_TOUR,new ClientCommandUpdateTour());
		commandes.put(Commande.DECONNEXION_UN_JOUEUR,new ClientCommandDeconnexionJoueur());
		commandes.put(Commande.PROPOSER_SAUVEGARDE_PARTIE,new ClientCommandPropositionDeSauvegardePartie());
		commandes.put(Commande.PROPOSER_SAUVEGARDE_OU_CONTINUER_PARTIE,new ClientCommandPropositionDeSauvegardeOuContinuationPartie());
		commandes.put(Commande.MISE_EN_PAUSE,new ClientCommandMiseEnPausePartie());
		commandes.put(Commande.SORTIE_PAUSE,new ClientCommandSortiePause());
		commandes.put(Commande.NOTIFIER_JOUEUR_PARTIE_SAUVEGARDEE,new ClientCommandNotifierPartieSauvegardee());
		commandes.put(Commande.JOUEUR_PERDU,new ClientCommandJoueurPerdu());
		commandes.put(Commande.JOUEUR_GAGNANT,new ClientCommandJoueurGagnant());
	}
	
	public static ClientCommand getCommand(Object[] args){
		ClientCommand commande = factory.commandes.get(Commande.ERROR);
		
		if (args.length > 0){
			Commande commande_name = (Commande)args[0];
			if (factory.commandes.containsKey(commande_name)){
				commande = factory.commandes.get(commande_name);
				
				Object[] args_without_first_element = Arrays.copyOfRange(args,1,args.length);
				commande.setArguments(args_without_first_element);
			}
		}
		
		return commande;
	}
	
	public static ClientCommand getCommand(Commande com){
		ClientCommand commande = factory.commandes.get(Commande.ERROR);
		
		if (factory.commandes.containsKey(com)){
			commande = factory.commandes.get(com);
		}
		
		return commande;
	}
	
}
