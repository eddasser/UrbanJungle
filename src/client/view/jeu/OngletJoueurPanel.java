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
import client.controller.OngletJoueurListener;

import common.Constante;
import common.Joueur;
import common.Translator;
import common.partie.unite.TypeUnite;


/**
 * @author omar
 */
public class OngletJoueurPanel extends OngletPanel{
	
	public OngletJoueurPanel(JeuPanel jeu){
		super("Amélioration des unités",jeu);
	}
	
	@Override
	public void updateContent(){
		super.updateContent();
		// Border used as padding
		Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);
		
		TypeUnite typesUnite[] = TypeUnite.values();
		panelContenu.setLayout(new GridLayout(typesUnite.length + 1,7));
		
		panelContenu.add(new JLabel());
		panelContenu.add(new JLabel(Translator.translate("TypeUnite")));
		panelContenu.add(new JLabel(Translator.translate("PointsAttaques")));
		panelContenu.add(new JLabel(Translator.translate("PointsVie")));
		panelContenu.add(new JLabel(Translator.translate("Salaire")));
		panelContenu.add(new JLabel(Translator.translate("Vitesse")));
		panelContenu.add(new JLabel(Translator.translate("NiveauSuperieur")));
		
		for (int i = 0 ; i < typesUnite.length ; i++){
			TypeUnite type = typesUnite[i];
			Client client = jeu.getClient();
			Joueur joueur = client.getJoueur();
			int niveau = joueur.getNiveauUnite(type);
			
			JLabel labelIcon = new JLabel();
			labelIcon.setBorder(BorderFactory.createCompoundBorder(paddingBorder,paddingBorder));
			Icon icon = new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranJeu/" + type.name() + ".png");
			labelIcon.setIcon(icon);
			panelContenu.add(labelIcon);
			
			JLabel labelType = new JLabel(type.name());
			panelContenu.add(labelType);
			
			
			JLabel labelPtsAttaque = new JLabel(TypeUnite.getPointAttaque(type,niveau) + " pts");
			JLabel labelPtsVie = new JLabel(TypeUnite.getPointDeVie(type,niveau) + " pts");
			JLabel labelSalaire = new JLabel(Constante.formatArgent(TypeUnite.getSalaire(type,niveau)));
			JLabel labelVitesse = new JLabel(TypeUnite.getVitesse(type,niveau) + " cases");
			
			panelContenu.add(labelPtsAttaque);
			panelContenu.add(labelPtsVie);
			panelContenu.add(labelSalaire);
			panelContenu.add(labelVitesse);
			
			JButton buttonLevelUp = new JButton(Constante.formatArgent(TypeUnite.getMontantLevelUp(type,niveau)));
			panelContenu.add(buttonLevelUp);
			
			buttonLevelUp.addActionListener(new OngletJoueurListener(jeu,joueur,type));
		}
	}
	
	@Override
	protected void paintBouton(Graphics g){
		g.fillRect(90,24,183,30);
	}
}
