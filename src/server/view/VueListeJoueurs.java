package server.view;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import common.Constante;
import common.Joueur;

/**
 * @author omar
 */
public class VueListeJoueurs extends JPanel{

	private static final long serialVersionUID = Constante.NUMERO_DE_VERSION;
	private JTable table = new JTable();
	private JScrollPane scrollPane = new JScrollPane(table);
	
	private String[] column = { "Login des Joueurs connectés au serveur" };
	private Object[][] data;
	
	public VueListeJoueurs(){
		setLayout(new BorderLayout());
		this.add(scrollPane,BorderLayout.CENTER);
	}
	
	public void updateData(ArrayList<Joueur> joueurs){
		data = new Object[joueurs.size()][column.length];
		
		for (int i = 0 ; i < joueurs.size() ; i++){
			data[i][0] = joueurs.get(i).getLogin();
		}
		
		table.setModel(new MonModele(data,column));
		table.setRowHeight(20);
		
		if (data.length > 0){
			TableColumn col = table.getColumnModel().getColumn(0);
			col.setPreferredWidth(50);
		}
	}
	
}
