package common;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

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
	
	
	public final static String MESSAGE_SEPARATOR = GENERAL_CONFIGURATION.getStringValue("MESSAGE_SEPARATOR");
	
	
	public final static String FICHIER_SAUVEGARDE_PARTIES_SERVEUR = GENERAL_CONFIGURATION.getStringValue("FICHIER_SAUVEGARDE_PARTIES_SERVEUR");
	
	
	public final static int LARGEUR_CASE = 20;
	public final static int HAUTEUR_CASE = 20;
	public final static int NB_CASES_LARGEUR_PLATEAU = 45;
	public final static int NB_CASES_HAUTEUR_PLATEAU = 26;
	public final static int LARGEUR_PLATEAU = LARGEUR_CASE * NB_CASES_LARGEUR_PLATEAU;
	public final static int HAUTEUR_PLATEAU = HAUTEUR_CASE * NB_CASES_HAUTEUR_PLATEAU;
	
	public final static int DECALAGE_PLATEAU_X = 85;
	public final static int DECALAGE_PLATEAU_Y = 60;
	
	
	public final static int ARGENT_DEPART = 20000;
	public final static double NB_CASES_DISTANCE_AVEC_UNITE_CONSTRUCTEUR_AUTORISE_POUR_CONSTRUCTION_BATIMENT = 1;
	public final static double NB_CASES_DISTANCE_AVEC_BATIMENT_AUTORISE_POUR_CREATION_UNITE = 2;
	
	
	/*******************************************/
	private final static DecimalFormatSymbols DECIMAL_FORMAT_SYMBOLS = DecimalFormatSymbols.getInstance();
	private final static DecimalFormat DECIMAL_FORMATTER = new DecimalFormat("###,###.##",DECIMAL_FORMAT_SYMBOLS);
	
	public static String formatArgent(int number){
		DECIMAL_FORMAT_SYMBOLS.setGroupingSeparator(' ');
		return DECIMAL_FORMATTER.format(number) + " $";
	}
	
	
	// liste des couleurs que l'on affiche pour differentier les batiement & unité des différents joueurs
	public final static Color[] COLORS = { Color.blue,Color.cyan,Color.darkGray,Color.gray,Color.green,Color.lightGray,Color.magenta,
			Color.orange,Color.pink,Color.red,Color.white,Color.yellow,Color.black };
}
