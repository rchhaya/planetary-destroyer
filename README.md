# planetaryDestroyer
A game implemented using Java Swing.

In this game application, you control planet Earth and are trying to defend yourself against the other planets while attacking them.
Destroying all 3 planets will result in leveling up and the game will continue until all 5 levels are beat or Earth runs out of lives.
Detailed instructions for gameplay can be found using the instructions tab on the main application window when the program is run.

The main class is the Game class that contains the functionality for various game screens. The actual gameplay implementation is in the 
GameCourtSpace class. 
Each planet has a shield and can shoot bullets (friendly fire between the 3 enemies is disabled - they are working together). Each planet's
bullet and shield has its own class that is a subtype of a more generic Bullet/Shield class. There is also an additional class for configuring the cover image 
to add some abstraction in the Game class. 

There is also a 'Save and Quit'/'Load existing game' feature that involves writing aspects of the game state to the 'save.txt' file 
and reading it when loading a previous game. If no game exists when loading, a new game is created (level 1, score 0).
The 'README.txt' file provides some in-depth explanations on various coding features, like subtyping/inheritance, collections, file I/O, and collisions.
