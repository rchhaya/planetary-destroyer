import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.LinkedList;


@SuppressWarnings("serial")
public class GameCourtSpace extends JPanel {
    private Planet earth;
    private Planet mars;
    private Planet jupiter;
    private Planet neptune;
    
    private Shield earthShield;
    private Shield marsShield;
    private Shield jupiterShield;
    private Shield neptuneShield;
    
    private LinkedList<Bullet> earthBullets;
    private LinkedList<Bullet> marsBullets;
    private LinkedList<Bullet> jupiterBullets;
    private LinkedList<Bullet> neptuneBullets;
    private LinkedList<Planet> planets;
    private LinkedList<Shield> planetShields;
    
    private boolean playing = false;
    private JLabel status;
    
    // Game constants
    public static final int COURT_WIDTH = 400;
    public static final int COURT_HEIGHT = 400;
    
    public static final int INTERVAL = 30;
    private int level;
    private int interval;
    private int gameLoop;
    private int score;
    
    
    public GameCourtSpace(JLabel status) {
        this.status = status;
        this.level = 1;
        this.interval = 100;
        this.gameLoop = 0;
        this.score = 0;
        earthBullets = new LinkedList<Bullet>();
        marsBullets = new LinkedList<Bullet>();
        jupiterBullets = new LinkedList<Bullet>();
        neptuneBullets = new LinkedList<Bullet>();
        planets = new LinkedList<Planet>();
        planetShields = new LinkedList<Shield>();
        reset();
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
                gameLoop++;
                //System.out.println(gameLoop);
            }
        });
        timer.start();
        
        setFocusable(true);
        
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (earthShield.getStuckEdge().equals("West")) {
                        earthShield.bounce();
                    } else {
                        earthShield.setVelocityX(-1);
                        earthShield.setVelocityY(-1);
                        earthShield.setAngleRotation(1);
                    }
                    
                    
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (earthShield.getStuckEdge().equals("South")) {
                        earthShield.bounce();
                    } else {
                        earthShield.setVelocityX(1);
                        earthShield.setVelocityY(1);
                        earthShield.setAngleRotation(-1);
                    }
                    
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE && earthBullets.size() <= 10) {
                    int shieldX = earthShield.getPositionX();
                    int shieldY = earthShield.getPositionY();
                    EarthBullet bullet = new EarthBullet(COURT_WIDTH, COURT_HEIGHT, Color.BLACK,
                            shieldX + 35, shieldY + 5, 1, -1, earthShield);
                    earthBullets.add(bullet);
                }
