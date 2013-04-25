package client.view.jeu;

import java.awt.Graphics;

import client.JeuPanel;

import common.Constante;


/**
 * @author omar
 */
public class OngletMenuPanel extends OngletPanel{
	
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;

	public OngletMenuPanel(JeuPanel jeu){
		super("Menu",jeu);
	}
	
	@Override
	protected void paintBouton(Graphics g){
		g.fillRect(746,20,46,35);
		g.fillRect(791,32,95,24);
		g.fillRect(884,20,45,35);
	}
	
}
