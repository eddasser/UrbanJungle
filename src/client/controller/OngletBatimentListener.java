package client.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import client.JeuPanel;
import client.view.jeu.EcranJeu;

import common.Constante;
import common.Joueur;
import common.Translator;
import common.partie.batiment.TypeBatiment;

/**
 * @author omar
 */
public class OngletBatimentListener implements MouseListener{
	private JeuPanel jeu;
	private Joueur joueur;
	private TypeBatiment type;
	
	public OngletBatimentListener(JeuPanel jeu,Joueur joueur,TypeBatiment type){
		super();
		this.jeu = jeu;
		this.joueur = joueur;
		this.type = type;
	}
	
	@Override
	public void mouseClicked(MouseEvent e){
	}
	
	@Override
	public void mousePressed(MouseEvent e){
		if (e.getButton() == MouseEvent.BUTTON1){
			// si c'est le clic gauche
			// si le joueur a les moyen, on active le mode creation batiment de la vue
			int montant = type.getPrix(joueur.getNiveauBatiment(type));
			if (joueur.getArgent() >= montant){
				EcranJeu ecranJeu = JeuPanel.getEcranJeu();
				ecranJeu.afficherModeCreation(type);
				ecranJeu.setPositionLabelConstructionBatiment(e.getXOnScreen() - Constante.DECALAGE_PLATEAU_X - 2 * Constante.LARGEUR_CASE,
						e.getYOnScreen() - Constante.DECALAGE_PLATEAU_Y - 2 * Constante.HAUTEUR_CASE);
				jeu.getClient().update();
			}else{
				jeu.notificationJoueur(Translator.translate("PasAssezArgent"));
			}
		}
	}
	
	
	@Override
	public void mouseReleased(MouseEvent e){
	}
	
	
	@Override
	public void mouseEntered(MouseEvent e){
	}
	
	
	@Override
	public void mouseExited(MouseEvent e){
	}
	
}
