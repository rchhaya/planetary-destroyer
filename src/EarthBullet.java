import java.awt.Color;

public class EarthBullet extends Bullet {
    private EarthShield shield;
    private double firedAngleEarth;
    
    public EarthBullet(int courtWidth, int courtHeight, Color color, int positionX, int positionY,
            int velocityX, int velocityY, Shield shield) {
        super(courtWidth, courtHeight, color, positionX, positionY,
                 velocityX, velocityY);
        this.shield = (EarthShield) shield;
        this.firedAngleEarth = 0;
        adjustAngle();
        
        
    }
    
    @Override 
    public void adjustAngle() {
        this.firedAngleEarth = ((shield.getInitialAngle() + 41) * Math.PI) / 180;
        
        if (firedAngleEarth > Math.PI / 2) {
            firedAngleEarth = Math.PI / 2 - 0.01;
            //angle is a double in radians
        }
        super.setFiredAngle(firedAngleEarth);
    }
    
    public EarthShield getShield() {
        return shield;
    }
    public void setShield(EarthShield shield) {
        this.shield = shield;
    }
}
