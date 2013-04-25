package common.partie.plateau;

import java.util.ArrayList;

import common.Constante;

public class Plateau{
	private ArrayList<Case> cases;
	
	public Plateau(){
		cases = new ArrayList<Case>();
		
		for (int y = 0 ; y < Constante.HAUTEUR_PLATEAU ; y += Constante.HAUTEUR_CASE){
			for (int x = 0 ; x < Constante.LARGEUR_PLATEAU ; x += Constante.LARGEUR_CASE){
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
	
	public Case getCasePlusProche(int x,int y){
		Case casePlusProche = cases.get(0);
		double distancePlusProche = casePlusProche.getDistance(x,y);
		
		for (int i = 1 ; i < cases.size() ; i++){
			Case caseCourante = cases.get(i);
			double distanceCourante = caseCourante.getDistance(x,y);
			if (distanceCourante < distancePlusProche && (caseCourante.getX() < x && caseCourante.getY() < y)){
				casePlusProche = cases.get(i);
				distancePlusProche = distanceCourante;
			}
		}
		
		return casePlusProche;
	}
}
