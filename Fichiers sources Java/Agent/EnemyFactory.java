package Agent;

import Controler.AgentAction;

public class EnemyFactory implements AgentFactory {
	
	private Enemy enemy;
	@Override
	public Agent createAgent(int x, int y, AgentAction agentAction) {
		enemy = new Enemy(x,y);
		return enemy;
	}

}
