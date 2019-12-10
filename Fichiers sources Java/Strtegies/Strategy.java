package Strtegies;

import Agent.AgentAction;
import Controler.GameState;

public interface Strategy {
	public AgentAction chooseAction(GameState game);
}
