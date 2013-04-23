package client;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import common.Constante;
import common.Joueur;
import common.Partie;


/**
 * @author omar
 */
public class Client{
	private Partie partie;// partie courante
	private Joueur joueur;// joueur courant
	
	public Client(){
		partie = new Partie();
		joueur = new Joueur();
		partie.addJoueur(joueur);
	}
	
	public Partie getPartie(){
		return partie;
	}
	
	public Joueur getJoueur(){
		return joueur;
	}
	
	public void setPartie(Partie partie){
		this.partie = partie;
	}
	
	public void setJoueur(Joueur joueur){
		this.joueur = joueur;
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setTitle("URBAN JUNGE " + Constante.NUMERO_DE_VERSION); // on donne un titre a la fenetre
		frame.setSize(Constante.LARGEUR_FENETRE_PRINCIPALE,Constante.HAUTEUR_FENETRE_PRINCIPALE); // on fixe sa taille
		frame.setLocationRelativeTo(null);// on la place au centre de l'écran
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // on definit l'opération par defaut de fermeture
		frame.setResizable(false);
		
		Client client = new Client();
		frame.setContentPane(new JeuPanel(frame.getLayeredPane(),client));
		frame.setVisible(true);
	}
}
