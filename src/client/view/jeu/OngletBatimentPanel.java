package client.view.jeu;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

import common.Translator;
import common.partie.batiment.TypeBatiment;


/**
 * @author omar
 */
public class OngletBatimentPanel extends OngletPanel{
	
	public OngletBatimentPanel(){
		super("Création de batiments");
		
		// Border used as padding
		Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);
		
		TypeBatiment typesBatiment[] = TypeBatiment.values();
		panelContenu.setLayout(new GridLayout(typesBatiment.length - 1,1));
		for (int i = 0 ; i < typesBatiment.length ; i++){
			if (typesBatiment[i] != TypeBatiment.QG){
				JLabel label = new JLabel(typesBatiment[i].name());
				label.setBorder(BorderFactory.createCompoundBorder(paddingBorder,paddingBorder));
				Icon icon = new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranJeu/" + typesBatiment[i].name() + ".png");
				label.setIcon(icon);
				panelContenu.add(label);
			}
		}
	}
	
	@Override
	protected void paintBouton(Graphics g){
		g.fillRect(30,83,60,187);
	}
}
