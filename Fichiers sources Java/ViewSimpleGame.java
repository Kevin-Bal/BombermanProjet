import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ViewSimpleGame  implements Observer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame jFrame;
	private JPanel jPanel;
	private JLabel tour;
	private Game game;
	
	public ViewSimpleGame(Game game) {
		//ajout de l'observer dans l'api 
		game.addObserver(this);
		
		this.game =game;
		jFrame = new JFrame();
		jFrame.setTitle("Game");
		jFrame.setSize(new Dimension(700, 700));
		
		Dimension windowSize = jFrame.getSize();
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		Point centerPoint = ge.getCenterPoint();
		
		int dx = centerPoint.x - windowSize.width / 2 ;
		int dy = centerPoint.y - windowSize.height / 2 - 350;
		jFrame.setLocation(dx, dy);
		
		jPanel = new JPanel();
		tour = new JLabel();
		
		jPanel.add(tour);
		jFrame.add(jPanel);
		
		
		
		jFrame.setVisible(true);
		
		
	}
	


	@Override
	public void update(Observable o, Object arg) {
		//mise à jour du nombre de tour dans le jFrame
		System.out.println("test");
		tour.setText( Integer.toString(this.game.getTurn()));
	}

}