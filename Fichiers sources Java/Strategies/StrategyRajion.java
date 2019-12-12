package Strategies;

import Agent.Agent;
import Agent.AgentAction;
import Agent.Bomberman;
import Agent.Rajion;
import Controler.GameState;

import java.util.ArrayList;
import java.util.Map;

public class StrategyRajion implements Strategy{

	private ArrayList<AgentAction> actions = new ArrayList<>();

	@Override
	public void chooseAction(Agent agent, GameState game) {

		ArrayList<Agent> bombermans = game.getBombermans();
		ArrayList<AgentAction> actions_strat = new ArrayList<>() ;
		actions.add(AgentAction.MOVE_DOWN);
		actions.add(AgentAction. MOVE_LEFT);
		actions.add(AgentAction.STOP);
		actions.add(AgentAction.MOVE_RIGHT);
		actions.add(AgentAction.MOVE_UP);

		Rajion rajion = (Rajion) agent;

		AgentAction action = null;;

		int x = rajion.getX();
		int y = rajion.getY();


		for(AgentAction act : actions){
			int ax = 0;
			int ay = 0;

			switch(act) {
				case MOVE_UP:
					ay--;
					break;
				case MOVE_DOWN:
					ay++;
					break;
				case MOVE_LEFT:
					ax--;
					break;
				case MOVE_RIGHT:
					ay++;
					break;
				case STOP:
					break;
				default :
					break;
			}

			int new_ec = 0;
			int aux_ecart = 0;
			int ecart = 40000;
			
				for(Agent bomberman: bombermans) {

					int xb = bomberman.getX();
					int yb = bomberman.getY();

					int xecb = Math.abs(xb-x);
					int yecb = Math.abs(yb-y);
					aux_ecart = xecb + yecb;

					System.out.println("Ecart : "+aux_ecart);

					if(aux_ecart < ecart) {
						ecart = aux_ecart;
						int depx = Math.abs(x+ax-xb);
						int depy = Math.abs(y+ay-yb);
						System.out.println("Ecart : "+ax+" , "+ay);
						new_ec = depx + depy;
					}

					if(new_ec <= ecart){
						actions_strat.add(act);
					}
				}
			}

		//System.out.println("Taille des actions performante:"+actions_strat.size());

		if(bombermans.size()>0) {
			AgentAction act = actions_strat.get((int) (Math.random() * actions_strat.size()));

			rajion.setAgentAction(act);
			if(rajion.isLegalMove(game.getMap())) {
				rajion.executeAction();
			}
		}
	}
}
