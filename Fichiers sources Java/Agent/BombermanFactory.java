package Agent;
import Controler.AgentAction;

public class BombermanFactory implements AgentFactory {

	static int id_color=0;
	ColorAgent[] tabColor= {ColorAgent.BLANC,ColorAgent.BLEU,ColorAgent.JAUNE,ColorAgent.ROUGE,ColorAgent.VERT,ColorAgent.DEFAULT};
	
	@Override
	public Agent createAgent(int x, int y, AgentAction agentAction) {
    	Agent agent = new Bomberman(x, y, tabColor[id_color]);
    	id_color++;
    	return agent;
	}

}
