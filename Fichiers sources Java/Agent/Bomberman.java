package Agent;

public class Bomberman extends Agent{
	private int range;
	public Bomberman(int x, int y, AgentAction agentAction, ColorAgent color) {
		super(x, y, agentAction, 'B', color, false, false);
		this.setRange(6);
		// TODO Auto-generated constructor stub
	}
	
	
	public void executeAction() {
		super.executeAction();
		System.out.println("My color is " + this.getColor());
		//this.strategyAgent.executeMove();
	}


	public int getRange() {
		return range;
	}


	public void setRange(int range) {
		this.range = range;
	}
}
