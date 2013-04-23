package client.view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import client.JeuPanel;

import common.Constante;
import common.Translator;


public class EcranConnexionServeurImpossible extends NamedJPanel{
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	
	public EcranConnexionServeurImpossible(String langue,JeuPanel jeu){
		super("ecranTestConnexionKO",jeu);
		
		JLabel imageDeFond;
		
		imageDeFond = new JLabel(new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranResultatTentativeConnexion/serveurKO.gif"));
		
		add(imageDeFond);
	}
	
}
