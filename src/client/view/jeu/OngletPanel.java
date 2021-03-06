package client.view.jeu;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import common.Constante;

import client.JeuPanel;
import client.view.RoundedPanel;

/**
 * @author omar
 */
public abstract class OngletPanel extends JPanel{

	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	private Color backgroundColor = new Color(86,135,188);
	protected JPanel panelTitre = new RoundedPanel();
	protected JPanel panelContenu = new RoundedPanel();
	protected JeuPanel jeu;
	protected JLabel labelTitre;

	public OngletPanel(String name,JeuPanel jeu){
		super();
		this.jeu = jeu;
		setBounds(0,0,995,585);
		setOpaque(false);
		
		setLayout(null);
		panelTitre.setBounds(100,100,getWidth() - 150,75);
		panelContenu.setBounds(100,200,getWidth() - 150,getHeight() - 250);
		this.add(panelTitre);
		this.add(panelContenu);
		
		panelTitre.setBackground(Color.LIGHT_GRAY);
		panelContenu.setBackground(Color.LIGHT_GRAY);
		
		panelContenu.setBorder(new EmptyBorder(10,10,10,10));
		
		labelTitre = new JLabel("<html><h1>" + name + "</h1></html>",SwingConstants.CENTER);
		// label.setBounds(getWidth() / 2 - 50,50,150,100);
		panelTitre.add(labelTitre);
		updateContent();
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.setColor(backgroundColor);
		g.fillRect(70,50,getWidth(),getHeight());
		paintBouton(g);
	}
	
	protected abstract void paintBouton(Graphics g);
	
	protected void updateContent(){
		panelContenu.removeAll();
	}
	
	public JeuPanel getJeu() {
		return jeu;
	}

	public JPanel getPanelContenu() {
		return panelContenu;
	}
	
	public void setLabelTitre(String label){
		labelTitre.setText("<html><h1>" + label + "</h1></html>");
	}
	
}