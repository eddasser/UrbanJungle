package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import common.Translator;

import client.JeuPanel;
import client.view.jeu.OngletMenuPanel;

public class OngletMenuPanelListener implements ActionListener{
	private OngletMenuPanel menuPanel;

	public OngletMenuPanelListener(OngletMenuPanel menuPanel_){
		this.menuPanel = menuPanel_;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		if (event.getSource() == menuPanel.getBoutonApply() ){
			Translator.changeLanguage(((String)menuPanel.getCombo().getSelectedItem()).substring(0, 2)); 
			JeuPanel.changeLanguage(((String)menuPanel.getCombo().getSelectedItem()).substring(0, 2));
			
			JeuPanel.getEcranJeu().update(); //maj de l'interface
			JeuPanel.getEcranJeu().repaint();
		}
		else if (event.getSource() == menuPanel.getButtonSauvegarder() ){
			JeuPanel.getEcranJeu().afficherEcranSauvegardePartie();// on apelle la vue de sauvegarde
		}
		else if (event.getSource() == menuPanel.getButtonQuitter() ){

			int reponse = JOptionPane.showConfirmDialog(menuPanel.getPanelContenu(), Translator.translate("demandeConfirmationQuitterPartie"));
			if (reponse == 0){
				
				if (menuPanel.getJeu().getClient().getPartie().isSolo()){
					JeuPanel.getEcranJeu().cacherPlateau();
					menuPanel.getJeu().chargerEcranChoixTypePartie();
				}else{
					JeuPanel.getEcranJeu().cacherPlateau();
					menuPanel.getJeu().chargerEcranMenuMultijoueur();
				}
				
			}
		}	
	}
}
