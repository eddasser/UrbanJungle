package client.view;

import javax.swing.JPanel;

import client.JeuPanel;

import common.Constante;

/**
 * @author omar
 */
public class NamedJPanel extends JPanel{
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	private String name;
	protected JeuPanel jeu;
	
	public NamedJPanel(String name,JeuPanel jeu){
		this.name = name;
		this.jeu = jeu;
	}
	
	@Override
	public String getName(){
		return name;
	}
	
	public JeuPanel getJeu(){
		return jeu;
	}
	
	@Override
	public void setName(String name){
		this.name = name;
	}
	
	public void setJeu(JeuPanel jeu){
		this.jeu = jeu;
	}
	
}
