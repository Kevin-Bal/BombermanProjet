package Strategies;

import Agent.Agent;
import Controler.GameState;

public interface Strategy {
	public void chooseAction(Agent agent, GameState game);
}
