package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

import server.command.ServerCommand;
import server.command.ServerCommandFactory;

import common.Constante;


/**
 * @author omar
 */

public class ClientListener implements Runnable,Serializable{
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	private Server server;
	private Socket socket;
	private ObjectOutputStream out; // flux de sortie d'objet
	private ObjectInputStream in; // flux d'entrée d'objet
	
	public ClientListener(Server server,Socket _socket){
		this.server = server;
		try{
			socket = _socket;
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		}catch (IOException e){
			e.printStackTrace();
		}
		
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public Socket getSocket(){
		return socket;
	}
	
	public Server getServer(){
		return server;
	}
	
	public void send(Object[] args){
		try{
			out.writeObject(args);
			out.flush();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void run(){
		try{
			while (true){
				// on recupere la commande que nous a envoyer le client puis on l'execute
				Object[] args = (Object[])in.readObject();
				ServerCommand command = ServerCommandFactory.getCommand(args);
				command.execute(this);
			}
		}catch (ClassNotFoundException | IOException e){
			// e.printStackTrace();
		}
	}
	
}
