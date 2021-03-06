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
import common.partie.plateau.Case;
import common.partie.plateau.Plateau;
import common.partie.unite.TypeUnite;
import common.partie.unite.Unite;

/**
 * @author omar
 */
public class EcranPlateau extends JPanel{

	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	private JeuPanel jeu;
	private final static int epaisseurContour = 2;
	private boolean creationUnite;
	private boolean creationBatiment;

	public EcranPlateau(JeuPanel jeu){
		this.jeu = jeu;
		setBounds(Constante.DECALAGE_PLATEAU_X,Constante.DECALAGE_PLATEAU_Y,Constante.LARGEUR_PLATEAU,Constante.HAUTEUR_PLATEAU);
		setOpaque(false);

		Border border = BorderFactory.createLineBorder(Color.black,3);
		setBorder(border);

		creationUnite = false;
		creationBatiment = false;
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

			ArrayList<Unite> unites = joueur.getUnites();
			ArrayList<Batiment> batiments = joueur.getBatiments();


			if (joueur.equals(jeu.getClient().getPartie().getJoueurCourant())){ //si il s'agit du joueur dont c'est le tour
				g.setColor(TransparentColor.transparentOrange);

				/*
				 * Affichage de la zone de deplacement possible d'une unite et des zone de construction possible des batiments
				 */
				for (int j = 0 ; j < unites.size() ; j++){
					Unite unite = unites.get(j);
					Case case_unite = unite.getPosition();

					if (unite.equals(JeuPanel.getEcranJeu().getUniteEnDeplacement())){ // si l'unité est l'unité actuellement selectionné pour etre deplacé

						int deplacementRestantPourUnite = unite.getDeplacementRestant(); 
						
						// on affiche un rectangle de couleur representant les cases ou l'unite peut se deplacer						
						g.fillRect(case_unite.getX() - (Constante.LARGEUR_CASE*deplacementRestantPourUnite), // x
								   case_unite.getY() - (Constante.LARGEUR_CASE*deplacementRestantPourUnite), //y
								   (Constante.LARGEUR_CASE + (Constante.LARGEUR_CASE*2*deplacementRestantPourUnite)), //largeur
								   (Constante.HAUTEUR_CASE + (Constante.LARGEUR_CASE*2*deplacementRestantPourUnite))); //hauteur
						
					}else if (creationBatiment && unite.getType().equals(TypeUnite.CONSTRUCTEUR)){

						
						// on affiche un rectangle de couleur representant les cases ou lles batiments peuvent etre construits
						g.fillRect(case_unite.getX() - (Constante.LARGEUR_CASE*(((int)Constante.NB_CASES_DISTANCE_AVEC_UNITE_CONSTRUCTEUR_AUTORISE_POUR_CONSTRUCTION_BATIMENT)+1)), // x - Constante+1, +1 car la zone dessine fait une case de plus que la zone de construction autorisé
								   case_unite.getY() - (Constante.LARGEUR_CASE*(((int)Constante.NB_CASES_DISTANCE_AVEC_UNITE_CONSTRUCTEUR_AUTORISE_POUR_CONSTRUCTION_BATIMENT)+1)), //y - Constante+1
								   (Constante.LARGEUR_CASE + (Constante.LARGEUR_CASE*2*(((int)Constante.NB_CASES_DISTANCE_AVEC_UNITE_CONSTRUCTEUR_AUTORISE_POUR_CONSTRUCTION_BATIMENT)+1))), //largeur
								   (Constante.HAUTEUR_CASE + (Constante.LARGEUR_CASE*2*(((int)Constante.NB_CASES_DISTANCE_AVEC_UNITE_CONSTRUCTEUR_AUTORISE_POUR_CONSTRUCTION_BATIMENT)+1)))); //hauteur
					}
				}


				/*
				 * Affichage de la zone de placement possible des unites
				 */
				for (int j = 0 ; j < batiments.size() ; j++){
					Batiment batiment = batiments.get(j);
					Case case_batiment = batiment.getPosition();

					/** affichage des zone de placement d'unite possible */
					if (creationUnite){ // si on est en mode placement d'unite
						
						g.fillRect(case_batiment.getX() - (Constante.LARGEUR_CASE*(int)Constante.NB_CASES_DISTANCE_AVEC_BATIMENT_AUTORISE_POUR_CREATION_UNITE), // x
								   case_batiment.getY() - (Constante.LARGEUR_CASE*(int)Constante.NB_CASES_DISTANCE_AVEC_BATIMENT_AUTORISE_POUR_CREATION_UNITE), //y
								   ((Constante.LARGEUR_CASE*2) + Constante.LARGEUR_CASE*2*(int)Constante.NB_CASES_DISTANCE_AVEC_BATIMENT_AUTORISE_POUR_CREATION_UNITE), //largeur
								   ((Constante.HAUTEUR_CASE*2) + Constante.LARGEUR_CASE*2*(int)Constante.NB_CASES_DISTANCE_AVEC_BATIMENT_AUTORISE_POUR_CREATION_UNITE)); //hauteur
					}
				}
			}


			g.setColor(colorJoueur);

			/*
			 * Affichage des batiments du joueur
			 */
			for (int j = 0 ; j < batiments.size() ; j++){
				Batiment batiment = batiments.get(j);
				Case case_batiment = batiment.getPosition();

				g.setColor(colorJoueur);

				/*
				 * Affichage du contour du batiment (avec la couleur du joueur)
				 */
				g.fillRect(case_batiment.getX() - epaisseurContour,case_batiment.getY() - epaisseurContour,
						(Constante.LARGEUR_CASE + epaisseurContour) * 2,(Constante.HAUTEUR_CASE + epaisseurContour) * 2);
				/*
				 * Affichage de l'icon du batiment
				 */
				Icon icon = batiment.getType().getIconMin();
				icon.paintIcon(this,g2d,case_batiment.getX(),case_batiment.getY());
			}

			/*
			 * Affichage des unités du joueur
			 */

			for (int j = 0 ; j < unites.size() ; j++){
				Unite unite = unites.get(j);
				Case case_unite = unite.getPosition();

				/*
				 * Affichage du contour du batiment (avec la couleur du joueur)
				 */
				g.fillRect(case_unite.getX() - epaisseurContour,case_unite.getY() - epaisseurContour,
						(Constante.LARGEUR_CASE + (epaisseurContour*2)),(Constante.HAUTEUR_CASE + (epaisseurContour*2)));

				/*
				 * affichage de l'icone
				 */
				Icon icon = unite.getType().getIconMin();
				icon.paintIcon(this,g2d,case_unite.getX(),case_unite.getY());


//				int x = case_unite.getX();
//				int y = case_unite.getY();
//				// affiche le coutour de l'unite (avec la couleur du joueur)
//				g.setColor(colorJoueur);
//				g.drawOval(x,y,Constante.LARGEUR_CASE,Constante.HAUTEUR_CASE);
//				// affiche l'interieur de l'unite (avec la couleur du type d'unite)
//				//g.setColor(colorUnite);
//				g.fillOval(x + epaisseurContour,y + epaisseurContour,Constante.LARGEUR_CASE - 2 * epaisseurContour,Constante.HAUTEUR_CASE - 2
//						* epaisseurContour);

			}
		}
	}

	public void setCreationUnite(boolean creationUnite) {
		this.creationUnite = creationUnite;
	}

	public void setCreationBatiment(boolean creationBatiment) {
		this.creationBatiment = creationBatiment;
	}
}

