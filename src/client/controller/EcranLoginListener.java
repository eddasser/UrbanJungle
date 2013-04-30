package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.JeuPanel;
import client.view.EcranLogin;
import client.view.JCoolButton;

import common.Commande;
import common.Translator;


public class EcranLoginListener implements ActionListener{
	
	private JeuPanel jeu;
	private EcranLogin ecranLogin;
	
	public EcranLoginListener(JeuPanel jeuParam,EcranLogin ecranLoginParam){
		jeu = jeuParam;
		ecranLogin = ecranLoginParam;
	}
	
	@Override
	public void actionPerformed(ActionEvent evenement){
		// Retrouve la source de l'action
		JCoolButton boutonClique = (JCoolButton)evenement.getSource();
		
		if (boutonClique.equals(ecranLogin.getConnexion())) // connection
		{
			String login = "" + ecranLogin.getZonePseudo().getText();
			String motDePasse = "" + ecranLogin.getZoneMDP().getText();
			
			if (login.compareTo("") == 0 || motDePasse.compareTo("") == 0)// si un des champ est vide ou les deux
			{
				boolean dejaAffiché = false;
				
				if (login.compareTo("") == 0 && motDePasse.compareTo("") == 0){
					dejaAffiché = true;
					jeu.notificationJoueur(Translator.translate("champLoginEtMDPVides"));
				}
				if (login.compareTo("") == 0 && !dejaAffiché)
					jeu.notificationJoueur(Translator.translate("champLoginVide"));
				else if (motDePasse.compareTo("") == 0 && !dejaAffiché) jeu.notificationJoueur(Translator.translate("champMDPVide"));
			}else{
				// appel a la fonction qui envoi le message de connection au serveur
				Object[] args = { Commande.DEMANDE_CONNEXION,login,motDePasse };
				jeu.getDialogueServeur().sendCommand(args);
			}
		}else if (boutonClique.equals(ecranLogin.getRetour())) // retour
		{
			jeu.chargerEcranChoixTypePartie();
		}else if (boutonClique.equals(ecranLogin.getCreerCompte())) // création compte
		{
			String login = "" + ecranLogin.getZonePseudo().getText();
			String motDePasse = "" + ecranLogin.getZoneMDP().getText();
			
			if (login.compareTo("") == 0 || motDePasse.compareTo("") == 0)// si un des champ est vide ou les deux
			{
				boolean dejaAffiché = false;
				
				if (login.compareTo("") == 0 && motDePasse.compareTo("") == 0){
					dejaAffiché = true;
					jeu.notificationJoueur(Translator.translate("champLoginEtMDPVides"));
				}
				if (login.compareTo("") == 0 && !dejaAffiché)
					jeu.notificationJoueur(Translator.translate("champLoginVide"));
				else if (motDePasse.compareTo("") == 0 && !dejaAffiché) jeu.notificationJoueur(Translator.translate("champMDPVide"));
			}else{
				// appel a la fonction qui envoi le message de connection au serveur
				Object[] args = { Commande.CREATION_COMPTE,login,motDePasse };
				jeu.getDialogueServeur().sendCommand(args);
			}
		}
		
	}
}
