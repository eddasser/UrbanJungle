package common;

/**
 * @author omar
 */
public class Constante{
	// fichier de configuration a partir duquel on recupere toutes les autres constantes
	public final static Configuration GENERAL_CONFIGURATION = new Configuration("ressources/config.xml");
	
	// taille de la fenetre de jeu
	public final static int LARGEUR_FENETRE_PRINCIPALE = GENERAL_CONFIGURATION.getIntValue("LARGEUR_FENETRE_PRINCIPALE");
	public final static int HAUTEUR_FENETRE_PRINCIPALE = GENERAL_CONFIGURATION.getIntValue("HAUTEUR_FENETRE_PRINCIPALE");
	
	// langue par defaut
	public final static String LANGUE_PAR_DEFAUT = GENERAL_CONFIGURATION.getStringValue("LANGUE_PAR_DEFAUT");
	
	// numéro de la version de developpement ( servira principalement pour les numéros de version lors de sérialisation si nous l'utilisons)
	public final static int NUMERO_DE_VERSION = GENERAL_CONFIGURATION.getIntValue("NUMERO_DE_VERSION");
	
	// sert a activer ou desactiver le mode debug
	public final static boolean MODE_DEBUG = GENERAL_CONFIGURATION.getBooleanValue("MODE_DEBUG");
	
	// informations sur le serveur
	public final static String IP_SERVEUR = GENERAL_CONFIGURATION.getStringValue("IP_SERVEUR");
	public final static int NUMERO_PORT_ECOUTE_PAR_DEFAUT = GENERAL_CONFIGURATION.getIntValue("NUMERO_PORT_ECOUTE_PAR_DEFAUT");
	
	
}