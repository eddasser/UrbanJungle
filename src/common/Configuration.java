package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class Configuration{
	private String filename;
	private HashMap<String,String> config;
	
	private org.jdom2.Document document;
	private Element racine;
	
	
	public Configuration(String nf){
		setFilename(nf);
	}
	
	public void setFilename(String nf){
		filename = System.getProperty("user.dir") + System.getProperty("file.separator") + nf;
		config = new HashMap<String,String>();
		
		load();
	}
	
	@Override
	public String toString(){
		StringBuffer conf = new StringBuffer();
		
		conf.append("***Configuration :  " + filename + " ***\n");
		Set<String> set = config.keySet();
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()){
			String key = iterator.next();
			String value = config.get(key);
			conf.append(key + " : " + value + "\n");
		}
		
		return conf.toString();
	}
	
	/*
	 * methode qui charge a partir du fichier la configuration 
	 */
	private void load(){
		File fi = new File(filename);
		if (!fi.exists()){
			try{
				save();// permet de cree un fichier vide
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
		InputStream in = null;
		try{
			in = new FileInputStream(filename);
		}catch (FileNotFoundException e1){
			e1.printStackTrace();
		}
		
		// On crée une instance de SAXBuilder
		SAXBuilder sxb = new SAXBuilder();
		try{
			// On crée un nouveau document JDOM avec en argument le fichier XML
			// Le parsing est terminé ;)
			document = sxb.build(in);
		}catch (Exception e){
			e.printStackTrace();
		}
		
		// On initialise un nouvel élément racine avec l'élément racine du document.
		if (document != null){
			racine = document.getRootElement();
			
			List<Element> listeElement = racine.getChildren();
			Iterator<Element> i = listeElement.iterator();
			while (i.hasNext()){
				Element courant = i.next();
				String cle = courant.getChildText("name");
				String valeur = courant.getChildText("value");
				
				config.put(cle,valeur);
			}
		}
		
	}
	
	/*
	 * methode qui sauvegarde dans le fichier la configuration courante
	 */
	public void save(){
		racine = new Element("configuration");
		document = new org.jdom2.Document(racine);
		
		Set<String> set = config.keySet();
		Iterator<String> iterator = set.iterator();
		
		while (iterator.hasNext()){
			String cle = iterator.next();
			String val = config.get(cle);
			
			
			Element element_nom = new Element("name");
			element_nom.setText(cle);
			Element element_valeur = new Element("value");
			element_valeur.setText(val);
			
			Element element_parametre = new Element("parameter");
			element_parametre.addContent(element_nom);
			element_parametre.addContent(element_valeur);
			
			racine.addContent(element_parametre);
		}
		/*
		 * ecriture dans le fichier
		 */
		try{
			// On utilise ici un affichage classique avec getPrettyFormat()
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			// Remarquez qu'il suffit simplement de créer une instance de FileOutputStream
			// avec en argument le nom du fichier pour effectuer la sérialisation.
			sortie.output(document,new FileOutputStream(filename,false));
		}catch (java.io.IOException e){
			e.printStackTrace();
		}
	}
	
	
	/*
	 * ******************************************************************************
	 * ******************************************************************************
	 * ******************************************************************************
	 * ******************************************************************************
	 * ******************************************************************************
	 * LISTE DE METHODES PERMETTANT D'ACCEDER AUX DIFFERENTS PARAMETRES DE LA CONFIGURATION COURANTE
	 */
	public String getStringValue(String param){
		return getStringValue(param,"");
	}
	
	public String getStringValue(String param,String defaut){
		String val = defaut;
		
		if (config.containsKey(param)){
			val = config.get(param);
		}
		
		return val;
	}
	
	
	public Boolean getBooleanValue(String param){
		return getBooleanValue(param,false);
	}
	
	public Boolean getBooleanValue(String param,Boolean defaut){
		Boolean val = defaut;
		
		if (config.containsKey(param)){
			if (config.get(param).equals("true") || config.get(param).equals("1")){
				val = true;
			}
		}
		
		return val;
	}
	
	
	public int getIntValue(String param){
		return getIntValue(param,-1);
	}
	
	public int getIntValue(String param,int defaut){
		int val = defaut;
		
		if (config.containsKey(param)){
			val = Integer.parseInt(config.get(param));
		}
		
		return val;
	}
	
	
	public String getStringKey(String val){
		String key = "";
		
		if (config.containsValue(val)){
			Set<String> set = config.keySet();
			Iterator<String> iterator = set.iterator();
			while (iterator.hasNext()){
				String courant = iterator.next();
				if (config.get(courant).equals(val)){
					key = courant;
				}
			}
		}
		
		return key;
	}
	
	public int getIntKey(String val){
		return Integer.parseInt(getStringKey(val));
	}
	
	public void set(String cle,String valeur){
		if (config.containsKey(cle)){
			config.remove(cle);
		}
		config.put(cle,valeur);
	}
	
	public void set(String cle,int valeur){
		set(cle,Integer.toString(valeur));
	}
}
