package client.view.jeu;

import java.awt.Graphics;


/**
 * @author omar
 */
public class OngletUnitePanel extends OngletPanel{
	
	public OngletUnitePanel(){
		super("Unités");
	}
	
	@Override
	protected void paintBouton(Graphics g){
		g.fillRect(30,320,60,187);
	}
}
