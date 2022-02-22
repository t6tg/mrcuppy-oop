package traygame;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class TrayGame extends JFrame implements ActionListener {

    homegames homegames1 = new homegames();

    public TrayGame() {
        this.setSize(1280, 720);
        this.add(homegames1);
        homegames1.BStart.addActionListener(this);
        homegames1.BExit.addActionListener(this);

    }

    public static void main(String[] args) throws IOException {
        ArrayList<String> ques = new ArrayList();
        JFrame jf = new TrayGame();
        jf.setSize(1280, 720);
        jf.setTitle("MR.CUPPY");
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            game mygame = new game();
            if (e.getSource() == homegames1.BStart) {
                this.setLocationRelativeTo(null);
                this.remove(homegames1);
                this.setSize(1282, 750);
                this.add(mygame);
                mygame.requestFocusInWindow();
            } else if (e.getSource() == homegames1.BExit) {
                System.exit(0);
            }
            this.validate();
            this.repaint();
        } catch (IOException ex) {
            Logger.getLogger(TrayGame.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Hello");
        }
    }

}
