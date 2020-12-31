import java.awt.Color;

public class MarsBullet extends Bullet {
    private MarsShield shield;
    private double firedAngleMars;
    
    public MarsBullet(int courtWidth, int courtHeight, Color color, int positionX, int positionY,
            int velocityX, int velocityY, Shield shield) {
        super(courtWidth, courtHeight, color, positionX, positionY,
                 velocityX, velocityY);
        this.shield = ((MarsShield) shield);
        this.firedAngleMars = 0;
        adjustAngle();
        
        
  
        
    }
    @Override
    public void adjustAngle() {
        this.firedAngleMars = ((shield.getInitialAngle() - 250) * Math.PI) / 180;
        this.firedAngleMars = (Math.PI / 2) - firedAngleMars;
        
        //System.out.println(firedAngleMars * 180 / Math.PI);
        
        if (firedAngleMars > Math.PI / 2) {
            firedAngleMars = Math.PI / 2 - 0.01;
            //angle is a double in radians
        }
        super.setFiredAngle(firedAngleMars);
    }



    public MarsShield getShield() {
        return shield;
    }

    public void setShield(MarsShield shield) {
        this.shield = shield;
    }
}
