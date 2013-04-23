package client.view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import client.JeuPanel;

import common.Constante;
import common.Translator;


public class EcranLoader extends NamedJPanel{
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	
	public EcranLoader(String langue,JeuPanel jeu){
		super("ecranLoader",jeu);
		JLabel imageDeFond = new JLabel(new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranLoader/fondEcranLoader.gif"));
		add(imageDeFond);
	}
}
