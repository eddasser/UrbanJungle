package client.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import client.JeuPanel;

import common.Joueur;
import common.Translator;
import common.partie.plateau.Case;
import common.partie.unite.TypeUnite;
import common.partie.unite.Unite;

/**
 * @author omar
 */
public class OngletUniteListener implements MouseListener{
	private JeuPanel jeu;
	private Joueur joueur;
	private TypeUnite type;
	
	public OngletUniteListener(JeuPanel jeu,Joueur joueur,TypeUnite type){
		super();
		this.jeu = jeu;
		this.joueur = joueur;
		this.type = type;
	}
	
	@Override
	public void mouseClicked(MouseEvent e){
		if (e.getButton() == MouseEvent.BUTTON1){
			// si c'est le clic gauche
			// si le joueur a les moyen, on procede à la création de l'unité et on lui decrement du montant de d'unité
			int montant = TypeUnite.getPrix(type,joueur.getNiveauUnite(type));
			if (joueur.getArgent() >= montant){
				Case position = jeu.getClient().getPartie().getPlateau().getCases().get(5);
				position = joueur.getPositionQG();
				Unite unite = new Unite(type,position);
				joueur.ajouterUnite(unite);
				joueur.decrementArgent(montant);
				jeu.getClient().update();
			}else{
				jeu.notificationJoueur(Translator.translate("PasAssezArgent"));
			}
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e){
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
