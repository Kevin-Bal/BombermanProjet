package Agent;

public abstract class Agent{
	protected int x;
	protected int y;
	private int id;
	
	static int iter_id = 0;
			
	public Agent(int x, int y) {
		this.x = x;
		this.y = y;
		this.id = iter_id;
		iter_id++;
	}

	
	public void executeAction() {
		System.out.println("Agent " + id + " en position (" + x + "," + y + ")");		
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
