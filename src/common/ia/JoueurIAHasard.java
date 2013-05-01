package common.ia;

import common.Constante;
import common.Partie;
import common.partie.batiment.Batiment;
import common.partie.batiment.TypeBatiment;
import common.partie.unite.TypeUnite;
import common.partie.unite.Unite;


/**
 * @author omar
 */
public class JoueurIAHasard extends JoueurIA{
	
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	
	boolean first = true;
	
	public JoueurIAHasard(){
		super(null,"IA","");
	}
	
	@Override
	public void jouer(Partie partie){
		// TODO: IA a implémenté ICI
		// faire des trucs au hasard
		
		if ( first){
			ajouterUnite(new Unite(TypeUnite.CAID, partie.getPlateau().getCases().get(900)));
			
			ajouterBatiment(new Batiment(TypeBatiment.BOOKMAKER, partie.getPlateau().getCases().get(903)));
			
			first = false;
		}
		
	}
	
}
