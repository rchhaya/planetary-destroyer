import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class OpenerImage extends JPanel {

    private BufferedImage img;
    
    public OpenerImage() {
        try {
            img = ImageIO.read(new File("files/intro.jpg"));
            //System.out.println("here");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("oops");
        } 
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 90, 25, 200, 100, null);
        //System.out.println("being painted");
    }
}
