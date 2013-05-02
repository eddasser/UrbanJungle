package common.ia;

import java.util.ArrayList;

import client.JeuPanel;

import common.Constante;
import common.Partie;


/**
 * @author omar
 */
public class JoueurIAHasard extends JoueurIA{
	
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	
	private boolean first = true; // flag permettant de faire un traitement spécial au premier tour 
	
	private ArrayList<AxeExpansion> listeAxeExpansion;
	private ArrayList<AxeAttaque> listeAxeAttaque;	
	
	private Partie partie;
	
	public JoueurIAHasard(){
		super(null,"IA","");
		listeAxeExpansion = new ArrayList<AxeExpansion>();
		listeAxeAttaque = new ArrayList<AxeAttaque>();
	}
	
	@Override
	public void jouer(Partie partie_){
				
		this.partie = partie_;
		if (first){ // au prmeier tour on cree deux axe d'expansion chargé d'assurer un peu de revenu en construisant des batiments sur la map
			//creation axe expansion gauche
			AxeExpansion axeExpansionGauche = new AxeExpansion(partie, this, getBatiments().get(0), "gauche");
			listeAxeExpansion.add(axeExpansionGauche);
			
			//creation axe expansion bas
			AxeExpansion axeExpansionBas = new AxeExpansion(partie, this, getBatiments().get(0), "bas");
			listeAxeExpansion.add(axeExpansionBas);
			
			first = false;
		}
		
		// execution des axe d'expension
		for ( AxeExpansion axeExpansion : listeAxeExpansion){
			axeExpansion.executeTour();
		}
		
		// execution des axes d'attaque
		for ( AxeAttaque axeAttaque : listeAxeAttaque){
			axeAttaque.executeTour();
		}
		System.out.println();
	}
	
	//methode permettant de savoir si on detruit le qg enemi et de lancer la fin de la partie
	public void aDetruitQG(){
		partie.passerTour();
		JeuPanel.getEcranJeu().getEcranFinPartie().setTextPartiePerdu();
		JeuPanel.getEcranJeu().afficherEcranFinPartie();
	}

	public ArrayList<AxeAttaque> getListeAxeAttaque() {
		return listeAxeAttaque;
	}
	
}
