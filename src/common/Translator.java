package common;

/**
 * @author omar
 */
public class Translator{
	private static String CURRENT_LANGUE = Constante.LANGUE_PAR_DEFAUT;
	/*
	 * Fichier de configuration de langue
	 */
	private final static Configuration TRANSLATE_TABLE = new Configuration("ressources/" + CURRENT_LANGUE + "/translate.xml");
	
	public static String translate(String message){
		return TRANSLATE_TABLE.getStringValue(message,message);
	}
	
	public final static void setLangue(String code_langue){
		TRANSLATE_TABLE.setFilename("ressources/" + code_langue + "/translate.xml");
	}
	
	public final static String getLangue(){
		return CURRENT_LANGUE;
	}
}
