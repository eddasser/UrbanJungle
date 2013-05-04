package server.controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import server.Server;

/**
 * @author omar
 */
public class FenetreServerListener implements WindowListener{
	
	public FenetreServerListener(){
	}
	
	@Override
	public void windowOpened(WindowEvent e){
	}
	
	@Override
	public void windowClosing(WindowEvent e){
		Server.sauvegarderPartiesDansFichier();
	}
	
	@Override
	public void windowClosed(WindowEvent e){
	}
	
	@Override
	public void windowIconified(WindowEvent e){
	}
	
	@Override
	public void windowDeiconified(WindowEvent e){
	}
	
	@Override
	public void windowActivated(WindowEvent e){
	}
	
	@Override
	public void windowDeactivated(WindowEvent e){
	}
	
}
