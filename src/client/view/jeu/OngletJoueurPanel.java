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
import client.controller.OngletJoueurListener;
import client.view.JCoolButton;

import common.Constante;
import common.Joueur;
import common.Translator;
import common.partie.unite.TypeUnite;


/**
 * @author omar
 */
public class OngletJoueurPanel extends OngletPanel{
	
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;

	public OngletJoueurPanel(JeuPanel jeu){
		super(Translator.translate("amelioration_unite"),jeu);
	}
	
	@Override
	public void updateContent(){
		super.updateContent();
		// Border used as padding
		Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);
		
		TypeUnite typesUnite[] = TypeUnite.values();
		panelContenu.setLayout(new GridLayout(typesUnite.length + 1,7));
		
		panelContenu.add(new JLabel());
		panelContenu.add(new JLabel(Translator.translate("typeUnite")));
		panelContenu.add(new JLabel(Translator.translate("pointsAttaques")));
		panelContenu.add(new JLabel(Translator.translate("pointsVie")));
		panelContenu.add(new JLabel(Translator.translate("salaire")));
		panelContenu.add(new JLabel(Translator.translate("vitesse")));
		panelContenu.add(new JLabel(Translator.translate("coutNiveauSuperieur")));
		
		for (int i = 0 ; i < typesUnite.length ; i++){
			TypeUnite type = typesUnite[i];
			Client client = jeu.getClient();
			Joueur joueur = client.getJoueur();
			int niveau = joueur.getNiveau(type);
			
			JLabel labelIcon = new JLabel();
			labelIcon.setBorder(BorderFactory.createCompoundBorder(paddingBorder,paddingBorder));
			Icon icon = new ImageIcon("ressources/" + Translator.getLangue() + "/images/EcranJeu/" + type.name() + ".png");
			labelIcon.setIcon(icon);
			panelContenu.add(labelIcon);
			
			JLabel labelType = new JLabel(Translator.translate(type.name()));
			panelContenu.add(labelType);
			
			
			JLabel labelPtsAttaque = new HTMLabel(type.getPointAttaque(niveau) + " pts ",type.getPointAttaque(niveau + 1) + " pts");
			JLabel labelPtsVie = new HTMLabel(type.getPointDeVie(niveau) + " pts",type.getPointDeVie(niveau + 1) + " pts");
			JLabel labelSalaire = new HTMLabel(Constante.formatArgent(type.getSalaire(niveau)),Constante.formatArgent(type
					.getSalaire(niveau + 1)));
			JLabel labelVitesse = new HTMLabel(type.getVitesse(niveau) + " cases",type.getVitesse(niveau + 1) + " cases");
			
			panelContenu.add(labelPtsAttaque);
			panelContenu.add(labelPtsVie);
			panelContenu.add(labelSalaire);
			panelContenu.add(labelVitesse);
			
			JCoolButton buttonLevelUp = new JCoolButton(Constante.formatArgent(type.getMontantLevelUp(niveau)));
			panelContenu.add(buttonLevelUp);
			
			buttonLevelUp.addActionListener(new OngletJoueurListener(jeu,joueur,type));
		}
		setLabelTitre(Translator.translate("amelioration_unite"));
	}
	
	@Override
	protected void paintBouton(Graphics g){
		g.fillRect(90,20,37,35);
		g.fillRect(127,30,108,24);
		g.fillRect(233,20,41,35);
	}
}
