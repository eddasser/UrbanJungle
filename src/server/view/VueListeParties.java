package server.view;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import common.Partie;

/**
 * @author omar
 */
public class VueListeParties extends JPanel{
	private JTable table = new JTable();
	private JScrollPane scrollPane = new JScrollPane(table);
	
	private String[] column = { "Parties" };
	private Object[][] data;
	
	public VueListeParties(){
		setLayout(new BorderLayout());
		this.add(scrollPane,BorderLayout.CENTER);
	}
	
	public void updateData(ArrayList<Partie> parties){
		data = new Object[parties.size()][column.length];
		
		for (int i = 0 ; i < parties.size() ; i++){
			data[i][0] = parties.get(i).getNomPartie();
		}
		
		table.setModel(new MonModele(data,column));
		table.setRowHeight(20);
		
		if (data.length > 0){
			TableColumn col = table.getColumnModel().getColumn(0);
			col.setPreferredWidth(50);
		}
	}
	
}
