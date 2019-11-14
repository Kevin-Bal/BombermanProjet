package View;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Point;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFileChooser;
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
	
	
	////////////////////////////////////////////////
	
	public ViewBombermanGame(BombermanGame game) throws Exception {
		//ajout de l'observer dans l'api 
		game.addObserver(this);
		
		jPanel = new PanelBomberman(this.game.getMap());
		
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

	}
}