//                System.out.println("edge: " + earthShield.getStuckEdge());
//                System.out.println("numbullets: " + earthBullets.size());
            }
            public void keyReleased(KeyEvent e) {
                earthShield.clip();
                earthShield.setVelocityX(0);
                earthShield.setVelocityY(0);
                earthShield.setAngleRotation(0);
                
            }
        });
    }
    
    public void reset() {
        earth = new Planet(COURT_WIDTH, COURT_HEIGHT, "files/earth-cis120.jpg", 25, 325);
        mars = new Planet(COURT_WIDTH, COURT_HEIGHT, "files/mars-cis120.png", 25, 25);
        jupiter = new Planet(COURT_WIDTH, COURT_HEIGHT, "files/jupiter-cis120.png", 325, 25);
        neptune = new Planet(COURT_WIDTH, COURT_HEIGHT, "files/neptune-cis120.jpg", 325, 325);
        planets.add(earth);
        planets.add(mars);
        planets.add(jupiter);
        planets.add(neptune);
        earthShield = new EarthShield(
                COURT_WIDTH, COURT_HEIGHT, Color.BLUE, 40, 310, 0, 60, 0, 0, 0);
        marsShield = new MarsShield(
                COURT_WIDTH, COURT_HEIGHT, Color.RED, 40, 40, 300, 60, -1, 1, -1);
        jupiterShield = new JupiterShield(
                COURT_WIDTH, COURT_HEIGHT, Color.ORANGE, 310, 40, 180, 60, 1, 1, 1);
        neptuneShield = new NeptuneShield(
                COURT_WIDTH, COURT_HEIGHT, Color.CYAN, 305, 305, 120, 60, -1, 1, 1);
        planetShields.add(earthShield);
        planetShields.add(marsShield);
        planetShields.add(jupiterShield);
        planetShields.add(neptuneShield);
        earthBullets = new LinkedList<Bullet>();
        marsBullets = new LinkedList<Bullet>();
        jupiterBullets = new LinkedList<Bullet>();
        neptuneBullets = new LinkedList<Bullet>();
        this.score = 0;
        this.level = 1;
    
        playing = true;
        status.setText("Welcome to level " + this.level + "!");
    
        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }
    public void resetLevel() {
        //Reset other planets, but the earth still exists from the previous level
        mars = new Planet(COURT_WIDTH, COURT_HEIGHT, "files/mars-cis120.png", 25, 25);
        jupiter = new Planet(COURT_WIDTH, COURT_HEIGHT, "files/jupiter-cis120.png", 325, 25);
        neptune = new Planet(COURT_WIDTH, COURT_HEIGHT, "files/neptune-cis120.jpg", 325, 325);
        planets.add(mars);
        planets.add(jupiter);
        planets.add(neptune);
        marsShield = 
                new MarsShield(COURT_WIDTH, COURT_HEIGHT, Color.RED, 40, 40, 300, 60, -1, 1, -1);
        jupiterShield = 
                new JupiterShield(
                        COURT_WIDTH, COURT_HEIGHT, Color.ORANGE, 310, 40, 180, 60, 1, 1, 1);
        neptuneShield = 
                new NeptuneShield(
                        COURT_WIDTH, COURT_HEIGHT, Color.CYAN, 305, 305, 120, 60, -1, 1, 1);
        planetShields.add(marsShield);
        planetShields.add(jupiterShield);
        planetShields.add(neptuneShield);
        earthBullets = new LinkedList<Bullet>();
        marsBullets = new LinkedList<Bullet>();
        jupiterBullets = new LinkedList<Bullet>();
        neptuneBullets = new LinkedList<Bullet>();
        level++;
        interval /= 1.25;
        playing = true;
        status.setText("Welcome to level " + this.level + "!");
    
        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }
    void tick() {
        if (playing) {

            for (Shield s : planetShields) {
                s.move();
                s.rotate();
                s.bounce();
                
            }
            
            if (gameLoop > 0 && gameLoop % interval == 0) {
                int marsShieldX = marsShield.getPositionX();
                int marsShieldY = marsShield.getPositionY();
                MarsBullet bullet = new MarsBullet(COURT_WIDTH, COURT_HEIGHT, Color.RED,
                        marsShieldX + 35, marsShieldY + 5, 1, 1, marsShield);
                marsBullets.add(bullet);
                //System.out.println("len: " + marsBullets.size());
                
                int jupShieldX = jupiterShield.getPositionX();
                int jupShieldY = jupiterShield.getPositionY();
                JupiterBullet bullet2 = new JupiterBullet(COURT_WIDTH, COURT_HEIGHT, Color.RED,
                        jupShieldX + 35, jupShieldY + 5, -1, 1, jupiterShield);
                jupiterBullets.add(bullet2);
                
                int nepShieldX = neptuneShield.getPositionX();
                int nepShieldY = neptuneShield.getPositionY();
                NeptuneBullet bullet3 = new NeptuneBullet(COURT_WIDTH, COURT_HEIGHT, Color.RED,
                        nepShieldX + 35, nepShieldY + 5, -1, -1, neptuneShield);
                neptuneBullets.add(bullet3);
                //System.out.println("len: " + marsBullets.size());
                
            }
            for (Planet p : planets) {
                if (!p.checkAlive()) {
                    /*Since concurrent modification is not allowed in a for-each loop, 
                     * each planet is 'flagged' by setting the positions to 500*/
                    p.setInitialX(500);
                    p.setInitialY(500);
                }
            }
            for (int i = 0; i < planets.size(); i++) {
                if (planets.get(i).getInitialX() == 500 && 
                        planets.get(i).getInitialY() == 500) {
                    planetShields.get(i).setPositionX(500);
                    planetShields.get(i).setPositionY(500);
                    planets.remove(planets.get(i));
                        
                    i--;
                
                }
            }
            
            for (int i = 0; i < planetShields.size(); i++) {
                if (planetShields.get(i).getPositionX() == 500 &&
                        planetShields.get(i).getPositionY() == 500) {
                    planetShields.remove(planetShields.get(i));
                    i--;
                }
            }
            for (Bullet bullet: earthBullets) {
                  //For bullets, flagging is done by setting color to white
                    //System.out.println("location: " + bullet.getPositionX() + ", " + 
                bullet.move();
                if (bullet.collidePlanet(neptune)) {
                    bullet.setColor(Color.WHITE);
                    neptune.setLives(neptune.getLives() - 1);
                    score += 100 * level;
                } else if (bullet.collidePlanet(mars)) {
                    bullet.setColor(Color.WHITE);
                    mars.setLives(mars.getLives() - 1);
                    score += 100 * level;
                } else if (bullet.collidePlanet(jupiter)) {
                    bullet.setColor(Color.WHITE);
                    jupiter.setLives(jupiter.getLives() - 1);
                    score += 100 * level;
                } 
                
                if (bullet.collideShield(marsShield) 
                            || bullet.collideShield(jupiterShield)
                            || bullet.collideShield(neptuneShield)) {
                    bullet.setColor(Color.WHITE);
                       //System.out.println("shield hit!");
                } 
                    //System.out.println(bullet.getOnEdge());
                if (bullet.edgeChecker()) {
                    bullet.setColor(Color.WHITE);
                }
            }
                

                
                
            for (Bullet bullet : marsBullets) {
                bullet.move();
                if (bullet.collidePlanet(neptune)) {
                    bullet.setColor(Color.WHITE);
                } else if (bullet.collidePlanet(earth)) {
                    bullet.setColor(Color.WHITE);
                    earth.setLives(earth.getLives() - 1);
                } else if (bullet.collidePlanet(jupiter)) {
                    bullet.setColor(Color.WHITE);
                } 
                
                if (bullet.collideShield(jupiterShield) 
                        || bullet.collideShield(neptuneShield)) {
                    bullet.setColor(Color.WHITE);
                    //System.out.println("shield hit!");
                } else if (bullet.collideShield(earthShield)) {
                    bullet.setColor(Color.WHITE);
                    score += 25;
                }
                //System.out.println(bullet.getOnEdge());
                if (bullet.edgeChecker()) {
                    bullet.setColor(Color.WHITE);
                }
            }
                
                
            for (Bullet bullet : jupiterBullets) {
                bullet.move();
                if (bullet.collidePlanet(neptune)) {
                    bullet.setColor(Color.WHITE);
                } else if (bullet.collidePlanet(earth)) {
                    bullet.setColor(Color.WHITE);
                    earth.setLives(earth.getLives() - 1);
                } else if (bullet.collidePlanet(mars)) {
                    bullet.setColor(Color.WHITE);
                } 
                    
                if (bullet.collideShield(marsShield) 
                        || bullet.collideShield(neptuneShield)) {
                    bullet.setColor(Color.WHITE);
                    //System.out.println("shield hit!");
                } else if (bullet.collideShield(earthShield)) {
                    bullet.setColor(Color.WHITE);
                    score += 25;
                }
                //System.out.println(bullet.getOnEdge());
                if (bullet.edgeChecker()) {
                    bullet.setColor(Color.WHITE);
                }
            }
            for (Bullet bullet : neptuneBullets) {
                bullet.move();
                if (bullet.collidePlanet(earth)) {
                    bullet.setColor(Color.WHITE);
                    earth.setLives(earth.getLives() - 1);
                } else if (bullet.collidePlanet(mars)) {
                    bullet.setColor(Color.WHITE);
                } else if (bullet.collidePlanet(jupiter)) {
                    bullet.setColor(Color.WHITE);
                } 
                
                if (bullet.collideShield(marsShield) 
                        || bullet.collideShield(jupiterShield)) {
                    bullet.setColor(Color.WHITE);
                    //System.out.println("shield hit!");
                } else if (bullet.collideShield(earthShield)) {
                    bullet.setColor(Color.WHITE);
                    score += 25;
                }
                    //System.out.println(bullet.getOnEdge());
                if (bullet.edgeChecker()) {
                    bullet.setColor(Color.WHITE);
                }
            }
                
            for (int i = 0; i < earthBullets.size(); i++) {
                if (earthBullets.get(i).getColor() == Color.WHITE) {
                    earthBullets.remove(i);
                    i--;
                }
            }
                
                
            for (int i = 0; i < marsBullets.size(); i++) {
                if (marsBullets.get(i).getColor() == Color.WHITE) {
                    marsBullets.remove(i);
                    i--;
                }
            }
            
                
            for (int i = 0; i < jupiterBullets.size(); i++) {
                if (jupiterBullets.get(i).getColor() == Color.WHITE) {
                    jupiterBullets.remove(i);
                    i--;
                }
            }
                
                
            for (int i = 0; i < neptuneBullets.size(); i++) {
                if (neptuneBullets.get(i).getColor() == Color.WHITE) {
                    neptuneBullets.remove(i);
                    i--;
                }
            }
            
            if (earth.checkAlive() && !jupiter.checkAlive()
                    && !neptune.checkAlive() && !mars.checkAlive()) {
                resetLevel();
            }
            
            
                
            repaint();
            if (level > 5) {
                //end conditions
                playing = false;
                status.setText("You win!");
            } else if (earth.getLives() <= 0) {

                playing = false;
                status.setText("You lose :(");
            }
        }
    }
                
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawString("Score: " + score, 175, 25);
        g.drawString("Level: " + level + "/5", 175, 35);
        
        earth.draw(g);
        mars.draw(g);
        jupiter.draw(g);
        neptune.draw(g);
        
        earthShield.draw(g);
        marsShield.draw(g);
        jupiterShield.draw(g);
        neptuneShield.draw(g);
        
        for (Bullet bullet: earthBullets) {
            bullet.draw(g);
        }
        for (Bullet bullet: marsBullets) {
            bullet.draw(g);
        }
        
        for (Bullet bullet: jupiterBullets) {
            bullet.draw(g);
        }
        for (Bullet bullet: neptuneBullets) {
            bullet.draw(g);
        }
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
    
    //Getters
    
    public int getLevel() {
        return this.level;
    }
    
    public JLabel getStatus() {
        return this.status;
    }
    
    public int getInterval() {
        return this.interval;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public int[] getPlanetLives() {
        int[] lives = new int[planets.size()];
        for (int i = 0; i < planets.size(); i++) {
            lives[i] = planets.get(i).getLives();
        }
        return lives;
    }
    
    public LinkedList<Planet> getPlanets() {
        return this.planets;
    }
    
    //Setters
    
    public void setLevel(int l) {
        this.level = l;
    }
    public void setInterval(int i) {
        this.interval = i;
    }
    public void setScore(int s) {
        this.score = s;
    }
    
    public void setStatus(String s) {
        this.status.setText(s);
    }
    
    public void setPlanetLives(int[] lives) {
        for (int i = 0; i < lives.length; i++) {
            planets.get(i).setLives(lives[i]);
            //System.out.println(planets.get(i).getImageFile() + "'s life is now: " + 
            //planets.get(i).getLives());
        }
        repaint();
    }
    
}
