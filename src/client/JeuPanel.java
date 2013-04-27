package client;

import java.awt.CardLayout;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import client.controller.EcranResultatTentativeConnexionListener;
import client.controller.EcranTitreListener;
import client.view.EcranChoixTypePartie;
import client.view.EcranConnexionServeurImpossible;
import client.view.EcranConnexionServeurPossible;
import client.view.EcranCreationPartie;
import client.view.EcranLoader;
import client.view.EcranLogin;
import client.view.EcranMenuMultijoueur;
import client.view.EcranSauvegardePartie;
import client.view.EcranTitre;
import client.view.NamedJPanel;
import client.view.jeu.EcranJeu;

import common.Constante;
import common.Partie;
import common.Translator;


/**
 * @author omar
 */
public class JeuPanel extends JPanel implements Observer{
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	
	private static CardLayout cardlayout = new CardLayout(); // on crée le layout qui gere l'enchainement des écrans
	
	private static NamedJPanel ecranTitre;
	private static NamedJPanel ecranLoader;
	private static NamedJPanel ecranTestConnexionOk;
	private static NamedJPanel ecranTestConnexionKO;
	private static NamedJPanel ecranChoixTypeDePartie;
	private static NamedJPanel ecranLogin;
	private static NamedJPanel ecranMenuMultijoueur;
	private static NamedJPanel ecranCreationPartie;
	private static NamedJPanel ecranJeu;
	private static NamedJPanel ecranSauvegardePartie;
	
	private Client client;
	private ServerListener dialogueServeur;
	
	private boolean accesServeur;
	
	private GestionnaireSauvegarde gestionnaireSauvegarde;
	
	public JeuPanel(JLayeredPane aLayeredPane,Client client){
		super(cardlayout);
		this.client = client;
		// de base l'acces serveur est ok, si un problème est detecté ensuite en tentant de le joindre, il passera a false
		accesServeur = true;
		
		gestionnaireSauvegarde = new GestionnaireSauvegarde();
		
		
		/** création des écrans */
		// ecran titre
		ecranTitre = new EcranTitre(this);
		EcranTitreListener listenerEcranTitre = new EcranTitreListener(this); // on cree un listener pour cet écran
		ecranTitre.addKeyListener(listenerEcranTitre);// on ajoute le listener à la vue
		
		// ecran loader
		ecranLoader = new EcranLoader(Translator.getLangue(),this);
		
		// ecran test connexion
		// ecran test connexion valide
		ecranTestConnexionOk = new EcranConnexionServeurPossible(Translator.getLangue(),this);
		// ecran test connexion invalide
		ecranTestConnexionKO = new EcranConnexionServeurImpossible(Translator.getLangue(),this);
		
		
		EcranResultatTentativeConnexionListener listenerEcranResultatTestConnexion = new EcranResultatTentativeConnexionListener(this);
		ecranTestConnexionOk.addMouseListener(listenerEcranResultatTestConnexion); // on ajoute le listener aux deux vue
		ecranTestConnexionKO.addMouseListener(listenerEcranResultatTestConnexion);
		
		
		// ecran du choix du type de partie ( solo ou multijoueur )
		ecranChoixTypeDePartie = new EcranChoixTypePartie(this);
		
		// ecran login pour multijoueur
		ecranLogin = new EcranLogin(this);
		
		// ecran du Menu Multijoueur
		ecranMenuMultijoueur = new EcranMenuMultijoueur(this);
		
		// ecran de creation d'une partie
		ecranCreationPartie = new EcranCreationPartie(this);
		
		//ecran de sauvegarde d'une partie
		ecranSauvegardePartie = new EcranSauvegardePartie(this);
		
		ecranJeu = new EcranJeu(this,aLayeredPane);
		
		/** ajout des écrans au container du gestionnaire d'écran */
		this.add(ecranTitre,ecranTitre.getName());
		this.add(ecranLoader,ecranLoader.getName());
		this.add(ecranTestConnexionOk,ecranTestConnexionOk.getName());
		this.add(ecranTestConnexionKO,ecranTestConnexionKO.getName());
		this.add(ecranChoixTypeDePartie,ecranChoixTypeDePartie.getName());
		this.add(ecranLogin,ecranLogin.getName());
		this.add(ecranMenuMultijoueur,ecranMenuMultijoueur.getName());
		this.add(ecranCreationPartie,ecranCreationPartie.getName());
		this.add(ecranJeu,ecranJeu.getName());
		this.add(ecranSauvegardePartie,ecranSauvegardePartie.getName());
		
		cardlayout.first(this); // on affiche le premier ecran, l'écran titre du jeu
		
	}
	
