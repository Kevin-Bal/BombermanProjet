package Agent;

public class Bomberman extends Agent{

	ColorAgent color;
	//StrategyAgent strategyAgent;
	
	public Bomberman(int x, int y, ColorAgent color) {
		super(x, y);
		this.color = color;	
	}
	
	public void executeAction() {
		super.executeAction();
		System.out.println("My color is " + color);
		//this.strategyAgent.executeMove();
	}
}
