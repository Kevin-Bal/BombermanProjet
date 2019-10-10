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
		
}
