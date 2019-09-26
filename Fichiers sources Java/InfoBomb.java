package graphics;

public class InfoBomb {
	
	private int x;
	private int y;

	private int time;
	private int range;
		 


	public InfoBomb(int x, int y, int range, int time) {
		this.x=x;
		this.y=y;
		this.range=range;
		this.time = time;

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

	public int getTime() {
		return time;
	}


	public void setTime(int time) {
		this.time = time;
	}


	public int getRange() {
		return range;
	}


	public void setRange(int range) {
		this.range = range;
	}


	
}
	