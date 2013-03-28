package client.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import client.JeuPanel;

/** listener de l'ecran titre chargé de vérifier quand l'utilisateur appuie sur entrée */
public class EcranTitreListener extends KeyAdapter{
	JeuPanel jeu;
	
	public EcranTitreListener(JeuPanel jeuParam){
		jeu = jeuParam;
	}
	
	@Override
	public void keyPressed(KeyEvent e){
		if (e.getKeyCode() == KeyEvent.VK_ENTER){
			jeu.chargerEcranChoixTypePartie();
		}
	}
}
