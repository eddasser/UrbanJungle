package client.view.jeu;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;

import client.Client;
import client.JeuPanel;
import client.controller.OngletVilleListener;

import common.Constante;
import common.Joueur;
import common.Translator;
import common.partie.batiment.TypeBatiment;


/**
 * @author omar
 */
public class OngletVillePanel extends OngletPanel{
	
	public OngletVillePanel(JeuPanel jeu){
		super("Amélioration des bâtiments",jeu);
	}
	
	@Override
	public void updateContent(){
		super.updateContent();
		// Border used as padding
		Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);
		
		TypeBatiment typesBatiment[] = TypeBatiment.values();
		panelContenu.setLayout(new GridLayout(typesBatiment.length,5));
		
		panelContenu.add(new JLabel());
		panelContenu.add(new JLabel(Translator.translate("TypeBatiment")));
		panelContenu.add(new JLabel(Translator.translate("PointsVie")));
		panelContenu.add(new JLabel(Translator.translate("Revenu")));
		panelContenu.add(new JLabel(Translator.translate("NiveauSuperieur")));
		
		for (int i = 0 ; i < typesBatiment.length ; i++){
			TypeBatiment type = typesBatiment[i];
			if (type != TypeBatiment.QG){
				Client client = jeu.getClient();
				Joueur joueur = client.getJoueur();
				int niveau = joueur.getNiveauBatiment(type);
				
				JLabel labelIcon = new JLabel();
				labelIcon.setBorder(BorderFactory.createCompoundBorder(paddingBorder,paddingBorder));
				Icon icon = new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranJeu/" + type.name() + ".png");
				labelIcon.setIcon(icon);
				panelContenu.add(labelIcon);
				
				JLabel label = new JLabel(type.name());
				panelContenu.add(label);
				
				JLabel labelPtsVie = new JLabel(TypeBatiment.getPointDeVie(type,niveau) + " pts");
				panelContenu.add(labelPtsVie);
				
				JLabel labelRevenu = new JLabel(Constante.formatArgent(TypeBatiment.getRevenu(type,niveau)));
				panelContenu.add(labelRevenu);
				
				JButton buttonLevelUp = new JButton(Constante.formatArgent(TypeBatiment.getMontantLevelUp(type,niveau)));
				panelContenu.add(buttonLevelUp);
				
				buttonLevelUp.addActionListener(new OngletVilleListener(jeu,joueur,type));
			}
		}
	}
	
	@Override
	protected void paintBouton(Graphics g){
		g.fillRect(421,24,183,30);
	}
}
