package client.view.jeu;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import client.JeuPanel;
import client.controller.EcranFinPartieListener;
import client.view.JCoolButton;

import common.Constante;
import common.Translator;

/**
 * @author omar
 */
public class EcranFinPartie extends JPanel{
	
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	
	private String message;
	private JLabel labelFond;
	private JCoolButton buttonQuitter;
	
	private JeuPanel jeu;
	
	
	/**
	 * @param name
	 * @param jeu
	 */
	public EcranFinPartie(JeuPanel jeu_){
		setLayout(null);
		
		labelFond = new JLabel();
		jeu = jeu_;
		
		int width = Constante.LARGEUR_FENETRE_PRINCIPALE;
		int height = Constante.HAUTEUR_FENETRE_PRINCIPALE-27;
		setBounds(0,0,width,height);
		message="<html><h1>" + Translator.translate("partieGagne") + "</h1></html>";
		labelFond = new JLabel(message,SwingConstants.CENTER);
		labelFond.setBounds(width / 4,125,width / 2,200);
		this.add(labelFond);
		
		EcranFinPartieListener listener = new EcranFinPartieListener(this);
		
		// bouton pour quitter la partie
		buttonQuitter = new JCoolButton(Translator.translate("quitterPartie"));
		buttonQuitter.setBounds(412, 400, 200, 50);
		buttonQuitter.addActionListener(listener);
		add(buttonQuitter);
	}

	@Override
	public void paintComponent(Graphics g){
		try{
			Image img = ImageIO.read(new File("ressources/" + Translator.getLangue()
					+ "/images/EcranChoixTypePartie/fondEcranChoixTypePartie.jpg"));
			g.drawImage(img,0,0,getWidth(),getHeight(),this);// Pour une image de fond
			
			buttonQuitter.setText(Translator.translate("quitterPartie"));
			
		}catch (IOException e){
			if (Constante.MODE_DEBUG){
				System.out.println("problème lors du chargement de l'image de fond");
				// e.printStackTrace();
			}
		}
	}

	public void setTextPartieGagne(){
		message="<html><h1>" + Translator.translate("partieGagne") + "</h1></html>";
		labelFond.setText(message);
		repaint();
	}

	public void setTextPartiePerdu(){
		message="<html><h1>" + Translator.translate("partiePerdu") + "</h1></html>";
		labelFond.setText(message);
		repaint();
	}
	
	public JCoolButton getButtonQuitter() {
		return buttonQuitter;
	}
	
	public JeuPanel getJeu() {
		return jeu;
	}
	
	public String getMessage() {
		return message;
	}
}
