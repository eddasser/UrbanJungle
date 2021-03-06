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
import client.controller.OngletVilleListener;
import client.view.JCoolButton;

import common.Constante;
import common.Joueur;
import common.Translator;
import common.partie.batiment.TypeBatiment;


/**
 * @author omar
 */
public class OngletVillePanel extends OngletPanel{
	
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;

	public OngletVillePanel(JeuPanel jeu){
		super(Translator.translate("amelioration_batiment"),jeu);
	}
	
	@Override
	public void updateContent(){
		super.updateContent();
		// Border used as padding
		Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);
		
		TypeBatiment typesBatiment[] = TypeBatiment.values();
		panelContenu.setLayout(new GridLayout(typesBatiment.length + 1,5));
		
		panelContenu.add(new JLabel());
		panelContenu.add(new JLabel(Translator.translate("typeBatiment")));
		panelContenu.add(new JLabel(Translator.translate("pointsVie")));
		panelContenu.add(new JLabel(Translator.translate("revenu")));
		panelContenu.add(new JLabel(Translator.translate("coutNiveauSuperieur")));
		
		for (int i = 0 ; i < typesBatiment.length ; i++){
			TypeBatiment type = typesBatiment[i];
			Client client = jeu.getClient();
			Joueur joueur = client.getJoueur();
			int niveau = joueur.getNiveau(type);
			
			JLabel labelIcon = new JLabel();
			labelIcon.setBorder(BorderFactory.createCompoundBorder(paddingBorder,paddingBorder));
			Icon icon = new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranJeu/" + type.name() + ".png");
			labelIcon.setIcon(icon);
			panelContenu.add(labelIcon);
			
			JLabel label = new JLabel(Translator.translate(type.name()));
			panelContenu.add(label);
			
			JLabel labelPtsVie = new HTMLabel(type.getPointDeVie(niveau) + " pts",type.getPointDeVie(niveau + 1) + " pts");
			panelContenu.add(labelPtsVie);
			
			JLabel labelRevenu = new HTMLabel(Constante.formatArgent(type.getRevenu(niveau)),Constante.formatArgent(type.getRevenu(niveau + 1)));
			panelContenu.add(labelRevenu);
			
			JCoolButton buttonLevelUp = new JCoolButton(Constante.formatArgent(type.getMontantLevelUp(niveau)));
			panelContenu.add(buttonLevelUp);
			
			buttonLevelUp.addActionListener(new OngletVilleListener(jeu,joueur,type));
		}
		setLabelTitre(Translator.translate("amelioration_batiment"));
	}
	
	@Override
	protected void paintBouton(Graphics g){
		g.fillRect(421,20,46,35);
		g.fillRect(467,32,95,24);
		g.fillRect(558,20,47,35);
	}
}
