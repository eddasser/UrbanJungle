package common;

import java.net.Socket;

import server.ClientListener;

public class Joueur{
	// info pour partie réseau
	private ClientListener clientListener; // socket a utiliser pour contacter le client
	private String login;
	private String password;
	
	// constructeur appelé par le serveur
	public Joueur(ClientListener _clientListener,String _login,String _password){
		clientListener = _clientListener;
		login = _login;
		password = _password;
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
}
