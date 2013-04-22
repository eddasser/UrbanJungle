package client.view.jeu;

import java.awt.Graphics;

import client.JeuPanel;


/**
 * @author omar
 */
public class OngletVillePanel extends OngletPanel{
	
	public OngletVillePanel(JeuPanel jeu){
		super("Ville",jeu);
	}
	
	@Override
	protected void paintBouton(Graphics g){
		g.fillRect(421,24,183,30);
	}
}
