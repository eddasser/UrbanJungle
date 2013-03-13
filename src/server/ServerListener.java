package server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author omar
 */
public class ServerListener extends Thread{
	private ServerSocket server;
	private boolean continuer;
	
	public ServerListener(){
		try{
			server = new ServerSocket();
		}catch (IOException e){
			e.printStackTrace();
		}
		continuer = true;
	}
	
	public ServerListener(int port){
		try{
			server = new ServerSocket(port);
		}catch (IOException e){
			e.printStackTrace();
		}
		continuer = true;
	}
	
	@Override
	public void run(){
		while (continuer){
			try{
				new ClientListener(server.accept());
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public void arreter(){
		continuer = false;
	}
}
