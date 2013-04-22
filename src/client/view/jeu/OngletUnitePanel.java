package client.view.jeu;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

import common.Translator;
import common.partie.unite.TypeUnite;


/**
 * @author omar
 */
public class OngletUnitePanel extends OngletPanel{
	
	public OngletUnitePanel(){
		super("Création d'unités");
		
		// Border used as padding
		Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);
		
		TypeUnite typesUnite[] = TypeUnite.values();
		panelContenu.setLayout(new GridLayout(typesUnite.length,1));
		for (int i = 0 ; i < typesUnite.length ; i++){
			JLabel label = new JLabel(typesUnite[i].name());
			label.setBorder(BorderFactory.createCompoundBorder(paddingBorder,paddingBorder));
			Icon icon = new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranJeu/" + typesUnite[i].name() + ".png");
			label.setIcon(icon);
			panelContenu.add(label);
		}
	}
	
	@Override
	protected void paintBouton(Graphics g){
		g.fillRect(30,320,60,187);
	}
}
