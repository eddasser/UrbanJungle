package common.partie.plateau;

import java.io.Serializable;

import common.Constante;

public class Case implements Serializable{
	
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	private int x;
	private int y;
	
	public Case(int x,int y){
		super();
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public double getDistance(int x1,int y1){
		return Math.sqrt((x1 - x) * (x1 - x) + (y1 - y) * (y1 - y));
	}
	
	public double getDistance(Case position){
		return getDistance(position.getX(),position.getY());
	}
}
