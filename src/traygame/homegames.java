package traygame;

import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class homegames extends JPanel {

    String mytime = "60";
    private ImageIcon feild = new ImageIcon(this.getClass().getResource("background.jpg"));
    private ImageIcon starts = new ImageIcon(this.getClass().getResource("playbutton.png"));
    private ImageIcon exit = new ImageIcon(this.getClass().getResource("exitbutton.png"));
    public JButton BStart = new JButton(starts);
    public JButton BExit = new JButton(exit);
    Toolkit toolkit = Toolkit.getDefaultToolkit();

    homegames() {

        setLayout(null);
        BStart.setOpaque(false);
        BStart.setBorderPainted(false);
        BStart.setContentAreaFilled(false);
        BStart.setRolloverIcon(new ImageIcon(this.getClass().getResource("hoverstart.png")));

        BExit.setOpaque(false);
        BExit.setBorderPainted(false);
        BExit.setContentAreaFilled(false);
        BExit.setRolloverIcon(new ImageIcon(this.getClass().getResource("hoverexit.png")));
        BStart.setBounds(340, 300, 584, 138);
        add(BStart);

        BExit.setBounds(340, 450, 584, 138);
        add(BExit);

        java.awt.Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("cursor2.png");
        Cursor a = toolkit.createCustomCursor(image, new Point(this.getX(), this.getY()), "");
        setSize(400, 400);
        this.setCursor(a);

    }

    public void setstarts(String start) {
        this.starts = new ImageIcon(this.getClass().getResource(start));
    }

    public void setfeild(String feild) {
        this.feild = new ImageIcon(this.getClass().getResource(feild));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(feild.getImage(), 0, 0, 1280, 720, this);

    }

}
