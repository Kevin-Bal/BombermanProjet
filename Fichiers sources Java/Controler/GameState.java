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
	
	public GameState(Map map, BombermanGame game) {
		this.game = game;
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
