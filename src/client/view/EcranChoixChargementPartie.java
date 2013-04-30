package client.view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import client.JeuPanel;
import client.controller.EcranChoixChargementPartieListener;

import common.Constante;
import common.Translator;

public class EcranChoixChargementPartie extends NamedJPanel{
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	
	private JTable table;
	private JScrollPane scrollPane;
	
	private JCoolButton chargerPartie;
	private JCoolButton retour;
	
	private ArrayList<String> listeSauvegardes;
	
	private Object[][] data;
	
	public EcranChoixChargementPartie(JeuPanel jeu){
		super("ecranChoixChargementPartie",jeu);
		setLayout(null);
		
		listeSauvegardes = new ArrayList<>();
		
		// récuperation de la liste des sauvegardes existantes
		File repertoire = new File("sauvegardes");
		if (!repertoire.exists()){
			repertoire.mkdir();
		}
		File[] listeSauvegardesTmp = repertoire.listFiles();
		
		String[] column = { Translator.translate("choisirPartieACharger") };
		
		if (listeSauvegardesTmp != null){
			data = new Object[listeSauvegardesTmp.length][1]; // les données sont un tableau a 2 dimention, 1 seule colonne qui sont les nom
																// des
																// sauvegardes
			
			for (int i = 0 ; i < listeSauvegardesTmp.length ; i++){
				data[i][0] = listeSauvegardesTmp[i].toString();
			}
		}else{
			data = new Object[0][1];
		}
		
		table = new JTable(data,column);
		table.setRowHeight(32);
		
		
		// TODO trouver comment ajouter a la table le noms des fichiers
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(212,220,600,200);
		add(scrollPane);
		
		
		EcranChoixChargementPartieListener listener = new EcranChoixChargementPartieListener(jeu,this,listeSauvegardesTmp);
		
		// boutton charger, charge la partie actuellement selectionné dans la jTable
		chargerPartie = new JCoolButton(Translator.translate("chargerPartie"));
		chargerPartie.setBounds(262,500,200,50);
		chargerPartie.addActionListener(listener);
		add(chargerPartie);
		
		// boutton retour
		retour = new JCoolButton(Translator.translate("retour"));
		retour.setBounds(562,500,200,50);
		retour.addActionListener(listener);
		add(retour);
	}
	
	@Override
	public void paintComponent(Graphics g){
		try{
			Image img = ImageIO.read(new File("ressources/" + Translator.getLangue()
					+ "/images/EcranChoixTypePartie/fondEcranChoixTypePartie.jpg"));
			g.drawImage(img,0,0,getWidth(),getHeight(),this);// Pour une image de fond
			
			// maj de l'interface en cas de changement de langue
			chargerPartie.setText(Translator.translate("chargerPartie"));
			retour.setText(Translator.translate("retour"));
			
			// maj du titre de la colonne de la table
			table.getColumnModel().getColumn(0).setHeaderValue(Translator.translate("choisirPartieACharger"));
			table.getTableHeader().resizeAndRepaint();
			
		}catch (IOException e){
			if (Constante.MODE_DEBUG){
				System.out.println("problème lors du chargement de l'image de fond");
				// e.printStackTrace();
			}
		}
	}
	
	public JTable getTable(){
		return table;
	}
	
	public JCoolButton getChargerPartie(){
		return chargerPartie;
	}
	
	public JCoolButton getRetour(){
		return retour;
	}
	
	
	public void majListePartie(String nomSauvegarde){
		listeSauvegardes.add(nomSauvegarde);
	}
	
}
