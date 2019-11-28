package Main;
import Model.BombermanGame;
import View.PanelCommande;
import View.ViewGame;

public class Test {

	public static void main(String[] args) throws Exception {
		BombermanGame sg = new BombermanGame();
		PanelCommande pc = new PanelCommande(sg);
		ViewGame vc = new ViewGame(sg, pc);

		sg.init();
	}
}
