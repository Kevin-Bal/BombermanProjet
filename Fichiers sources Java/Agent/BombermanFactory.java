package Agent;

import java.util.ArrayList;

public class BombermanFactory implements AgentFactory {
	ColorAgent[] colors = {ColorAgent.BLEU, ColorAgent.ROUGE, ColorAgent.VERT, ColorAgent.JAUNE, ColorAgent.BLANC, ColorAgent.DEFAULT};
	private Bomberman bbm;
	static int color_id =0;
	@Override
	public Agent createAgent(int x, int y) {
		
		bbm = new Bomberman(x,y,colors[color_id]);
		color_id ++;
		return bbm;
		
	}

}
