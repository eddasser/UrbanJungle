package client.view.jeu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import common.Constante;

/**
 * @author omar
 */
public class EcranAffichageDeplacement extends JPanel{
	
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	
	private final static int LARGEUR_FLECHE = 6;
	private boolean deplacementAutorise;
	
	public EcranAffichageDeplacement(){
		setBounds(Constante.DECALAGE_PLATEAU_X,Constante.DECALAGE_PLATEAU_Y,Constante.LARGEUR_PLATEAU,Constante.HAUTEUR_PLATEAU);
		setOpaque(false);
		deplacementAutorise = true;
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawArrow(g,x1 + Constante.LARGEUR_CASE / 2,y1 + Constante.HAUTEUR_CASE / 2,x2,y2);
	}
	
	void drawArrow(Graphics g,int x1,int y1,int x2,int y2){
		Graphics2D g2d = (Graphics2D)g.create();
		
		g2d.setStroke(new BasicStroke(3));
		if (deplacementAutorise){
			g2d.setColor(Color.yellow);
		}else{
			g2d.setColor(Color.red);
		}
		
		double dx = x2 - x1, dy = y2 - y1;
		double angle = Math.atan2(dy,dx);
		int len = (int)Math.sqrt(dx * dx + dy * dy);
		AffineTransform at = AffineTransform.getTranslateInstance(x1,y1);
		at.concatenate(AffineTransform.getRotateInstance(angle));
		g2d.transform(at);
		
		g2d.drawLine(0,0,len,0);
		g2d.fillPolygon(new int[] { len,len - LARGEUR_FLECHE,len - LARGEUR_FLECHE,len },new int[] { 0,-LARGEUR_FLECHE,LARGEUR_FLECHE,0 },4);
	}
	
	
	public void setPositionUnite(int x,int y){
		x1 = x;
		y1 = y;
		repaint();
	}
	
	public void setPositionSouris(int x,int y){
		x2 = x;
		y2 = y;
		repaint();
	}
	
	public void setDeplacementAutorise(boolean deplacementAutorise){
		this.deplacementAutorise = deplacementAutorise;
	}
	
}
