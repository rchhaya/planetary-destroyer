
// imports necessary libraries for Java swing
import java.awt.*;
import java.io.*;
import java.awt.event.*;

import javax.swing.*;

public class Game implements Runnable {
    private static GameCourtSpace landscapeGame;
    public static void main(String[] args) {
        landscapeGame = new GameCourtSpace(new JLabel());
        SwingUtilities.invokeLater(new Game());
    }
    public void run() {
        
        
        //Opening screen of new game, load game, and instructions
        final JFrame opener = new JFrame("Welcome to Planetary Destroyer");
        opener.setLocation(300, 300);

        OpenerImage title = new OpenerImage();      
        opener.add(title, SwingConstants.CENTER);
        title.repaint();
        JLabel caption = new JLabel("PLANETARY DESTROYER!", SwingConstants.CENTER);
        opener.add(caption, BorderLayout.SOUTH);
        final JPanel buttonPanel = new JPanel();

        final JButton instructions = new JButton("Instructions");
        final JButton newGame = new JButton("New Game");
        final JButton loadGame = new JButton("Load Existing Game");
        
        buttonPanel.add(instructions);
        buttonPanel.add(newGame);
        buttonPanel.add(loadGame);
        
        opener.setVisible(true);
        opener.pack();
        opener.add(buttonPanel, BorderLayout.NORTH);
        opener.setSize(400, 400);
        opener.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        instructions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                instructionsScreen();
                opener.setVisible(false);
            }
        });
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openGame();
                
                opener.setVisible(false);
            }
        });
        loadGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openGame();
                opener.setVisible(false);
                landscapeGame.reset();
                loader("files/save");
            }
        });
        
        
    }
    
    public void openGame() {
     // NOTE : recall that the 'final' keyword notes immutability even for local variables.

        // Top-level frame in which game components live
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
        final JFrame frame = new JFrame("Planetary Destroyer!");
        frame.setLocation(300, 300);
        //frame.getContentPane().setBackground(Color.BLACK);

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);

        // Main playing area
        final GameCourtSpace landscape = new GameCourtSpace(status);
        landscapeGame = landscape;
        frame.add(landscape, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        // Note here that when we add an action listener to the reset button, we define it as an
        // anonymous inner class that is an instance of ActionListener with its actionPerformed()
        // method overridden. When the button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("New Game");
        final JButton saveGame = new JButton("Save and Quit");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                landscape.reset();
            }
        });
        control_panel.add(reset);
        
        saveGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                saver("files/save");
                run();
                
            }
        });
        control_panel.add(saveGame);
        

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        landscape.reset();
    }
    
    public void instructionsScreen() {
        final JFrame instruct = new JFrame("Instructions");
        instruct.setLocation(300,300);
        instruct.setSize(500, 500);
        final JPanel text = new JPanel();
        instruct.add(text, BorderLayout.CENTER);
        JLabel title = new JLabel("Instructions for Planetary Destroyer:");
        JLabel space = new JLabel("\n");
        text.add(title);
        text.add(space);
        String instructions1 = 
                "You are planet earth trying to defend yourself from the other planets.";
        String instructions2 = 
                "Each planet has a shield, and yours is controlled by the "
                + "left and right arrow keys.";
        String instructions3 = 
                "Each planet will also be shooting bullets and you can shoot via the space bar";
        String instructions4 = " (only 10 of your bullets allowed on the screen at a time).";
        String instructions5 = 
                "Each planet has 3 lives and the goal is to kill the planets before they kill you.";
        String instructions6 = 
                "Note that the other planets are in an alliance and cannot harm each other, ";
        String instructions7 = "even if they shoot at each other.";
        String instructions8 = 
                "You get points for blocking bullets with your shield and "
                + "destroying the other planets.";
        String instructions9 = 
                "If you kill all planets, the next level begins with a faster "
                + "opponent shooting speed.";
        String instructions10 = "There are 5 levels. If you beat them, you win.";
        String instructions11 = "If you lose all 3 of your lives, you lose.";
        String instructions12 = "Godspeed, soldier.";
        JLabel i1 = new JLabel(instructions1, SwingConstants.LEFT);
        JLabel i2 = new JLabel(instructions2, SwingConstants.LEFT);
        JLabel i3 = new JLabel(instructions3, SwingConstants.LEFT);
        JLabel i4 = new JLabel(instructions4, SwingConstants.LEFT);
        JLabel i5 = new JLabel(instructions5, SwingConstants.LEFT);
        JLabel i6 = new JLabel(instructions6, SwingConstants.LEFT);
        JLabel i7 = new JLabel(instructions7, SwingConstants.LEFT);
        JLabel i8 = new JLabel(instructions8, SwingConstants.LEFT);
        JLabel i9 = new JLabel(instructions9, SwingConstants.LEFT);
        JLabel i10 = new JLabel(instructions10, SwingConstants.LEFT);
        JLabel i11 = new JLabel(instructions11, SwingConstants.LEFT);
        JLabel i12 = new JLabel(instructions12, SwingConstants.LEFT);
        
        text.add(i1);
        text.add(i2);
        text.add(i3);
        text.add(i4);
        text.add(i5);
        text.add(i6);
        text.add(i7);
        text.add(i8);
        text.add(i9);
        text.add(i10);
        text.add(i11);
        text.add(i12);

        JButton returner = new JButton("Return to Home");
        instruct.add(returner, BorderLayout.SOUTH);
        
        instruct.setVisible(true);
        
        returner.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                instruct.setVisible(false);
                run();
            }
        });
    }
    
    public void saver(String path) {
        try {
            FileWriter fileWrite = new FileWriter(path);
            BufferedWriter buffWrite = new BufferedWriter(fileWrite);
            buffWrite.write(Integer.toString(landscapeGame.getLevel()));
            buffWrite.newLine();
            buffWrite.write(Integer.toString(landscapeGame.getInterval()));
            buffWrite.newLine();
            buffWrite.write(Integer.toString(landscapeGame.getScore()));
            buffWrite.newLine();
            for (int life : landscapeGame.getPlanetLives()) {
                buffWrite.write(Integer.toString(life));
                buffWrite.newLine();
            }
            buffWrite.close();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException();
               
        } catch (IOException e) {
            throw new IllegalArgumentException();
        } 
        
    }
    
    public void loader(String path) {
        try {
            FileReader fileRead = new FileReader(path);
            BufferedReader buffRead = new BufferedReader(fileRead);
            String level = buffRead.readLine();
            //System.out.println("saved level: " + level);
            try {
                int levelInt = Integer.parseInt(level);
                landscapeGame.setLevel(levelInt);
                landscapeGame.setStatus("Welcome to level " + levelInt + "!");
            } catch (NumberFormatException e) {
                landscapeGame.setLevel(1);
            } catch (NullPointerException e) {
                landscapeGame.setLevel(1);
            }
            String interval = buffRead.readLine();
            try {
                int intervalInt = Integer.parseInt(interval);
                landscapeGame.setInterval(intervalInt);
            } catch (NumberFormatException e) {
                landscapeGame.setInterval(100);
            } catch (NullPointerException e) {
                landscapeGame.setLevel(100);
            }
            String score = buffRead.readLine();
            //System.out.println("saved score: " + score);
            try {
                int scoreInt = Integer.parseInt(score);
                landscapeGame.setScore(scoreInt);
            } catch (NumberFormatException e) {
                landscapeGame.setScore(0);
            } catch (NullPointerException e) {
                landscapeGame.setScore(0);
            }
            String [] lives = new String[4];
            buffRead.readLine();
            buffRead.readLine();
            buffRead.readLine();
            buffRead.readLine();
            for (int i = 0; i < lives.length; i++) {
                String life = buffRead.readLine();
                if (life == null) {
                    life = "0";
                }
                //System.out.println(LANDSCAPE.getPlanets().get(i).imageFile + "'s life: " + life);
                lives[i] = life;
//                System.out.println("integer: " + life);
            }
            try {
                int[] livesInt = new int[lives.length];
                for (int i = 0; i < livesInt.length; i++) {
                    int lifeInt = Integer.parseInt(lives[i]);
                    
//                    System.out.println(
//                            LANDSCAPE.getPlanets().get(i).imageFile + "'s int life: " + lifeInt);
                    livesInt[i] = lifeInt;
                }
//                for (int i : livesInt) {
//                    System.out.println("life array: " + i);
//                }
                landscapeGame.setPlanetLives(livesInt);
                landscapeGame.tick();
            } catch (NumberFormatException e) {
                int[] def = {3,3,3,3};
                landscapeGame.setPlanetLives(def);
                System.out.println("'here'");
            }
            buffRead.close();
            
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException();
           
        } catch (IOException e) {
            throw new IllegalArgumentException();
        } 
    }
    
}
