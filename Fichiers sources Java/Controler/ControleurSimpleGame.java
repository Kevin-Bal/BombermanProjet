package Controler;

import Model.Game;
import View.ViewCommand;
import View.ViewSimpleGame;


public class ControleurSimpleGame implements InterfaceControleur {

	private Game game;
	
	public ControleurSimpleGame(Game g, ViewCommand vc) {
		this.game=g;
		ViewCommand viewCommand =  vc;
		ViewSimpleGame viewSimpleGame = new ViewSimpleGame(g);
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
