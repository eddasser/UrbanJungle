package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import common.command.ServerCommand;

/**
 * @author omar
 */
public class ClientListener implements Runnable{
	private Socket socket;
	private ObjectOutputStream out; // flux de sortie d'objet
	private ObjectInputStream in; // flux d'entrée d'objet
	
	public ClientListener(Socket _socket){
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
	
	public void send(Object object){
		try{
			out.writeObject(object);
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
				ServerCommand command = (ServerCommand)in.readObject();
				command.execute(this);
			}
		}catch (ClassNotFoundException | IOException e){
			// e.printStackTrace();
		}
	}
	
}
