package Strtegies;

import Agent.Agent;
import Agent.AgentAction;
import Agent.Bomberman;
import Controler.GameState;

import java.util.ArrayList;
import java.util.Map;

public class StrategyRajion implements Strategy{

	@Override
	public AgentAction chooseAction(Agent agent, GameState game) {

		ArrayList<AgentAction> listAction = new ArrayList<AgentAction>();
		ArrayList<Agent> bombermans = game.getBombermans();

		int x = agent.getX();
		int y = agent.getY();


		//liste de bombermans triée du plus proche au plus lointain par rapport au bomberman
		ArrayList<Agent> bbms_proche = new ArrayList<>();
		if (bombermans.size() != 0) bbms_proche.add(bombermans.get(0));
		else bbms_proche.add(bombermans.get(1));

		//Gère les bomberman mort dans le jeu
		/*for (Agent bomberman: bombermans) {

			if( !bomberman.isDead()) {

				int bbm_x = bomberman.getX();
				int bbm_y = bomberman.getY();

				Bomberman bbm_p =  bbms_proche.get(0);

				int bbm_px = bbm_p.getX();
				int bbm_py = bbm_p.getY();

				if( Math.abs(bbm_x - x) +  Math.abs(bbm_y - y)  <  Math.abs(bbm_px - x) +  Math.abs(bbm_py - y))
					bbms_proche.set(0,bomberman);
				else bbms_proche.add(bomberman);
			}

		}*/

		for (Agent bomberman: bombermans) {


			int bbm_x = bomberman.getX();
			int bbm_y = bomberman.getY();

			int portee = 100;

			if ( (bbm_x >= x-portee) & (bbm_x <= x+portee)  & (bbm_y >= y-portee) & (bbm_y <= y+portee)){


				if( (bbm_x > x) & (bbm_x <= x + portee) ){
					if (getEtat().isLegalMove(new AgentAction(Map.EAST),getAgent())) return new AgentAction(Map.EAST);
					else if(getEtat().isLegalMove(new AgentAction(Map.NORTH),getAgent())) return new AgentAction(Map.NORTH);
					else if(getEtat().isLegalMove(new AgentAction(Map.SOUTH),getAgent())) return new AgentAction(Map.SOUTH);
					else if(getEtat().isLegalMove(new AgentAction(Map.WEST),getAgent())) return new AgentAction(Map.WEST);

					else return new AgentAction(Map.STOP);
				}

				if( (bbm_x < x) & (bbm_x >= x - portee) ){
					if (getEtat().isLegalMove(new AgentAction(Map.WEST),getAgent())) return new AgentAction(Map.WEST);
					else if(getEtat().isLegalMove(new AgentAction(Map.SOUTH),getAgent())) return new AgentAction(Map.SOUTH);
					else if(getEtat().isLegalMove(new AgentAction(Map.NORTH),getAgent())) return new AgentAction(Map.NORTH);
					else if(getEtat().isLegalMove(new AgentAction(Map.EAST),getAgent())) return new AgentAction(Map.EAST);
					else return new AgentAction(Map.STOP);
				}

				if(	(bbm_y < y)& (bbm_y >= y - portee)){
					if (getEtat().isLegalMove(new AgentAction(Map.NORTH),getAgent())) return new AgentAction(Map.NORTH);
					else if(getEtat().isLegalMove(new AgentAction(Map.WEST),getAgent())) return new AgentAction(Map.WEST);
					else if(getEtat().isLegalMove(new AgentAction(Map.EAST),getAgent())) return new AgentAction(Map.EAST);
					else if(getEtat().isLegalMove(new AgentAction(Map.SOUTH),getAgent())) return new AgentAction(Map.SOUTH);
					else return new AgentAction(Map.STOP);
				}

				if(	(bbm_y > y)& (bbm_y <= y + portee)){
					if (getEtat().isLegalMove(new AgentAction(Map.SOUTH),getAgent())) return new AgentAction(Map.SOUTH);
					else if(getEtat().isLegalMove(new AgentAction(Map.EAST),getAgent())) return new AgentAction(Map.EAST);
					else if(getEtat().isLegalMove(new AgentAction(Map.WEST),getAgent())) return new AgentAction(Map.WEST);
					else if(getEtat().isLegalMove(new AgentAction(Map.NORTH),getAgent())) return new AgentAction(Map.NORTH);
					else return new AgentAction(Map.STOP);
				}
			}
		}


		//Choisi une action aléatoire
		for(int i=0;i<=5;i++) {
			if (getEtat().isLegalMove(new AgentAction(i), getAgent()))
				listAction.add(new AgentAction(i));
		}
		if(listAction.size() == 0) return new AgentAction(Map.STOP);
		else return(listAction.get((int)(Math.random()*listAction.size())));
		//return new AgentAction(Map.STOP);
		return AgentAction.MOVE_DOWN;

	}

}
