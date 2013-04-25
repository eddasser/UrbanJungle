package client.view.jeu;

import java.awt.Graphics;

import common.Constante;

import client.JeuPanel;


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
		g.fillRect(746,24,183,30);
	}
	
}
