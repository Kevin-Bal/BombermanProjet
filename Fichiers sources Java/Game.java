import java.util.Observable;

public abstract class Game extends Observable implements Runnable{
	private int turn;
	private int maxturn=50;
	private boolean isRunning;
	private Thread thread;
	private double time =100;
		
	//Initialise le jeu
	public void init() {
		this.turn = 0;
		this.isRunning=true;
		initializeGame();
	}

	
	//Effectue un tour de jeu
	public void step() {
		++turn;
		
		if(gameContinue())
			takeTurn();
		else {
			isRunning=false;
			gameOver();
		}
		
	}

	//Lance le jeu en tour par tour 
	public void run() {
		while(isRunning) {
			step();
			this.setChanged();
			this.notifyObservers();
			try {
				Thread.sleep((long) time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	//Met le jeu en pause
	public void stop() {
		isRunning = false;
	}
	
	
	//Lancement du thread
	public void launch() {
		isRunning=true;
		thread = new Thread(this);
		thread.start();
	}
	
	
	public abstract boolean gameContinue();		//Vérifie que le jeu soit fini ou non
	public abstract void gameOver();			//Affiche le message de fin du jeu
	public abstract void takeTurn();			
	public abstract void initializeGame();		//Initialise le jeu en sous classe
	
	
	
	//#################################################
	//				Getters et Setters
	public int getTurn() {
		return turn;
	}


	public void setTurn(int turn) {
		this.turn = turn;
	}


	public int getMaxturn() {
		return maxturn;
	}


	public void setMaxturn(int maxturn) {
		this.maxturn = maxturn;
	}


	public boolean isRunning() {
		return isRunning;
	}


	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	//#################################################
	
}
