
public class Test {

	public static void main(String[] args) {
		SimpleGame sg = new SimpleGame();
		ViewSimpleGame game = new ViewSimpleGame(sg);
		
		sg.init();
		sg.run();
		
		
	}

}
