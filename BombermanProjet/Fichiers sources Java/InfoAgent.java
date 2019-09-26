package graphics;

public class InfoAgent {
	
	private int x;
	private int y;
	private int dir;
	private Color color;
	private char type;
	
	private boolean isInvincible;
	private boolean isSick;
	
	
	public InfoAgent(int x, int y, int dir, Color color, char type) {
		this.x=x;
		this.y=y;
		this.dir=dir;
		this.color = color;
		this.type = type;
		
		this.isInvincible = false;
		this.isSick = false;
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

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	
	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}


	public boolean isInvincible() {
		return isInvincible;
	}


	public void setInvincible(boolean isInvincible) {
		this.isInvincible = isInvincible;
	}


	public boolean isSick() {
		return isSick;
	}


	public void setSick(boolean isSick) {
		this.isSick = isSick;
	}



	
}
	