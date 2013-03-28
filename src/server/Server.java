package server;

import java.net.Socket;
import java.util.ArrayList;

import common.Constante;
import common.Joueur;
import common.Partie;


public class Server{
	private final static ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
	private final static ArrayList<Partie> parties = new ArrayList<Partie>();
	
	public static ArrayList<Partie> getParties(){
		return parties;
	}
	
	public static Partie getParte(int id){
		return parties.get(id);
	}
	
	public static Joueur getJoueur(Socket socket){
		Joueur joueur = null;
		for (int i = 0 ; i < joueurs.size() && joueur == null ; i++){
			if (joueurs.get(i).getSocket().equals(socket)){
				joueur = joueurs.get(i);
			}
		}
		return joueur;
	}
	
	public static void add(Joueur j){
		joueurs.add(j);
	}
	
	public static void add(Partie p){
		parties.add(p);
	}
	
	public static void remove(Joueur j){
		joueurs.remove(j);
	}
	
	public static void remove(Partie p){
		parties.remove(p);
	}
	
	
	public static void main(String[] args){
		int port = Constante.NUMERO_PORT_ECOUTE_PAR_DEFAUT;
		ServerListener sl;
		
		if (port > 0){
			sl = new ServerListener(port);
		}else{
			// dans le cas ou il n'y a pas de port par defaut dans le fichier de configuration, on utilise un port libre
			sl = new ServerListener();
		}
		
		sl.start();
	}
}