package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.JeuPanel;
import client.view.EcranChoixTypePartie;
import client.view.JCoolButton;


public class EcranChoixTypePartieListener implements ActionListener{
	private JeuPanel jeu;
	private EcranChoixTypePartie ecranChoixTypeDePartie;
	
	public EcranChoixTypePartieListener(JeuPanel jeuParam,EcranChoixTypePartie ecranChoixTypeDePartieParam){
		jeu = jeuParam;
		ecranChoixTypeDePartie = ecranChoixTypeDePartieParam;
	}
	
	@SuppressWarnings("null")
	@Override
	public void actionPerformed(ActionEvent evenement){
		// Retrouve la source de l'action
		JCoolButton boutonClique = (JCoolButton)evenement.getSource();
		
		if (boutonClique.equals(ecranChoixTypeDePartie.getPartieSolo())) // partie solo
		{
			jeu.lancerPartieSolo();
		}else if (boutonClique.equals(ecranChoixTypeDePartie.getChargerPartie())) // charger partie
		{
			jeu.chargerPartieSolo();
		}else if (boutonClique.equals(ecranChoixTypeDePartie.getPartieEnLigne())) // partie multi
		{
			jeu.chargerEcranLoader();
		}else if (boutonClique.equals(ecranChoixTypeDePartie.getQuitter())) // quitter
		{
			System.exit(0);
		}else{
			System.out.println("probleme, cas impossible");
			// code qui plante volontairement
			String s = null;
			s.charAt(0);
		}
	}
}
