package Controler;

import java.util.ArrayList;

import Agent.Agent;
import Agent.Bomberman;
import Agent.BombermanFactory;
import Agent.EnemyFactory;
import Model.BombermanGame;
import View.Map;

public class GameState {
	private ArrayList<Bomberman> bombermans;
	private ArrayList<Agent> enemies;
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
	
	public void takeTurn() {
		
	}
}
