package Agent;

import Strategies.StrategyRajion;
import View.Map;

public class Rajion extends Agent {

	private StrategyRajion strategyRajion = new StrategyRajion();

	public Rajion(int x, int y, AgentAction agentAction) {
		super(x, y, agentAction, 'R', ColorAgent.DEFAULT, false, false);
	}

	public void executeAction() {
		super.executeAction();

		int x = getX();
		int y = getY();

		switch(getAgentAction()) {
			case MOVE_UP:
				y --;
				break;
			case MOVE_DOWN:
				y ++;
				break;
			case MOVE_LEFT:
				x--;
				break;
			case MOVE_RIGHT:
				x++;
				break;
			case STOP:
				break;
			case PUT_BOMB:
				break;
			default :
				break;
		}
		setX(x);
		setY(y);

	}

	public boolean isLegalMove(Map map) {
		int x = getX();
		int y = getY();

		switch(getAgentAction()) {
			case MOVE_UP:
				y --;
				break;
			case MOVE_DOWN:
				y ++;
				break;
			case MOVE_LEFT:
				x--;
				break;
			case MOVE_RIGHT:
				x++;
				break;
			case STOP:
				break;
			case PUT_BOMB:
				break;
			default :
				break;
		}

		if(map.get_walls()[x][y] || map.getStart_brokable_walls()[x][y] )
			return false;

		else return true;

	}


	public StrategyRajion getStrategyRajion() {
		return strategyRajion;
	}

	public void setStrategyRajion(StrategyRajion strategyRajion) {
		this.strategyRajion = strategyRajion;
	}

}