	public static EcranJeu getEcranJeu(){
		return (EcranJeu)ecranJeu;
	}
	
	public void deconnexion(){
		dialogueServeur.deconnexion();
		cardlayout.show(this,ecranChoixTypeDePartie.getName());
	}
	
	public ServerListener getDialogueServeur(){
		return dialogueServeur;
	}
	
	public void setAccesServeur(boolean accesServeur){
		this.accesServeur = accesServeur;
	}
	
	public boolean getAccesServeur(){
		return accesServeur;
	}
	
	public Client getClient(){
		return client;
	}
	
	public void setClient(Client client){
		this.client = client;
	}
	
	/**
	 * méthode qui fait afficher au gestionnaire d'écran celui qui contient le loader, le changement d'écran est normalement assez rapide
	 * pour que cet ecran ne soit pas visible mais sur un pc plus lent il serra utile
	 **/
	public void chargerEcranLoader(){
		// création d'une instance de la classe DialogueAvec serveur fournissant une bibliothèque de fonction pour dialoguer avec le serveur
		if (dialogueServeur == null) dialogueServeur = new ServerListener(Constante.IP_SERVEUR,Constante.NUMERO_PORT_ECOUTE_PAR_DEFAUT,this);
		dialogueServeur.connect();
		accesServeur = dialogueServeur.isConnected();
		
		if (accesServeur){
			cardlayout.show(this,ecranLoader.getName()); // chargement de l'ecran loader
			
			String[] args = { Constante.COMMANDE_PING };
			dialogueServeur.sendCommand(args);
		}else{
			cardlayout.show(this,ecranTestConnexionKO.getName());
		}
	}
	
	public void chargerEcranResultatTentativeConnection(){
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
		chargerEcranJeu();
	}
	
	
	public void chargerPartieSolo(){
		System.out.println("bouton charger partie solo");
	}
	
	public void chargerEcranCreationPartie(){
		cardlayout.show(this,ecranCreationPartie.getName());
	}
	
	public void chargerEcranAttenteJoueur(){
		cardlayout.show(this,ecranLoader.getName());
	}
	
	public void chargerEcranJeu(){
		cardlayout.show(this,ecranJeu.getName());
		((EcranJeu)ecranJeu).afficherPlateau();
	}
	
	public void chargerEcranSauvegardePartie(){
		cardlayout.show(this,ecranSauvegardePartie.getName());
	}
	
	public void lancerMultijoueurs(){
		cardlayout.show(this,ecranLogin.getName());
	}
	
	
	public void notificationJoueur(String message){
		JOptionPane.showMessageDialog(null,message,"UrbanJungle",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void chargerEcranMenuMultijoueur(){
		((EcranMenuMultijoueur)ecranMenuMultijoueur).rafraichirCadrePartie();
		cardlayout.show(this,ecranMenuMultijoueur.getName());
	}
	
	public EcranMenuMultijoueur getEcranMenuMultijoueur(){
		return ((EcranMenuMultijoueur)ecranMenuMultijoueur);
	}
	
	/** méthode qui permet de récupérer du serveur la liste des parties */
	public void recuperationListePartie(){
		String[] args = { Constante.COMMANDE_LISTE_PARTIES };
		dialogueServeur.sendCommand(args);
	}
	
	/** methode qui va mettre a jour la liste des parties dispo sur le serveur */
	public void updateListePartie(CopyOnWriteArrayList<Partie> liste){
		// ecranMenuMultijoueur.setListeDesParties(liste);
	}
	
	@Override
	public void update(Observable o,Object arg){
		client = (Client)o;
		((EcranJeu)ecranJeu).update();
	}

	public void detruirePartie() {
		//TODO reset toutes les objet qui stock les infos de la partie car on la quitte
	}

	/** cette methode se charge de deleguer la sauvergarder l'etat de la partie courante en faisant appelle a la classe destionnaire sauvegarde
	 * 
	 * @param nomPartie, le nom choisi pour la sauvegarde par l'utilisateur dans la vue de sauvegarde de la partie
	 * @return res, boolean true si la sauvegarde s'est bien passé, false si une erreur est survenue
	 */
	public boolean sauvegardePartie(String nomSauvegarde) {
		boolean res = true;
		res = gestionnaireSauvegarde.sauvegarderPartie(client.getPartie(), nomSauvegarde);
		return res;
	}
}
