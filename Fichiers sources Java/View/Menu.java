package View;

import Model.BombermanGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Menu extends JFrame {
    //choix du stage
    private JComboBox<File> liste_lay;
    private String content = null;

    private JPanel top = null;
    private JPanel reviewMap = null;

    private BombermanGame game = null;
    private PanelCommande pc = null;
    private JButton jouer = null;

    public Menu() throws HeadlessException {
        this.liste_lay = liste_lay;
        this.setTitle("Menu Jeu Bomberman");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        setSize(400, 500);
        top = new JPanel();
        top.setLayout(new GridLayout(2,1));
        game = new BombermanGame();

        File repertoire = new File("./layouts/");
        File[] files=repertoire.listFiles();

        liste_lay = new JComboBox<File>(files);
        jouer = new JButton("Jouer");
        content = liste_lay.getSelectedItem().toString();

        Map map = null;
        try {
            map = new Map(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        reviewMap = new PanelBomberman(map);

        top.add(liste_lay);
        top.add(jouer);
        this.add("North",top);
        this.add("Center",reviewMap);

        try {
            pc = new PanelCommande(game);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //setSize(reviewMap.getX()*40+5*25, reviewMap.getY()*40+50);
        creer_button(this);
        this.setVisible(true);

    }

    public void creer_button(final Menu menu) {

        liste_lay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                content = liste_lay.getSelectedItem().toString();
                try {
                    Map map = new Map(content);
                    reviewMap = new PanelBomberman(map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                setLocationRelativeTo(null);
                System.out.println(reviewMap.getX()*40+" "+ reviewMap.getY()*40+50);
                setSize(reviewMap.getX()*40+5*25, reviewMap.getY()*40+50);
                revalidate();
            }
        });

        jouer.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evenement) {
                content = liste_lay.getSelectedItem().toString();
                try {
                    ViewGame vc = new ViewGame(content,game, pc);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                game.init();
                menu.dispose();
            }
        });
    }

}

