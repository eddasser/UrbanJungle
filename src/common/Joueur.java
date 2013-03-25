package common;

import java.net.Socket;
import java.util.ArrayList;

import common.partie.batiment.Batiment;
import common.partie.unite.Unite;

import server.ClientListener;

public class Joueur{
	// info pour partie réseau
	private ClientListener clientListener; // socket a utiliser pour contacter le client
	private String login;
	private String password;
	
	// info pour les unites et batiments que le joueur possede
	private ArrayList<Unite> unites;
	private ArrayList<Batiment> batiments;
	
	// constructeur appelé par le serveur
	public Joueur(ClientListener _clientListener,String _login,String _password){
		clientListener = _clientListener;
		login = _login;
		password = _password;
		unites = new ArrayList<Unite>();
		batiments = new ArrayList<Batiment>();
	}
	
	public Socket getSocket(){
		return clientListener.getSocket();
	}
	
	public void send(String[] args){
		clientListener.send(args);
	}
	
	@Override
	public String toString(){
		return "\nlogin : " + login + "\nmot de passe : " + password;
	}

	public ArrayList<Unite> getUnites() {
		return unites;
	}

	public void setUnites(ArrayList<Unite> unites) {
		this.unites = unites;
	}

	public ArrayList<Batiment> getBatiments() {
		return batiments;
	}

	public void setBatiments(ArrayList<Batiment> batiments) {
		this.batiments = batiments;
	}
	
	public void ajouterBatiment(Batiment b){
		this.batiments.add(b);
	}
	
	public void ajouterUnite(Unite u){
		this.unites.add(u);
	}
}
