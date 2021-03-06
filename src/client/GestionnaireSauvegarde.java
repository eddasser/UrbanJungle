package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/** cette classe va se charger de gerer la sauvegarde des parties et le chargement des parties */
public class GestionnaireSauvegarde{
	
	public boolean sauvegarderPartie(Client client,String nomSauvegarde){
		boolean res = true;
		try{
			File file = new File("sauvegardes/" + nomSauvegarde);
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			try{
				// serialisation de la partie
				oos.writeObject(client);
				oos.flush();
			}finally{
				// fermeture des flux
				try{
					oos.close();
				}finally{
					fos.close();
				}
			}
		}catch (IOException ioe){
			System.out.println("ERRUER IO LORS DE LA SAUVEGARDE");
			res = false;
			ioe.printStackTrace();
		}
		return res;
	}
	
	
	public Client chargerPartie(File nomPartieACharger){
		Client partieDeserialise = null;
		
		try{
			FileInputStream fis = new FileInputStream(nomPartieACharger);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			try{
				// desérialisation de la partie
				partieDeserialise = (Client)ois.readObject();
				
			}finally{
				// fermeture des flux
				try{
					ois.close();
				}finally{
					fis.close();
				}
			}
		}catch (IOException ioe){
			System.out.println("ERREUR IO LORS DU CHARGEMENT");
			ioe.printStackTrace();
		}catch (ClassNotFoundException e){
			System.out.println("ERREUR CLASS_NOT_FOUND LORS DU CHARGEMENT");
			e.printStackTrace();
		}
		return partieDeserialise;
	}
	
}
