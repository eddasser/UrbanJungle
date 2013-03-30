package client.view.jeu;

import java.awt.Graphics;


/**
 * @author omar
 */
public class OngletVillePanel extends OngletPanel{
	
	public OngletVillePanel(){
		super("Ville");
	}
	
	@Override
	protected void paintBouton(Graphics g){
		g.fillRect(421,24,183,30);
	}
}
