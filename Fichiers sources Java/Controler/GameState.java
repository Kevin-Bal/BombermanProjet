package Controler;

import java.util.ArrayList;
import java.util.Random;

import Agent.Agent;
import Agent.AgentAction;
import Agent.Bomberman;
import Agent.BombermanFactory;
import Agent.EnemyFactory;
import Item.InfoBomb;
import Item.StateBomb;
import Model.BombermanGame;
import View.InfoBomb;
import View.InfoItem;
import View.Map;

public class GameState {
	private ArrayList<Bomberman> bombermans = new ArrayList<>();
	private ArrayList<Agent> enemies = new ArrayList<>();
	public ArrayList<InfoItem> items = new ArrayList<>();
	private ArrayList<InfoBomb> bombs = new ArrayList<>();
	private ArrayList<InfoBomb> bombsSupprime = new ArrayList<>();
	private boolean brokable_walls[][];

	private BombermanGame game;
	private Map map;
	
	public GameState(Map map, BombermanGame game) {
		this.game = game;
		this.map = map;
		
		ArrayList<Agent> agent = map.getStart_agents();
		BombermanFactory bFactory = new BombermanFactory();
		EnemyFactory eFactory = new EnemyFactory();
		brokable_walls = map.getStart_brokable_walls();
		
		for(Agent a : agent) {
			
			switch(a.getType()) {
			case 'B':
				bombermans.add( (Bomberman) bFactory.createAgent(a.getX(),a.getY(),a.getType(),a.getAgentAction(),a.getColor()));
				break;
			default:
				enemies.add( eFactory.createAgent(a.getX(),a.getY(),a.getType(),a.getAgentAction(),a.getColor()));
				
			}
		}
	}
	
	/*
	public boolean isLegalMove(AgentAction action, Agent agent) {
		int x = agent.getX();
		int y = agent.getY();
		
		switch(action) {
		case MOVE_UP: 
			x --;
			checkForItem(agent);
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
	
	public void moveAgent(AgentAction action, Agent agent) {
		
		int x = agent.getX();
		int y = agent.getY();
		switch(action) {
		case MOVE_UP: 
			x --;
			checkForItem(agent);
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
	}
	*/
	
	public AgentAction GenerateRandomMove() {
	    int pick = new Random().nextInt(AgentAction.values().length); 
	    return AgentAction.values()[pick];
	}

	public void takeTurn() {
		takeTurnEnemies();
		takeTurnBomberman();
		bombTurn();
		game.notifyObservers();
	}
	
	public void takeTurnEnemies() {
		for (Agent enemie : enemies) {
			AgentAction aa=GenerateRandomMove();
			enemie.setAgentAction(aa);
			enemie.setAgentAction(aa);
			if(enemie.isLegalMove(map)) {
				enemie.executeAction();
			}
		}		
	}
	
	public void takeTurnBomberman() {
		for (Bomberman bomberman : bombermans) {
			AgentAction aa=GenerateRandomMove();
			bomberman.setAgentAction(aa);
			if(bomberman.isLegalMove(map)) {
				bomberman.executeAction();
				bomberman.checkForItem(items);
			}
      if(aa == AgentAction.PUT_BOMB) {
				bombs.add(new InfoBomb(bomberman.getX(), bomberman.getY(), b.getRange(), StateBomb.Step1));
			}
				
		}		
	}

  
	public void bombTurn() {
		for(InfoBomb bomb : bombs) {
			switch(bomb.getStateBomb()) {
			case Step1 :
				bomb.setStateBomb(StateBomb.Step2);
				break;
			case Step2 :
				bomb.setStateBomb(StateBomb.Step3);
				break;
			case Step3 :
				bomb.setStateBomb(StateBomb.Boom);
				break;
			case Boom :
				bombsSupprime.add(bomb);
				if(this.brokable_walls[bomb.getX()+bomb.getRange()][bomb.getY()]);
					this.brokable_walls[bomb.getX()+bomb.getRange()][bomb.getY()] = false;
					
				if(this.brokable_walls[bomb.getX()-bomb.getRange()][bomb.getY()]);
					this.brokable_walls[bomb.getX()-bomb.getRange()][bomb.getY()] = false;
					
				if(this.brokable_walls[bomb.getX()][bomb.getY()+bomb.getRange()]);
					this.brokable_walls[bomb.getX()][bomb.getY()+bomb.getRange()] = false;
				
				if(this.brokable_walls[bomb.getX()][bomb.getY()-bomb.getRange()]);
					this.brokable_walls[bomb.getX()][bomb.getY()-bomb.getRange()] = false;
					
				break;
			default :
				break;
			}
		}
		
		for(InfoBomb bomb : bombsSupprime) {
			bombs.remove(bomb);
		}
		bombsSupprime.clear();
	}
	
	//##################################################################################################
	//				GETTERS AND SETTERS
  
  public ArrayList<Bomberman> getBombermans() {
		return bombermans;
	}
  
	public void setBombermans(ArrayList<Bomberman> bombermans) {
		this.bombermans = bombermans;
	}

	public ArrayList<Agent> getEnemies() {
		return enemies;
	}

	public void setEnemies(ArrayList<Agent> enemies) {
		this.enemies = enemies;
	}
	
	public ArrayList<Agent> getAgents(){
		ArrayList<Agent> all = new ArrayList<>();
		all.addAll(this.getBombermans());
		all.addAll(this.getEnemies());
		return all;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
  
	public ArrayList<InfoBomb> getBombs() {
		return bombs;
	}

	public void setBombs(ArrayList<InfoBomb> bombs) {
		this.bombs = bombs;
	}
	
	public boolean[][] getBrokable_walls() {
		return brokable_walls;
	}

	public void setBrokable_walls(boolean[][] brokable_walls) {
		this.brokable_walls = brokable_walls;
	}
	//##################################################################################################	
}
