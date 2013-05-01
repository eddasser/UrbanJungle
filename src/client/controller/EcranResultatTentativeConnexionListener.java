package client.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import client.JeuPanel;

/**
 * listener de l'ecran le notification du resultat de la tentative de connexion. Quand l'utilisateur appuie sur entrée, on passe a l'écran
 * de connexion
 */
public class EcranResultatTentativeConnexionListener extends MouseAdapter{
	private JeuPanel jeu;
	
	public EcranResultatTentativeConnexionListener(JeuPanel jeuParam){
		jeu = jeuParam;
	}
	
	@Override
	public void mouseClicked(MouseEvent e){
		if (jeu.isAccesServeur()){
			jeu.lancerMultijoueurs();
		}else{
			jeu.chargerEcranChoixTypePartie();
		}
	}
}
