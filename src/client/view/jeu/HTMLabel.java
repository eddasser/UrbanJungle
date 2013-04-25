package client.view.jeu;

import javax.swing.JLabel;

import common.Constante;

/**
 * @author omar
 */
public class HTMLabel extends JLabel{
	
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;

	public HTMLabel(String txt){
		super("<html>" + txt + "</html>");
	}
	
	public HTMLabel(String txt1,String txt2){
		super("<html>" + txt1 + " <br/> <font color=\"green\">" + txt2 + "</font></html>");
	}
}
