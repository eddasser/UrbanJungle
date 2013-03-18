package client.view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.JeuPanel;
import client.controller.EcranLoginListener;

import common.Constante;
import common.Translator;


public class EcranLogin extends NamedJPanel{
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	
	private JTextField zonePseudo;
	private JTextField zoneMDP;
	
	private JCoolButton connexion;
	private JCoolButton retour;
	private JCoolButton creerCompte;
	
	public EcranLogin(JeuPanel jeu){
		super("ecranLogin");
		
		setLayout(null);
		
		zonePseudo = new JTextField(Translator.translate("login"));
		zoneMDP = new JPasswordField(Translator.translate("mdp"));
		
		
		connexion = new JCoolButton("Connection");
		retour = new JCoolButton(Translator.translate("retour"));
		creerCompte = new JCoolButton(Translator.translate("creerCompte"));
		
		EcranLoginListener ecranLoginListener = new EcranLoginListener(jeu,this); // ajout du listener aux boutons
		
		connexion.addActionListener(ecranLoginListener);
		retour.addActionListener(ecranLoginListener);
		creerCompte.addActionListener(ecranLoginListener);
		
		zonePseudo.setBounds(530,235,200,50);
		zoneMDP.setBounds(530,320,200,50);
		
		connexion.setBounds(340,400,150,50);
		retour.setBounds(540,400,150,50);
		creerCompte.setBounds(550,465,200,30);
		
		add(zonePseudo);
		add(zoneMDP);
		add(connexion);
		add(retour);
		add(creerCompte);
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		try{
			Image img = null;
			
			img = ImageIO.read(new File("ressources/" + Translator.getLangue() + "/images/EcranLogin/fondLogin.jpg"));
			
			g.drawImage(img,0,0,getWidth(),getHeight(),this);// Pour une image de fond
			
		}catch (IOException e){
			if (Constante.MODE_DEBUG){
				System.out.println("problème lors du chargement de l'image de fond");
				// e.printStackTrace();
			}
		}
	}
	
	public JTextField getZonePseudo(){
		return zonePseudo;
	}
	
	public JTextField getZoneMDP(){
		return zoneMDP;
	}
	
	public JCoolButton getConnexion(){
		return connexion;
	}
	
	public JCoolButton getRetour(){
		return retour;
	}
	
	public JCoolButton getCreerCompte(){
		return creerCompte;
	}
}
