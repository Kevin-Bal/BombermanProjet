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

    private BombermanGame game = null;
    private PanelCommande pc = null;
    private JButton jouer = null;

    public Menu() throws HeadlessException {
        this.liste_lay = liste_lay;
        this.setTitle("Menu Jeu Bomberman");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(400, 500));
        this.setLayout(new BorderLayout());
        top = new JPanel();
        top.setLayout(new GridLayout(2,1));
        game = new BombermanGame();

        File repertoire = new File("./layouts/");
        File[] files=repertoire.listFiles();

        liste_lay = new JComboBox<File>(files);

        jouer = new JButton("Jouer");

        this.add("North",liste_lay);
        this.add("Center",jouer);

        try {
            pc = new PanelCommande(game);
        } catch (Exception e) {
            e.printStackTrace();
        }

        creer_button(this);
        this.setVisible(true);

    }

    public void creer_button(final Menu menu) {
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

