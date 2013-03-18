package client.view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import client.JeuPanel;
import client.controller.EcranChoixTypePartieListener;

import common.Constante;
import common.Translator;


public class EcranChoixTypePartie extends NamedJPanel{
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	private JCoolButton partieSolo;
	private JCoolButton chargerPartie;
	private JCoolButton partieEnLigne;
	private JCoolButton quitter;
	private boolean multijoueurPossible;
	private boolean premierPassage; // boolean qui va servir de flag pour savoir si c'est la premiere fois qu'on passe dans le paint
									// componant
	
	
	public EcranChoixTypePartie(JeuPanel jeu){
		super("ecranChoixTypeDePartie");
		
		multijoueurPossible = false;
		premierPassage = true;
		
		partieSolo = new JCoolButton(Translator.translate("partieSolo")); // bouton partie solo
		chargerPartie = new JCoolButton(Translator.translate("chargerPartie")); // bouton partie solo
		quitter = new JCoolButton(Translator.translate("quitterJeu")); // bouton quitter le jeu
		partieEnLigne = new JCoolButton(Translator.translate("partieMulti")); // bouton partie en ligne ( aparait si le serveur est
																				// joignable )
		
		EcranChoixTypePartieListener ecranChoixTypePartieListener = new EcranChoixTypePartieListener(jeu,this); // ajout du listener aux
																												// boutons
		partieSolo.addActionListener(ecranChoixTypePartieListener);
		chargerPartie.addActionListener(ecranChoixTypePartieListener);
		quitter.addActionListener(ecranChoixTypePartieListener);
		partieEnLigne.addActionListener(ecranChoixTypePartieListener);
	}
	
	
	/**
	 * méthode chargé de placer les composant dans l'espace
	 * 
	 * @param nbBoutton
	 *            , le nombre de boutons a placer
	 */
	private void placementComposant(int nbBoutton){
		int hauteurBouton = getHeight() / 10;
		int largeurBouton = (getWidth() / 10) * 3;
		
		int xPremierBouton = getWidth() / 10;
		int xSecondBouton = (getWidth() / 10) * 6;
		
		int yPremierBouton = (getHeight() / 5) * 2;
		int ySecondBouton = (getHeight() / 5) * 3;
		
		partieSolo.setBounds(xPremierBouton,yPremierBouton,largeurBouton,hauteurBouton);
		chargerPartie.setBounds(xSecondBouton,yPremierBouton,largeurBouton,hauteurBouton);
		
		if (nbBoutton == 3){
			quitter.setBounds((int)((getWidth() / 10) * 3.5),ySecondBouton,largeurBouton,hauteurBouton);
			
			if (premierPassage){
				premierPassage = false; // super laid mais seul moyen trouvé pour avoir quelque chose de joli
				add(partieSolo);
				add(chargerPartie);
				add(quitter);
			}
		}else // nb de bouton = 4
		{
			partieEnLigne.setBounds(xPremierBouton,ySecondBouton,largeurBouton,hauteurBouton);
			quitter.setBounds(xSecondBouton,ySecondBouton,largeurBouton,hauteurBouton);
			
			if (premierPassage){
				premierPassage = false; // super laid mais seul moyen trouvé pour avoir quelque chose de joli
				add(partieSolo);
				add(chargerPartie);
				add(quitter);
				add(partieEnLigne);
			}
		}
		
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		try{
			Image img = ImageIO.read(new File("ressources/" + Translator.getLangue()
					+ "/images/EcranChoixTypePartie/fondEcranChoixTypePartie.jpg"));
			g.drawImage(img,0,0,getWidth(),getHeight(),this);// Pour une image de fond
			
			if (multijoueurPossible){
				placementComposant(4);
			}else{
				placementComposant(3);
			}
			
		}catch (IOException e){
			if (Constante.MODE_DEBUG){
				System.out.println("problème lors du chargement de l'image de fond");
				// e.printStackTrace();
			}
		}
	}
	
	public void setMultijoueurPossible(boolean etat){
		multijoueurPossible = etat;
	}
	
	public JCoolButton getPartieSolo(){
		return partieSolo;
	}
	
	public JCoolButton getChargerPartie(){
		return chargerPartie;
	}
	
	public JCoolButton getPartieEnLigne(){
		return partieEnLigne;
	}
	
	public JCoolButton getQuitter(){
		return quitter;
	}
}
