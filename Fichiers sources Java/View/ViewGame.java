package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import Item.InfoItem;
import Model.BombermanGame;

public class ViewGame extends JFrame implements Observer{
	private PanelBomberman jeu_bomberman;
	private PanelCommande jeu_commande;
	private JButton back_to_menu;
	private BombermanGame game;
	
	public ViewGame(String map, BombermanGame game, PanelCommande vc) throws Exception {
		super();
		
		this.game = game;
		this.game.addObserver(this);
		
		//Interface pour choisir la map
		this.game.setMap(new Map(map));

		back_to_menu = new JButton("Retour Menu");
		
		this.jeu_bomberman = new PanelBomberman(this.game.getMap());;
		this.jeu_commande = vc;

		creerBoouton(this);
		
		this.setTitle("Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(this.game.getMap().getSizeX()*30, this.game.getMap().getSizeY()*20+250));
		Dimension windowSize = jeu_bomberman.getSize();
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
        this.add("North",jeu_commande.getjPanelView());
        this.add("Center",jeu_bomberman);
		this.setVisible(true);

	}

	public void creerBoouton(ViewGame view){
		//bouton retour menu
		back_to_menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evenement) {
				System.out.println("Retour Menu");
				game.stop();
				Menu menu = new Menu();
				view.dispose();
			}
		});
		jeu_commande.getjPanelButtons().add(back_to_menu);
	}
	
	@Override
	public void update(Observable o, Object arg) {

		jeu_bomberman.setInfoGame(game.etatJeu.getBrokable_walls(), game.etatJeu.getAgents(), game.etatJeu.getItems(), game.etatJeu.getBombs());
		this.jeu_bomberman.repaint();
	}
	
	

}
