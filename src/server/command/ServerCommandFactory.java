package server.command;

import java.util.Arrays;
import java.util.HashMap;


import common.Constante;

/**
 * @author omar
 */
public class ServerCommandFactory{
	private final static ServerCommandFactory factory = new ServerCommandFactory();
	
	private HashMap<String,ServerCommand> commandes = new HashMap<String,ServerCommand>();
	
	private ServerCommandFactory(){
		commandes.put(Constante.COMMANDE_CREATION_COMPTE,new ServerCommandCreationCompte());
		commandes.put(Constante.COMMANDE_DEMANDE_CONNEXION,new ServerCommandDemandeConnexion());
		commandes.put(Constante.COMMANDE_LISTE_PARTIES,new ServerCommandListePartie());
		commandes.put(Constante.COMMANDE_PING,new ServerCommandPing());
	}
	
	public static ServerCommand getCommand(String[] args){
		ServerCommand commande = null;
		
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
