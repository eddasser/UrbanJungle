package client.view.jeu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import client.Client;
import client.JeuPanel;
import client.controller.EcranJeuListener;
import client.view.NamedJPanel;

import common.Constante;
import common.Joueur;
import common.partie.batiment.TypeBatiment;
import common.partie.unite.TypeUnite;


public class EcranJeu extends NamedJPanel{
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	private JLayeredPane layeredPane;
	
	private JPanel fond = new FondJeu();
	private OngletPanel ongletJoueur;
	private OngletPanel ongletVille;
	private OngletPanel ongletMenu;
	private OngletPanel ongletBatiment;
	private OngletPanel ongletUnite;
	
	private EcranPlateau ecranPlateau;
	private EcranAttenteTour ecranAttenteTour;
	
	private JLabel labelCouleurJoueur = new JLabel();
	private JLabel labelArgent = new JLabel("ARGENT :",JLabel.RIGHT);
	private Font font = new Font("Serif",Font.BOLD,30);
	
	
	// label qui est affiché (et qui suit la souris) lors du mode création de batiment
	private JLabel labelEnConstruction = new JLabel();
	private TypeBatiment typeBatimentEnConstruction;
	private TypeUnite typeUniteEnConstruction;
	
	public EcranJeu(JeuPanel jeu,JLayeredPane layeredPane){
		super("ecranJeu",jeu);
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
		
		layeredPane.add(labelCouleurJoueur,new Integer(-500));
		
		layeredPane.add(ongletJoueur,new Integer(0));
		layeredPane.add(ongletVille,new Integer(0));
		layeredPane.add(ongletMenu,new Integer(0));
		layeredPane.add(ongletBatiment,new Integer(0));
		layeredPane.add(ongletUnite,new Integer(0));
		
		layeredPane.add(labelEnConstruction,new Integer(10));
		
		layeredPane.add(ecranAttenteTour,new Integer(200));
		
		labelEnConstruction.setBounds(Constante.LARGEUR_FENETRE_PRINCIPALE / 2,Constante.HAUTEUR_FENETRE_PRINCIPALE / 2,50,50);
		
		update();
		labelArgent.setFont(font);
		labelArgent.setBounds(Constante.LARGEUR_FENETRE_PRINCIPALE - 350,Constante.HAUTEUR_FENETRE_PRINCIPALE - 80,300,40);
		
		Joueur joueur = jeu.getClient().getJoueur();
		int indJoueur = jeu.getClient().getPartie().getListeParticipants().indexOf(joueur);
		Color couleurJoueur = Constante.COLORS[indJoueur];
		labelCouleurJoueur.setOpaque(true);
		labelCouleurJoueur.setBackground(couleurJoueur);
		labelCouleurJoueur.setBounds(Constante.LARGEUR_FENETRE_PRINCIPALE - 350,Constante.HAUTEUR_FENETRE_PRINCIPALE - 80,40,40);
		
		cacherTousLesOngets();
		
		EcranJeuListener ejl = new EcranJeuListener(jeu,joueur,this);
		addMouseListener(ejl);
		addMouseMotionListener(ejl);
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
	
	@Override
	public void setJeu(JeuPanel jeu){
		this.jeu = jeu;
	}
	
	public void update(){
		Client client = jeu.getClient();
		Joueur joueur = client.getJoueur();
		int argent = joueur.getArgent();
		labelArgent.setText(Constante.formatArgent(argent));
		
		ongletJoueur.updateContent();
		ongletVille.updateContent();
		ongletMenu.updateContent();
		ongletBatiment.updateContent();
		ongletUnite.updateContent();
	}
	
	public void afficherModeCreationBatiment(TypeBatiment type){
		cacherTousLesOngets();
		typeBatimentEnConstruction = type;
		labelEnConstruction.setIcon(TypeBatiment.getIcon(type));
		labelEnConstruction.setVisible(true);
	}
	
	public void afficherModeCreationUnite(TypeUnite type){
		cacherTousLesOngets();
		typeUniteEnConstruction = type;
		labelEnConstruction.setIcon(TypeUnite.getIcon(type));
		labelEnConstruction.setVisible(true);
	}
	
	public void cacherModeCreation(){
		typeBatimentEnConstruction = null;
		typeUniteEnConstruction = null;
		labelEnConstruction.setIcon(null);
		labelEnConstruction.setVisible(false);
	}
	
	public boolean isModeCreationBatiment(){
		return (typeBatimentEnConstruction != null);
	}
	
	public boolean isModeCreationUnite(){
		return (typeUniteEnConstruction != null);
	}
	
	public void setPositionLabelConstructionBatiment(int x,int y){
		Rectangle rect = labelEnConstruction.getBounds();
		rect.setBounds(x - Constante.LARGEUR_CASE,y - Constante.HAUTEUR_CASE,(int)rect.getWidth(),(int)rect.getHeight());
		labelEnConstruction.setBounds(rect);
	}
	
	public TypeBatiment getTypeBatimentEnConstruction(){
		return typeBatimentEnConstruction;
	}
	
	public TypeUnite getTypeUniteEnConstruction(){
		return typeUniteEnConstruction;
	}
}
