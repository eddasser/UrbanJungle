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
			//TODO trouver pk sa a autant de mal a changer l'interface ( probleme rafraichissement ?,
		}
		else if (event.getSource() == menuPanel.getButtonSauvegarder() ){
			System.out.println("bouton sauvegarder actif");
			JeuPanel.getEcranJeu().afficherEcranSauvegardePartie();// on apelle la vue de sauvegarde
		}
		else if (event.getSource() == menuPanel.getButtonQuitter() ){
			System.out.println("quit");
			int reponse = JOptionPane.showConfirmDialog(menuPanel.getPanelContenu(), Translator.translate("demandeConfirmationQuitterPartie"));
			if (reponse == 0){
				menuPanel.getJeu().detruirePartie();
				menuPanel.getJeu().chargerEcranChoixTypePartie();
			}
		}
		
	}

}
