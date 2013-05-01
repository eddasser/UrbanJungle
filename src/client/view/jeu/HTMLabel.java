package client.view.jeu;

import javax.swing.JLabel;

import common.Constante;

/**
 * @author omar
 */
public class HTMLabel extends JLabel{
	
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	
	public HTMLabel(){
		super();
	}
	
	public HTMLabel(int alignment){
		super("",alignment);
	}
	
	public HTMLabel(String text){
		setText(text);
	}
	
	public HTMLabel(String text,int alignment){
		this(alignment);
		setText(text);
	}
	
	public HTMLabel(String txt1,String txt2){
		super("<html>" + txt1 + " <br/> <font color=\"green\">" + txt2 + "</font></html>");
	}
	
	@Override
	public void setText(String text){
		super.setText("<html>" + text + "</html>");
	}
}
