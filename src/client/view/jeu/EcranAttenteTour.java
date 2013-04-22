package client.view.jeu;

import java.awt.Graphics;

import javax.swing.JLabel;

import client.JeuPanel;

import common.Translator;

/**
 * @author omar
 */
public class EcranAttenteTour extends OngletPanel{
	
	/**
	 * @param name
	 * @param jeu
	 */
	public EcranAttenteTour(JeuPanel jeu){
		super("Attente",jeu);
		
		JLabel label = new JLabel(Translator.translate("PasAVotreTourDeJouer"));
		panelContenu.add(label);
	}
	
	@Override
	protected void paintBouton(Graphics g){
	}
	
}
