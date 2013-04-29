package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import client.JeuPanel;
import client.view.EcranChoixChargementPartie;
import client.view.JCoolButton;

public class EcranChoixChargementPartieListener implements ActionListener{

	private JeuPanel jeu;
	private EcranChoixChargementPartie ecranChoixChargementPartie;
	
	private File[] listeSauvegardes;
	
	public EcranChoixChargementPartieListener(JeuPanel jeuParam,EcranChoixChargementPartie ecranChoixChargementPartie_, File[] listeSauvegardesTmp){
		jeu = jeuParam;
		ecranChoixChargementPartie = ecranChoixChargementPartie_;
		listeSauvegardes = listeSauvegardesTmp;
	}
	
	@Override
	public void actionPerformed(ActionEvent evenement) {
		// Retrouve la source de l'action
		JCoolButton boutonClique = (JCoolButton)evenement.getSource();
		
		if (boutonClique.equals(ecranChoixChargementPartie.getChargerPartie())) // creer partie
		{
			int numeroSauvegarde = ecranChoixChargementPartie.getTable().getSelectedRow();
			File nomPartieACharger = listeSauvegardes[numeroSauvegarde];
			
			jeu.chargePartie(nomPartieACharger);
			
		}else if (boutonClique.equals(ecranChoixChargementPartie.getRetour())) // deconnection
		{
			jeu.chargerEcranChoixTypePartie();
		}
	}

}
