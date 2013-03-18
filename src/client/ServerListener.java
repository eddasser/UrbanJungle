package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import client.command.ClientCommand;
import client.command.ClientCommandFactory;


public class ServerListener implements Runnable{
	private Socket socket;
	private ObjectOutputStream out; // flux de sortie d'objet
	private ObjectInputStream in; // flux d'entrée d'objet
	private Thread thd;
	private JeuPanel jeu;
	
	public ServerListener(String addr,int port,JeuPanel jeu){
		this.jeu = jeu;
		try{
			socket = new Socket(addr,port);
			socket.setSoLinger(true,10);
			
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			
		}catch (IOException a){
			System.out.println("erreur2 " + a.getMessage());
			a.printStackTrace();
		}
		
		thd = new Thread(this);
		thd.start();
	}
	
	public void run(){
		try{
			while (true){
				
				String[] args = (String[])in.readObject();
				ClientCommand command = ClientCommandFactory.getCommand(args);
				command.execute(this);
				
			}
		}catch (IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public void sendCommand(String[] args){
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
	
	
}