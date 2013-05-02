package common.ia;

import client.JeuPanel;

import common.Constante;
import common.ElementPlateau;
import common.Partie;
import common.partie.unite.TypeUnite;
import common.partie.unite.Unite;


/**
 * @author omar
 */
public class JoueurIAHasard extends JoueurIA{
	
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	
	boolean first = true;
	boolean QGdetruit = false;
	
	public JoueurIAHasard(){
		super(null,"IA","");
	}
	
	@Override
	public void jouer(Partie partie){
				
		if (first){
			
			for (int i =900 ; i< 1080; i++){
				ajouterUnite(new Unite(TypeUnite.CAID,200,partie.getPlateau().getCases().get(i)));
			}
			first = false;
		}
		
		int x = partie.getPlateau().getCases().get(1082).getX();
		int y = partie.getPlateau().getCases().get(1082).getY();
		
		ElementPlateau elementSurCase = partie.elementSurCase(partie.getPlateau().getCases().get(1081));

		for ( Unite unit : getUnites()){
			if ( unit.deplacementPossibleVersPosition(x, y)){
				QGdetruit = elementSurCase.attaque(unit);
			}
		}
		
		if (QGdetruit){
				partie.passerTour();
				JeuPanel.getEcranJeu().getEcranFinPartie().setTextPartiePerdu();
				JeuPanel.getEcranJeu().afficherEcranFinPartie();
		}
	}
	
}
