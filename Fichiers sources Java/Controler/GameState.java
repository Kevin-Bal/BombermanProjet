package Controler;

import java.util.ArrayList;
import java.util.Random;

import Agent.Agent;
import Agent.Bird;
import Agent.Rajion;
import Agent.AgentAction;
import Agent.Bomberman;
import Agent.BombermanFactory;
import Agent.EnemyFactory;
import Item.InfoBomb;
import Item.InfoItem;
import Item.ItemType;
import Item.StateBomb;
import Model.BombermanGame;
import View.Map;

public class GameState {
	private int MAX_RANDOM_GENERATE_ITEM=100;
	
	private ArrayList<Agent> bombermans = new ArrayList<>();
	private ArrayList<Agent> enemies = new ArrayList<>();
	private ArrayList<InfoBomb> bombs = new ArrayList<>();
	private ArrayList<InfoBomb> bombsSupprime = new ArrayList<>();
	private ArrayList<InfoItem> items = new ArrayList<>();
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
		items = new ArrayList<>();
		
		
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
	
	public ItemType GenerateRandomItem() {
	    int pick = new Random().nextInt(ItemType.values().length); 
	    return ItemType.values()[pick];
	}

	public int GenerateRandomNumber() {
	    int pick = new Random().nextInt(100); 
	    return pick;
	}
	
	public void takeTurn() {
		takeTurnEnemies();
		takeTurnBomberman();
		checkIfEnemieIsOnBomberman();
		bombTurn();
		game.notifyObservers();
	}

	public void takeTurnEnemies() {
		ArrayList<Agent> enemieSupprime = new ArrayList<>();
		for (Agent enemie : enemies) {
			if(enemie instanceof Bird)
				((Bird) enemie).getStrategyBird().chooseAction(enemie,this);
			else if(enemie instanceof Rajion){
					((Rajion) enemie).getStrategyRajion().chooseAction(enemie,this);
			}
			else{
				AgentAction aa = GenerateRandomMove();
				enemie.setAgentAction(aa);
				if(enemie.isLegalMove(map)) {
					enemie.executeAction();
				}
			}

			if(enemie.isDead()==true) {
				enemieSupprime.add(enemie);
			}
		}
        
		//Remove dead Enemies from list
		for(Agent a: enemieSupprime) {
			enemies.remove(a);
		}
        
	}
	
	public void takeTurnBomberman() {
		ArrayList<Agent> bombermanSupprime = new ArrayList<>();
		for (Agent bomberman : bombermans) {
			Bomberman b = (Bomberman) bomberman;
			AgentAction aa = GenerateRandomMove();
			b.checkForItem(items);
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
			if(b.isDead()==true) {
				bombermanSupprime.add(b);
			}
		}
		
		//Remove dead Enemies from list
		for(Agent a: bombermanSupprime) {
			bombermans.remove(a);
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
				isLegalExplosion(bomb);
					
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
	
	
	private void checkIfEnemieIsOnBomberman() {
		for(Agent bomberman : bombermans) {
			for(Agent enemie : enemies) {
				if(bomberman.getX()==enemie.getX() && bomberman.getY()==enemie.getY())
					bomberman.setDead(true);
			}
		}
	}
	
	/*
	 * Check if Enemies And Bombermans Are touches by a bomb
	 */
	public void checkIfEnemiesAndBombermansAreTouchedByFlames(int x, int y,InfoBomb bomb) {
		for(Agent bomberman : bombermans) {
			if(bomberman.getX()==x && bomberman.getY()==y && !bomberman.isInvincible() && bomberman.getId()!=bomb.getBombermanId()) {
				bomberman.setDead(true);
			}
			break;
		}
		for(Agent enemie : enemies) {
			if(enemie.getX()==x && enemie.getY()==y && !enemie.isInvincible()) {
				enemie.setDead(true);
				System.out.println("true");
				break;
			}
		}
	}
	
	
	/*
	 * Check if a wall is broken or an ennemi is killed by a flame 
	 */
	public void isLegalExplosion(InfoBomb bomb) {
		int x = bomb.getX();
		int y = bomb.getY();
		int range = bomb.getRange();
		
		for(int i = x; i <= x+range; i++ ) {
			
			if( i > 0 && i < map.getSizeX() ) {
				if(map.get_walls()[i][y]) {
					break;
				}
				if(this.brokable_walls[i][y]) {
					this.brokable_walls[i][y] =false;
					if(this.GenerateRandomNumber()<MAX_RANDOM_GENERATE_ITEM) {
						ItemType type = GenerateRandomItem();
						items.add(new InfoItem(i,y,type));
					}
					break;
				}
				checkIfEnemiesAndBombermansAreTouchedByFlames(i,y,bomb);
			}
			
		}
		
		for(int i = x; i >= x-range; i--) {
			
			if( i > 0 && i < map.getSizeX() ) {
				if(map.get_walls()[i][y]) {
					break;
				}
				if(this.brokable_walls[i][y]) {
					this.brokable_walls[i][y] =false;
					if(this.GenerateRandomNumber()<MAX_RANDOM_GENERATE_ITEM) {
						ItemType type = GenerateRandomItem();
						items.add(new InfoItem(i,y,type));
					}
					break;
				}
				checkIfEnemiesAndBombermansAreTouchedByFlames(i,y,bomb);
			}
		}
		
		for(int i = y; i <= y+range; i++ ) {
			
			if( i > 0 && i < map.getSizeY() ) {
				if(map.get_walls()[i][y]) {
					break;
				}
				if(this.brokable_walls[x][i]) {
					this.brokable_walls[x][i] =false;
					if(this.GenerateRandomNumber()<MAX_RANDOM_GENERATE_ITEM) {
						ItemType type = GenerateRandomItem();
						items.add(new InfoItem(x,i,type));
					}
					break;
				}
				checkIfEnemiesAndBombermansAreTouchedByFlames(x,i,bomb);
			}
		}
		
		for(int i = y; i >= y-range; i-- ) {
			
			if( i > 0 && i < map.getSizeY() ) {
				if(map.get_walls()[i][y]) {
					break;
				}
				if(this.brokable_walls[x][i]) {
					this.brokable_walls[x][i] =false;
					if(this.GenerateRandomNumber()<MAX_RANDOM_GENERATE_ITEM) {
						ItemType type = GenerateRandomItem();
						items.add(new InfoItem(x,i,type));
					}
					break;
				}
				checkIfEnemiesAndBombermansAreTouchedByFlames(x,i,bomb);
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


	public ArrayList<InfoItem> getItems() {
		return items;
	}


	public void setItems(ArrayList<InfoItem> items) {
		this.items = items;
	}
	//###########################################################################


	
}
