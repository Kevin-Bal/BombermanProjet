package Controler;

import java.util.ArrayList;
import java.util.Random;

import Agent.Agent;
import Agent.Bomberman;
import Agent.BombermanFactory;
import Agent.EnemyFactory;
import Model.BombermanGame;
import View.Map;

public class GameState {
	private ArrayList<Bomberman> bombermans = new ArrayList<>();
	private ArrayList<Agent> enemies = new ArrayList<>();
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
	
	public boolean isLegalMove(AgentAction action, Agent agent, int x, int y) {
		
		if(map.get_walls()[agent.getX()+x][ agent.getY()+y] || map.getStart_brokable_walls()[agent.getX()+x][ agent.getY()+y] )
			return false;
		else return true;
	}
	
	public void moovAgent(AgentAction action, Agent agent) {
		
		int x = 0;
		int y = 0;
		switch(action) {
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
		if(isLegalMove( action, agent, x, y)) {
			agent.setX(x);
			agent.setY(y);
		}
	}
	
	public AgentAction GenerateRandomMove() {
	    int pick = new Random().nextInt(AgentAction.values().length); 
	    return AgentAction.values()[pick];
	}

	public void takeTurn() {
		takeTurnEnemies();
		takeTurnBomberman();
	}
	
	public void takeTurnEnemies() {
		for (Agent enemie : enemies) {
			AgentAction aa=GenerateRandomMove();
			System.out.println(aa);
			moveAgent(enemie,aa);
		}		
	}
	
	public void takeTurnBomberman() {
		for (Bomberman bomberman : bombermans) {
			AgentAction aa=GenerateRandomMove();
			System.out.println(aa);
			moveAgent(bomberman,aa);
		}		
	}
}
