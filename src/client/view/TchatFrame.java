package client.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import client.ServerListener;
import client.TchatCheatCode;

import common.Commande;
import common.Constante;
import common.Joueur;
import common.Partie;

/**
 * @author omar
 */
public class TchatFrame extends JFrame{
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	
	private ServerListener server;
	
	private JPanel panel = new JPanel();
	private JPanel haut = new JPanel();
	private JPanel bas = new JPanel();
	
	private JTextArea textArea = new JTextArea(10,10);
	private JTextField textField = new JTextField(20);
	private JButton button = new JButton("OK");
	
	private Joueur joueur;
	
	public TchatFrame(){
		this.setSize(Constante.LARGEUR_FENETRE_PRINCIPALE / 3,Constante.HAUTEUR_FENETRE_PRINCIPALE / 2); // on fixe sa taille
		setLocationRelativeTo(null);// on la place au centre de l'écran
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // on definit l'opération par defaut de fermeture
		setResizable(false);
		
		textArea.setEditable(false);
	}
	
	public void initialise(Partie partie,Joueur joueur,ServerListener server){
		haut.removeAll();
		bas.removeAll();
		
		this.joueur = joueur;
		this.server = server;
		
		/*
		 * Partie du haut
		 */
		
		ArrayList<Joueur> joueurs = partie.getListeParticipants();
		haut.setLayout(new GridLayout(joueurs.size(),1));
		for (int i = 0 ; i < joueurs.size() ; i++){
			JLabel label = new JLabel(joueurs.get(i).getLogin());
			haut.add(label);
		}
		
		/*
		 * Partie du bas
		 */
		JPanel tmp = new JPanel();
		tmp.add(textField);
		tmp.add(button);
		bas.setLayout(new BorderLayout());
		bas.add(new JScrollPane(textArea),BorderLayout.CENTER);
		bas.add(tmp,BorderLayout.SOUTH);
		
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				sendMessage();
			}
		});
		
		textField.addKeyListener(new KeyAdapter(){
			
			@Override
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					sendMessage();
				}
			}
		});
		
		/*
		 * initialisation de la fenetre en elle-meme
		 */
		
		panel.setLayout(new BorderLayout());
		panel.add(haut,BorderLayout.NORTH);
		panel.add(bas,BorderLayout.CENTER);
		
		setTitle("TCHAT URBAN JUNGE " + Constante.NUMERO_DE_VERSION + " - " + partie.getNomPartie());
		setContentPane(panel);
		setVisible(true);
	}
	
	private void sendMessage(){
		String text = textField.getText();
		
		if (!TchatCheatCode.execute(text,joueur)){
			Object[] args = { Commande.TCHAT_SEND_MESSAGE,text };
			server.sendCommand(args);
		}
		
		textField.setText("");
	}
	
	public void newMessage(Joueur joueur,String message){
		String msg = joueur.getLogin() + " : " + message + "\n";
		textArea.append(msg);
		textArea.setCaretPosition(textArea.getCaretPosition() + msg.length());// permet de maintenir le scroll toujours en bas
	}
	
}
