package client.view;

import java.util.HashMap;

import client.JeuPanel;

import common.Joueur;
import common.partie.batiment.TypeBatiment;
import common.partie.unite.TypeUnite;

/**
 * @author omar
 */
public class TchatCheatCode{
	abstract class TcheatAction{
		public abstract void execute();
	}
	
	private final static HashMap<String,TcheatAction> cheats = new HashMap<String,TcheatAction>();
	private Joueur joueur;
	
	public TchatCheatCode(Joueur joueur){
		this.joueur = joueur;
		if (cheats.isEmpty()){
			initialiserMap();
		}
	}
	
	private void initialiserMap(){
		/*
		 * creation du premier cheat code qui augmente le nombre de niveau de +10
		 */
		TcheatAction lvlup = new TcheatAction(){
			private final static int nbLvlUp = 10;
			
			@Override
			public void execute(){
				for (TypeBatiment type : TypeBatiment.values()){
					joueur.incrementeNiveauBatiment(type,nbLvlUp);
				}
				for (TypeUnite type : TypeUnite.values()){
					joueur.incrementeNiveauUnite(type,nbLvlUp);
				}
			}
		};
		cheats.put("/lvlup",lvlup);
		
		/*
		 * creation du second cheat code qui augemente l'argent du joueur de +10.000
		 */
		TcheatAction money = new TcheatAction(){
			private final static int montant = 10000;
			
			@Override
			public void execute(){
				joueur.incrementArgent(montant);
			}
		};
		cheats.put("/money",money);
	}
	
	
	public boolean execute(String cheatCode){
		boolean execute = false;
		
		if (cheats.containsKey(cheatCode)){
			TcheatAction action = cheats.get(cheatCode);
			action.execute();
			JeuPanel.getEcranJeu().update();
			execute = true;
		}
		
		return execute;
	}
}
