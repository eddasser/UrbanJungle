package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.Constante;


/**
 * @author omar
 */
public class DBConnexion{
	private static Connection connection;
	private static boolean connected = false;
	
	
	public DBConnexion(){
		DBConnexion.connect();
	}
	
	public static void connect(){
		if (!connected){
			/******************* ouverture de la connection *************************/
			String host = Constante.GENERAL_CONFIGURATION.getStringValue("DB_HOST");
			String port = Constante.GENERAL_CONFIGURATION.getStringValue("DB_PORT");
			String name = Constante.GENERAL_CONFIGURATION.getStringValue("DB_NAME");
			String user = Constante.GENERAL_CONFIGURATION.getStringValue("DB_USERNAME");
			String passwd = Constante.GENERAL_CONFIGURATION.getStringValue("DB_USERPASSWORD");
			String url = "jdbc:mysql://" + host + ":" + port + "/" + name;
			
			connection = null;
			try{
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(url,user,passwd);
				connected = true;
				System.out.println("connection a la base effective !\n");
			}catch (Exception e){
				System.out.println("Probléme de connection avec la base de donnée");
				// e.printStackTrace();
			}
		}
	}
	
	/** methode chargée de fermer la connection avec la base */
	public static void closeconnection(){
		if (connected){
			/******************* fermeture de la connection *************************/
			try{
				connection.close();
			}catch (SQLException e){
				System.out.println("la fermeture de la connection n'a pas fonctionné");
			}
		}
	}
	
	
	/** méthode charger de verifier si le login et le mot de passe passé en parametre sont corrects */
	public static boolean verificationIdentifiantsConnection(String login,String mdp){
		connect();
		
		boolean resultat = false;
		
		String queryVerificationExistanceCompte = "SELECT * FROM compte where login ='" + login + "' and mdp='" + mdp + "'";
		
		ResultSet compte = null;
		Statement stmtCompte = null;
		
		try{
			stmtCompte = connection.createStatement();
			compte = stmtCompte.executeQuery(queryVerificationExistanceCompte);
			
			compte.last();
			// on récupère le numéro de la ligne
			int nombreLignes = compte.getRow();
			
			if (nombreLignes == 1) resultat = true;
		}catch (SQLException e){
			System.out.println("SQLException: probleme d'execution de la requete de verification des identifiants");
			// e.printStackTrace();
		}
		return resultat;
	}
	
	
	/**
	 * méthode qui creer un compte dans la base si un compte n'existe pas deja pour le login demandé
	 * 
	 * @return true si le compte a pu etre crée
	 **/
	public static boolean creationDeCompte(String login,String mdp){
		connect();
		
		String queryVerificationExistanceCompte = "SELECT * FROM compte where login ='" + login + "' and mdp='" + mdp + "'";
		
		ResultSet compte = null;
		Statement stmtCompte = null;
		
		try{
			stmtCompte = connection.createStatement();
			compte = stmtCompte.executeQuery(queryVerificationExistanceCompte);
			
			if (compte.next()) // si on a deja un resultat alors le mot de passe existe deja et il faut en choisir un autre
				return false;
			else{
				String queryCreationCompte = "INSERT INTO compte (login ,mdp)VALUES ('" + login + "', '" + mdp + "')";
				stmtCompte.executeUpdate(queryCreationCompte);
				return true;
			}
		}catch (SQLException e){
			System.out.println("SQLException: probleme d'execution de la requete de creation de compte");
			return false;
			// e.printStackTrace();
		}
	}
	
	public static boolean isDataBaseAccessible(){
		connect();
		return connected;
	}
	
	public static boolean isConnected(){
		return connected;
	}
	
}
