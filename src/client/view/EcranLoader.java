package client.view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;

import client.JeuPanel;
import client.view.jeu.HTMLabel;

import common.Constante;
import common.Translator;


public class EcranLoader extends NamedJPanel{
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	private JLabel labelTitre;
	
	public EcranLoader(JeuPanel jeu){
		super("ecranLoader",jeu);
		
		JLayeredPane layeredPane = new JLayeredPane();
		setLayout(new BorderLayout());
		this.add(layeredPane,BorderLayout.CENTER);
		
		JLabel fond = new JLabel(new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranLoader/fondEcranLoader.gif"));
		fond.setBounds(0,0,Constante.LARGEUR_FENETRE_PRINCIPALE,Constante.HAUTEUR_FENETRE_PRINCIPALE);
		layeredPane.add(fond,new Integer(0));
		
		
		labelTitre = new HTMLabel(SwingConstants.CENTER);
		labelTitre.setBounds(0,Constante.HAUTEUR_FENETRE_PRINCIPALE * 2 / 3,Constante.LARGEUR_FENETRE_PRINCIPALE,200);
		layeredPane.add(labelTitre,new Integer(1));
	}
	
	public void setTitre(String titre){
		labelTitre.setText("<h1>" + titre + "<h1>");
	}
	
}
