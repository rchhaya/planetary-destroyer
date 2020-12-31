import java.awt.Color;

public class NeptuneBullet extends Bullet {
    private NeptuneShield shield;
    private double firedAngleNeptune;
    
    public NeptuneBullet(int courtWidth, int courtHeight, Color color, int positionX, int positionY,
            int velocityX, int velocityY, Shield shield) {
        super(courtWidth, courtHeight, color, positionX, positionY,
                 velocityX, velocityY);
        this.shield = ((NeptuneShield) shield);
        adjustAngle();
        
    }
    
    @Override
    public void adjustAngle() {
        this.firedAngleNeptune = ((shield.getInitialAngle() - 55) * Math.PI) / 180;
        this.firedAngleNeptune = (Math.PI / 2) - this.firedAngleNeptune;
        
       // System.out.println(firedAngleNeptune * 180 / Math.PI);
        
        if (firedAngleNeptune > Math.PI / 2) {
            firedAngleNeptune = Math.PI / 2 - 0.01;
            //angle is a double in radians
        }
        super.setFiredAngle(firedAngleNeptune);
    }


    public NeptuneShield getShield() {
        return shield;
    }

    public void setShield(NeptuneShield shield) {
        this.shield = shield;
    }
}
