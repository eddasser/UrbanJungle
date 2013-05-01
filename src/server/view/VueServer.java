package server.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

import server.Server;

import common.Constante;

/**
 * @author omar
 */
public class VueServer extends JFrame implements Observer{
	
	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	private Server server;
	
	private VueListeJoueurs vueListeJoueurs = new VueListeJoueurs();
	private VueListeParties vueListeParties = new VueListeParties();
	private JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,vueListeJoueurs,vueListeParties);
	
	private final static JLabel label = new JLabel(new ImageIcon("ressources/server/UrbanJungleServer.gif"));
	
	private JPanel panel = new JPanel();
	private CardLayout cardLayout = new CardLayout();
	
	public VueServer(Server server){
		super();
		this.server = server;
		/*	
			try{
				Image imgFond = ImageIO.read(new File("ressources/server/UrbanJungleServer.gif"));
				label = new JLabel(new ImageIcon(imgFond));
			}catch (IOException e){
				e.printStackTrace();
			}
		*/
		
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(250);
		
		
		panel.setBackground(new Color(148,171,196));
		panel.setLayout(cardLayout);
		panel.add(label,"1");
		panel.add(splitPane,"2");
		
		
		setTitle("URBAN JUNGE " + Constante.NUMERO_DE_VERSION + " SERVER"); // on donne un titre a la fenetre
		setSize(Constante.LARGEUR_FENETRE_PRINCIPALE,Constante.HAUTEUR_FENETRE_PRINCIPALE / 2); // on fixe sa taille
		setLocationRelativeTo(null);// on la place au centre de l'écran
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // on definit l'opération par defaut de fermeture
		setResizable(false);
		setContentPane(panel);
		setVisible(true);
	}
	
	public void updateView(){
		if (server.getJoueurs().size() > 0){
			vueListeJoueurs.updateData(server.getJoueurs());
			vueListeParties.updateData(server.getParties());
			cardLayout.show(panel,"2");
		}else{
			cardLayout.show(panel,"1");
		}
	}
	
	@Override
	public void update(Observable o,Object arg){
		server = (Server)o;
		updateView();
	}
	
}
