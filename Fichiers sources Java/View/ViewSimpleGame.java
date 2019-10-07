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

import Model.Game;


public class ViewSimpleGame  implements Observer {
	
	/**
	 * Classe d'affichage pour le jeu : 
	 * Permet d'avoir les changement operé sur le jeu en temps réel.
	 * Réalise l'affiche de ce dernier.
	 */
	
	///////////----Attributs de la classe----///////////
	private JFrame jFrame;
	
	//jpanel du JFrame -> permet l'organisation du layout
	private JPanel jPanel;
	
	//Information de l'etat du jeu
	private JLabel tour;
	private JLabel info;

	//Jeu en en cour
	private Game game;
	
	////////////////////////////////////////////////
	
	public ViewSimpleGame(Game game) {
		//ajout de l'observer dans l'api 
		game.addObserver(this);
		
		this.game =game;
		jFrame = new JFrame();
		jFrame.setTitle("Game");
		jFrame.setSize(new Dimension(400, 100));
		
		Dimension windowSize = jFrame.getSize();
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		Point centerPoint = ge.getCenterPoint();
		
		int dx = centerPoint.x - windowSize.width / 2 ;
		int dy = centerPoint.y - windowSize.height / 2 - 350;
		jFrame.setLocation(dx, dy);
		
		jPanel = new JPanel();
		
		jPanel.setLayout(new GridLayout(1,2));
		
		tour = new JLabel();
		info = new JLabel();
		
		jPanel.add(tour);
		jPanel.add(info);
		
		jFrame.add(jPanel);
		
		jFrame.setVisible(true);
		
		
	}

	@Override
	public void update(Observable o, Object arg) {
		//System.out.println("test");
		
		//Permet de réaliser l'affichage d'information sur le jeu
		if(game.gameContinue()) {
			info.setText("Le jeu est en cours ...");
		}
		else info.setText("Le jeu est fini");
		
		//mise à jour du nombre de tour dans le jFrame
		tour.setText("Nombre de tours :"+ Integer.toString(this.game.getTurn()));
	}
	
}

