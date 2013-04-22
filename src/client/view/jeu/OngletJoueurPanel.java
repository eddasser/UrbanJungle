package client.view.jeu;

import java.awt.Graphics;

import client.JeuPanel;


/**
 * @author omar
 */
public class OngletJoueurPanel extends OngletPanel{
	
	public OngletJoueurPanel(JeuPanel jeu){
		super("Joueurs",jeu);
	}
	
	@Override
	protected void paintBouton(Graphics g){
		g.fillRect(90,24,183,30);
	}
}
