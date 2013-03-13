package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import common.command.ClientCommand;
import common.command.ServerCommand;

public class MainClient implements Runnable{
	private Socket socket;
	private Thread thd;
	private static JeuPanel jeu;
	private ObjectOutputStream out; // flux de sortie d'objet
	private ObjectInputStream in; // flux d'entrée d'objet
	
	public MainClient(String addr,int port,JeuPanel jeu){
		MainClient.jeu = jeu;
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
				
				ClientCommand com = (ClientCommand)in.readObject();
				com.execute(jeu);
				
			}
		}catch (IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public void sendCommand(ServerCommand com){
		try{
			out.writeObject(com);
			out.flush();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	
}