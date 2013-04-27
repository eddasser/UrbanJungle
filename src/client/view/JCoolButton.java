package client.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JButton;

public class JCoolButton extends JButton{
	
	private static final long serialVersionUID = 1671314658637614873L;
	private final static Font font = new Font("Verdana",Font.BOLD,14);
	
	public JCoolButton(String aNameString){
		super(aNameString);
		setContentAreaFilled(false);
		setFont(font);
	}
	
	@Override
	protected void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g.create();
		g2.setPaint(new GradientPaint(new Point(0,0),Color.BLACK,new Point(0,getHeight()),Color.LIGHT_GRAY.darker()));
		g2.fillRoundRect(0,0,getWidth(),getHeight(),getWidth() / 6,getHeight());
		g2.dispose();
		
		g.setColor(Color.WHITE);
		String txt = getText();
		int fontSize = getFont().getSize();
		g.drawString(txt,getWidth() / 2 - txt.length() * fontSize / 4,getHeight() / 2);
	}
}
