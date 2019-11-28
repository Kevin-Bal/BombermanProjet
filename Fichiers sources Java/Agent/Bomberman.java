package Agent;

import java.util.ArrayList;

import Controler.AgentAction;
import View.InfoItem;
import View.Map;

public class Bomberman extends Agent{

	public Bomberman(int x, int y, AgentAction agentAction, ColorAgent color) {
		super(x, y, agentAction, 'B', color, false, false);
		// TODO Auto-generated constructor stub
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
		
		if(map.get_walls()[x][y] || map.getStart_brokable_walls()[x][y] )
			return false;
		else return true;
	}
	
	
	//Vérifie si le perso entre en contact avec un item
	public void checkForItem(ArrayList<InfoItem> items) {
		for(int i=0; i<items.size();++i) {
			InfoItem item = items.get(i);
			if(getX()==item.getX() && getY()==item.getY()) {
				switch(item.getType()) {
				case FIRE_UP:
					break;
				case FIRE_DOWN:
					break;
				case BOMB_UP:
					break;
				case BOMB_DOWN:
					break;
				case FIRE_SUIT:
					setInvincible(true);
					break;
				case SKULL:
					setSick(true);
					break;
				default:
					break;
				}
			}
		}

	}
	
}
