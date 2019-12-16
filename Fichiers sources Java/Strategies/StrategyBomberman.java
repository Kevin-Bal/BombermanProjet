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

        //Choisi une action aléatoire
        AgentAction aa = game.GenerateRandomMove();
        bomberman.setAgentAction(aa);
        if(aa == AgentAction.PUT_BOMB) {
            bomberman.setAgentAction(AgentAction.STOP);
            bomberman.executeAction();
        }else{
            if(bomberman.isLegalMove(game.getMap(), bombermans)) {
                bomberman.executeAction();
            }
        }

    }
}
