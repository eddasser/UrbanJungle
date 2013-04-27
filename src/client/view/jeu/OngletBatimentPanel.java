package client.view.jeu;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.border.Border;

import client.Client;
import client.JeuPanel;
import client.controller.OngletBatimentListener;

import common.Constante;
import common.Joueur;
import common.partie.batiment.TypeBatiment;


/**
 * @author omar
 */
public class OngletBatimentPanel extends OngletPanel{
	
	public OngletBatimentPanel(JeuPanel jeu){
		super("Création de bâtiments",jeu);
	}
	
	@Override
	protected void updateContent(){
		super.updateContent();
		// Border used as padding
		Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);
		
		TypeBatiment typesBatiment[] = TypeBatiment.values();
		panelContenu.setLayout(new GridLayout(typesBatiment.length - 1,1));
		for (int i = 0 ; i < typesBatiment.length ; i++){
			TypeBatiment type = typesBatiment[i];
			if (type != TypeBatiment.QG){
				Client client = jeu.getClient();
				Joueur joueur = client.getJoueur();
				int niveau = joueur.getNiveau(type);
				JLabel label = new JLabel(type.name() + " (" + Constante.formatArgent(type.getPrix(niveau)) + ")");
				label.setBorder(BorderFactory.createCompoundBorder(paddingBorder,paddingBorder));
				Icon icon = type.getIcon();
				label.setIcon(icon);
				panelContenu.add(label);
				
				label.addMouseListener(new OngletBatimentListener(jeu,joueur,type));
			}
		}
	}
	
	@Override
	protected void paintBouton(Graphics g){
		g.fillRect(30,83,60,187);
	}
	
}
