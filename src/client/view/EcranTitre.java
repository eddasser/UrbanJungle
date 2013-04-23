package client.view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import client.JeuPanel;

import common.Constante;
import common.Translator;


public class EcranTitre extends NamedJPanel{
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	
	public EcranTitre(JeuPanel jeu){
		super("ecranTitre",jeu);
		
		setFocusable(true);
		
		JLabel imageDeFond = new JLabel(new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranTitre/fond.gif"));
		
		add(imageDeFond);
	}
}