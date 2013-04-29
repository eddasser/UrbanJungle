package client.view.jeu;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import client.JeuPanel;

import common.Constante;
import common.Translator;

/**
 * @author omar
 */
public class EcranAttenteTour extends JPanel{
	
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;

	/**
	 * @param name
	 * @param jeu
	 */
	public EcranAttenteTour(JeuPanel jeu){
		// super("Attente",jeu);
		setLayout(null);
		
		int width = 995;
		int height = 585;
		setBounds(0,0,width,height);
		
		JLabel label = new JLabel("<html><h1>" + Translator.translate("PasAVotreTourDeJouer") + "</h1></html>",SwingConstants.CENTER);
		label.setBounds(width / 4,85,width / 2,200);
		this.add(label);
		setOpaque(false);
	}
	
	@Override
	public void paintComponent(Graphics g){
		Color backgroundColor = Color.GRAY;
		
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.4f));
		// g2d.setColor(Color.yellow);
		// g2d.fillOval(point.x,point.y,120,60);
		g2d.setColor(backgroundColor);
		g2d.fillRect(70,50,getWidth(),getHeight());
	}
	
	
}
