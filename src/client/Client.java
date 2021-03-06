package client;

import java.io.Serializable;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.ColorUIResource;

import client.controller.FenetreListener;

import common.Constante;
import common.Joueur;
import common.Partie;
import common.Translator;


/**
 * @author omar
 */
public class Client extends Observable implements Serializable{
	
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	
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
		update();
	}
	
	public void setJoueur(Joueur joueur){
		this.joueur = joueur;
		update();
	}
	
	public void update(){
		setChanged();
		notifyObservers();
	}
	
	public static void main(String[] args){
		UIManager.put("OptionPane.background",new ColorUIResource(148,171,196));
		UIManager.put("Panel.background",new ColorUIResource(148,171,196));
		UIManager.put("OptionPane.informationIcon",new ImageIcon("ressources/" + Translator.getLangue() + "/images/icon.png"));
		
		JFrame frame = new JFrame();
		frame.setTitle("URBAN JUNGE " + Constante.NUMERO_DE_VERSION); // on donne un titre a la fenetre
		frame.setSize(Constante.LARGEUR_FENETRE_PRINCIPALE,Constante.HAUTEUR_FENETRE_PRINCIPALE); // on fixe sa taille
		frame.setLocationRelativeTo(null);// on la place au centre de l'écran
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // on definit l'opération par defaut de fermeture
		frame.setResizable(false);
		
		Client client = new Client();
		JeuPanel jeuPanel = new JeuPanel(frame.getLayeredPane(),client);
		frame.addWindowListener(new FenetreListener(jeuPanel));
		client.addObserver(jeuPanel);
		frame.setContentPane(jeuPanel);
		frame.setVisible(true);
	}
}
