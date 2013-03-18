package client.view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import common.Constante;
import common.Translator;


public class EcranConnexionServeurPossible extends NamedJPanel{
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	
	public EcranConnexionServeurPossible(String langue){
		super("ecranTestConnexionOk");
		
		JLabel imageDeFond = new JLabel(new ImageIcon("ressources/" + Translator.getLangue()
				+ "/images/EcranResultatTentativeConnexion/serveurOK.gif"));
		
		add(imageDeFond);
	}
}
