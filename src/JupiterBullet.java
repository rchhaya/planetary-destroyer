import java.awt.Color;

public class JupiterBullet extends Bullet {
    private JupiterShield shield;
    private double firedAngleJupiter;
    
    public JupiterBullet(int courtWidth, int courtHeight, Color color, int positionX, int positionY,
            int velocityX, int velocityY, Shield shield) {
        super(courtWidth, courtHeight, color, positionX, positionY,
                 velocityX, velocityY);
        this.shield = ((JupiterShield) shield);
        this.firedAngleJupiter = 0;
        adjustAngle();
        
        
    }
    
    @Override 
    public void adjustAngle() {
        this.firedAngleJupiter = ((shield.getInitialAngle() - 140) * Math.PI) / 180;
        
        //System.out.println(firedAngleJupiter * 180 / Math.PI);
        if (firedAngleJupiter > Math.PI / 2) {
            firedAngleJupiter = Math.PI / 2 - 0.01;
            //angle is a double in radians
        }
        super.setFiredAngle(firedAngleJupiter);
    }

    public JupiterShield getShield() {
        return shield;
    }

    public void setShield(JupiterShield shield) {
        this.shield = shield;
    }
}