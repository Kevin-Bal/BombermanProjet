package View;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Controler.ControleurSimpleGame;
import Model.Game;


public class ViewCommand implements Observer{

	private ControleurSimpleGame controleurSimpleGame;
	private JFrame jFrame;
	private JPanel jPanelView;
	private JPanel jPanelButtons;
	private JPanel jPanelSlider;
	private JLabel jLabel;
	int tours;
	Game game;
	
	//Constructeur + Ouvre la fenetre JFrame
	public ViewCommand(Game game){
		tours = 0;
		this.game=game;
		this.controleurSimpleGame=new ControleurSimpleGame(game, this);
		this.game.addObserver(this);
		
		jFrame = new JFrame();
		jFrame.setTitle("Game");
		jFrame.setSize(new Dimension(600, 200));
		Dimension windowSize = jFrame.getSize();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Point centerPoint = ge.getCenterPoint();		
		int dx = centerPoint.x - windowSize.width / 2 ;
		int dy = centerPoint.y - windowSize.height / 2 - 350;
		jFrame.setLocation(dx, dy);
		
		//Instanciations des JPanels/JLabels
		jPanelView = new JPanel();
		jPanelButtons = new JPanel();
		jPanelSlider = new JPanel();
		jLabel = new JLabel();
				
		
		//Ajout des boutons et slider
		jPanelView.setLayout(new GridLayout(2,1));
		createView();
		
		//Ajouter tous les JPanels à la Frame
		jPanelView.add(jPanelButtons);
		jPanelView.add(jPanelSlider);
		jFrame.add(jPanelView);

		
		jFrame.setVisible(true);
	}
	
	//afficher le compteur de tours à chaque fois qu'il est notifié
	@Override
	public void update(Observable o, Object arg) {
		tours = game.getTurn(); 
		jLabel.setText("Tours : "+Integer.toString(tours));
		jLabel.setHorizontalAlignment(JLabel.CENTER);

	}
	
	//Ajoute les boutons et le slider à la vue
	public void createView(){
		
	//Boutons
		jPanelButtons.setLayout(new GridLayout(1,4));
		
		//Bouton Restart
		Icon icon_restart = new ImageIcon("Icones/icon_restart.png");
		JButton jb_restart= new JButton(icon_restart);
		jb_restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evenement) {
				System.out.println("Restart Game");
				controleurSimpleGame.restart();
			}
		});
		jPanelButtons.add(jb_restart);

		//Bouton Run
		Icon icon_run = new ImageIcon("Icones/icon_run.png");
		JButton jb_run= new JButton(icon_run);
		jb_run.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evenement) {
				System.out.println("Start");
				controleurSimpleGame.start();
			}
		});
		jPanelButtons.add(jb_run);
		
		//Bouton Step		
		Icon icon_step = new ImageIcon("Icones/icon_step.png");
		JButton jb_step = new JButton(icon_step);
		jb_step.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evenement) {
				System.out.println("Step");
				controleurSimpleGame.step();
			}
		});
		jPanelButtons.add(jb_step);	
		
		
		//Bouton Pause
		Icon icon_pause = new ImageIcon("Icones/icon_pause.png");
		JButton jb_pause = new JButton(icon_pause);
		jb_pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evenement) {
				System.out.println("Pause Game");
				controleurSimpleGame.stop();
			}
		});
		jPanelButtons.add(jb_pause);
		
		
	//SliderPanelButtons.setLayout(new GridLayout(1,4));
		JPanel jPanelHor = new JPanel();
		
		jPanelSlider.setLayout(new GridLayout(1,2));
		jPanelHor.setLayout(new GridLayout(2,1));
		
		JLabel jl=new JLabel();
		jl.setText("Number of turns per seconds");
		jl.setHorizontalAlignment(JLabel.CENTER);
		jPanelHor.add(jl);
		
		JSlider js = new JSlider(1,10,2);
		// paint the ticks and tracks 
		js.setMajorTickSpacing(1);
		js.setMinorTickSpacing(1);
		js.setPaintTicks(true); 
		js.setPaintLabels(true);
		js.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				int time = js.getValue();
				controleurSimpleGame.setTime(time);
			}
		});
		
		jPanelHor.add(js);
		jPanelSlider.add(jPanelHor);
		
	//Turn
		jPanelSlider.add(jLabel);
		
		
	}
	

}
