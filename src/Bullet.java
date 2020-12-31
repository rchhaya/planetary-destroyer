import java.awt.*;

public class Bullet {
//methods for move, draw, intersect w shield, intersect w planet, 
    public static final int SIZE_WIDTH = 10;
    public static final int SIZE_HEIGHT = 10;
    private int courtWidth;
    private int courtHeight;
    private int positionX;
    private int positionY;
    private int velocityX;
    private int velocityY;
    private Color color;
    private double firedAngle;
    private Point bulletCenter;
    
    public Bullet(int courtWidth, int courtHeight, Color color, int positionX, int positionY,
            int velocityX, int velocityY) {
        this.courtHeight = courtHeight;
        this.courtWidth = courtWidth;
        
        this.color = color;
        this.positionX = positionX;
        this.positionY = positionY;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.firedAngle = 0;
        calculateCenter();
        
    }
    
    public void adjustAngle() {
        this.firedAngle = this.firedAngle * Math.PI / 180;
    }
    
    public void calculateCenter() {
        this.bulletCenter = 
                new Point(this.getPositionX() + ((int) (0.5 * Bullet.SIZE_WIDTH)), 
                        this.getPositionY() + ((int)(0.5 * Bullet.SIZE_HEIGHT)));
    }
    
    public void move() {
        calculateCenter();
        int currX = this.getPositionX();
        int currXVel = this.getVelocityX();
       
        int currY = this.getPositionY();
        int currYVel = this.getVelocityY();
        
        int angleY = (int) Math.tan(firedAngle);  
        
        int multiplierY = currYVel * angleY;
        if (multiplierY >= 0) {
            multiplierY = Math.min(multiplierY, 7);
        } else {
            multiplierY = Math.max(multiplierY, -7);
        }
//        double multiplierX;
//        if (multiplierY == 0) {
//            multiplierX = 1;
//        } else {
//            multiplierX = currXVel / multiplierY;
//        }
//       
//        if (multiplierX < 1 && multiplierX > 0) {
//            multiplierX = 1;
//        } else if (multiplierX > -1 && multiplierX < 0) {
//            multiplierX = 1;
//        }
                
        
        this.setPositionX(currX + currXVel);
        this.setPositionY((currY + multiplierY));
    }
    
    public void draw(Graphics g) {
        
        g.setColor(this.color);
        g.fillRect(positionX, positionY, SIZE_WIDTH, SIZE_HEIGHT);
//        g.setColor(Color.GREEN);
//        g.drawOval(bulletCenter.x, bulletCenter.y, 1, 1);
    }
    
    public boolean edgeChecker() {
        boolean edge = false;
        if (this.positionX < 0 || this.positionY < 0 
                || this.positionX > this.courtWidth
                || this.positionY > this.courtHeight) {
            edge = true;
        }
        return edge;
    }
    
    public boolean collidePlanet(Planet p) {
        return (this.getPositionX() + SIZE_WIDTH >= p.getInitialX()
                && this.getPositionY() + SIZE_HEIGHT >= p.getInitialY()
                && p.getInitialX() + Planet.SIZE >= this.getPositionX()
                && p.getInitialY() + Planet.SIZE >= this.getPositionY());
    }
    
//    public boolean collideNeptune(Planet p) {
//        return (this.getPositionX() + SIZE_WIDTH >= p.getInitialX()
//                && this.getPositionY() + SIZE_HEIGHT >= p.getInitialY()
//                && p.getInitialX() + Planet.SIZE >= this.getPositionX()
//                && p.getInitialY() + Planet.SIZE >= this.getPositionY());
//    }
//    
//    public boolean collideJupiter(Planet p) {
//        return (this.getPositionX() + SIZE_WIDTH >= p.getInitialX()
//                && this.getPositionY() + SIZE_HEIGHT <= p.getInitialY()
//                && p.getInitialX() + Planet.SIZE >= this.getPositionX()
//                && p.getInitialY() + Planet.SIZE <= this.getPositionY());
//    }
//    
//    public boolean collideMars(Planet p) {
//        return (this.getPositionX() + SIZE_WIDTH <= p.getInitialX()
//                && this.getPositionY() + SIZE_HEIGHT <= p.getInitialY()
//                && p.getInitialX() + Planet.SIZE <= this.getPositionX()
//                && p.getInitialY() + Planet.SIZE <= this.getPositionY());
//    }
//    
//    public boolean collideEarth(Planet p) {
//        return (this.getPositionX() + SIZE_WIDTH <= p.getInitialX()
//                && this.getPositionY() + SIZE_HEIGHT >= p.getInitialY()
//                && p.getInitialX() + Planet.SIZE <= this.getPositionX()
//                && p.getInitialY() + Planet.SIZE >= this.getPositionY());
//    }
//    
    
