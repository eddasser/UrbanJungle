package common.partie.plateau;

import java.util.ArrayList;

import common.Constante;

public class Plateau{
	private ArrayList<Case> cases;
	
	public Plateau(){
		cases = new ArrayList<Case>();
		
		for (int x = 0 ; x < Constante.LARGEUR_PLATEAU ; x += Constante.LARGEUR_CASE){
			for (int y = 0 ; y < Constante.HAUTEUR_PLATEAU ; y += Constante.HAUTEUR_CASE){
				Case case_courante = new Case(x,y);
				cases.add(case_courante);
			}
		}
	}
	
	public ArrayList<Case> getCases(){
		return cases;
	}
	
	public void setCases(ArrayList<Case> cases){
		this.cases = cases;
	}
}
