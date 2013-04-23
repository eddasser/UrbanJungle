package client.view.jeu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;

import javax.swing.JPanel;

import client.JeuPanel;

import common.Constante;
import common.partie.plateau.Case;
import common.partie.plateau.Plateau;

/**
 * @author omar
 */
public class EcranPlateau extends JPanel{
	private Plateau plateau;
	private final static int epaisseurCoutour = 5;
	
	public EcranPlateau(JeuPanel jeu){
		plateau = jeu.getClient().getPartie().getPlateau();
		setBounds(85 - epaisseurCoutour,60 - epaisseurCoutour,Constante.LARGEUR_PLATEAU + epaisseurCoutour,Constante.HAUTEUR_PLATEAU
				+ epaisseurCoutour);
		setOpaque(false);
	}
	
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g.setColor(Color.black);
		
		Stroke stroke = g2d.getStroke();
		g2d.setStroke(new BasicStroke(epaisseurCoutour));// trait épais
		g2d.drawRect(0,0,getWidth(),getHeight());
		g2d.setStroke(stroke);
		
		ArrayList<Case> cases = plateau.getCases();
		for (int i = 0 ; i < cases.size() ; i++){
			Case case_courante = cases.get(i);
			int x = case_courante.getX() + epaisseurCoutour / 2;
			int y = case_courante.getY() + epaisseurCoutour / 2;
			int width = 40;
			int height = 40;
			g.drawRect(x,y,width,height);
		}
	}
	
}
