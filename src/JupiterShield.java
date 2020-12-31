import java.awt.*;

public class JupiterShield extends Shield {
//    private static int COURT_WIDTH = 300;
//    private static int COURT_HEIGHT = 300;
//    private static int POSITION_X = 235;
//    private static int POSITION_Y = 15;
//    private static int VELOCITY_X = 1;
//    private static int VELOCITY_Y = -1;
//    private static int INITIAL_ANGLE = 180;
//    private static int FINAL_ANGLE = 60;
//    private int ANGLE_CHANGE;
//    private static Color SHIELD_COLOR = Color.ORANGE;
    
    public JupiterShield(int courtWidth, int courtHeight, Color color, int positionX, int positionY,
            int initialAngle, int finalAngle, int velocityX, int velocityY, int angleChange) {
        super(courtWidth, courtHeight, color, positionX, positionY,
                initialAngle, finalAngle, velocityX, velocityY, angleChange);
        cornerCalculation();
        
    }
    
    public void cornerCalculation() {
        Point tLeft = new Point(super.getPositionX(), super.getPositionY() + 
                Shield.SIZE_WIDTH / 2);
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
//    
//    @Override 
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
    
 
    
    public void bounce() {
        switch (super.getStuckEdge()) {
            case "West":
          //this.velocity
            case "East":
                super.setVelocityX(-1);
                super.setVelocityY(-1);
                super.setAngleRotation(-super.getAngleChange());
                break;
            case "North":
                super.setVelocityX(1);
                super.setVelocityY(1);
                super.setAngleRotation(-super.getAngleChange());
                break;
            case "South":
            default: 
                break;
        }
    }
}
