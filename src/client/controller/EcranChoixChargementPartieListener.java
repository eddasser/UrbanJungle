package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import client.JeuPanel;
import client.view.EcranChoixChargementPartie;
import client.view.JCoolButton;

public class EcranChoixChargementPartieListener implements ActionListener{

	private JeuPanel jeu;
	private EcranChoixChargementPartie ecranChoixChargementPartie;
	
	private ArrayList<String> listeSauvegardes;
	
	public EcranChoixChargementPartieListener(JeuPanel jeuParam,EcranChoixChargementPartie ecranChoixChargementPartie_, ArrayList<String> listeSauvegardes2){
		jeu = jeuParam;
		ecranChoixChargementPartie = ecranChoixChargementPartie_;
		listeSauvegardes = listeSauvegardes2;
	}
	
	@Override
	public void actionPerformed(ActionEvent evenement) {
		// Retrouve la source de l'action
		JCoolButton boutonClique = (JCoolButton)evenement.getSource();
		
		if (boutonClique.equals(ecranChoixChargementPartie.getChargerPartie())) // creer partie
		{
			int numeroSauvegarde = ecranChoixChargementPartie.getTable().getSelectedRow();
			String nomPartieACharger = listeSauvegardes.get(numeroSauvegarde);
			
			jeu.chargePartie(nomPartieACharger);
			
		}else if (boutonClique.equals(ecranChoixChargementPartie.getRetour())) // deconnection
		{
			jeu.chargerEcranChoixTypePartie();
		}
	}

}
