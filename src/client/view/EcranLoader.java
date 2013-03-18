package client.view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import common.Constante;
import common.Translator;


public class EcranLoader extends NamedJPanel{
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	
	public EcranLoader(String langue){
		super("ecranLoader");
		JLabel imageDeFond = new JLabel(new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranLoader/fondEcranLoader.gif"));
		add(imageDeFond);
	}
}
