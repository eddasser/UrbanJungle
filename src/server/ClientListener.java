package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import server.command.ServerCommand;
import server.command.ServerCommandFactory;


/**
 * @author omar
 */
public class ClientListener implements Runnable{
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
	
	public void send(String[] args){
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
				String[] args = (String[])in.readObject();
				ServerCommand command = ServerCommandFactory.getCommand(args);
				command.execute(this);
			}
		}catch (ClassNotFoundException | IOException e){
			// e.printStackTrace();
		}
	}
	
}
