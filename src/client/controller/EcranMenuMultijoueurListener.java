package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.JeuPanel;
import client.view.EcranMenuMultijoueur;
import client.view.JCoolButton;

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
			System.out.println("bouton creer partie marche bien ");
		}else if (boutonClique.equals(ecranMenuMutlijoueur.getDeconnexion())) // deconection
		{
			System.out.println("bouton deconnection marche bien ");
		}else if (boutonClique.equals(ecranMenuMutlijoueur.getRafraichir())) // raffraichir
		{
			ecranMenuMutlijoueur.rafraichirCadrePartie();
		}
	}
}
