package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import agent.Bomberman;
import agent.BasicEnnemy;
import agent.Rajion;
import bomberman.BombermanGame;
import bomberman.Game;
import objets.Bomb;
import objets.Objet;
import objets.ItemType;
import agent.Bird;


/** 
 * Classe qui permet de charger d'afficher le panneau du jeu à partir d'une carte et de listes d'agents avec leurs positions.
 * Inspiré du code de Kevin Balavoine et Victor Lelu--Ribaimont.
 */


public class PanelBomberman extends JPanel{

	private static final long serialVersionUID = 1L;
	protected Color wallColor=Color.GRAY;
	protected Color brokable_walls_Color=Color.lightGray;
	protected Color ground_Color= new Color(50,175,50);

	
	
	private int taille_x;
	private int taille_y;

	float[] contraste = { 0, 0, 0, 1.0f };
	float[] invincible = { 200, 200, 200, 1.0f };
	float[] skull = { 0.5f, 0.5f, 0.5f, 0.75f };

	private Map map;


	protected ArrayList<InfoAgent> listInfoAgents;
	protected ArrayList<InfoItem> listInfoItems;
	protected ArrayList<InfoBomb> listInfoBombs;
	
	
	private boolean breakable_walls[][];

	int cpt;

	public PanelBomberman(Map map) {

		this.map = map;

		this.breakable_walls = map.getStart_brokable_walls();
		
		listInfoAgents = map.getStart_agents();
		
		System.out.println("listInfoAgents.size()");
		System.out.println(listInfoAgents.size());
		
		listInfoItems = new ArrayList<InfoItem>();
		listInfoBombs = new ArrayList<InfoBomb>();
		
	}

