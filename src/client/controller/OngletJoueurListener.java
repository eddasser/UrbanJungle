package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.JeuPanel;

import common.Joueur;
import common.Translator;
import common.partie.unite.TypeUnite;

/**
 * @author omar
 */
public class OngletJoueurListener implements ActionListener{
	private JeuPanel jeu;
	private Joueur joueur;
	private TypeUnite type;
	
	public OngletJoueurListener(JeuPanel jeu,Joueur joueur,TypeUnite type){
		super();
		this.jeu = jeu;
		this.joueur = joueur;
		this.type = type;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e){
		// si le joueur a les moyen, on procede à l'amelioration et on lui decrement du montant de l'amelioration
		int montantAmelioration = type.getMontantLevelUp(joueur.getNiveau(type));
		if (joueur.getArgent() >= montantAmelioration){
			joueur.incrementeNiveauUnite(type);
			joueur.decrementArgent(montantAmelioration);
			jeu.getClient().update();
		}else{
			jeu.notificationJoueur(Translator.translate("PasAssezArgent"));
		}
	}
	
}
