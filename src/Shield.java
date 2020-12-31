
import java.awt.*;
//Once it hits the edge, clip freezes the icon 

public abstract class Shield {
    public static final int SIZE_WIDTH = 50;
    public static final int SIZE_HEIGHT = 50;
//    private int courtWidth;
//    private int courtHeight;
    private int positionX;
    private int positionY;
    private int velocityX;
    private int velocityY;
    private int initialAngle;
    private int finalAngle;
    private int angleChange = 1;
    private Point topLeft;
    private Point topRight;
    private Point bottomLeft;
    private Point bottomRight;
    
    private String stuckEdge;
    
//    
    private int maxX;
    private int maxY;

    private Color color;

    public Shield(int courtWidth, int courtHeight, Color color, int positionX, int positionY,
            int initialAngle, int finalAngle, int velocityX, int velocityY, int angleChange) {
        

//        this.courtHeight = courtHeight;
//        this.courtWidth = courtWidth;
        
        this.color = color;
        this.initialAngle = initialAngle;
        this.finalAngle = finalAngle;
        this.positionX = positionX;
        this.positionY = positionY;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.angleChange = angleChange;
        
        this.maxX = courtWidth - SIZE_WIDTH;
        this.maxY = courtHeight - SIZE_HEIGHT;
        this.stuckEdge = "None";
        
        calculatePoints();
    }
    
    public void calculatePoints() {
//        center = new Point(positionX + (SIZE_WIDTH / 2) ,positionY + (SIZE_HEIGHT / 2));
//        
//        double initAngle = (this.initialAngle * Math.PI) / 180;
//        int deltaX1 = (int) (Shield.SIZE_WIDTH * Math.cos(initAngle) / 2);
//        int deltaY1 = (int) (Shield.SIZE_HEIGHT * Math.sin(initAngle) / 2);
//        this.initialCorner = new Point(center.x + deltaX1, center.y + deltaY1);
//        
//        
//        double finalAngleRad = ((this.initialAngle - this.finalAngle) * Math.PI) / 180;
//        int deltaX2 = (int) (Shield.SIZE_WIDTH * Math.cos(finalAngleRad) / 2);
//        int deltaY2 = (int) (Shield.SIZE_HEIGHT * Math.sin(finalAngleRad) / 2);
//        this.finalCorner = new Point(center.x + deltaX2, center.y + deltaY2);
    }
    public void move() {
        
        int currX = this.getPositionX();
        int currXVel = this.getVelocityX();
        
        int currY = this.getPositionY();
        int currYVel = this.getVelocityY();
        
        this.setPositionX(currX + currXVel);
        this.setPositionY(currY + currYVel);
        

        this.clip();
        calculatePoints();
    }
    public void rotate() {
        calculatePoints();
        int currAngle = this.getInitialAngle();
        int currAngleChange = this.getAngleChange();
        this.setInitialAngle(currAngle + currAngleChange);
        this.clip();
    }
    public abstract void bounce();
    
    //@Override
    public void draw(Graphics g) {
//        center.x += 25;
//        center.y += 25;
        g.setColor(this.color);
        g.fillArc(positionX, positionY, SIZE_WIDTH, SIZE_HEIGHT, initialAngle, finalAngle);
        g.setColor(Color.BLACK);
//        g.drawLine(center.x, center.y, initialCorner.x, initialCorner.y);
//        g.drawLine(center.x, center.y, finalCorner.x, finalCorner.y);
//        g.drawLine(initialCorner.x, initialCorner.y, finalCorner.x, finalCorner.y);
//        
//        g.setColor(Color.GREEN);
//        g.drawOval(center.x,  center.y,  10, 10);
        //g.drawOval(positionX,  positionY,  10,  10);
//        g.drawRect(center.x , center.y
//                , Shield.SIZE_WIDTH / 2, Shield.SIZE_HEIGHT / 2);
    }
    
    public void clip() {

        
        if ((Math.max(this.positionX, -5) != this.positionX)) {
            stuckEdge = "West";
            //this.positionX = newPosX;
        } else if ((Math.min(this.positionX, maxX) != this.positionX)) {
            stuckEdge = "East";
            //this.positionX = newPosX;
        } else if ((Math.max(this.positionY, 0) != this.positionY)) {
            stuckEdge = "North";
            //this.positionY = newPosY;
        } else if ((Math.min(this.positionY, maxY) != this.positionY)) {
            stuckEdge = "South";
            //this.positionY = newPosY;
        } else {
            stuckEdge = "None";
        }
        
    }
    public boolean checkExist(Planet p) {
        boolean exists = true;
        if (!p.checkAlive()) {
            setPositionX(500);
            setPositionY(500);
            exists = false;
        }
        return exists;
    }
    
    //Getters
    public int getVelocityX() {
        return this.velocityX;
    }
    
    public int getVelocityY() {
        return this.velocityY;
    }
    
    public int getPositionX() {
        return this.positionX;
    }
    
    public int getPositionY() {
        return this.positionY;
    }
    
    public int getInitialAngle() {
        return this.initialAngle;
    }
    
    public int getFinalAngle() {
        return this.finalAngle;
    }
    
    public int getAngleChange() {
        return this.angleChange;
    }
    
    public String getStuckEdge() {
        return this.stuckEdge;
    }
    
    public Point getTopLeft() {
        return this.topLeft;
    }
    
    public Point getTopRight() {
        return this.topRight;
    }
    
    public Point getBottomLeft() {
        return this.bottomLeft;
    }
    public Point getBottomRight() {
        return this.bottomRight;
    }
    ////////////////////////////////////////////////////////////////////////////////
    //Setters 
    public void setVelocityX(int px) {
        //clip();
        this.velocityX = px;
        
    }
    
    public void setVelocityY(int py) {
        this.velocityY = py;
        //clip();
    }
    
    public void setPositionX(int px) {
        this.positionX = px;
        clip();
    }
    
    public void setPositionY(int py) {
        this.positionY = py;
        clip();
    }
    
    public void setInitialAngle(int ang) {
        this.initialAngle = ang;
        clip();
    }
    
    public void setAngleRotation(int py) {
        this.angleChange = py;
        //clip();
    }
    
    public void setTopLeft(Point p) {
        this.topLeft = p;
    }
    
    public void setBottomLeft(Point p) {
        this.bottomLeft = p;
    }
    
    public void setTopRight(Point p) {
        this.topRight = p;
    }
    
    public void setBottomRight(Point p) {
        this.bottomRight = p;
    }
    
}
