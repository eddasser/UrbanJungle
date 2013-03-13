package client;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import common.Constante;


/**
 * @author omar
 */
public class Client{
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setTitle("URBAN JUNGE " + Constante.NUMERO_DE_VERSION); // on donne un titre a la fenetre
		frame.setSize(Constante.LARGEUR_FENETRE_PRINCIPALE,Constante.HAUTEUR_FENETRE_PRINCIPALE); // on fixe sa taille
		frame.setLocationRelativeTo(null);// on la place au centre de l'écran
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // on definit l'opération par defaut de fermeture
		frame.setResizable(false);
		
		frame.setContentPane(new JeuPanel());
		frame.setVisible(true);
	}
}
