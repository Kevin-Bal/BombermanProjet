package Agent;

import java.util.ArrayList;

public class Bomberman extends Agent {
	
	private ColorAgent color;
	
	public Bomberman(int x, int y,ColorAgent color) {
		super(x, y);
		this.color = color;
	}
	
}
