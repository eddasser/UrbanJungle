package client.view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;

import client.JeuPanel;
import client.controller.EcranMenuMultijoueurListener;

import common.Constante;
import common.Partie;
import common.Translator;

public class EcranMenuMultijoueur extends NamedJPanel{
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	
	private JeuPanel jeu;
	
	private CopyOnWriteArrayList<Partie> listeDesParties; // liste des parties en cours envoyées par le serveur
	
	// elements constituants la vue
	private PanelAffichagePartie panelAffichagePartie;
	
	private JCoolButton creerPartie;
	private JCoolButton chargerPartie;
	private JCoolButton rejoindrePartie;
	private JCoolButton deconnexion;
	
	private JCoolButton rafraichir;
	
	public EcranMenuMultijoueur(JeuPanel jeuParam){
		super("ecranMenuMultijoueur");
		
		jeu = jeuParam;
		listeDesParties = new CopyOnWriteArrayList<Partie>();
		setLayout(null);
		
		
		// iniatialisation des composants de la vue
		panelAffichagePartie = new PanelAffichagePartie(listeDesParties,jeu);
		
		creerPartie = new JCoolButton(Translator.translate("creerPartie"));
		chargerPartie = new JCoolButton(Translator.translate("chargerPartie"));
		rejoindrePartie = new JCoolButton(Translator.translate("rejoindrePartie"));
		// rejoindrePartie.setEnabled(false); // on rend le bouton inutilisable tant qu'une partie a rejoindre n'a pas été selectionné
		deconnexion = new JCoolButton(Translator.translate("deconnexion"));
		rafraichir = new JCoolButton(Translator.translate("rafraichir"));
		
		EcranMenuMultijoueurListener ecranLoginListener = new EcranMenuMultijoueurListener(jeu,this); // ajout du listener aux boutons
		
		creerPartie.addActionListener(ecranLoginListener);
		chargerPartie.addActionListener(ecranLoginListener);
		rejoindrePartie.addActionListener(ecranLoginListener);
		deconnexion.addActionListener(ecranLoginListener);
		rafraichir.addActionListener(ecranLoginListener);
		
		// placement précis pour les composant se superposetn parfaitement a l'image chargé en fond
		// la heuteur du panel est recalculé en fonction du nb de ligne a afficher + l'entete des colonnes
		panelAffichagePartie.setBounds(110,230,473,50 * (listeDesParties.size() + 1));
		
		creerPartie.setBounds(670,230,200,50);
		chargerPartie.setBounds(670,305,200,50);
		rejoindrePartie.setBounds(670,380,200,50);
		deconnexion.setBounds(670,455,200,50);
		rafraichir.setBounds(245,528,200,25);
		
		add(panelAffichagePartie);
		add(creerPartie);
		add(chargerPartie);
		add(rejoindrePartie);
		add(deconnexion);
		add(rafraichir);
	}
	
	@Override
	public void paintComponent(Graphics g){
		try{
			Image img = null;
			img = ImageIO.read(new File("ressources/" + Translator.getLangue() + "/images/EcranMenuMultijoueur/fondEcranMenuMultijoueur.png"));
			g.drawImage(img,0,0,getWidth(),getHeight(),this);// Pour une image de fond
		}catch (IOException e){
			if (Constante.MODE_DEBUG){
				System.out.println("problème lors du chargement de l'image de fond");
				// e.printStackTrace();
			}
		}
	}
	
	
	public JCoolButton getCreerPartie(){
		return creerPartie;
	}
	
	
	public JCoolButton getChargerPartie(){
		return chargerPartie;
	}
	
	
	public JCoolButton getRejoindrePartie(){
		return rejoindrePartie;
	}
	
	
	public JCoolButton getDeconnexion(){
		return deconnexion;
	}
	
	public JCoolButton getRafraichir(){
		return rafraichir;
	}
	
	public void setListeDesParties(CopyOnWriteArrayList<Partie> listeDesParties){
		this.listeDesParties = listeDesParties;
	}
	
	public void rafraichirCadrePartie(){
		jeu.recuperationListePartie();
	}
	
}
