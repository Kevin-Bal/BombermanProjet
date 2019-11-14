package Main;
import Model.BombermanGame;
import View.ViewBombermanGame;
import View.ViewCommand;

public class Test {

	public static void main(String[] args) throws Exception {
		BombermanGame sg = new BombermanGame();
		ViewBombermanGame game = new ViewBombermanGame(sg);
		ViewCommand vc = new ViewCommand(sg);

		sg.init();
	}

}
