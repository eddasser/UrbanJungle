package common;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import server.ClientListener;

import common.partie.batiment.Batiment;
import common.partie.batiment.TypeBatiment;
import common.partie.unite.TypeUnite;
import common.partie.unite.Unite;

public class Joueur{
	// info pour partie réseau
	private ClientListener clientListener; // socket a utiliser pour contacter le client
	private String login;
	private String password;
	
	// info pour les unites et batiments que le joueur possede
	private ArrayList<Unite> unites;
	private ArrayList<Batiment> batiments;
	
	// niveau pour les types de batiments et unités pour le joueur courant
	private HashMap<TypeBatiment,Integer> niveauBatiment;
	private HashMap<TypeUnite,Integer> niveauUnite;
	
	// constructeur appelé par le serveur
	public Joueur(ClientListener _clientListener,String _login,String _password){
		clientListener = _clientListener;
		login = _login;
		password = _password;
		unites = new ArrayList<Unite>();
		batiments = new ArrayList<Batiment>();
		
		niveauBatiment = new HashMap<TypeBatiment,Integer>();
		for (TypeBatiment type : TypeBatiment.values()){
			niveauBatiment.put(type,TypeBatiment.getNiveauBase(type));
		}
		
		niveauUnite = new HashMap<TypeUnite,Integer>();
		for (TypeUnite type : TypeUnite.values()){
			niveauUnite.put(type,TypeUnite.getNiveauBase(type));
		}
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
	
	public ArrayList<Unite> getUnites(){
		return unites;
	}
	
	public void setUnites(ArrayList<Unite> unites){
		this.unites = unites;
	}
	
	public ArrayList<Batiment> getBatiments(){
		return batiments;
	}
	
	public void setBatiments(ArrayList<Batiment> batiments){
		this.batiments = batiments;
	}
	
	public void ajouterBatiment(Batiment b){
		batiments.add(b);
	}
	
	public void ajouterUnite(Unite u){
		unites.add(u);
	}
}
