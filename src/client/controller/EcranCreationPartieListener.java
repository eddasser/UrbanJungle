package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.JeuPanel;
import client.view.EcranCreationPartie;
import client.view.JCoolButton;

import common.Constante;
import common.Translator;


public class EcranCreationPartieListener implements ActionListener{
	
	private JeuPanel jeu;
	
	private EcranCreationPartie ecranCreationPartie;
	
	public EcranCreationPartieListener(JeuPanel jeuParam,EcranCreationPartie ecranCreationPartie){
		jeu = jeuParam;
		this.ecranCreationPartie = ecranCreationPartie;
	}
	
	@Override
	public void actionPerformed(ActionEvent evenement){
		// Retrouve la source de l'action
		JCoolButton boutonClique = (JCoolButton)evenement.getSource();
		
		if (boutonClique.equals(ecranCreationPartie.getCreation())) // creation d'une partie
		{
			String nomPartie = ecranCreationPartie.getTextName().getText();
			String password = ecranCreationPartie.getTextPassword().getText();
			int nbjoueur = (int)ecranCreationPartie.getComboNbJoueur().getSelectedItem();
			
			if (nomPartie.equals("")){
				// champs nom de partie vide
				jeu.notificationJoueur(Translator.translate("champsNomPartieVide"));
			}else{
				String[] args = { Constante.COMMANDE_CREATION_PARTIE,nomPartie,password,Integer.toString(nbjoueur) };
				jeu.getDialogueServeur().sendCommand(args);
			}
		}else if (boutonClique.equals(ecranCreationPartie.getRetour()))// retour
		{
			jeu.chargerEcranChoixTypePartie();
		}
		
	}
}
