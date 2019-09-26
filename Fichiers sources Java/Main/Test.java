package Main;
import Model.SimpleGame;
import View.ViewCommand;
import View.ViewSimpleGame;

public class Test {

	public static void main(String[] args) {
		SimpleGame sg = new SimpleGame();

		ViewCommand vc = new ViewCommand(sg);

		sg.init();
		sg.run();

		ViewSimpleGame game = new ViewSimpleGame(sg);
		
		sg.init();
		sg.run();
	}

}
