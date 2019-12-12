package Agent;


import View.Map;


public class Enemy extends Agent {

	public Enemy(int x, int y, AgentAction agentAction) {
		super(x, y, agentAction, 'E', ColorAgent.DEFAULT, false, false);
	}

	public void executeAction() {
		super.executeAction();
		//System.out.println("My color is " + this.getColor());
		
		int x = getX();
		int y = getY();
		
		switch(getAgentAction()) {
		case MOVE_UP: 
			x --;
			break;
		case MOVE_DOWN:
			x ++;
			break;
		case MOVE_LEFT:
			y--;
			break;
		case MOVE_RIGHT:
			y++;
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
			x --;
			break;
		case MOVE_DOWN:
			x ++;
			break;
		case MOVE_LEFT:
			y--;
			break;
		case MOVE_RIGHT:
			y++;
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

}
