package client.view.jeu;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

import client.Client;
import client.JeuPanel;
import client.controller.OngletUniteListener;

import common.Constante;
import common.Joueur;
import common.Translator;
import common.partie.unite.TypeUnite;


/**
 * @author omar
 */
public class OngletUnitePanel extends OngletPanel{
	
	public OngletUnitePanel(JeuPanel jeu){
		super("Création d'unités",jeu);
	}
	
	@Override
	protected void updateContent(){
		super.updateContent();
		// Border used as padding
		Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);
		
		TypeUnite typesUnite[] = TypeUnite.values();
		panelContenu.setLayout(new GridLayout(typesUnite.length,1));
		for (int i = 0 ; i < typesUnite.length ; i++){
			TypeUnite type = typesUnite[i];
			Client client = jeu.getClient();
			Joueur joueur = client.getJoueur();
			int niveau = joueur.getNiveauUnite(type);
			
			JLabel label = new JLabel(type.name() + " (" + Constante.formatArgent(TypeUnite.getPrix(type,niveau)) + ")");
			label.setBorder(BorderFactory.createCompoundBorder(paddingBorder,paddingBorder));
			Icon icon = new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranJeu/" + type.name() + ".png");
			label.setIcon(icon);
			panelContenu.add(label);
			
			label.addMouseListener(new OngletUniteListener(jeu,joueur,type));
		}
	}
	
	@Override
	protected void paintBouton(Graphics g){
		g.fillRect(30,320,60,187);
	}
}
