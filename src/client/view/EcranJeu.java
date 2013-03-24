package client.view;

import java.awt.Color;
import java.awt.Graphics;

import client.JeuPanel;

import common.Constante;


public class EcranJeu extends NamedJPanel{
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	
	
	public EcranJeu(JeuPanel jeu){
		super("ecranJeu");
		
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0,0,getWidth(),getHeight());
	}
	
}
