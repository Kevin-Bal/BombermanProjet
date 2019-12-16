package Strategies;

import Agent.Agent;
import Agent.AgentAction;
import Agent.Bomberman;
import Controler.GameState;
import Item.InfoBomb;
import Item.StateBomb;

import java.util.ArrayList;

public class StrategyBomberman implements Strategy {
    @Override
    public void chooseAction(Agent agent, GameState game) {
        ArrayList<AgentAction> listAction = new ArrayList<AgentAction>();
        ArrayList<Agent> bombermans = game.getBombermans();
        ArrayList<InfoBomb> bombes = game.getBombs();
        ArrayList<Agent> enemies = game.getEnemies();

        Bomberman bomberman = (Bomberman) agent;

        int x = bomberman.getX();
        int y = bomberman.getY();

        int range = bomberman.getRange();

        //Comportement pour éviter les bombes adverses
        for(InfoBomb bombe : bombes){

            if (bombe.getBomberman().getId() != bomberman.getId()) {
                int bombe_x = bombe.getX();
                int bombe_y = bombe.getY();

                int bombe_range = bombe.getRange();

                if((bombe_y == y) & ( x >= bombe_x-bombe_range & x <= bombe_x+bombe_range)){
                    bomberman.setAgentAction(AgentAction.MOVE_UP);
                    if (bomberman.isLegalMove(game.getMap(), bombermans)) listAction.add(AgentAction.MOVE_UP);

                    bomberman.setAgentAction(AgentAction.MOVE_DOWN);
                    if (bomberman.isLegalMove(game.getMap(), bombermans)) listAction.add(AgentAction.MOVE_DOWN);

                    bomberman.setAgentAction(AgentAction.MOVE_RIGHT);
                    if((x == bombe_x-bombe_range) & bomberman.isLegalMove(game.getMap(), bombermans))
                        listAction.add(AgentAction.MOVE_RIGHT);

                    bomberman.setAgentAction(AgentAction.MOVE_LEFT);
                    if((x == bombe_x+bombe_range) & bomberman.isLegalMove(game.getMap(), bombermans))
                        listAction.add(AgentAction.MOVE_LEFT);

                    bomberman.setAgentAction(AgentAction.STOP);
                    if (listAction.size() == 0) bomberman.executeAction();
                    else{
                        AgentAction action = listAction.get((int)(Math.random()*listAction.size()));
                        bomberman.setAgentAction(action);
                        bomberman.executeAction();
                    }

                }else
                    if((bombe_x == x) & ( y >= bombe_y-bombe_range & y <= bombe_y+bombe_range)){
                        bomberman.setAgentAction(AgentAction.MOVE_UP);
                        if (bomberman.isLegalMove(game.getMap(), bombermans)) listAction.add(AgentAction.MOVE_UP);

                        bomberman.setAgentAction(AgentAction.MOVE_DOWN);
                        if (bomberman.isLegalMove(game.getMap(), bombermans)) listAction.add(AgentAction.MOVE_DOWN);

                        bomberman.setAgentAction(AgentAction.MOVE_RIGHT);
                        if((x == bombe_x-bombe_range) & bomberman.isLegalMove(game.getMap(), bombermans))
                            listAction.add(AgentAction.MOVE_RIGHT);

                        bomberman.setAgentAction(AgentAction.MOVE_LEFT);
                        if((x == bombe_x+bombe_range) & bomberman.isLegalMove(game.getMap(), bombermans))
                            listAction.add(AgentAction.MOVE_LEFT);

                        bomberman.setAgentAction(AgentAction.STOP);
                        if (listAction.size() == 0) bomberman.executeAction();
                        else{
                            AgentAction action = listAction.get((int)(Math.random()*listAction.size()));
                            bomberman.setAgentAction(action);
                            bomberman.executeAction();
                        }
                    }
            }

        }

        //Comportement pour placer une bombe quand un ennemi est a portée.
        for(Agent bbm : bombermans){

            if(bbm.getId() != bomberman.getId() & !bbm.isDead()) {
                int bbm_x = bbm.getX();
                int bbm_y = bbm.getY();

                if( (bbm_x == x) & (bbm_y >= y-range & bbm_y <= y+range) ) {
                    bomberman.setAgentAction(AgentAction.PUT_BOMB);

                    int nbOfBombsPerBomberman = 0;
                    for(InfoBomb bomb : bombes) {
                        if(bomberman.getId()==bomb.getBomberman().getId())
                            nbOfBombsPerBomberman++;
                    }
                    if(bomberman.getNumberOfBombs()>nbOfBombsPerBomberman) {
                        bombes.add(new InfoBomb(bomberman.getX(), bomberman.getY(), bomberman.getRange(), StateBomb.Step1,bomberman));
                    }

                    bomberman.executeAction();
                }
                else if( (bbm_y == y) & (bbm_x >= x-range & bbm_x <= x+range) ){
                    bomberman.setAgentAction(AgentAction.PUT_BOMB);

                    int nbOfBombsPerBomberman = 0;
                    for(InfoBomb bomb : bombes) {
                        if(bomberman.getId()==bomb.getBomberman().getId())
                            nbOfBombsPerBomberman++;
                    }
                    if(bomberman.getNumberOfBombs()>nbOfBombsPerBomberman) {
                        bombes.add(new InfoBomb(bomberman.getX(), bomberman.getY(), bomberman.getRange(), StateBomb.Step1,bomberman));
                    }

                    bomberman.executeAction();
                }
            }
        }

        //Stratégie de déplacement
        ArrayList<AgentAction> actions_strat = new ArrayList<>();
        int compteBrokableWalls = 0;
        int compteWalls = 0;

        ArrayList<AgentAction> actions = new ArrayList<>();
        actions.add(AgentAction.MOVE_DOWN);
        actions.add(AgentAction. MOVE_LEFT);
        actions.add(AgentAction.STOP);
        actions.add(AgentAction.MOVE_RIGHT);
        actions.add(AgentAction.MOVE_UP);

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
                    ax --;
                    break;
                case MOVE_RIGHT:
                    ax ++;
                    break;
                case STOP:
                    break;
                default :
                    break;
            }

