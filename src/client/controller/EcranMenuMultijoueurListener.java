package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.JeuPanel;
import client.view.EcranMenuMultijoueur;
import client.view.JCoolButton;

import common.Commande;

public class EcranMenuMultijoueurListener implements ActionListener{
	private JeuPanel jeu;
	private EcranMenuMultijoueur ecranMenuMutlijoueur;
	
	public EcranMenuMultijoueurListener(JeuPanel jeuParam,EcranMenuMultijoueur ecranMenuMultijoueurParam){
		jeu = jeuParam;
		ecranMenuMutlijoueur = ecranMenuMultijoueurParam;
	}
	
	@Override
	public void actionPerformed(ActionEvent evenement){
		// Retrouve la source de l'action
		JCoolButton boutonClique = (JCoolButton)evenement.getSource();
		
		if (boutonClique.equals(ecranMenuMutlijoueur.getCreerPartie())) // creer partie
		{
			jeu.chargerEcranCreationPartie();
		}else if (boutonClique.equals(ecranMenuMutlijoueur.getDeconnexion())) // deconection
		{
			Object[] args = { Commande.DECONNEXION };
			jeu.getDialogueServeur().sendCommand(args);
			jeu.deconnexion();
		}else if (boutonClique.equals(ecranMenuMutlijoueur.getRafraichir())) // raffraichir
		{
			ecranMenuMutlijoueur.rafraichirCadrePartie();
		}
	}
}
