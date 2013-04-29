package client.view;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import client.JeuPanel;
import client.controller.EcranChoixChargementPartieListener;

import common.Constante;
import common.Translator;

public class EcranChoixChargementPartie extends NamedJPanel{
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	
	private JLabel labelChargementPartie;
	
	private JTable table = new JTable();
	private JScrollPane scrollPane = new JScrollPane(table);
	
	private JCoolButton chargerPartie;
	private JCoolButton retour;
	
	private ArrayList<String> listeSauvegardes;
	
	public EcranChoixChargementPartie(JeuPanel jeu) {
		super("ecranChoixChargementPartie", jeu);
		
		listeSauvegardes = new ArrayList<>();
		
		//label titre
		labelChargementPartie = new JLabel(Translator.translate("choisirPartieACharger"));
		labelChargementPartie.setBounds(400, 100, 200, 50);
		add(labelChargementPartie);

		
		//récuperation de la liste des sauvegardes existantes
		File repertoire = new File("sauvegardes");
		
		File[] listeSauvegardesTmp = repertoire.listFiles();
		
		for (int i=0; i< listeSauvegardesTmp.length;i++){
			System.out.println(listeSauvegardesTmp[i].getName());
			
			//TODO parser les chaines pour avoir juste le nom des sauvegardes et les ajouter a listeSauvegardes
		}
	
		//TODO trouver comment ajouter a la table le noms des fichiers
		scrollPane.setBounds(110,220,800,300);
		
		add(scrollPane);
		
		
		EcranChoixChargementPartieListener listener = new EcranChoixChargementPartieListener(jeu, this, listeSauvegardes);
		
		//boutton charger, charge la partie actuellement selectionné dans la jTable
		chargerPartie = new JCoolButton(Translator.translate("charger"));
		chargerPartie.setBounds(100, 500, 200, 50);
		chargerPartie.addActionListener(listener);
		add(chargerPartie);
		
		//boutton retour
		retour = new JCoolButton(Translator.translate("retour"));
		retour.setBounds(100, 500, 200, 50);
		retour.addActionListener(listener);
		add(retour);	
	}

	
	public JTable getTable() {
		return table;
	}

	public JCoolButton getChargerPartie() {
		return chargerPartie;
	}

	public JCoolButton getRetour() {
		return retour;
	}


	public void majListePartie(String nomSauvegarde) {
		listeSauvegardes.add(nomSauvegarde);
		//TODO refresh la table avec le nouveau contenu de la liste
	}
	
}
