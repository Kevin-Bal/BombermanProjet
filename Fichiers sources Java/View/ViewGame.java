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

import Agent.Agent;
import Agent.Bomberman;
import Item.InfoItem;
import Model.BombermanGame;
import Model.GameMode;

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

		creerBouton(this);
		
		this.setTitle("Game");
		this.setSize(new Dimension(this.game.getMap().getSizeX()*50, this.game.getMap().getSizeY()*40+250));
		Dimension windowSize = jeu_bomberman.getSize();
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
        this.add("North",jeu_commande.getjPanelView());
        this.add("Center",jeu_bomberman);
		this.setVisible(true);

	}

	public void creerBouton(ViewGame view){
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
		checkEndGame();
	}
	
	/*
	 * Affiche un popup avec les scores des bombermans quand la partie se termine
	 */
	public void checkEndGame(){
		if(game.isEndgame()) {
			String text = "";
			String title ="";
			if(game.getGameMode()==GameMode.PVE) {				
				if(game.getEtatJeu().getBombermans().size()<1)
					title += "Perdu ! \n";
				else
					title += "Gagné ! \n";
				
				for(Agent b : game.getEtatJeu().getBombermans()) {
					Bomberman temp = (Bomberman) b;
					text += "Le Bomberman a fait un score de "+temp.score+"\n";	
				}
				for(Agent b : game.getEtatJeu().getDeadBombermans()) {
					Bomberman temp = (Bomberman) b;
					text += "Le Bomberman a fait un score de "+temp.score+"\n";	
				}
			}
			if(game.getGameMode()==GameMode.PVP) {				
				text += "Gagnants : \n";
				for(Agent b : game.getEtatJeu().getBombermans()) {
					Bomberman temp = (Bomberman) b;
					text += "Le Bomberman "+ temp.getId() +" a fait un score de "+temp.score+"\n";	
				}
				text += "Perdants : \n";
				for(Agent b : game.getEtatJeu().getDeadBombermans()) {
					Bomberman temp = (Bomberman) b;
					text += "Le Bomberman "+ temp.getId() +" a fait un score de "+temp.score+"\n";	
				}				
			}
			createPopup(title,text);
		}
	}
	
	public void createPopup(String title,String text) {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JLabel label = new JLabel();
		
		JTextArea jtext = new JTextArea();
		jtext.setText(text);

		label.setText("<html><h1>"+title+"</h1></br></html>");

		panel.add(label);
		panel.add(jtext);
		frame.add(panel);
	    frame.setSize(800, 400);
	    frame.setVisible(true);
	}
	
	
	public void orderByScore(ArrayList<Agent> bombermans) {
		for(Agent b : bombermans) {
			Bomberman temp = (Bomberman) b;
			//if(temp.score)
		}
	}
}
