package client.controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import client.JeuPanel;

import common.Commande;

/**
 * @author omar
 */
public class FenetreListener implements WindowListener{
	private JeuPanel jeu;
	
	public FenetreListener(JeuPanel jeu){
		this.jeu = jeu;
	}
	
	@Override
	public void windowOpened(WindowEvent e){
	}
	
	@Override
	public void windowClosing(WindowEvent e){
		if (jeu.isAccesServeur()){
			Object[] args = { Commande.DECONNEXION };
			jeu.getDialogueServeur().sendCommand(args);
		}
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
