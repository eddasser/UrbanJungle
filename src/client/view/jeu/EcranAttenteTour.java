package client.view.jeu;

import java.awt.Graphics;

import javax.swing.JLabel;

import client.JeuPanel;

import common.Constante;
import common.Translator;

/**
 * @author omar
 */
public class EcranAttenteTour extends OngletPanel{
	
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;

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
	protected void updateContent(){
	}
	
	
	@Override
	protected void paintBouton(Graphics g){
	}
	
}
