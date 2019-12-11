package Strtegies;

import Agent.Agent;
import Agent.AgentAction;
import Controler.GameState;
import Agent.Bird;

import java.util.ArrayList;

public class StrategyBird implements Strategy {
    @Override
    public AgentAction chooseAction(Agent agent, GameState game) {

        ArrayList<Agent> bombermans = game.getBombermans();
        ArrayList<AgentAction> actions = new ArrayList<>();
        AgentAction action = null;;

        Bird bird = (Bird) agent;

        int x = bird.getX();
        int y = bird.getY();



        for(int k=0;k<5;k++){
            AgentAction tmp_action = new AgentAction(k);

            if(!bird.isEndormie()) {

                for(int j =0; j< bombermans.size(); j++) {
                    Agent b = bombermans.get(j);
                    int xb = b.getX();
                    int yb = b.getY();

                    int xecb = Math.abs(xb-x);
                    int yecb = Math.abs(yb-y);
                    int becart = xecb + yecb;

                    if(becart < 5) {
                        bird.setEndormie(false);

                    }
                }
                action = AgentAction.STOP;
            }
            else {
                int new_ec = 0;
                int aux_ecart = 0;
                int ecart = 40000;
                for(Agent bomberman: bombermans) {

                    int xb = bomberman.getX();
                    int yb = bomberman.getY();

                    int xecb = Math.abs(xb-x);
                    int yecb = Math.abs(yb-y);
                    aux_ecart = xecb + yecb;

                    if(aux_ecart < ecart) {
                        ecart = aux_ecart;
                        int depx = Math.abs(x+tmp_action.getVx()-xb);
                        int depy = Math.abs(y+tmp_action.getVy()-yb);
                        new_ec = depx + depy;
                    }

                    if(new_ec < ecart)
                        if (getEtat().isLegalMoveBird(tmp_action, getAgent()))
                            actions.add(tmp_action);

                    if(ecart > 8)
                        bird.setEndormie(true);
                }


            }
            if  (actions.size() > 0) action = actions.get((int)(Math.random()*actions.size()));
        }


        return action;
    }
}
