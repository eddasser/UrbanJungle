package common;

import javax.swing.Icon;

/**
 * @author omar
 */
public interface TypeElementPlateau{
	
	public Icon getIcon();
	
	public Icon getIconMin();
	
	public int getNiveauBase();
	
	public int getPointDeVie(int niveau);
	
	public int getPrix(int niveau);
	
	public int getMontantLevelUp(int niveau);
	
}
