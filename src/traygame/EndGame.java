package traygame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class EndGame extends JPanel implements ActionListener {

    homegames home = new homegames();
    String mytime = "60";
    private ImageIcon feild = new ImageIcon(this.getClass().getResource("background_end.jpg"));
    private ImageIcon exit = new ImageIcon(this.getClass().getResource("exitbutton.png"));
    public JButton BExit = new JButton(exit);
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    public int score;
    private JLabel j_score = new JLabel("Score : " + Integer.toString(score));

    EndGame(int score) {
        this.score = score;
        BExit.setOpaque(false);
        BExit.setBorderPainted(false);
        BExit.setContentAreaFilled(false);
        BExit.setRolloverIcon(new ImageIcon(this.getClass().getResource("hoverexit.png")));

        j_score.setOpaque(true);
        j_score.setFont(new Font("Impact", Font.PLAIN, 70));
        j_score.setLocation(970, 300);
        add(j_score);

        BExit.setBounds(340, 450, 584, 138);
        add(BExit);

        java.awt.Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("cursor2.png");
        Cursor a = toolkit.createCustomCursor(image, new Point(this.getX(), this.getY()), "");
        setSize(1280, 720);
        this.setCursor(a);
        BExit.addActionListener(this);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(feild.getImage(), 0, 0, 1280, 720, this);
        j_score.setText("Score : " + Integer.toString(this.score));
        j_score.setSize(300, 150);
        j_score.setLocation(500, 300);
        j_score.setForeground(Color.decode("#e18fbd"));
        j_score.setBackground(Color.decode("#ffffff"));
        BExit.setBounds(340, 480, 584, 138);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BExit) {
            System.exit(0);
        }
    }

}
