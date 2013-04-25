package client.view.jeu;

import javax.swing.JLabel;

/**
 * @author omar
 */
public class HTMLabel extends JLabel{
	public HTMLabel(String txt){
		super("<html>" + txt + "</html>");
	}
	
	public HTMLabel(String txt1,String txt2){
		super("<html>" + txt1 + " <br/> <font color=\"green\">" + txt2 + "</font></html>");
	}
}
