package common.partie.batiment;


import java.io.Serializable;
import common.Constante;
import common.ElementPlateau;
import common.partie.plateau.Case;


public class Batiment extends ElementPlateau implements Serializable{

	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	
	public Batiment(TypeBatiment type,Case position){
		super(type,position);
	}
	
}
