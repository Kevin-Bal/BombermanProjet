package Agent;

import Controler.AgentAction;

public class Bomberman extends Agent{

	public Bomberman(int x, int y, AgentAction agentAction, ColorAgent color) {
		super(x, y, agentAction, 'B', color, false, false);
		// TODO Auto-generated constructor stub
	}
	
	
	public void executeAction() {
		super.executeAction();
		System.out.println("My color is " + this.getColor());
		//this.strategyAgent.executeMove();
	}
}
