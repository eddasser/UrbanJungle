package common.ia;

import server.ClientListener;

import common.Joueur;
import common.Partie;

/**
 * @author omar
 */
public abstract class JoueurIA extends Joueur{
	
	public JoueurIA(){
		super();
	}
	
	public JoueurIA(ClientListener _clientListener,String _login,String _password){
		super(_clientListener,_login,_password);
	}
	
	/*
	 * on donne la partie en attribut de facon a pouvoir créé plus tard une IA
	 * qui bouge en fonction des autres joueurs
	 */
	public abstract void jouer(Partie partie);
	
}
