package Controler;

import Model.BombermanGame;
import Model.Game;
import View.ViewBombermanGame;
import View.ViewCommand;


public class ControleurBombermanGame implements InterfaceControleur {

	private BombermanGame game;
	
	public ControleurBombermanGame(BombermanGame g, ViewCommand vc) throws Exception {
		this.game=g;
		ViewCommand viewCommand =  vc;
		ViewBombermanGame viewJeu = new ViewBombermanGame(game); 
	}
	
	@Override
	public void start() {
		game.launch();
	}

	@Override
	public void step() {
		game.step();
	}

	@Override
	public void run() {
		game.run();
	}

	@Override
	public void stop() {
		game.stop();
	}
	
	@Override
	public void restart() {
		game.init();
		game.launch();
	}
	
	@Override
	public void setTime(double time) {
		game.setTime(time);
	}



}
