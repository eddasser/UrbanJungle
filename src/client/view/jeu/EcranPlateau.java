package client.view.jeu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.border.Border;

import client.JeuPanel;

import common.Constante;
import common.Joueur;
import common.partie.batiment.Batiment;
import common.partie.batiment.TypeBatiment;
import common.partie.plateau.Case;
import common.partie.plateau.Plateau;
import common.partie.unite.TypeUnite;
import common.partie.unite.Unite;

/**
 * @author omar
 */
public class EcranPlateau extends JPanel{
	private JeuPanel jeu;
	private final static int epaisseurContour = 2;
	
	public EcranPlateau(JeuPanel jeu){
		this.jeu = jeu;
		setBounds(85,60,Constante.LARGEUR_PLATEAU,Constante.HAUTEUR_PLATEAU);
		setOpaque(false);
		
		Border border = BorderFactory.createLineBorder(Color.black,3);
		setBorder(border);
	}
	
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		/*
		* Affichage du plateau en lui-meme
		*/
		Plateau plateau = jeu.getClient().getPartie().getPlateau();
		ArrayList<Case> cases = plateau.getCases();
		for (int i = 0 ; i < cases.size() ; i++){
			Case case_courante = cases.get(i);
			int x = case_courante.getX();
			int y = case_courante.getY();
			int width = 40;
			int height = 40;
			g.drawRect(x,y,width,height);
		}
		
		/*
		 * Affichage de tous les elements (batiments et unités) sur le plateau
		 */
		ArrayList<Joueur> joueurs = jeu.getClient().getPartie().getListeParticipants();
		for (int i = 0 ; i < joueurs.size() ; i++){
			Joueur joueur = joueurs.get(i);
			Color colorJoueur = Constante.COLORS[i];// recuperation de la couleur du joueur
			g.setColor(colorJoueur);
			
			/*
			 * Affichage des batiments du joueur
			 */
			ArrayList<Batiment> batiments = joueur.getBatiments();
			for (int j = 0 ; j < batiments.size() ; j++){
				Batiment batiment = batiments.get(j);
				Case case_batiment = batiment.getPosition();
				
				/*
				 * Affichage du contour du batiment (avec la couleur du joueur)
				 */
				g.fillRect(case_batiment.getX() - epaisseurContour,case_batiment.getY() - epaisseurContour,
						(Constante.LARGEUR_CASE + epaisseurContour) * 2,(Constante.HAUTEUR_CASE + epaisseurContour) * 2);
				/*
				 * Affichage de l'icon du batiment
				 */
				Icon icon = TypeBatiment.getIcon(batiment.getType());
				icon.paintIcon(this,g2d,case_batiment.getX(),case_batiment.getY());
			}
			
			/*
			 * Affichage des unités du joueur
			 */
			ArrayList<Unite> unites = joueur.getUnites();
			for (int j = 0 ; j < unites.size() ; j++){
				Unite unite = unites.get(j);
				Case case_unite = unite.getPosition();
				Color colorUnite = TypeUnite.getColor(unite.getType());
				
				int x = case_unite.getX();
				int y = case_unite.getY();
				// affiche le coutour de l'unite (avec la couleur du joueur)
				g.setColor(colorJoueur);
				g.fillOval(x,y,Constante.LARGEUR_CASE,Constante.HAUTEUR_CASE);
				// affiche l'interieur de l'unite (avec la couleur du type d'unite)
				g.setColor(colorUnite);
				g.fillOval(x + epaisseurContour,y + epaisseurContour,Constante.LARGEUR_CASE - 2 * epaisseurContour,Constante.HAUTEUR_CASE - 2
						* epaisseurContour);
				
			}
		}
	}
}
