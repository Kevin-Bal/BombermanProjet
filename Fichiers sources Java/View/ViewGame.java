package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Item.InfoItem;
import Model.BombermanGame;

public class ViewGame extends JFrame implements Observer{
	private PanelBomberman jeu_bomberman;
	private PanelCommande jeu_commande;
	
	private BombermanGame game;
	
	public ViewGame(String map, BombermanGame game, PanelCommande vc) throws Exception {
		super();
		
		this.game = game;
		this.game.addObserver(this);
		
		//Interface pour choisir la map
		this.game.setMap(new Map(map));

		
		this.jeu_bomberman = new PanelBomberman(this.game.getMap());;
		this.jeu_commande = vc;
		
		this.setTitle("Game");
		this.setSize(new Dimension(jeu_bomberman.getTaille_x()*50+1024, jeu_bomberman.getTaille_y()*40+600));
		Dimension windowSize = jeu_bomberman.getSize();
		
		this.setLayout(new BorderLayout());
        this.add("North",jeu_commande.getjPanelView());
        this.add("Center",jeu_bomberman);
		this.setVisible(true);
	}
	
	@Override
	public void update(Observable o, Object arg) {

		jeu_bomberman.setInfoGame(game.etatJeu.getBrokable_walls(), game.etatJeu.getAgents(), new ArrayList<InfoItem>(), game.etatJeu.getBombs());
		this.jeu_bomberman.repaint();
	}
	
	

}
