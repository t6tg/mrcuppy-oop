package traygame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import javax.swing.ImageIcon;
public class cupcake {
	Image img;
	public int x = (int) ((Math.random() * 1200));
	public int y= 100;
	public int count = 0;
        public String kumtob;
        cupcake(){
        }
	cupcake(String s){
                this.kumtob = s;
                String imageLocation = "0.png";
                URL imageURL = this.getClass().getResource(imageLocation);
		img = Toolkit.getDefaultToolkit().getImage(imageURL);
		runner.start();
//                System.out.println("Kumtob : " + kumtob);
	}
	Thread runner = new Thread(new Runnable() {
            public void run() {
		while(true){
                    y += 10;
                    if(y >= 750){
			img = null;
			runner = null;
			x = -500;
			y = -500;
                    }
                    try{
			runner.sleep(35);
                    }catch(InterruptedException e){}
                }
            }
	});
	
	public Image getImage(){
            return img;
	}
	
	public int getX(){
            return x;
	}
	public int getY(){
            return y;
	}
	public Rectangle2D getbound(){
    	    return (new Rectangle2D.Double(x,y,100,100));
	}
}
