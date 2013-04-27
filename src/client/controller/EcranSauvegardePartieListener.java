package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.JeuPanel;
import client.view.EcranSauvegardePartie;

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
				boolean etatSauvegarde = ecranSauvegardePartie.getJeu().sauvegardePartie(ecranSauvegardePartie.getFieldSauvegardeName().getText());
				
				// on notifie au joueur si la sauvegarde a reussi ou non
				if (etatSauvegarde){ //partie sauvegardé
					jeu.notificationJoueur(Translator.translate("partieSauvegardeOK"));
				}else{ //echec de la sauvegarde
					jeu.notificationJoueur(Translator.translate("partieSauvegardeKO"));
				}
				
				// on retourne au jeu
				jeu.chargerEcranJeu();
			}
		}else if (evenement.getSource().equals(ecranSauvegardePartie.getBoutonRetour()))// retour
		{
			jeu.chargerEcranJeu();
		}
		
	}
}
