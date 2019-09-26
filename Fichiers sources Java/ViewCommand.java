import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ViewCommand implements Observer{

	private JFrame jFrame;
	private JPanel jPanel;
	private JLabel jLabel;
	int tours;
	Game game;
	
	//Constructeur + Ouvre la fenetre JFrame
	public ViewCommand(Game game){
		tours = 0;
		this.game=game;
		this.game.addObserver(this);
		
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
		jLabel = new JLabel();
		jLabel.setText(Integer.toString(tours)); 		
		
		jPanel.add(jLabel);
		jFrame.add(jPanel);
		jFrame.setVisible(true);
	}
	
	//afficher le compteur de tours à chaque fois qu'il est notifié
	@Override
	public void update(Observable o, Object arg) {
		tours = game.getTurn(); 
		jLabel.setText(Integer.toString(tours));

	}

}
