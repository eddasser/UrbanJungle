package client.view.jeu;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import client.Client;
import client.JeuPanel;
import client.view.NamedJPanel;

import common.Constante;
import common.Joueur;


public class EcranJeu extends NamedJPanel{
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	private JLayeredPane layeredPane;
	
	private JeuPanel jeu;
	
	private JPanel fond = new FondJeu();
	private JPanel ongletJoueur;
	private JPanel ongletVille;
	private JPanel ongletMenu;
	private JPanel ongletBatiment;
	private JPanel ongletUnite;
	
	private EcranPlateau ecranPlateau;
	private EcranAttenteTour ecranAttenteTour;
	
	private JLabel labelArgent = new JLabel("ARGENT :",JLabel.RIGHT);
	private Font font = new Font("Serif",Font.BOLD,30);
	
	public EcranJeu(JeuPanel jeu,JLayeredPane layeredPane){
		super("ecranJeu");
		this.jeu = jeu;
		this.layeredPane = layeredPane;
		
		
		ongletJoueur = new OngletJoueurPanel(jeu);
		ongletVille = new OngletVillePanel(jeu);
		ongletMenu = new OngletMenuPanel(jeu);
		ongletBatiment = new OngletBatimentPanel(jeu);
		ongletUnite = new OngletUnitePanel(jeu);
		ecranAttenteTour = new EcranAttenteTour(jeu);
		ecranPlateau = new EcranPlateau(jeu);
		cacherEcranAttente();
	}
	
	public void afficherPlateau(){
		layeredPane.add(fond,new Integer(-3000));
		layeredPane.add(labelArgent,new Integer(-2000));
		layeredPane.add(ecranPlateau,new Integer(-1000));
		
		layeredPane.add(ongletJoueur,new Integer(0));
		layeredPane.add(ongletVille,new Integer(0));
		layeredPane.add(ongletMenu,new Integer(0));
		layeredPane.add(ongletBatiment,new Integer(0));
		layeredPane.add(ongletUnite,new Integer(0));
		
		
		layeredPane.add(ecranAttenteTour,new Integer(200));
		
		update();
		labelArgent.setFont(font);
		labelArgent.setBounds(Constante.LARGEUR_FENETRE_PRINCIPALE - 350,Constante.HAUTEUR_FENETRE_PRINCIPALE - 110,300,100);
		
		cacherTousLesOngets();
		
		addMouseListener(new MouseListener(){
			
			@Override
			public void mouseReleased(MouseEvent e){
			}
			
			@Override
			public void mousePressed(MouseEvent e){
				int x = e.getX();
				int y = e.getY();
				
				if (y <= 46){
					// test des trois liens du haut
					if (x >= 90 && x <= 275){
						// lien joueur
						afficherOngletJoueur();
					}else if (x >= 420 && x <= 605){
						// lien ville
						afficherOngletVille();
					}else if (x >= 750 && x <= 930){
						// lien menu
						afficherOngletMenu();
					}
				}else if (x <= 60){
					// test des deux liens à gauche
					if (y >= 85 && y <= 275){
						// lien batiement
						afficherOngletBatiment();
					}else if (y >= 320 && y <= 510){
						// lien unités
						afficherOngletUnite();
					}
				}
				// System.out.println(e.getX() + " : " + e.getY());
			}
			
			@Override
			public void mouseExited(MouseEvent e){
			}
			
			@Override
			public void mouseEntered(MouseEvent e){
			}
			
			@Override
			public void mouseClicked(MouseEvent e){
			}
		});
	}
	
	private void cacherTousLesOngets(){
		ongletJoueur.setVisible(false);
		ongletVille.setVisible(false);
		ongletMenu.setVisible(false);
		ongletBatiment.setVisible(false);
		ongletUnite.setVisible(false);
	}
	
	public void afficherOngletJoueur(){
		if (ongletJoueur.isVisible()){
			ongletJoueur.setVisible(false);
		}else{
			cacherTousLesOngets();
			ongletJoueur.setVisible(true);
		}
	}
	
	public void afficherOngletVille(){
		if (ongletVille.isVisible()){
			ongletVille.setVisible(false);
		}else{
			cacherTousLesOngets();
			ongletVille.setVisible(true);
		}
	}
	
	public void afficherOngletMenu(){
		if (ongletMenu.isVisible()){
			ongletMenu.setVisible(false);
		}else{
			cacherTousLesOngets();
			ongletMenu.setVisible(true);
		}
	}
	
	public void afficherOngletBatiment(){
		if (ongletBatiment.isVisible()){
			ongletBatiment.setVisible(false);
		}else{
			cacherTousLesOngets();
			ongletBatiment.setVisible(true);
		}
	}
	
	public void afficherOngletUnite(){
		if (ongletUnite.isVisible()){
			ongletUnite.setVisible(false);
		}else{
			cacherTousLesOngets();
			ongletUnite.setVisible(true);
		}
	}
	
	public void afficherEcranAttente(){
		ecranAttenteTour.setVisible(true);
	}
	
	public void cacherEcranAttente(){
		ecranAttenteTour.setVisible(false);
	}
	
	
	public void update(){
		Client client = jeu.getClient();
		Joueur joueur = client.getJoueur();
		int argent = joueur.getArgent();
		labelArgent.setText(Constante.formatArgent(argent));
	}
}
