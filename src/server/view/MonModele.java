package server.view;

import javax.swing.table.AbstractTableModel;

/**
 * @author omar
 */
public class MonModele extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private Object donnees[][];
	private String titres[];
	
	public MonModele(Object donnees[][],String titres[]){
		this.donnees = donnees;
		this.titres = titres;
	}
	
	public int getColumnCount(){
		if (donnees.length > 0){
			return donnees[0].length;
		}else{
			return 0;
		}
	}
	
	public Object getValueAt(int parm1,int parm2){
		return donnees[parm1][parm2];
	}
	
	public int getRowCount(){
		return donnees.length;
	}
	
	@Override
	public String getColumnName(int col){
		return titres[col];
	}
	
}
