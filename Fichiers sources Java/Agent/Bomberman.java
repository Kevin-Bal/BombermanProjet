package Agent;


import java.util.ArrayList;

import Item.InfoBomb;
import Item.InfoItem;
import Item.StateBomb;
import View.Map;


public class Bomberman extends Agent{
	private int range;
	private int numberOfBombs;
	
	int numberOfInvincibleTurns;
	
	public Bomberman(int x, int y, AgentAction agentAction, ColorAgent color) {
		super(x, y, agentAction, 'B', color, false, false);
		this.setRange(1);
		numberOfBombs =1;
		numberOfInvincibleTurns=0;
	}
	
	public void executeAction() {
		super.executeAction();
		
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
		default :
			break;
		}
			setX(x);
			setY(y);
			
			
		IterateInvincibleCountdown();
		
	}
	
	//Vérifie si on le déplacement est possible ou non, en fonction des murs
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
					setRange(getRange()+1);
					break;
				case FIRE_DOWN:
					if(getRange()>1)
						setRange(getRange()-1);
					break;
				case BOMB_UP:
					setNumberOfBombs(getNumberOfBombs()+1);
					break;
				case BOMB_DOWN:
					if(getNumberOfBombs()>1)
						setNumberOfBombs(getNumberOfBombs()-1);
					break;
				case FIRE_SUIT:
					setInvincible(true);
					numberOfInvincibleTurns=0;
					break;
				case SKULL:
					setSick(true);
					break;
				default:
					break;
				}
				items.remove(i);
			}
		}
		

	}


	private void IterateInvincibleCountdown() {
		if(numberOfInvincibleTurns>3)
			setInvincible(false);
		else
			numberOfInvincibleTurns++;
	}

	//##########################################################
	//				GETTERS AND SETTERS
	public int getRange() {
		return range;
	}


	public void setRange(int range) {
		this.range = range;
	}

	public int getNumberOfBombs() {
		return numberOfBombs;
	}
	public void setNumberOfBombs(int numberOfBombs) {
		this.numberOfBombs = numberOfBombs;
	}
	//##########################################################
}
