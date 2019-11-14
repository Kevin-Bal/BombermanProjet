package Model;

import Controler.GameState;
import View.Map;

public class BombermanGame extends Game{

	private Map map;
	public GameState etatJeu;
	
	@Override
	public boolean gameContinue() {
		//System.out.println("Jeu en cours...");
		return this.getMaxturn()>this.getTurn();
	}

	@Override
	public void gameOver() {		
		//System.out.println("Fin du jeu");
	}

	@Override
	public void takeTurn() {		
		//System.out.println("Tour"+ this.getTurn()+"du jeu en cours");
		this.etatJeu.takeTurn();
		this.notifyObservers();	
	}

	@Override
	public void initializeGame() {
		this.etatJeu = new GameState(this.map,this);
	}

	
	//################################################################################
	//			Getters and Setters
	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
	//################################################################################
}
