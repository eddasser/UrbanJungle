package client.view.jeu;

import java.awt.Color;

import common.Constante;

/**
 * @author omar
 */
public class TransparentColor extends Color{
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	
	private static int alpha = 100;
	
	public final static Color transparentBlack = new TransparentColor(0,0,0);
	public final static Color transparentBlue = new TransparentColor(0,0,255);
	public final static Color transparentCyan = new TransparentColor(0,255,255);
	public final static Color transparentDarkGray = new TransparentColor(85,85,85);
	public final static Color transparentGray = new TransparentColor(30,30,30);
	public final static Color transparentGreen = new TransparentColor(0,255,0);
	public final static Color transparentLightGray = new TransparentColor(211,211,211);
	public final static Color transparentMagenta = new TransparentColor(255,0,255);
	public final static Color transparentOrange = new TransparentColor(255,165,0);
	public final static Color transparentPink = new TransparentColor(255,192,203);
	public final static Color transparentRed = new TransparentColor(255,0,0);
	public final static Color transparentWhite = new TransparentColor(255,255,255);
	public final static Color transparentYellow = new TransparentColor(255,255,0);
	
	/*
	private final static Color transparentGreen = new Color(0x8800FF00,true);// alpha=0x88, r=0x00, g=0xFF, b=0x00
	private final static Color transparentRed = new Color(0xFF,0,0,0x33); // R,G,B,Alpha
	private final static Color transparentBlue = new Color(0.0f,0.0f,1.0f,0.5f); // R,G,B,Alpha
	*/
	
	public TransparentColor(float r,float g,float b,float a){
		super(r,g,b,a);
	}
	
	public TransparentColor(float r,float g,float b){
		super(r,g,b,alpha);
	}
	
	public TransparentColor(int r,int g,int b,int a){
		super(r,g,b,a);
	}
	
	public TransparentColor(int r,int g,int b){
		super(r,g,b,alpha);
	}
	
	public TransparentColor(int rgb){
		super(rgb,true);
	}
	
	
}
