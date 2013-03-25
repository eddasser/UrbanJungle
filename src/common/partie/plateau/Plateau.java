package common.partie.plateau;

import java.util.ArrayList;

public class Plateau {
	private ArrayList<Case> cases;
	
 	public Plateau(){
		cases = new ArrayList<Case>();
	}

	public Plateau(ArrayList<Case> cases) {
		super();
		this.cases = cases;
	}

	public ArrayList<Case> getCases() {
		return cases;
	}

	public void setCases(ArrayList<Case> cases) {
		this.cases = cases;
	}
}
