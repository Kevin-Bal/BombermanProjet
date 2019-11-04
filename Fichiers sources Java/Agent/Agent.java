package Agent;

public abstract class Agent  {
	
	private int x;
	private int y;
	private AgentAction agentAction;
	
	private int id;
	
	static int cpt_id = 0;
	
	public Agent(int x, int y) {
		this.x=x;
		this.y=y;
		this.setId(cpt_id);
		cpt_id++;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
