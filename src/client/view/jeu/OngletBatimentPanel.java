package client.view.jeu;

import java.awt.Graphics;


/**
 * @author omar
 */
public class OngletBatimentPanel extends OngletPanel{
	
	public OngletBatimentPanel(){
		super("Batiments");
	}
	
	@Override
	protected void paintBouton(Graphics g){
		g.fillRect(30,83,60,187);
	}
}
