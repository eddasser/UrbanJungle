package client;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import common.Partie;

/** cette classe va se charger de gerer la sauvegarde des parties et le chargement des parties */
public class GestionnaireSauvegarde {
	
	public boolean sauvegarderPartie(Partie partie, String nomSauvegarde){
		boolean res = true;
		try {
			FileOutputStream fos = new FileOutputStream("sauvegarde/"+nomSauvegarde);
			ObjectOutputStream oos= new ObjectOutputStream(fos);
			
			try {
				// sérialisation de la partie
				oos.writeObject(partie); 
				oos.flush();
			} finally {
				//fermeture des flux
				try {
					oos.close();
				} finally {
					fos.close();
				}
			}
		} catch(IOException ioe) {
			System.out.println("ERRUER IO LORS DE LA SAUVEGARDE");
			res = false;
//			ioe.printStackTrace();
		}
		return res;
	}

}
