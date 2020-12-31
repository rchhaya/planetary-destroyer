import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//Class for a planet. There will be 4 of them at each corner 

public class Planet {
    private String imageFile;
    public static final int SIZE = 40;
    private int initialX;
    private int initialY;
    private int lives;
    
    private BufferedImage img;

    public Planet(int courtWidth, int courtHeight, String imageFile, int initialX, int initialY) {
      //  super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, SIZE, SIZE, courtWidth, 
        //courtHeight);
        this.setImageFile(imageFile);
        this.initialX = initialX;
        this.initialY = initialY;
        this.lives = 3;
        
        try {
            if (img == null) {
                img = ImageIO.read(new File(imageFile));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }

    //@Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawImage(img, initialX, initialY, SIZE, SIZE, null);
        g.drawString(Integer.toString(lives), initialX, initialY);
    }
    
    public boolean checkAlive() {
        return (lives > 0);
    }
    
    //getters
    public int getLives() {
        return this.lives;
    }
    
    public int getInitialX() {
        return this.initialX;
    }
    
    public int getInitialY() {
        return this.initialY;
    }
    public String getImageFile() {
        return imageFile;
    }
    
    //Setters
    public void setLives(int l) {
        this.lives = l;
        if (l == 0) {
            this.initialX = 500;
            this.initialY = 500;
        }
    }
    
    public void setInitialX(int x) {
        this.initialX = x;
    }
    
    public void setInitialY(int y) {
        this.initialY = y;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }
}
