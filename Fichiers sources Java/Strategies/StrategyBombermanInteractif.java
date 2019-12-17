package Strategies;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Agent.Agent;
import Agent.AgentAction;
import Agent.Bomberman;
import Controler.GameState;

public class StrategyBombermanInteractif implements Strategy,KeyListener {

	private AgentAction currentAction;
	
	//Par défaut, le Bomberman est à l'arrêt au départ
	public StrategyBombermanInteractif() {
		this.currentAction = AgentAction.STOP;
	}

	@Override
	public AgentAction chooseAction(Agent agent, GameState game) {
		Bomberman bomberman = (Bomberman) agent;
        if(bomberman.isLegalMove(game.getMap(),game.getBombermans(), currentAction))
            return currentAction;
        else return  AgentAction.STOP;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.println(arg0.getKeyChar());
		switch(arg0.getKeyChar()) {
		case 'd':
			currentAction = AgentAction.MOVE_RIGHT;
			break;
		case 'q':
			currentAction = AgentAction.MOVE_LEFT;
			break;
		case 's':
			currentAction = AgentAction.MOVE_DOWN;
			break;
		case 'z':
			currentAction = AgentAction.MOVE_UP;
			break;
		case 'f':
			currentAction = AgentAction.PUT_BOMB;
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
