package client.view;

import javax.swing.JPanel;

import common.Constante;

/**
 * @author omar
 */
public class NamedJPanel extends JPanel{
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	private String name;
	
	public NamedJPanel(String name){
		this.name = name;
	}
	
	@Override
	public String getName(){
		return name;
	}
	
	@Override
	public void setName(String name){
		this.name = name;
	}
	
}
