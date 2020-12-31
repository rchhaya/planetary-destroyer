import java.awt.*;
public class EarthShield extends Shield {

    public EarthShield(int courtWidth, int courtHeight, Color color, int positionX, int positionY,
            int initialAngle, int finalAngle, int velocityX, int velocityY, int angleChange) {
        super(courtWidth, courtHeight, color, positionX, positionY,
                initialAngle, finalAngle, velocityX, velocityY, angleChange);
        
        cornerCalculation();
        
    }
    public void cornerCalculation() {
        Point tLeft = new Point(super.getPositionX() + 
                Shield.SIZE_WIDTH / 2, super.getPositionY());
        Point tRight  = new Point(tLeft.x + Shield.SIZE_WIDTH / 2, tLeft.y);
        Point bLeft = new Point(tLeft.x, tLeft.y +  Shield.SIZE_HEIGHT / 2);
        Point bRight = new Point(tLeft.x +  
                  Shield.SIZE_WIDTH / 2, tRight.y +  Shield.SIZE_HEIGHT / 2);
          
        super.setTopLeft(tLeft);
        super.setTopRight(tRight);
        super.setBottomLeft(bLeft);
        super.setBottomRight(bRight);
    }
    
    @Override 
    public void move() {
        cornerCalculation();
        super.move();
    }

    public void bounce() {
        super.setVelocityX(0);
        super.setVelocityY(0);
        super.setAngleRotation(0);
    }
    
//    public void draw(Graphics g) {
//        super.draw(g);
//        g.drawRect(super.getTopLeft().x , super.getTopLeft().y
//                , Shield.SIZE_WIDTH / 2, Shield.SIZE_HEIGHT / 2);
//        g.drawOval(super.getTopLeft().x, super.getTopLeft().y, 10, 10);
//        g.drawOval(super.getTopRight().x, super.getTopRight().y, 10, 10);
//        g.drawOval(super.getBottomRight().x, super.getBottomRight().y, 10, 10);
//        g.drawOval(super.getBottomLeft().x, super.getBottomLeft().y, 10, 10);
//    }
//    
    
}
