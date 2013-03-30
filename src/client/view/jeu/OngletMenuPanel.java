package client.view.jeu;

import java.awt.Graphics;


/**
 * @author omar
 */
public class OngletMenuPanel extends OngletPanel{
	
	public OngletMenuPanel(){
		super("Menu");
	}
	
	@Override
	protected void paintBouton(Graphics g){
		g.fillRect(746,24,183,30);
	}
}
