package client.view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.JeuPanel;
import client.controller.EcranCreationPartieListener;

import common.Constante;
import common.Translator;


public class EcranCreationPartie extends NamedJPanel{
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	
	private JLabel labelName = new JLabel(Translator.translate("nomPartie"));
	private JLabel labelPassword = new JLabel(Translator.translate("passwordPartie"));
	private JLabel labelNbJoueur = new JLabel(Translator.translate("nbJoueur"));
	
	private JTextField textName;
	private JTextField textPassword;
	private JComboBox<Integer> comboNbJoueur;
	
	private JCoolButton creation;
	private JCoolButton retour;
	
	
	public EcranCreationPartie(JeuPanel jeu){
		super("ecranCreationPartie");
		
		setLayout(null);
		
		textName = new JTextField();
		textPassword = new JPasswordField();
		
		creation = new JCoolButton(Translator.translate("creer"));
		retour = new JCoolButton(Translator.translate("retour"));
		
		Integer[] items = new Integer[3];
		for (int i = 0 ; i < items.length ; i++){
			items[i] = i + 2;
		}
		comboNbJoueur = new JComboBox<Integer>(items);
		/*
		Font font = new Font("Dialog",Font.PLAIN,24);
		labelName.setFont(font);
		labelNbJoueur.setFont(font);
		labelPassword.setFont(font);
		*/
		EcranCreationPartieListener ecranCreationPartieListener = new EcranCreationPartieListener(jeu,this);
		
		creation.addActionListener(ecranCreationPartieListener);
		retour.addActionListener(ecranCreationPartieListener);
		
		labelName.setBounds(330,235,200,50);
		labelPassword.setBounds(330,320,200,50);
		
		textName.setBounds(530,235,200,50);
		textPassword.setBounds(530,320,200,50);
		
		labelNbJoueur.setBounds(340,380,150,50);
		comboNbJoueur.setBounds(540,380,150,50);
		
		creation.setBounds(340,450,150,50);
		retour.setBounds(540,450,150,50);
		
		
		add(labelName);
		add(labelPassword);
		add(textName);
		add(textPassword);
		add(labelNbJoueur);
		add(comboNbJoueur);
		add(creation);
		add(retour);
		
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
	
	public JCoolButton getCreation(){
		return creation;
	}
	
	public JCoolButton getRetour(){
		return retour;
	}
	
	public JTextField getTextName(){
		return textName;
	}
	
	public JTextField getTextPassword(){
		return textPassword;
	}
	
	public JComboBox<Integer> getComboNbJoueur(){
		return comboNbJoueur;
	}
	
}
