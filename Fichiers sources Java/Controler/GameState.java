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
import View.Map;

public class GameState {
	private ArrayList<Agent> bombermans = new ArrayList<>();
	private ArrayList<Agent> enemies = new ArrayList<>();
	private ArrayList<InfoBomb> bombs = new ArrayList<>();
	private ArrayList<InfoBomb> bombsSupprime = new ArrayList<>();

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
				bombermans.add( bFactory.createAgent(a.getX(),a.getY(),a.getType(),a.getAgentAction(),a.getColor()));
				break;
			default:
				enemies.add( eFactory.createAgent(a.getX(),a.getY(),a.getType(),a.getAgentAction(),a.getColor()));
				
			}
		}
	}
	
	public boolean isLegalMove(AgentAction action, Agent agent, int x, int y) {
		
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
			agent.setAgentAction(action);
		}
	}
	
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
			//System.out.println(aa);
			moveAgent(aa,enemie);
		}		
	}
	
	public void takeTurnBomberman() {
		for (Agent bomberman : bombermans) {
			Bomberman b = (Bomberman) bomberman;
			AgentAction aa = GenerateRandomMove();
			if(aa == AgentAction.PUT_BOMB) {
				bombs.add(new InfoBomb(bomberman.getX(), bomberman.getY(), b.getRange(), StateBomb.Step1));
			}
			else
				moveAgent(aa,bomberman);
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
	public ArrayList<Agent> getBombermans() {
		return bombermans;
	}

	public void setBombermans(ArrayList<Agent> bombermans) {
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
	
}
