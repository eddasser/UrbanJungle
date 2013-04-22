package client.view.jeu;

import java.awt.Graphics;

import client.JeuPanel;


/**
 * @author omar
 */
public class OngletMenuPanel extends OngletPanel{
	
	public OngletMenuPanel(JeuPanel jeu){
		super("Menu",jeu);
	}
	
	@Override
	protected void paintBouton(Graphics g){
		g.fillRect(746,24,183,30);
	}
}
