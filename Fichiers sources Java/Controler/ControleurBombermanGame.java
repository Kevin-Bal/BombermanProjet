package Controler;

import Model.BombermanGame;
import Model.Game;
import View.ViewBombermanGame;
import View.ViewCommand;

public class ControleurBombermanGame implements InterfaceControleur {
	
	private Game game;
	
	public ControleurBombermanGame(Game g) throws Exception {
		this.game = g;
		ViewBombermanGame viewJeu = new ViewBombermanGame(game);
		ViewCommand viewCommand = new ViewCommand(game);
	}
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		game.launch();
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTime(double time) {
		// TODO Auto-generated method stub
		
	}

}
