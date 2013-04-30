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
	
}