    public boolean collideShield(Shield s) {
        boolean x = bulletCenter.x >= s.getTopLeft().x && bulletCenter.x <= s.getTopRight().x;
        boolean y = bulletCenter.y >= s.getTopLeft().y && bulletCenter.y <= s.getBottomLeft().y;
        
        //Using trig to find the exact region of the sector of the shield
//        Point center = new Point(s.getPositionX(), s.getPositionY());
//        double initAngle = (s.getInitialAngle() * Math.PI) / 180;
//        int deltaX1 = (int) (Shield.SIZE_WIDTH * Math.cos(initAngle));
//        int deltaY1 = (int) (Shield.SIZE_WIDTH * Math.sin(initAngle));
//        Point initialCorner = new Point(center.x + deltaX1, center.y + deltaY1);
//        
//        double finalAngle = ((s.getInitialAngle() + s.getFinalAngle()) * Math.PI) / 180;
//        int deltaX2 = (int) (Shield.SIZE_WIDTH * Math.cos(finalAngle));
//        int deltaY2 = (int) (Shield.SIZE_WIDTH * Math.sin(finalAngle));
//        Point finalCorner = new Point(center.x + deltaX2, center.y + deltaY2);
        
        
//        Point bulletTopLeft = new Point(this.getPositionX(), this.getPositionY());
//        Point bulletTopRight = new Point(this.getPositionX() + SIZE_WIDTH, this.getPositionY());
//        Point bulletBottomLeft =
//        new Point(this.getPositionX(), this.getPositionY() + SIZE_HEIGHT);
//        Point bulletBottomRight = 
//        new Point(this.getPositionX() + SIZE_WIDTH, this.getPositionY() + SIZE_HEIGHT);
        
        
        return (x && y);
//        
//        int bottom = center.x;
//        int top = finalCorner.x;
//        int left = center.y;
//        int right = initialCorner.y;
//        
//        
//        return (bulletCenter.x  >=  bottom
//                && bulletCenter.y  >= left
//                && bulletCenter.x  <=  top
//                && bulletCenter.y  <=  right);
//                && p.getInitialX() + Planet.SIZE >= this.getPositionX()
//                && p.getInitialY() + Planet.SIZE >= this.getPositionY());
    
        ///return false;
       
//        //Getting the bounds of the sector
//        int arcLength1 = distance(finalCorner.x, finalCorner.y, initialCorner.x, initialCorner.y);
//        int pointToInitial1 = 
//                distance(bulletCenter.x, bulletCenter.y, initialCorner.x, initialCorner.y);
//        int pointToFinal1 = distance
        //(bulletCenter.x, bulletCenter.y, finalCorner.x, finalCorner.y);
//        
//        //Point-to-line analysis
//        int arcLength2 = distance(center.x, center.y, initialCorner.x, initialCorner.y);
//        int pointToInitial2 = 
//                distance(bulletCenter.x, bulletCenter.y, initialCorner.x, initialCorner.y);
//        int pointToFinal2 = distance(bulletCenter.x, bulletCenter.y, center.x, center.y);
//        
//        int arcLength3 = distance(finalCorner.x, finalCorner.y, center.x, center.y);
//        int pointToInitial3 = 
//                distance(bulletCenter.x, bulletCenter.y, finalCorner.x, finalCorner.y);
//        int pointToFinal3 = distance(bulletCenter.x, bulletCenter.y, center.x, center.y);
//        int buffer = 10;
//        
////        System.out.println("Center" + center.toString());
////        System.out.println("Edge1: " + initialCorner.toString());
////        System.out.println("Edge2" + finalCorner.toString());
//        
//        
//        boolean check1 = (pointToFinal1 + pointToInitial1 <= (
//                arcLength1) + buffer && pointToFinal1 + pointToInitial1 >= (
//                        arcLength1) - buffer);
//        boolean check2 = (pointToFinal2 + pointToInitial2 <= (
//                arcLength2) + buffer && pointToFinal2 + pointToInitial2 >= (
//                        arcLength2) - buffer);
//        boolean check3 = (pointToFinal3 + pointToInitial3 <= (arcLength3) + 
//                buffer && pointToFinal3 + pointToInitial3 >= (arcLength3) -
//                buffer);
//
//        
//        //System.out.println("Line Length: " + lineLength1 + "   
//        //PointToInitial: " + pointToInitial + "  PointToFinal: " + pointToFinal);
//        //System.out.println("Sum: " + Integer.toString(pointToInitial + pointToFinal));
//        
//        return (check1 || check2 || check3);
    }
    
    public int distance(int x1, int y1, int x2, int y2) {
        double deltaX = (x2 - x1) * (x2 - x1);
        double deltaY = (y2 - y1) * (y2 - y1);
        int dist = (int) Math.sqrt(deltaX + deltaY);
        return dist;
    }
    
    public int distanceArc(int r, int angle) {
        double circumference = 2 * Math.PI * r;
        double angleRatio = angle / (2 * Math.PI);
        int arcLength = (int) (circumference * angleRatio);
        return arcLength;
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
    
    
    public Color getColor() {
        return this.color;
    }
    
    public double getFiredAngle() {
        return this.firedAngle;
    }
    
  //Setters 
    public void setVelocityX(int px) {
        this.velocityX = px;
        
    }
    
    public void setVelocityY(int py) {
        this.velocityY = py;
    }
    
    public void setPositionX(int px) {
        this.positionX = px;
    }
    
    public void setPositionY(int py) {
        this.positionY = py;
    }
    
   
    
    public void setColor(Color c) {
        this.color = c;
    }
    
    public void setFiredAngle(double a) {
        this.firedAngle = a;
    }
    
    
}
