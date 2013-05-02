package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.JeuPanel;
import client.view.jeu.EcranFinPartie;

public class EcranFinPartieListener implements ActionListener{

	private EcranFinPartie ecranfinPartie;
	
	public EcranFinPartieListener(EcranFinPartie ecranfinPartie_){
		this.ecranfinPartie = ecranfinPartie_;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		if (event.getSource() == ecranfinPartie.getButtonQuitter() ){

			JeuPanel.getEcranJeu().cacherPlateau();
			ecranfinPartie.getJeu().chargerEcranChoixTypePartie();
		}	
	}
}
