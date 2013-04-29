package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.JeuPanel;
import client.view.jeu.EcranSauvegardePartie;

import common.Translator;

public class EcranSauvegardePartieListener implements ActionListener{

	private JeuPanel jeu;
	
	private EcranSauvegardePartie ecranSauvegardePartie;
	
	public EcranSauvegardePartieListener(JeuPanel jeuParam,EcranSauvegardePartie ecranSauvegardePartie_){
		jeu = jeuParam;
		this.ecranSauvegardePartie = ecranSauvegardePartie_;
	}
	
	@Override
	public void actionPerformed(ActionEvent evenement){
		
		if (evenement.getSource().equals(ecranSauvegardePartie.getBoutonSauvegarder())){ // sauvegarde d'une partie
			
			String nomPartie = ecranSauvegardePartie.getFieldSauvegardeName().getText();
			
			if (nomPartie.equals("")){
				// champs nom de partie vide
				jeu.notificationJoueur(Translator.translate("champsNomSauvegardeVide"));
			}else{
				// on apelle la methode sauvegarde dans Jeu panel
				jeu.sauvegardePartie(ecranSauvegardePartie.getFieldSauvegardeName().getText());
			}	
				// on retourne au jeu
				JeuPanel.getEcranJeu().cacherEcranSauvegardePartie();
		}else if (evenement.getSource().equals(ecranSauvegardePartie.getBoutonRetour()))// retour
		{
			JeuPanel.getEcranJeu().cacherEcranSauvegardePartie();
		}
		
	}
}
