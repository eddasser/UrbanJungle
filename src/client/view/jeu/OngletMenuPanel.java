package client.view.jeu;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import client.JeuPanel;
import client.controller.OngletMenuPanelListener;
import client.view.JCoolButton;

import common.Constante;
import common.Translator;


/**
 * @author omar
 */
public class OngletMenuPanel extends OngletPanel{
	
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	
	private JComboBox<String> combo;
	private JCoolButton boutonApply;
	private JCoolButton buttonSauvegarder;
	private JCoolButton buttonQuitter;
	
	public OngletMenuPanel(JeuPanel jeu){
		super("Menu",jeu);
	}
	
	@Override
	public void updateContent(){
		super.updateContent();
		
		OngletMenuPanelListener listener = new OngletMenuPanelListener(this);
	
		panelContenu.setLayout(new GridLayout(5,5));
		
		panelContenu.add(new JLabel());
		panelContenu.add(new JLabel());
		
		// creation du jlabel pour le choix de la langue
		JLabel labelChoixLangue = new JLabel(Translator.translate("choixLangue"));
		panelContenu.add(labelChoixLangue);
		
		panelContenu.add(new JLabel());
		panelContenu.add(new JLabel());
		panelContenu.add(new JLabel());
		
		// creation de la combo box pour le choix de la langue
		DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<String>();
		comboModel.addElement("français");
		comboModel.addElement("english");

		combo = new JComboBox<String>(comboModel);
		panelContenu.add(combo);
		
		panelContenu.add(new JLabel());
		
		// creation du jlabel pour le choix de la langue
		boutonApply = new JCoolButton(Translator.translate("appliquer"));
		boutonApply.addActionListener(listener);
		panelContenu.add(boutonApply);
			
		panelContenu.add(new JLabel());
		
		panelContenu.add(new JLabel("-----------------------------------------"));
		panelContenu.add(new JLabel("-----------------------------------------"));
		panelContenu.add(new JLabel("-----------------------------------------"));
		panelContenu.add(new JLabel("-----------------------------------------"));
		panelContenu.add(new JLabel("----------------------------------------"));
		
		panelContenu.add(new JLabel());
		
		// bouton pour sauvegarder la partie
		buttonSauvegarder = new JCoolButton(Translator.translate("sauvegarderPartie"));
		buttonSauvegarder.addActionListener(listener);
		panelContenu.add(buttonSauvegarder);
		
		panelContenu.add(new JLabel());
		
		// bouton pour quitter la partie
		buttonQuitter = new JCoolButton(Translator.translate("quitterPartie"));
		buttonQuitter.addActionListener(listener);
		panelContenu.add(buttonQuitter);
		
		panelContenu.add(new JLabel());
		panelContenu.add(new JLabel());
		panelContenu.add(new JLabel());
		panelContenu.add(new JLabel());
		panelContenu.add(new JLabel());
		panelContenu.add(new JLabel());
	}
	
	@Override
	protected void paintBouton(Graphics g){
		g.fillRect(746,20,46,35);
		g.fillRect(791,32,95,24);
		g.fillRect(884,20,45,35);
	}

	public JComboBox<String> getCombo() {
		return combo;
	}

	public JCoolButton getBoutonApply() {
		return boutonApply;
	}

	public JCoolButton getButtonSauvegarder() {
		return buttonSauvegarder;
	}

	public JCoolButton getButtonQuitter() {
		return buttonQuitter;
	}
	
	
}