	public void paint(Graphics g){

		System.out.println("paint");
		
		int fen_x=getSize().width;
		int fen_y=getSize().height;

		g.setColor(ground_Color);
		g.fillRect(0, 0,fen_x,fen_y);

		double stepx=fen_x/(double)taille_x;
		double stepy=fen_y/(double)taille_y;
		double position_x=0;

		taille_x= map.getSizeX();
		taille_y= map.getSizeY();
		   
		boolean[][] walls = map.get_walls();
		
		for(int x=0; x<taille_x; x++)
		{
			double position_y = 0 ;
			
			
			for(int y=0; y<taille_y; y++)
			{
				//Création des murs
				if (walls[x][y]){

					try {
						Image img = ImageIO.read(new File("./image/wall.png"));
						g.drawImage(img, (int)position_x, (int)position_y, (int)stepx, (int)stepy, this);

	
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				//Création des briques destructibles
				else if (this.breakable_walls[x][y]){

					try {
						Image img = ImageIO.read(new File("./image/brique_2.png"));
						g.drawImage(img, (int)position_x, (int)position_y, (int)stepx, (int)stepy, this);
					} catch (IOException e) {
						e.printStackTrace();
					}

				}else {
					try {
						Image img = ImageIO.read(new File("./image/grass.png"));
						g.drawImage(img, (int)position_x, (int)position_y, (int)stepx, (int)stepy, this);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				position_y+=stepy;				
			}
			position_x+=stepx;
		}


		for(int i = 0; i < listInfoItems.size(); i++){
			dessine_Items(g, listInfoItems.get(i));	
		}

		for (int j = 0 ; j < listInfoBombs.size() ; j++) {
			dessine_Bomb(g, listInfoBombs.get(j));
		}

		for(int i = 0; i < listInfoAgents.size(); i++){
			dessine_Agent(g,listInfoAgents.get(i));	
		}

	}


	void dessine_Agent(Graphics g, InfoAgent infoAgent)
	{
		
		int fen_x = getSize().width;
		int fen_y = getSize().height;

		double stepx = fen_x/(double)taille_x;
		double stepy = fen_y/(double)taille_y;

		int px = infoAgent.getX();
		int py = infoAgent.getY();

		double pos_x=px*stepx;
		double pos_y=py*stepy;

		int direc_en = infoAgent.getDir();

		BufferedImage img = null;
		
		try {
			if(infoAgent.getType() == 'R') {
				img = ImageIO.read(new File("./image/" + infoAgent.getType() + direc_en + this.cpt % 2 + ".png"));	
			}else {
				img = ImageIO.read(new File("./image/" + infoAgent.getType() + direc_en + this.cpt % 3 + ".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}			

		if(infoAgent.getType() == 'B') {
			float [] scales = null;

			switch(infoAgent.getColor())
			{
			case ROUGE :
				scales = new float[]{3 ,0.75f, 0.75f, 1.0f };
				break;
			case VERT :
				scales = new float[]{0.75f ,3, 0.75f, 1.0f };
				break;
			case BLEU :
				scales = new float[]{0.75f ,0.75f, 3, 1.0f };
				break;
			case JAUNE :
				scales = new float[]{3 ,3, 0.75f, 1.0f };
				break;
			case BLANC :
				scales = new float[]{2 ,2, 2, 1.0f };
				break;
			case DEFAULT :
				scales = new float[]{1 ,1, 1, 1.0f };
				break;
			}
			
			if (infoAgent.isInvincible() & cpt % 2 == 0)
				contraste = invincible;
			else contraste = new float[]{ 0, 0, 0, 1.0f };
			
			if (infoAgent.isSick() & cpt % 2 == 0)
				scales = skull;
			
			RescaleOp op = new RescaleOp(scales, contraste, null);
			img = op.filter( img, null);
		
		}
		
		
		if(img != null) {
			g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
		}
		
		cpt++;
	}

	
	
	


	void dessine_Items(Graphics g, InfoItem item){

		int fen_x = getSize().width;
		int fen_y = getSize().height;

		double stepx = fen_x/(double)taille_x;
		double stepy = fen_y/(double)taille_y;

		int px = item.getX();
		int py = item.getY();

		double pos_x=px*stepx;
		double pos_y=py*stepy;

		if (item.getType() == ItemType.FIRE_UP) {
			try {
				Image img = ImageIO.read(new File("./image/Item_FireUp.png"));
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (item.getType() == ItemType.FIRE_DOWN) {
			try {
				Image img = ImageIO.read(new File("./image/Item_FireDown.png"));
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (item.getType() == ItemType.BOMB_UP) {
			try {
				Image img = ImageIO.read(new File("./image/Item_BombUp.png"));
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (item.getType() == ItemType.BOMB_DOWN) {
			try {
				Image img = ImageIO.read(new File("./image/Item_BombDown.png"));
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (item.getType() == ItemType.FIRE_SUIT) {
			try {
				Image img = ImageIO.read(new File("./image/Item_FireSuit.png"));
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (item.getType() == ItemType.SKULL) {
			try {
				Image img = ImageIO.read(new File("./image/Item_Skull.png"));
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}



	void dessine_Bomb(Graphics g, InfoBomb bomb)
	{
		int fen_x = getSize().width;
		int fen_y = getSize().height;

		double stepx = fen_x/(double)taille_x;
		double stepy = fen_y/(double)taille_y;

		int px = bomb.getX();
		int py = bomb.getY();

		double pos_x=px*stepx;
		double pos_y=py*stepy;

		if (bomb.getTime() == 0 ){

			try {
				Image img = ImageIO.read(new File("./image/Bomb_0.png"));
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (bomb.getTime() == 1 ){

			try {
				Image img = ImageIO.read(new File("./image/Bomb_1_jaune.png"));
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (bomb.getTime() == 2 ){

			try {
				Image img = ImageIO.read(new File("./image/Bomb_2_rouge.png"));
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}else if (bomb.getTime() > 2 ){

			try {
				Image img = ImageIO.read(new File("./image/Range_CENTRE.png"));
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			} catch (IOException e) {
				e.printStackTrace();
			}

			int range = bomb.getRange();

			for (int i = 1 ; i <= range; i++){

				if(py+i < map.getSizeY()) {
					if((py+i == map.getSizeY()-1) || i == range ) {
						try {
							Image img = ImageIO.read(new File("./image/Range_NORTH.png"));
							g.drawImage(img, (int)pos_x, (int)(pos_y + (stepy*i)), (int)stepx, (int)stepy, this);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}else {
						try {
							Image img = ImageIO.read(new File("./image/Range_NORTH_Fin.png"));
							g.drawImage(img, (int)pos_x, (int)(pos_y + (stepy*i)), (int)stepx, (int)stepy, this);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}

				if(py-i >= 0) {
					if(py-i == 0 || i == range) {
						try {
							Image img = ImageIO.read(new File("./image/Range_SOUTH.png"));
							g.drawImage(img, (int)pos_x, (int)(pos_y - (stepy*i)), (int)stepx, (int)stepy, this);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}else {
						try {
							Image img = ImageIO.read(new File("./image/Range_SOUTH_Fin.png"));
							g.drawImage(img, (int)pos_x, (int)(pos_y - (stepy*i)), (int)stepx, (int)stepy, this);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}

				if(px+i < map.getSizeX()) {
					if((px+i == map.getSizeX()-1) || i == range ) {
						try {
							Image img = ImageIO.read(new File("./image/Range_EAST.png"));
							g.drawImage(img, (int)(pos_x + (stepy*i)), (int)(pos_y), (int)stepx, (int)stepy, this);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}else {
						try {
							Image img = ImageIO.read(new File("./image/Range_EAST_Fin.png"));
							g.drawImage(img, (int)(pos_x + (stepy*i)), (int)(pos_y), (int)stepx, (int)stepy, this);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}



				if(px-i >= 0) {
					if(px-i == 0 || i == range) {
						try {
							Image img = ImageIO.read(new File("./image/Range_WEST.png"));
							g.drawImage(img, (int)(pos_x - (stepy*i)), (int)(pos_y), (int)stepx, (int)stepy, this);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}else {
						try {
							Image img = ImageIO.read(new File("./image/Range_WEST_Fin.png"));
							g.drawImage(img, (int)(pos_x - (stepy*i)), (int)(pos_y), (int)stepx, (int)stepy, this);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}


			}

		}

	}



	public void setInfoGame(boolean[][] breakable_walls, ArrayList<InfoAgent> listInfoAgents, ArrayList<InfoItem> listInfoItems, ArrayList<InfoBomb> listInfoBombs) {
		
		this.listInfoAgents = listInfoAgents;
		this.listInfoItems = listInfoItems;
		this.listInfoBombs = listInfoBombs;
		this.breakable_walls = breakable_walls;
		
	}






}
