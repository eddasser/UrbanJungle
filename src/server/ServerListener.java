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
	
	public ServerListener(Server server) throws IOException{
		this.server = server;
		serverSocket = new ServerSocket();
		continuer = true;
	}
	
	public ServerListener(Server server,int port) throws IOException{
		this.server = server;
		serverSocket = new ServerSocket(port);
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
