package server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;

import server.view.VueServer;

import common.Constante;
import common.Joueur;
import common.Partie;


public class Server extends Observable{
	private final static ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
	private final static ArrayList<Partie> parties = new ArrayList<Partie>();
	
	public Server(){
		int port = Constante.NUMERO_PORT_ECOUTE_PAR_DEFAUT;
		ServerListener sl;
		
		if (port > 0){
			sl = new ServerListener(this,port);
		}else{
			// dans le cas ou il n'y a pas de port par defaut dans le fichier de configuration, on utilise un port libre
			sl = new ServerListener(this);
		}
		
		sl.start();
	}
	
	public ArrayList<Joueur> getJoueurs(){
		return joueurs;
	}
	
	public ArrayList<Partie> getParties(){
		return parties;
	}
	
	public Partie getParte(int id){
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
	
	public void add(Joueur j){
		joueurs.add(j);
		update();
	}
	
	public void add(Partie p){
		parties.add(p);
		update();
	}
	
	public void remove(Joueur j){
		joueurs.remove(j);
		update();
	}
	
	public void remove(Partie p){
		parties.remove(p);
		update();
	}
	
	public void update(){
		setChanged();
		notifyObservers();
	}
	
	public static void main(String[] args){
		Server server = new Server();
		VueServer vueServer = new VueServer(server);
		server.addObserver(vueServer);
	}
}