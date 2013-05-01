package client.view.jeu;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import client.view.RoundedPanel;

import common.Constante;
import common.ElementPlateau;
import common.Translator;
import common.TypeElementPlateau;
import common.partie.batiment.TypeBatiment;
import common.partie.unite.Unite;

/**
 * @author omar
 */
public class ToolTipInfo extends RoundedPanel{

	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	private ElementPlateau element;
	private int niveau;
	private JProgressBar progressBar;
	
	private void afficher(){
		removeAll();
		setBorder(new EmptyBorder(10,10,10,10));
		setBounds(element.getPosition().getX() + 2 * Constante.LARGEUR_CASE + Constante.DECALAGE_PLATEAU_X,element.getPosition().getY(),200,100);
		
		TypeElementPlateau type = element.getType();
		setLayout(new GridLayout(5,1));
		add(new JLabel(type.toString()));
		
		add(new JLabel(Translator.translate("PointsVie") + " : "));
		progressBar = new JProgressBar(0,type.getPointDeVie(niveau));
		progressBar.setValue(element.getPointsVie());
		progressBar.setStringPainted(true);
		add(progressBar);
		
		if (element instanceof Unite){
			afficherUnite();
		}else{
			afficherBatiment();
		}
	}
	
	private void afficherUnite(){
		add(new JLabel(Translator.translate("DeplacementRestant") + " : "));
		add(new JLabel(((Unite)element).getDeplacementRestant() + ""));
	}
	
	private void afficherBatiment(){
		TypeBatiment type = (TypeBatiment)element.getType();
		add(new JLabel(Translator.translate("Revenu") + " : "));
		add(new JLabel(Constante.formatArgent(type.getRevenu(niveau))));
	}
	
	public ElementPlateau getElement(){
		return element;
	}
	
	public void setElement(ElementPlateau element,int niveau){
		this.element = element;
		this.niveau = niveau;
		afficher();
	}
	
}
