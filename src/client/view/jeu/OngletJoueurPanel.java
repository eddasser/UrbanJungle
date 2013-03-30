package client.view.jeu;

import java.awt.Graphics;


/**
 * @author omar
 */
public class OngletJoueurPanel extends OngletPanel{
	
	public OngletJoueurPanel(){
		super("Joueurs");
	}
	
	@Override
	protected void paintBouton(Graphics g){
		g.fillRect(90,24,183,30);
	}
}
