=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: rchhaya
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. Collisions. I am using advanced collisions to detect an intersection between bullets and shields. 
  The shield is an arc with a specific angle,which means the center and endpoints can be calculated.
  The points are calculated using circle geometry and trigonometry. 
  Geometry is also used to check for intersection between the sector and the bullet by checking the 
  distance of the arc and the sum of the distances of the bullets to each endpoint, because if those 2 
  values are equal, it indicates a collision (while also factoring in the angle).

  2. File I/O. In the main Game class, I implement functionality to save a game 
  (the level, score, and shooting interval). I save those pieces of data to an external text file 
  when the "Save and Quit" button is pressed from the game screen. Whenever the "Load Existing Game"
  button is pressed from the instructions screen, the data from the text file is read and the game state 
  uses those saved values. If there is no game data saved, the "load Existing Game" button will have the same effect
  as starting a new game. Also, image input is done by reading images from a file for the 4 planets and the 
  title image

  3. Collections. The game uses Lists to store the bullets that are shot from each planet and also a list for the planets
  and planetShields. Since sometimes only 1 planet/shield will be manipulated, the elements in the planets and planetShields 
  list are explicitly named. These lists are iterated over multiple times using for-each loops and handle different cases. In the 
  event where items are to be removed, the element is flagged and then later removed using a for loop. This prevents a concurrent modification exception. 
  Collections offer a convienient way to apply actions to all elements of a certain group, like redrawing all planets or advancing each bullet. This especially 
  helps condense the paintComponent method.

  4. Inheritance. The 'Bullet' class offers functionality for setting the angle to radians, moving,
  being drawn, and checking collisions with the edge, planets, and shields. Since each planet's shield's angle
  is in different quadrants, the adjustAngle() function needs to be overriden by each child class (but 
  the rest of the functions can stay the same). This means that the best structure is to have EarthBullet, 
  MarsBullet, JupiterBullet, and NeptuneBullet extend Bullet and offer their own adjustAngle() methods that 
  override the parent method. 
  The shield class is an abstract class that offers the methods for moving, rotating, clipping, and drawing 
  the shield. However, each planet has a different bouncing mechanism (and Earth's bouncing mechanism involves
  freezing it). Therefore, the EarthShield, MarsShield, JupiterShield, and NeptuneShield extends Shield 
  by using the same move/rotate/clip/draw methods, but unique bounce methods. This allows for individualized 
  dynamic dispatch. 

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
  The main method exists in the Game class, which sets up the different screens/buttons. This is 
  the main game window that the user sees when opening and manages the various screens. The actual game 
  functionality is in the GameCourtSpace class, which involves methods for initializing the gamescape, 
  resetting the level, shooting bullets with specific commands, redrawing updates, calculating lives and scores, 
  and so on. It also contains the end condition of losing (no Earth lives left) and winning (complete 5 levels).
  
  The Planet class allows for the different planets to be drawn onto the screen. The Shield class allows for a 
  shield to be drawn next to each planet, which is actually a filled arc that bounces around the planet. Its 4 subclasses, 
  EarthShield, MarsShield, JupiterShield, and NeptuneShield are the 4 shields for each planet, each with unique bounce methods 
  that handle how the shield should switch directions (or freeze, in the case of Earth) if the shield hits an edge. The bullet 
  class represents a bullet being fired from each planet at a unique position and angle. This class also handles collisions with the 
  edge, the planet, and the shield (which uses more complex geometries). Each bullet's angle is based on the planet's associated shield, so 
  the 4 subclasses, EarthBullet, MarsBullet, JupiterBullet, and NeptuneBullet, all change the firing angle based on the position of the 
  associated shield. 


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  
  The major stumbling block was handling the shield's response to hitting the edge, since my initial implementations 
  froze the shield completely or didn't stop it at all. It took me a few days to figure out how to use clip to check to see
  which edge was hit and handle the cases from there. It was also a challenge to figure out where clip should be called. 
  
  Additionally, implementing multiple screens in the game took some practice and analysis of the lecture material to understand how 
  JPanels/JButtons/JFrames work. At first, the buttons kept on opening up the wrong screens, but I was able to address that as well. 
  
  Another major issue that I wasn't able to fully solve (although I made significant progress) was to handle the angle the bullets were shot at. 
  This was because of the geometry involved in the flipped coordinate plane (positive y indicates down). Another issue because of the same 
  inverted geometries occured when detecting collisions for the shields. However, revewing basic geometries helped fix these issues.


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  
  I think the funcionality is seperated quite well. The planet, bullet, shield, and their subclasses all have 1 role and do them well. 
  While GameCourtSpace is a little crowded, it serves the function of gameplay, so it is still 1 function. Also, much of the code is simply iterating over the
  different collections used and handling various events. The game class also fulfills the 1 function of setting up the game with various Panels and Buttons. 
  
  Private state is encapsulated quite well because most of the fields belonging to each class are private (especially non-static fields). They are accessed through 
  a lot of diffeent getters and setters in different classes, which is a way to encapsulate state while still being to accesss the state outside of the current class. 
  
  If given the chance, I would see if there's a way to use interfaces instead of inheritance/abstract classes, because interfaces are easy to implement and help improve the 
  abstraction of the code as a whole. I would also try to refactor the code from Game into different classes for each window, so the Game class only creates instances of each window 
  when needed (ex. button press, etc.). 



========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
  
  All other material not cited is created by the owner and/or inspired by the starter code, Mushroom of Doom.
  
  Images: 
  -Intro image: https://www.google.com/url?sa=i&url=https%3A%2F%2Fforums.frontier.co.uk%2Fthreads%2Fnew-class-of-ship-planet-destroyer.538947%2F&psig=AOvVaw0vpnNb35ZDVOtzt_HuYW0s&ust=1607744147232000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCMj9i-j_xO0CFQAAAAAdAAAAABAD
  -Earth image: https://pnghut.com/png/w2eDTbUGmW/globe-world-map-earth-transparent-png
  -Mars image: https://www.leanderumc.org/vbs/
  -Jupiter image: http://clipart-library.com/clip-art/34-347675_jupiter-png-cartoon-jupiter-png.htm
  -Neptune image: https://commons.wikimedia.org/wiki/File:Neptune_sketch.svg
  
  Tutorials (no code):
  -Geometry of circles: https://www.youtube.com/watch?v=aHaFwnqH5CU
  -Line/arc and point/arc intersections: https://www.youtube.com/watch?v=R0bGxNzgL2o
