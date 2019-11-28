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
				bombermans.add( bFactory.createAgent(a.getX(),a.getY(),a.getType(),a.getAgentAction(),a.getColor()));
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
		bombTurn();
		game.notifyObservers();
	}
	
	public void takeTurnEnemies() {
		for (Agent enemie : enemies) {
			AgentAction aa=GenerateRandomMove();
			enemie.setAgentAction(aa);
			if(enemie.isLegalMove(map)) {
				enemie.executeAction();
			}
		}		
	}
	
	public void takeTurnBomberman() {
		for (Agent bomberman : bombermans) {
			Bomberman b = (Bomberman) bomberman;
			AgentAction aa = GenerateRandomMove();
			b.setAgentAction(aa);
			if(b.isLegalMove(map)) {
				b.executeAction();
			}
			if(aa == AgentAction.PUT_BOMB) {
				int nbOfBombsPerBomberman = 0;
				for(InfoBomb bomb : bombs) {
					if(b.getId()==bomb.getBombermanId())
						nbOfBombsPerBomberman++;
				}
				if(b.getNumberOfBombs()>nbOfBombsPerBomberman) {
					bombs.add(new InfoBomb(bomberman.getX(), bomberman.getY(), b.getRange(), StateBomb.Step1,b.getId()));
				}
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
				isLegalExplosion(bomb.getX(),bomb.getY(),bomb.getRange());
					
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
	
	public void isLegalExplosion(int x, int y, int range) {
		
		for(int i = x; i <= x+range; i++ ) {
			
			if( i > 0 && i < map.getSizeX() ) {
				if(map.get_walls()[i][y]) {
					break;
				}
				else if(this.brokable_walls[i][y]) {
					this.brokable_walls[i][y] =false;
					break;
				}
			}
			
		}
		
		for(int i = x; i >= x-range; i--) {
			
			if( i > 0 && i < map.getSizeX() ) {
				if(map.get_walls()[i][y]) {
					break;
				}
				if(this.brokable_walls[i][y]) {
					this.brokable_walls[i][y] =false;
					break;
				}
			}
		}
		
		for(int i = y; i <= y+range; i++ ) {
			
			if( i > 0 && i < map.getSizeY() ) {
				if(map.get_walls()[i][y]) {
					break;
				}
				if(this.brokable_walls[x][i]) {
					this.brokable_walls[x][i] =false;
					break;
				}
			}
		}
		
		for(int i = y; i >= y-range; i-- ) {
			
			if( i > 0 && i < map.getSizeY() ) {
				if(map.get_walls()[i][y]) {
					break;
				}
				if(this.brokable_walls[x][i]) {
					this.brokable_walls[x][i] =false;
					break;
				}
			}
		}
	}	
	
	//###########################################################################
	//				GETTERS AND SETTERS
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
	
	public boolean[][] getBrokable_walls() {
		return brokable_walls;
	}

	public void setBrokable_walls(boolean[][] brokable_walls) {
		this.brokable_walls = brokable_walls;
	}
	//###########################################################################
	
}
