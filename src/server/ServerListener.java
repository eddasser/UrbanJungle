package server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author omar
 */
public class ServerListener extends Thread{
	private Server server;
	private ServerSocket serverSocket;
	private boolean continuer;
	
	public ServerListener(Server server){
		this.server = server;
		try{
			serverSocket = new ServerSocket();
		}catch (IOException e){
			e.printStackTrace();
		}
		continuer = true;
	}
	
	public ServerListener(Server server,int port){
		this.server = server;
		try{
			serverSocket = new ServerSocket(port);
		}catch (IOException e){
			e.printStackTrace();
		}
		continuer = true;
	}
	
	@Override
	public void run(){
		while (continuer){
			try{
				new ClientListener(server,serverSocket.accept());
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public void arreter(){
		continuer = false;
	}
	
	public Server getServer(){
		return server;
	}
	
}
