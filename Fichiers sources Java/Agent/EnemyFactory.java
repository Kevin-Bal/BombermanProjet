package Agent;

public class EnemyFactory implements AgentFactory {
	
	private Enemy enemy;
	@Override
	public Agent createAgent(int x, int y) {
		enemy = new Enemy(x,y);
		return enemy;
	}


}
