
public class Test {

	public static void main(String[] args) {
		SimpleGame sg = new SimpleGame();
		ViewCommand vc = new ViewCommand(sg);

		sg.init();
		sg.run();
	}

}
