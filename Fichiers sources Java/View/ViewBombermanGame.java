package View;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.BombermanGame;
import Model.Game;


public class ViewBombermanGame implements Observer {
	
	/**
	 * Classe d'affichage pour le jeu : 
	 * Permet d'avoir les changement operé sur le jeu en temps réel.
	 * Réalise l'affiche de ce dernier.
	 */
	
	///////////----Attributs de la classe----///////////
	private JFrame jFrame;
	
	//jpanel du JFrame -> permet l'organisation du layout
	private PanelBomberman jPanel;
	private Map map;
	
	//Information de l'etat du jeu
	private JLabel tour;
	private JLabel info;

	//Jeu en en cour
	private BombermanGame game;
	
	////////////////////////////////////////////////
	
	public ViewBombermanGame(BombermanGame game) throws Exception {
		//ajout de l'observer dans l'api 
		game.addObserver(this);
		map = new Map("./layouts/alone.lay");
		
		jPanel = new PanelBomberman(map);
		
		this.game = game;
		jFrame = new JFrame();
		jFrame.setTitle("Game");
		jFrame.setSize(new Dimension(jPanel.getTaille_x()*40+600, jPanel.getTaille_y()*40+400));
		
		Dimension windowSize = jFrame.getSize();
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		Point centerPoint = ge.getCenterPoint();
		
		int dx = centerPoint.x - windowSize.width / 2 ;
		int dy = centerPoint.y - windowSize.height / 2 - 350;
		jFrame.setLocation(dx, dy);
		
		
		
		jFrame.add(jPanel);
//		jPanel.setLayout(new GridLayout(1,2));
//		
//		tour = new JLabel();
//		info = new JLabel();
//		
//		jPanel.add(tour);
//		jPanel.add(info);
//		
		jFrame.add(jPanel);
		
		jFrame.setVisible(true);
		
		
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	@Override
	public void update(Observable o, Object arg) {
		//System.out.println("test");
		
		//Permet de réaliser l'affichage d'information sur le jeu
//		if(game.gameContinue()) {
//			//info.setText("Le jeu est en cours ...");
//		}
//		else info.setText("Le jeu est fini");
		
		//mise à jour du nombre de tour dans le jFrame
		//tour.setText("Nombre de tours :"+ Integer.toString(this.game.getTurn()));
	}
	
}

