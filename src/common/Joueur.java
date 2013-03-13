package common;

import java.io.Serializable;
import java.net.Socket;

public class Joueur implements Serializable{
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	// info pour partie réseau
	private Socket socket; // socket a utiliser pour contacter le client
	private String login;
	private String password;
	
	// constructeur appelé par le serveur
	public Joueur(Socket _socket,String _login,String _password){
		socket = _socket;
		login = _login;
		password = _password;
	}
	
	@Override
	public String toString(){
		return socket + "\nlogin : " + login + "\nmot de passe : " + password;
	}
}