            if(game.getBrokable_walls()[bomberman.getX()+ax][bomberman.getY()+ay]) compteBrokableWalls++;
            if(game.getBrokable_walls()[bomberman.getX()+ax][bomberman.getY()+ay]) compteWalls++;

            int new_ec = 0;
            int aux_ecart = 0;
            int ecart = 40000;

            for(Agent b: bombermans) {
                if(b.getId() != bomberman.getId()) {
                    int xb = b.getX();
                    int yb = b.getY();

                    int xecb = Math.abs(xb - x);
                    int yecb = Math.abs(yb - y);
                    aux_ecart = xecb + yecb;


                    if (aux_ecart < ecart) {
                        ecart = aux_ecart;
                        int depx = Math.abs(x + ax - xb);
                        int depy = Math.abs(y + ay - yb);
                        new_ec = depx + depy;
                    }

                    if (new_ec <= ecart) {
                        actions_strat.add(act);
                    }
                }
            }

            for(Agent e: enemies) {

                int xe = e.getX();
                int ye = e.getY();

                int xecb = Math.abs(xe - x);
                int yecb = Math.abs(ye - y);
                aux_ecart = xecb + yecb;


                if (aux_ecart < ecart) {
                    ecart = aux_ecart;
                    int depx = Math.abs(x + ax - xe);
                    int depy = Math.abs(y + ay - ye);
                    new_ec = depx + depy;
                }

                if (new_ec <= ecart) {
                    actions_strat.add(act);
                }

            }
        }


        if( compteWalls + compteBrokableWalls > 1){
            bomberman.setAgentAction(AgentAction.PUT_BOMB);
            int nbOfBombsPerBomberman = 0;
            for(InfoBomb bomb : bombes) {
                if(bomberman.getId()==bomb.getBomberman().getId())
                    nbOfBombsPerBomberman++;
            }
            if(bomberman.getNumberOfBombs()>nbOfBombsPerBomberman) {
                bombes.add(new InfoBomb(bomberman.getX(), bomberman.getY(), bomberman.getRange(), StateBomb.Step1,bomberman));
            }
        } else{
            AgentAction act = actions_strat.get((int) (Math.random() * actions_strat.size()));

            bomberman.setAgentAction(act);
            if(bomberman.isLegalMove(game.getMap(), bombermans)) {
                bomberman.executeAction();
            }
        }



    }
}
