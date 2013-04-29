package client.view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.JeuPanel;

import common.Constante;

public class EcranConnexionServeurImpossible extends NamedJPanel{
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	
	private JPanel fondFR;
	private JPanel fondEN;
	
	public EcranConnexionServeurImpossible(String langue,JeuPanel jeu){
		super("ecranTestConnexionKO",jeu);
		
		fondFR = new JPanel();
		fondEN = new JPanel();

		JLabel imageDeFondFR;
		JLabel imageDeFondEN;
		
		imageDeFondFR = new JLabel(new ImageIcon("ressources/fr/images/EcranResultatTentativeConnexion/serveurKO.gif"));
		imageDeFondEN = new JLabel(new ImageIcon("ressources/en/images/EcranResultatTentativeConnexion/serveurKO.gif"));
		
		fondFR.add(imageDeFondFR);
		fondEN.add(imageDeFondEN);
		
		add(fondFR);
		add(fondEN);
		
		if (Constante.LANGUE_PAR_DEFAUT.equals("fr")){
			fondFR.setVisible(true);
			fondEN.setVisible(false);
		}else{
			fondFR.setVisible(false);
			fondEN.setVisible(true);
		}
	}
	
	public void changeLanguage(String langue){
		if (langue.equals("fr")){
			fondFR.setVisible(true);
			fondEN.setVisible(false);
		}else{
			fondFR.setVisible(false);
			fondEN.setVisible(true);
		} 
	}
	
}
