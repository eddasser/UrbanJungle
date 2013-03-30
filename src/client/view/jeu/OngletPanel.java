package client.view.jeu;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @author omar
 */
public abstract class OngletPanel extends JPanel{
	private Color backgroundColor = new Color(86,135,188);
	
	public OngletPanel(String name){
		super();
		setBounds(0,0,995,585);
		setOpaque(false);
		
		setLayout(null);
		JLabel label = new JLabel("<html><h1>" + name + "</h1></html>",SwingConstants.CENTER);
		label.setBounds(getWidth() / 2 - 50,50,150,100);
		this.add(label);
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.setColor(backgroundColor);
		g.fillRect(70,50,getWidth(),getHeight());
		paintBouton(g);
	}
	
	protected abstract void paintBouton(Graphics g);
	
}