package client.command;

import java.util.Arrays;
import java.util.HashMap;

import common.Constante;

/**
 * @author omar
 */
public class ClientCommandFactory{
	private final static ClientCommandFactory factory = new ClientCommandFactory();
	
	private HashMap<String,ClientCommand> commandes = new HashMap<String,ClientCommand>();
	
	private ClientCommandFactory(){
		commandes.put(Constante.COMMANDE_CREATION_COMPTE,new ClientCommandCreationCompte());
		commandes.put(Constante.COMMANDE_DEMANDE_CONNEXION,new ClientCommandDemandeConnexion());
		commandes.put(Constante.COMMANDE_LISTE_PARTIES,new ClientCommandListePartie());
		commandes.put(Constante.COMMANDE_PING,new ClientCommandPing());
		commandes.put(Constante.COMMANDE_ERROR,new ClientCommandError());
		commandes.put(Constante.COMMANDE_REJOINDRE_PARTIE,new ClientCommandRejoindrePartie());
		commandes.put(Constante.COMMANDE_REPRENDRE_PARTIE,new ClientCommandRejoindrePartie());
		commandes.put(Constante.COMMANDE_CREATION_PARTIE,new ClientCommandCreationPartie());
		commandes.put(Constante.COMMANDE_DEBUT_JEU,new ClientCommandDebutJeu());
	}
	
	public static ClientCommand getCommand(String[] args){
		ClientCommand commande = factory.commandes.get(Constante.COMMANDE_ERROR);
		
		if (args.length > 0){
			String commande_name = args[0];
			if (factory.commandes.containsKey(commande_name)){
				commande = factory.commandes.get(commande_name);
				
				String[] args_without_first_element = Arrays.copyOfRange(args,1,args.length);
				commande.setArguments(args_without_first_element);
			}
		}
		
		return commande;
	}
	
}