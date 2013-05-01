package server.command;

import java.util.Arrays;
import java.util.HashMap;

import common.Commande;

/**
 * @author omar
 */
public class ServerCommandFactory{
	private final static ServerCommandFactory factory = new ServerCommandFactory();
	
	private HashMap<Commande,ServerCommand> commandes = new HashMap<Commande,ServerCommand>();
	
	private ServerCommandFactory(){
		commandes.put(Commande.CREATION_COMPTE,new ServerCommandCreationCompte());
		commandes.put(Commande.DEMANDE_CONNEXION,new ServerCommandDemandeConnexion());
		commandes.put(Commande.LISTE_PARTIES,new ServerCommandListePartie());
		commandes.put(Commande.PING,new ServerCommandPing());
		commandes.put(Commande.ERROR,new ServerCommandError());
		commandes.put(Commande.REJOINDRE_PARTIE,new ServerCommandRejoindrePartie());
		commandes.put(Commande.REPRENDRE_PARTIE,new ServerCommandRejoindrePartie());
		commandes.put(Commande.CREATION_PARTIE,new ServerCommandCreationPartie());
		commandes.put(Commande.DECONNEXION,new ServerCommandDeconnexion());
		commandes.put(Commande.FIN_TOUR,new ServerCommandFinTour());
	}
	
	public static ServerCommand getCommand(Object[] args){
		ServerCommand commande = factory.commandes.get(Commande.ERROR);
		
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
	
}
