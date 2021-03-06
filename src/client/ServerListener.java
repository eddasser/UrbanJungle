package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import client.command.ClientCommand;
import client.command.ClientCommandFactory;

import common.Translator;


public class ServerListener implements Runnable{
	private Socket socket;
	private ObjectOutputStream out; // flux de sortie d'objet
	private ObjectInputStream in; // flux d'entrée d'objet
	private Thread thd;
	private JeuPanel jeu;
	
	private boolean connected;
	private boolean continu;
	
	private String adress;
	private int port;
	
	public ServerListener(String addr,int port,JeuPanel jeu){
		adress = addr;
		this.port = port;
		this.jeu = jeu;
		connected = false;
	}
	
	public void connect(){
		deconnexion();
		try{
			socket = new Socket(adress,port);
			socket.setSoLinger(true,10);
			
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			
			continu = true;
			connected = true;
			thd = new Thread(this);
			thd.start();
		}catch (IOException a){
			System.out.println("connexion au serveur impossible");
		}
	}
	
	public boolean isConnected(){
		return connected;
	}
	
	public void run(){
		try{
			while (continu){
				Object[] args = (Object[])in.readObject();
				
				ClientCommand command = ClientCommandFactory.getCommand(args);
				command.execute(this);
			}
			if (in != null) in.close();
			if (out != null) out.close();
			if (socket != null) socket.close();
		}catch (java.io.EOFException e){
			deconnexion();
			jeu.notificationJoueur(Translator.translate("LeServerNEstPlusAccessible"));
			jeu.chargerEcranChoixTypePartie();
		}catch (IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public void sendCommand(Object[] args){
		try{
			out.writeObject(args);
			out.flush();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public JeuPanel getJeu(){
		return jeu;
	}
	
	public void deconnexion(){
		continu = false;
		connected = false;
		thd = null;
		socket = null;
		out = null;
		in = null;
	}
}