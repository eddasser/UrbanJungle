package client.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import client.TchatCheatCode;

import common.Joueur;

/**
 * @author omar
 */
public class KeyCheatCode implements KeyListener{
	private ArrayList<Integer> keys = new ArrayList<Integer>();
	private Joueur joueur;
	
	public KeyCheatCode(Joueur joueur){
		this.joueur = joueur;
	}
	
	@Override
	public void keyTyped(KeyEvent e){
	}
	
	@Override
	public void keyPressed(KeyEvent e){
		if (e.getKeyCode() == KeyEvent.VK_ENTER){
			if (keys.size() == 4){
				// les cheats code ont une longueur de 4 caracteres
				TchatCheatCode.execute(keys,joueur);
			}
			keys.clear();
		}else{
			keys.add(e.getKeyCode());
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e){
	}
	
	
}
