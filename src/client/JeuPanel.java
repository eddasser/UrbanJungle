package client;

import java.awt.CardLayout;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import client.controller.EcranResultatTentativeConnexionListener;
import client.controller.EcranTitreListener;
import client.view.EcranChoixTypePartie;
import client.view.EcranConnexionServeurImpossible;
import client.view.EcranConnexionServeurPossible;
import client.view.EcranLoader;
import client.view.EcranLogin;
import client.view.EcranMenuMultijoueur;
import client.view.EcranTitre;
import client.view.NamedJPanel;

import common.Constante;
import common.Partie;
import common.Translator;


/**
 * @author omar
 */
public class JeuPanel extends JPanel{
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	
	private static CardLayout cardlayout = new CardLayout(); // on crée le layout qui gere l'enchainement des écrans
	
	private static NamedJPanel ecranTitre;
	private static NamedJPanel ecranLoader;
	private static NamedJPanel ecranTestConnexionOk;
	private static NamedJPanel ecranTestConnexionKO;
	private static NamedJPanel ecranChoixTypeDePartie;
	private static NamedJPanel ecranLogin;
	private static NamedJPanel ecranMenuMultijoueur;
	
	private ServerListener dialogueServeur;
	private boolean accesServeur;
	
	public JeuPanel(){
		super(cardlayout);
		
		// création d'une instance de la classe DialogueAvec serveur fournissant une bibliothèque de fonction pour dialoguer avec le serveur
		dialogueServeur = new ServerListener(Constante.IP_SERVEUR,Constante.NUMERO_PORT_ECOUTE_PAR_DEFAUT,this);
		// de base l'acces serveur est ok, si un problème est detecté ensuite en tentant de le joindre, il passera a false
		accesServeur = true;
		
		
		/** création des écrans */
		// ecran titre
		ecranTitre = new EcranTitre(this);
		EcranTitreListener listenerEcranTitre = new EcranTitreListener(this); // on cree un listener pour cet écran
		ecranTitre.addKeyListener(listenerEcranTitre);// on ajoute le listener à la vue
		
		// ecran loader
		ecranLoader = new EcranLoader(Translator.getLangue());
		
		// ecran test connexion
		ecranTestConnexionOk = new EcranConnexionServeurPossible(Translator.getLangue());// ecran test
																							// connexion valide
		ecranTestConnexionKO = new EcranConnexionServeurImpossible(Translator.getLangue());// ecran test
																							// connexion
																							// invalide
		EcranResultatTentativeConnexionListener listenerEcranResultatTestConnexion = new EcranResultatTentativeConnexionListener(this);
		ecranTestConnexionOk.addMouseListener(listenerEcranResultatTestConnexion); // on ajoute le listener aux deux vue
		ecranTestConnexionKO.addMouseListener(listenerEcranResultatTestConnexion);
		
		
		// ecran du choix du type de partie ( solo ou multijoueur )
		ecranChoixTypeDePartie = new EcranChoixTypePartie(this);
		
		// ecran login pour multijoueur
		ecranLogin = new EcranLogin(this);
		
		// ecran du Menu Multijoueur
		ecranMenuMultijoueur = new EcranMenuMultijoueur(this);
		
		/** ajout des écrans au container du gestionnaire d'écran */
		this.add(ecranTitre,ecranTitre.getName());
		this.add(ecranLoader,ecranLoader.getName());
		this.add(ecranTestConnexionOk,ecranTestConnexionOk.getName());
		this.add(ecranTestConnexionKO,ecranTestConnexionKO.getName());
		this.add(ecranChoixTypeDePartie,ecranChoixTypeDePartie.getName());
		this.add(ecranLogin,ecranLogin.getName());
		this.add(ecranMenuMultijoueur,ecranMenuMultijoueur.getName());
		
		cardlayout.first(this); // on affiche le premier ecran, l'écran titre du jeu
		
	}
	
	public ServerListener getDialogueServeur(){
		return dialogueServeur;
	}
	
	public void setAccesServeur(boolean accesServeur){
		this.accesServeur = accesServeur;
	}
	
	/**
	 * méthode qui fait afficher au gestionnaire d'écran celui qui contient le loader, le changement d'écran est normalement assez rapide
	 * pour que cet ecran ne soit pas visible mais sur un pc plus lent il serra utile
	 **/
	public void chargerEcranLoader(){
		cardlayout.show(this,ecranLoader.getName()); // chargement de l'ecran loader
		
		String[] args = { Constante.COMMANDE_PING };
		dialogueServeur.sendCommand(args);
	}
	
	public void chargerEcranResultatTentativeConnection(){
		((EcranChoixTypePartie)ecranChoixTypeDePartie).setMultijoueurPossible(accesServeur);
		chargerEcranTestConnexion();
	}
	
	/**
	 * cette méthode se charge d'afficher un message pour avertir l'utilisateur si oui ou non le serveur est joignable pour la suite et
	 * redirige vers l'acran suivant en conséquence
	 */
	private void chargerEcranTestConnexion(){
		if (accesServeur){
			cardlayout.show(this,ecranTestConnexionOk.getName()); // chargement de l'ecran tentative de connexion ok
		}else{
			cardlayout.show(this,ecranTestConnexionKO.getName()); // chargement de l'ecran tentative de connexion echoué
		}
	}
	
	public void chargerEcranChoixTypePartie(){
		cardlayout.show(this,ecranChoixTypeDePartie.getName());
	}
	
	
	public void lancerPartieSolo(){
		// TODO Auto-generated method stub
		System.out.println("bouton lancer partie solo");
	}
	
	
	public void chargerPartieSolo(){
		// TODO Auto-generated method stub
		System.out.println("bouton charger partie solo");
	}
	
	
	public void lancerMultijoueur(){
		cardlayout.show(this,ecranLogin.getName());
	}
	
	
	public void notificationJoueur(String message){
		JOptionPane.showMessageDialog(null,message);
	}
	
	
	public void chargerEcranMenuMultijoueur(){
		// ecranMenuMultijoueur.rafraichirCadrePartie();
		cardlayout.show(this,ecranMenuMultijoueur.getName());
	}
	
	/** méthode qui permet de récupérer du serveur la liste des parties */
	public void recuperationListePartie(){
		// dialogueServeur.recupereListeParties();
	}
	
	/** methode qui va mettre a jour la liste des parties dispo sur le serveur */
	public void updateListePartie(CopyOnWriteArrayList<Partie> liste){
		// ecranMenuMultijoueur.setListeDesParties(liste);
	}
	
}
