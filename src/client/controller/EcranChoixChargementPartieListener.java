package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import common.Translator;

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
			
			if (numeroSauvegarde != -1){
				File nomPartieACharger = listeSauvegardes[numeroSauvegarde];
				jeu.chargePartie(nomPartieACharger);
			}else{
				jeu.notificationJoueur(Translator.translate("aucunePartieSelectionne"));
			}
			
		}else if (boutonClique.equals(ecranChoixChargementPartie.getRetour())) // deconnection
		{
			jeu.chargerEcranChoixTypePartie();
		}
	}

	public void setListeSauvegardes(File[] listeSauvegardes) {
		this.listeSauvegardes = listeSauvegardes;
	}
	
	

}
