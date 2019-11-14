package Agent;

import Controler.AgentAction;

public interface AgentFactory {
	public Agent createAgent(int x, int y, char type, AgentAction agentAction, ColorAgent col);
}
