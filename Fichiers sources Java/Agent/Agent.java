package Agent;


import Controler.AgentAction;
import View.Map;

public class Agent {
	
	private int x;
	private int y;
	private AgentAction agentAction;
	private ColorAgent color;
	private char type;
	
	
	private int id;
	static int iter_id = 0;
			
	
	private boolean isInvincible;
	private boolean isSick;
	
	public Agent(int x, int y, AgentAction agentAction, char type, ColorAgent color, boolean isInvincible, boolean isSick) {
		this.x=x;
		this.y=y;
		this.agentAction = agentAction;
		this.color = color;
		this.type = type;
		
		this.isInvincible = isInvincible;
		this.isSick = isSick;
		
		this.id = iter_id;
		iter_id++;
	}
	
	public void executeAction() {
        System.out.println("Agent " + id + " en position (" + x + "," + y + ")");        
    }

	
	public int getX() { return x;}
	public void setX(int x) {this.x = x;}

	public int getY() {return y;}
	public void setY(int y) {this.y = y;}
	
	public ColorAgent getColor() {return color;}
	public void setColor(ColorAgent color) {this.color = color;}
	
	
	public char getType() {return type;}
	public void setType(char type) {this.type = type;}


	public boolean isInvincible() {return isInvincible;}
	public void setInvincible(boolean isInvincible) {this.isInvincible = isInvincible;}


	public boolean isSick() {return isSick;}
	public void setSick(boolean isSick) {this.isSick = isSick;}


	public AgentAction getAgentAction() {return agentAction;}
	public void setAgentAction(AgentAction agentAction) {this.agentAction = agentAction;}


	public int getId() {return id;}
	public void setId(int id) {this.id = id;}


	public boolean isLegalMove(Map map) {return false;}
}
	