package Controler;

import java.util.ArrayList;
import java.util.Random;

import Agent.Agent;
import Agent.Bomberman;
import Agent.BombermanFactory;
import Agent.EnemyFactory;
import Model.BombermanGame;
import View.InfoBomb;
import View.InfoItem;
import View.Map;

public class GameState {
	private ArrayList<Bomberman> bombermans = new ArrayList<>();
	private ArrayList<Agent> enemies = new ArrayList<>();
	public ArrayList<InfoBomb> bombs = new ArrayList<>();
	public ArrayList<InfoItem> items = new ArrayList<>();
	private BombermanGame game;
	private Map map;
	
	public GameState(Map map, BombermanGame game) {
		this.game = game;
		this.map = map;
		
		ArrayList<Agent> agent = map.getStart_agents();
		BombermanFactory bFactory = new BombermanFactory();
		EnemyFactory eFactory = new EnemyFactory();
		
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
				
		}		
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
	//##################################################################################################
	
}
