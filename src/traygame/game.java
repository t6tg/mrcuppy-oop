package traygame;

import java.awt.Graphics;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class game extends JPanel implements MouseListener, MouseMotionListener {

    private final ImageIcon imgstate1 = new ImageIcon(this.getClass().getResource("g_bg.jpg"));
    private final ImageIcon imgstate2 = new ImageIcon(this.getClass().getResource("g_bg2.jpg"));
    private final ImageIcon feildover = new ImageIcon(this.getClass().getResource("background.jpg"));
    private final ImageIcon pause = new ImageIcon(this.getClass().getResource("play.png"));
    private final ImageIcon play = new ImageIcon(this.getClass().getResource("pause.png"));
    ArrayList<String> ques = new ArrayList();
    Rectangle2D.Double mouse;
    public JButton Bplay = new JButton(play);
    private int t = 0, q = 0, score = 0;
    private boolean ch1 = true, ch2 = true;
    private int x = 0, mousex = 0, mousey = 0;
    private String y;
    private int ran = 0;
    private JLabel j_time = new JLabel("Time : 90");
    private JLabel p_g = new JLabel("5");
    private JLabel j_score = new JLabel("Score : 0");
    private JLabel j_ques = new JLabel(ques.get(getQues()));
    public ArrayList<cupcake> cupcake_arr = new ArrayList<cupcake>();
    ArrayList<String> ans = new ArrayList();
    Toolkit toolkit = Toolkit.getDefaultToolkit();

    homegames hg = new homegames();
    public int times = 90;
    boolean timestart = true;
    boolean startball = false;
    int times_p = 5;
    Thread time = new Thread(new Runnable() {
        public void run() {
            while (times > 0) {
                try {
                    times--;
                    j_time.setText("Time : " + " " + Integer.toString(times));
                    Thread.sleep(1000);
                    j_time.repaint();
                } catch (Exception e) {
                }
            }
        }

    }
    );
    Thread time_p_g = new Thread(new Runnable() {
        public void run() {
            while (times_p > 0) {
                try {
                    times_p--;
                    p_g.setText(Integer.toString(times_p));
                    Thread.sleep(1000);
                    p_g.repaint();
                } catch (Exception e) {
                }
            }
        }

    }
    );
    Thread cup1 = new Thread(new Runnable() {
        public void run() {
            while (true) {
                try {
                    if (startball == false) {
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (startball == false) {
                    q++;
                    if (q == 3) {
                        cupcake_arr.add(new cupcake(ans.get(x)));
                        q -= 3;
                    }
                    cupcake_arr.add(new cupcake(Integer.toString((int) (Math.random() * 8))));
                    repaint();
                }
            }

        }
    });

    private void keep_ans() throws FileNotFoundException, IOException {
        String fileName = "ans.txt";
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            ans.add(line);
        }
    }

    private void keep() throws FileNotFoundException, IOException {
        String fileName = "question.txt";
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            ques.add(line);
        }
    }

    int getQues() throws IOException {
        if (ch1 == true) {
            keep();
            ch1 = false;
        }
        this.x = (int) (Math.random() * ques.size());
        return x;
    }

    String getAns(int x) throws IOException {
        if (ch2 == true) {
            keep_ans();
            ch2 = false;
        }
        this.y = ans.get(x);
        return this.y;
    }

    public void cursorCustom() {
        Image img = toolkit.getImage("cursor.png");
        Point point = new Point(getX(), getY());
        Cursor cur = toolkit.createCustomCursor(img, point, "cursor");
        setCursor(cur);
    }

    game() throws IOException {
        cursorCustom();
        addMouseListener(this);
        time.start();
        cup1.start();
        time_p_g.start();

        j_time.setOpaque(true);
        j_time.setFont(new Font("Impact", Font.PLAIN, 50));
        j_time.setLocation(970, 35);
        add(j_time);

        p_g.setOpaque(true);
        p_g.setFont(new Font("Impact", Font.PLAIN, 50));
        p_g.setLocation(20, 630);
        add(p_g);

        j_score.setOpaque(true);
        j_score.setFont(new Font("Impact", Font.PLAIN, 50));
        j_score.setLocation(120, 35);
        add(j_score);

        j_ques.setOpaque(true);
        j_ques.setFont(new Font("Impact", Font.PLAIN, 70));
        j_ques.setLocation(580, 35);
        add(j_ques);

        Bplay.setOpaque(false);
        Bplay.setBorderPainted(false);
        Bplay.setContentAreaFilled(false);
        Bplay.setRolloverIcon(pause);

//        Bplay.setBounds(1180, 600, 70, 70);
//        add(Bplay);
//        Bplay.addActionListener(this);
        this.setFocusable(true);
        this.setLayout(null);
//        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("cursor.png").getImage(), new Point(0, 0), "custom cursor"));
        getQues();
        getAns(x);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (times <= 0) {
            EndGame eg = new EndGame(score);
            time.stop();
            this.setLayout(null);
            this.removeAll();
            this.setSize(1282, 720);
            this.add(eg);
            eg.requestFocusInWindow();
        } else if (times <= 50) {
            this.setLayout(null);
            g.drawImage(imgstate2.getImage(), 0, 0, 1280, 720, this);
            j_time.setSize(200, 50);
            j_time.setForeground(Color.decode("#dc364a"));
            j_time.setBackground(Color.decode("#a5e726"));

            p_g.setSize(80, 50);
            p_g.setForeground(Color.decode("#dc364a"));
            p_g.setBackground(Color.decode("#a5e726"));

            j_score.setSize(200, 50);
            j_score.setForeground(Color.decode("#dc364a"));
            j_score.setBackground(Color.decode("#71357f"));

            j_ques.setSize(200, 80);
            j_ques.setForeground(Color.decode("#ffffff"));
            j_ques.setBackground(Color.decode("#e81b39"));
            if (times_p > 0) {
                for (int i = 0; i < cupcake_arr.size(); i++) {
                    g.setFont(new Font("Impact", Font.PLAIN, 50));
                    g.drawString(cupcake_arr.get(i).kumtob, cupcake_arr.get(i).getX(), cupcake_arr.get(i).getY());
                    g.drawImage(cupcake_arr.get(i).getImage(), cupcake_arr.get(i).getX(), cupcake_arr.get(i).getY(), 100, 100, this);
                    repaint();
                }
            } else {
                try {
                    cupcake_arr.removeAll(cupcake_arr);
                    times_p = 5;
                    getQues();
                    getAns(x);
                    j_ques.setText(ques.get(x));
                    j_ques.repaint();
                } catch (IOException ex) {
                    Logger.getLogger(game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (times <= 0) {
            EndGame eg = new EndGame(score);
            time.stop();
            cup1.stop();
            this.setLayout(null);
            this.removeAll();
            this.setSize(1282, 780);
            this.add(eg);
            eg.requestFocusInWindow();
        } else {
            this.setLayout(null);
            g.drawImage(imgstate1.getImage(), 0, 0, 1280, 720, this);
            j_time.setSize(200, 50);
            j_time.setForeground(Color.decode("#dc364a"));
            j_time.setBackground(Color.decode("#a5e726"));

            p_g.setSize(80, 50);
            p_g.setForeground(Color.decode("#dc364a"));
            p_g.setBackground(Color.decode("#a5e726"));

            j_score.setSize(200, 50);
            j_score.setForeground(Color.decode("#dc364a"));
            j_score.setBackground(Color.decode("#71357f"));

            j_ques.setSize(200, 80);
            j_ques.setForeground(Color.decode("#ffffff"));
            j_ques.setBackground(Color.decode("#e81b39"));
            if (times_p > 0) {
                for (int i = 0; i < cupcake_arr.size(); i++) {
                    g.setFont(new Font("Impact", Font.PLAIN, 50));
                    g.drawString(cupcake_arr.get(i).kumtob, cupcake_arr.get(i).getX(), cupcake_arr.get(i).getY());
                    repaint();
                    if (mousex > 0) {
                        if ((mousex <= cupcake_arr.get(i).getX() && mousex >= cupcake_arr.get(i).getX() + 10) && (mousey <= cupcake_arr.get(i).getY() && mousey >= cupcake_arr.get(i).getY() + 10)) {
                            score += 1;
                        }
                        mousex = 0;
                    }
                    g.drawImage(cupcake_arr.get(i).getImage(), cupcake_arr.get(i).getX(), cupcake_arr.get(i).getY(), 100, 100, this);
                }
            } else {
                try {
                    cupcake_arr.removeAll(cupcake_arr);
                    times_p = 5;
                    getQues();
                    getAns(x);
                    j_ques.setText(ques.get(x));
                    j_ques.repaint();
                } catch (IOException ex) {
                    Logger.getLogger(game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public boolean Intersect(Rectangle2D a, Rectangle2D b) {
        return (a.intersects(b));
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == Bplay) {
//            if (t % 2 == 0) {
//                this.t += 1;
//                Bplay = new JButton(pause);
//                time.suspend();
//            } else {
//                this.t += 1;
//                Bplay = new JButton(pause);
//                time.resume();
//            }
//        }
//    }
    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            times_p = 5;
            for (int i = 0; i < cupcake_arr.size(); i++) {
                cupcake a = cupcake_arr.get(i);
                int x1 = getX();
                int y1 = getY();
                if (Intersect(mousebound(e.getX(), e.getY()), cupcake_arr.get(i).getbound())) {
                    if (cupcake_arr.get(i).kumtob == ans.get(x)) {
                        score += 1;
                        j_score.setText("Score : " + Integer.toString(score));
                        j_score.repaint();
                    } else {
                        score -= 1;
                        j_score.setText("Score : " + Integer.toString(score));
                        j_score.repaint();
                    }
                    break;
                }
            }
            cupcake_arr.removeAll(cupcake_arr);
            getQues();
            j_ques.setText(ques.get(x));
            j_ques.repaint();
            q = 0;
        } catch (IOException ex) {
            Logger.getLogger(game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        System.out.println(e.getX() + ", " + e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        System.out.println(e.getX() + ", " + e.getY());
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        System.out.println(e.getX() + ", " + e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent me) {

    }

    public Rectangle2D mousebound(int x, int y) {
        return (new Rectangle2D.Double(x, y, 100, 100));
    }

}
