package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.JeuPanel;

import common.Joueur;
import common.Translator;
import common.partie.batiment.TypeBatiment;

/**
 * @author omar
 */
public class OngletVilleListener implements ActionListener{
	private JeuPanel jeu;
	private Joueur joueur;
	private TypeBatiment type;
	
	public OngletVilleListener(JeuPanel jeu,Joueur joueur,TypeBatiment type){
		super();
		this.jeu = jeu;
		this.joueur = joueur;
		this.type = type;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e){
		// si le joueur a les moyen, on procede à l'amelioration et on lui decrement du montant de l'amelioration
		int montantAmelioration = type.getMontantLevelUp(joueur.getNiveauBatiment(type));
		if (joueur.getArgent() >= montantAmelioration){
			joueur.incrementeNiveauBatiment(type);
			joueur.decrementArgent(montantAmelioration);
			jeu.getClient().update();
		}else{
			jeu.notificationJoueur(Translator.translate("PasAssezArgent"));
		}
	}
	
}
