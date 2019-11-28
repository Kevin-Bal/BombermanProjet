package Agent;

import Controler.AgentAction;
import View.Map;

public class Bird extends Agent {

	public Bird(int x, int y, AgentAction agentAction) {
		super(x, y, agentAction, 'V', ColorAgent.DEFAULT, false, false);
	}

	public void executeAction() {
		super.executeAction();
		System.out.println("My color is " + this.getColor());
		
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
		
		if(x<map.getSizeX() && x>0 && y<map.getSizeY() && y>0) {
			System.out.println(map.getSizeX());
			return true;
		}
			
		return false;
		
	}

}
