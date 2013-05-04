package client;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import common.Joueur;
import common.partie.batiment.TypeBatiment;
import common.partie.unite.TypeUnite;

/**
 * @author omar
 */
public class TchatCheatCode{
	
	abstract class TcheatAction{
		public abstract void execute(Joueur joueur);
	}
	
	private final static TchatCheatCode tchatCheatCode = new TchatCheatCode();
	private HashMap<Object,TcheatAction> cheats = new HashMap<Object,TcheatAction>();
	
	private TchatCheatCode(){
		/*
		 * creation du premier cheat code qui augmente le nombre de niveau de +10
		 */
		ArrayList<Integer> touchLvlUp = new ArrayList<Integer>();
		touchLvlUp.add(KeyEvent.VK_LEFT);
		touchLvlUp.add(KeyEvent.VK_RIGHT);
		touchLvlUp.add(KeyEvent.VK_LEFT);
		touchLvlUp.add(KeyEvent.VK_RIGHT);
		TcheatAction lvlup = new TcheatAction(){
			private final static int nbLvlUp = 20;
			
			@Override
			public void execute(Joueur joueur){
				for (TypeBatiment type : TypeBatiment.values()){
					joueur.incrementeNiveauBatiment(type,nbLvlUp);
				}
				for (TypeUnite type : TypeUnite.values()){
					joueur.incrementeNiveauUnite(type,nbLvlUp);
				}
			}
		};
		cheats.put("/lvlup",lvlup);
		cheats.put(touchLvlUp,lvlup);
		
		/*
		 * creation du second cheat code qui augemente l'argent du joueur de +10.000
		 */
		ArrayList<Integer> touchMoney = new ArrayList<Integer>();
		touchMoney.add(KeyEvent.VK_UP);
		touchMoney.add(KeyEvent.VK_DOWN);
		touchMoney.add(KeyEvent.VK_UP);
		touchMoney.add(KeyEvent.VK_DOWN);
		TcheatAction money = new TcheatAction(){
			private final static int montant = 20000;
			
			@Override
			public void execute(Joueur joueur){
				joueur.incrementArgent(montant);
			}
		};
		cheats.put("/money",money);
		cheats.put(touchMoney,money);
	}
	
	public static boolean execute(Object cheatCode,Joueur joueur){
		boolean execute = false;
		
		if (tchatCheatCode.cheats.containsKey(cheatCode)){
			TcheatAction action = tchatCheatCode.cheats.get(cheatCode);
			action.execute(joueur);
			JeuPanel.getEcranJeu().update();
			execute = true;
		}
		
		return execute;
	}
	
}
