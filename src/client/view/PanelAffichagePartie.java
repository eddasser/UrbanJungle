package client.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import client.JeuPanel;

import common.Constante;
import common.Partie;
import common.Translator;

/** classe representant le paneau qui affiche les partie dans le menu multijoueur */
public class PanelAffichagePartie extends JPanel{
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	private static final int HAUTEUR_CASE = 50;
	private static final int LARGEUR_CASE = 94;
	private CopyOnWriteArrayList<Partie> listeDesPartiesEnAttente;
	// private JeuPanel jeu;
	private JPanel panelPartieLock;
	private ButtonGroup group;
	
	public PanelAffichagePartie(CopyOnWriteArrayList<Partie> listeDesParties,JeuPanel jeuParam){
		// jeu = jeuParam;
		setBackground(Color.yellow);
		
		int nbLigne;
		if (listeDesPartiesEnAttente != null)
			nbLigne = listeDesPartiesEnAttente.size();
		else
			nbLigne = 1;
		setLayout(new GridLayout(nbLigne,5)); // je cree un layout permettant d'afficher les parties
		
		// création du groupe de radio bouton pour selectionner la partie
		group = new ButtonGroup();
		
		// ajout de l'intitulé de colonne "selection de la partie"
		add(new JPanel(){
			
			@Override
			public void paintComponent(Graphics g){
				setBounds(0,0,LARGEUR_CASE,HAUTEUR_CASE);
				try{
					Image img = null;
					
					img = ImageIO.read(new File("ressources/" + Translator.getLangue() + "/images/EcranMenuMultijoueur/selection.png"));
					
					g.drawImage(img,0,0,getWidth(),getHeight(),this);// Pour une image de fond
				}catch (IOException e){
					if (Constante.MODE_DEBUG){
						System.out.println("problème lors du chargement de l'image de fond");
						e.printStackTrace();
					}
				}
			}
		});
		
		// ajout de l'intitulé de colonne "identifiant de la partie"
		add(new JPanel(){
			
			@Override
			public void paintComponent(Graphics g){
				setBounds(0,0,LARGEUR_CASE,HAUTEUR_CASE);
				try{
					Image img = null;
					
					img = ImageIO.read(new File("ressources/" + Translator.getLangue() + "/images/EcranMenuMultijoueur/identifiantPartie.png"));
					
					g.drawImage(img,0,0,getWidth(),getHeight(),this);// Pour une image de fond
				}catch (IOException e){
					if (Constante.MODE_DEBUG){
						System.out.println("problème lors du chargement de l'image de fond");
						e.printStackTrace();
					}
				}
			}
		});
		
		// ajout de l'intitulé de colonne "nombre de joueur"
		add(new JPanel(){
			
			@Override
			public void paintComponent(Graphics g){
				setBounds(0,0,LARGEUR_CASE,HAUTEUR_CASE);
				try{
					Image img = null;
					
					img = ImageIO.read(new File("ressources/" + Translator.getLangue() + "/images/EcranMenuMultijoueur/nbJoueur.png"));
					
					g.drawImage(img,0,0,getWidth(),getHeight(),this);// Pour une image de fond
				}catch (IOException e){
					if (Constante.MODE_DEBUG){
						System.out.println("problème lors du chargement de l'image de fond");
						e.printStackTrace();
					}
				}
			}
		});
		
		// ajout de l'intitulé de colonne "statut"
		add(new JPanel(){
			
			@Override
			public void paintComponent(Graphics g){
				setBounds(0,0,LARGEUR_CASE,HAUTEUR_CASE);
				try{
					Image img = null;
					
					img = ImageIO.read(new File("ressources/" + Translator.getLangue() + "/images/EcranMenuMultijoueur/statut.png"));
					
					g.drawImage(img,0,0,getWidth(),getHeight(),this);// Pour une image de fond
				}catch (IOException e){
					if (Constante.MODE_DEBUG){
						System.out.println("problème lors du chargement de l'image de fond");
						e.printStackTrace();
					}
				}
			}
		});
		
		// ajout de l'intitulé de colonne "password"
		add(new JPanel(){
			
			@Override
			public void paintComponent(Graphics g){
				setBounds(0,0,LARGEUR_CASE,HAUTEUR_CASE);
				try{
					Image img = null;
					
					img = ImageIO.read(new File("ressources/" + Translator.getLangue() + "/images/EcranMenuMultijoueur/password.png"));
					
					g.drawImage(img,0,0,getWidth(),getHeight(),this);// Pour une image de fond
				}catch (IOException e){
					if (Constante.MODE_DEBUG){
						System.out.println("problème lors du chargement de l'image de fond");
						e.printStackTrace();
					}
				}
			}
		});
		
		
		// creation d'un Jpanel avec l'icon de cadenas pour les partie verouilléees
		panelPartieLock = new JPanel(){
			
			@Override
			public void paintComponent(Graphics g){
				setBounds(0,0,LARGEUR_CASE,HAUTEUR_CASE);
				try{
					Image img = null;
					img = ImageIO.read(new File("Ressources/images/EcranMenuMultijoueur/lock.png"));
					g.drawImage(img,0,0,getWidth(),getHeight(),this);// Pour une image de fond
				}catch (IOException e){
					if (Constante.MODE_DEBUG){
						System.out.println("problème lors du chargement de l'image de fond");
						e.printStackTrace();
					}
				}
			}
		};
	}
	
	@Override
	public void paintComponent(Graphics g){
		// on ajoute au panel le contenu de la liste
		int compteur = 0;
		if (listeDesPartiesEnAttente != null) // si la liste a deja été transmise au client
		{
			for (Partie partie : listeDesPartiesEnAttente) // pour chaque partie de la liste des partie
			{
				JRadioButton radioBouton = new JRadioButton();
				radioBouton.setActionCommand("" + compteur);
				group.add(radioBouton);
				
				if (compteur == 0) radioBouton.setSelected(true);
				
				add(radioBouton);
				add(new JLabel(partie.getNomPartie()));
				add(new JLabel(partie.getNbJoueur() + ""));
				// add(new JLabel(partie.getEtatDeLaPartie()));
				
				if (!(partie.getPassword().compareTo("") == 0)) // si la partie posséde un password
				{
					add(panelPartieLock); // on ajoute l'icone de cadenas dans la derniere colonne
				}
				
				compteur++;
			}
		}
	}
}
