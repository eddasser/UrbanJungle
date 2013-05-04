package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import server.controller.FenetreServerListener;
import server.view.VueServer;

import common.Constante;
import common.Joueur;
import common.Partie;


public class Server extends Observable{
	private final static ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
	private final static ArrayList<Partie> parties = new ArrayList<Partie>();
	
	public Server(){
		/*
		 * on teste d'abord si la base de données est accessible
		 */
		if (!DBConnexion.isDataBaseAccessible()){
			error("Le serveur n'a pu être lancé : connexion impossible a la base de données");
		}
		
		/*
		 * on teste ensuite la thread d'ecoute du server
		 */
		try{
			ServerListener sl;
			int port = Constante.NUMERO_PORT_ECOUTE_PAR_DEFAUT;
			if (port > 0){
				sl = new ServerListener(this,port);
			}else{
				// dans le cas ou il n'y a pas de port par defaut dans le fichier de configuration, on utilise un port libre
				sl = new ServerListener(this);
			}
			initialiserAPartirFichierDeSauvegardePartie();
			sl.start();
		}catch (java.net.BindException e){
			// le port n'est pas libre
			error("Le port d'écoute n'est pas libre");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static void initialiserAPartirFichierDeSauvegardePartie(){
		File file = new File(Constante.FICHIER_SAUVEGARDE_PARTIES_SERVEUR);
		if (file.exists()){
			try{
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				@SuppressWarnings("unchecked")
				ArrayList<Partie> partiesSauvegardees = (ArrayList<Partie>)ois.readObject();
				parties.addAll(partiesSauvegardees);
				ois.close();
				fis.close();
			}catch (FileNotFoundException e){
				e.printStackTrace();
			}catch (IOException e){
				e.printStackTrace();
			}catch (ClassNotFoundException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void sauvegarderPartiesDansFichier(){
		try{
			File file = new File(Constante.FICHIER_SAUVEGARDE_PARTIES_SERVEUR);
			if (file.exists()){
				file.delete();
			}
			try{
				file.createNewFile();
			}catch (IOException e){
				e.printStackTrace();
			}
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(parties);
			oos.close();
			fos.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public synchronized boolean isDejaConnecte(String login,String password){
		boolean isConnecte = false;
		
		for (int i = 0 ; !isConnecte && i < joueurs.size() ; i++){
			Joueur joueurCourant = joueurs.get(i);
			if (login.equals(joueurCourant.getLogin()) && password.equals(joueurCourant.getPassword())){
				isConnecte = true;
			}
		}
		
		return isConnecte;
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
	
	public Joueur getJoueur(Socket socket){
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
	
	public Partie getPartieWhereJoueur(Joueur joueur){
		Partie partie = null;
		
		for (int i = 0 ; partie == null && i < parties.size() ; i++){
			if (parties.get(i).getListeParticipants().contains(joueur)){
				partie = parties.get(i);
			}
		}
		
		return partie;
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
	
	public void error(String message){
		JOptionPane.showMessageDialog(new JFrame(),message,"Erreur Server",JOptionPane.ERROR_MESSAGE);
		System.exit(-1);
	}
	
	public static void main(String[] args){
		Server server = new Server();
		VueServer vueServer = new VueServer(server);
		server.addObserver(vueServer);
		vueServer.addWindowListener(new FenetreServerListener());
	}
}