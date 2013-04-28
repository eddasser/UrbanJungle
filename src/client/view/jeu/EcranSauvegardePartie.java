package client.view.jeu;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.JeuPanel;
import client.controller.EcranSauvegardePartieListener;
import client.view.JCoolButton;

import common.Constante;
import common.Translator;

public class EcranSauvegardePartie extends JPanel{

	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;

	private JLabel labelNameSauvegarde ;
	private JTextField fieldSauvegardeName;
	private JCoolButton boutonSauvegarder;
	private JCoolButton boutonRetour;

	
	public EcranSauvegardePartie(JeuPanel jeu) {
	
		setLayout(null);
		EcranSauvegardePartieListener listener = new EcranSauvegardePartieListener(jeu, this);
		
		
		labelNameSauvegarde = new JLabel(Translator.translate("choisirNomSauvegarde"));
		labelNameSauvegarde.setBounds(300, 200, 200, 50); 
		add(labelNameSauvegarde);
		
		fieldSauvegardeName = new JTextField(new Date().toString());
		fieldSauvegardeName.setBounds(300, 300, 200, 50);
		add(fieldSauvegardeName);
		
		boutonSauvegarder = new JCoolButton(Translator.translate("sauvegarderPartie"));
		boutonSauvegarder.setBounds(200, 500, 200, 50);
		boutonSauvegarder.addActionListener(listener);
		add(boutonSauvegarder);
		
		boutonRetour = new JCoolButton(Translator.translate("retour"));
		boutonRetour.setBounds(500, 500, 200, 50);
		boutonRetour.addActionListener(listener);
		add(boutonRetour);
	}
	
	
	@Override
	public void paintComponent(Graphics g){
		try{
			Image img = null;
			
			img = ImageIO.read(new File("ressources/" + Translator.getLangue() + "/images/EcranCreationPartie/fond.jpg"));
			
			g.drawImage(img,0,0,getWidth(),getHeight(),this);// Pour une image de fond
			
		}catch (IOException e){
			if (Constante.MODE_DEBUG){
				System.out.println("problème lors du chargement de l'image de fond");
				// e.printStackTrace();
			}
		}
	}


	public JTextField getFieldSauvegardeName() {
		return fieldSauvegardeName;
	}


	public JCoolButton getBoutonSauvegarder() {
		return boutonSauvegarder;
	}


	public JCoolButton getBoutonRetour() {
		return boutonRetour;
	}
	

}
