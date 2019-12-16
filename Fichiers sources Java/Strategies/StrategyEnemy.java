package Strategies;

import Agent.Agent;
import Agent.AgentAction;
import Agent.Enemy;
import Controler.GameState;

public class StrategyEnemy implements Strategy {
    @Override
    public AgentAction chooseAction(Agent agent, GameState game) {
        Enemy enemy = (Enemy) agent;
        AgentAction aa = game.GenerateRandomMove();
        if(enemy.isLegalMove(game.getMap(), aa))
            return aa;
        else return  AgentAction.STOP;
    }
}
