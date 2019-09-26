package Model;

public class SimpleGame extends Game{
			
	@Override
	public boolean gameContinue() {
		//System.out.println("Jeu en cours...");
		return this.getMaxturn()>this.getTurn();
	}

	@Override
	public void gameOver() {		
		//System.out.println("Fin du jeu");
	}

	@Override
	public void takeTurn() {		
		//System.out.println("Tour"+ this.getTurn()+"du jeu en cours");
	}

	@Override
	public void initializeGame() {
		//System.out.println("Jeu initialisé");
	}

}
