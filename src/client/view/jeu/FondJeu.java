package client.view.jeu;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import common.Constante;
import common.Translator;

/**
 * @author omar
 */
public class FondJeu extends JPanel{
	
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	private Image imgFond;
	
	public FondJeu(){
		setBounds(0,0,Constante.LARGEUR_FENETRE_PRINCIPALE,Constante.HAUTEUR_FENETRE_PRINCIPALE - 20);
		setOpaque(false);
		try{
			imgFond = ImageIO.read(new File("ressources/" + Translator.getLangue() + "/images/EcranJeu/fond.gif"));
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(imgFond,0,0,getWidth(),getHeight(),this);// Pour une image de fond
	}
	
}
