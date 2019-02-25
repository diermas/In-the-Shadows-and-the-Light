import java.io.*;

import processing.core.PApplet;
import processing.core.PImage;

public class LightDark extends PApplet{

	private Level levelObject;
	private int height = 800;
	private int width = 800;
	private int cells = 10;
	private Player player;
	private StartMenu start;
	private int gameMode = 0;
	private String[][] levelFile;
	private PImage[][] sprites;
	private PImage spritesheet;
	private int level = 0;
	private GameCleared gameEnd;
	
	public static void main(String[] args) {
		PApplet.main("LightDark");
	}
	
	public void settings() {
		size(width,height+100);
		
	}

	public void setup() {
		// Load the spritesheet and split it into an array of sprites
		spritesheet = loadImage("Data/Textures.png");
		sprites = new PImage[6][2];
		for (int i = 0; i < sprites.length; i++) {
			for (int j = 0; j < sprites[i].length; j++) {
				sprites[i][j] = spritesheet.get(j*90,i*90,90,90);
			}
		}
		levelFile = new String[1000][10];
		// Read level.txt into a 2D array of Strings
		readLevel();
		surface.setTitle("In the Shadows and the Light");
		start = new StartMenu(this, height, width);
		player = new Player(cells, height/cells, width/cells, this, 1, 1, 1);
		levelObject = new Level(width,height,cells,cells,this,getLevel(level),sprites);
		gameEnd = new GameCleared(this, height, width);
	}
	
	public void draw() {
		switch (gameMode) {
		// Display the start menu
		case 0:
			start.render();
			fill(0);
			stroke(0);
			rect(0,height,width,100);
			break;
		// Display the game level plus any level text (hard-coded for the first 6 levels)
		case 1:
			background(255);
			levelObject.render();
			player.render();
			fill(0);
			stroke(0);
			rect(0,height,width,100);
			fill(255);
			stroke(255);
			textSize(20);
			switch(level) {
			case 0:
				text("Grab the key then unlock the door.", 50, height+50);
				break;
			case 1:
				text("Press Spacebar on buttons to toggle the lights.", 50, height+35);
				text("Some things are visible only in shadows.", 50, height+75);
				break;
			case 2:
				text("Sometimes walls can appear or disappear in the presence of the light.", 50, height+50);
				break;
			case 3:
				text("Some walls can be pushed when in the light or the shadow.", 50, height+50);
				break;
			case 4:
				text("Some walls can be bypassed by moving a portal", 50, height+40);
				text("over them before toggling the lights.", 50, height+60);
				break;
			case 5:
				text("Don't forget. You can pull blocks by pressing Spacebar.", 50, height+50);
				break;
			default:
				text("You know everything you need to to clear this level.", 50, height+50);
				break;
			}
			break;
		// Display the game clear screen
		case 2:
			gameEnd.render();
			fill(0);
			stroke(0);
			rect(0,height,width,100);
			break;
		}
	}
	
	public void keyPressed() {
		int x = player.getX();
		int y = player.getY();
		if (key == ' ') {
			// Interactions are priority based with button being the most important
			if (levelObject.checkButton(x, y)) {
				// The button can only be toggled if the player isn't currently holding on to a block
				if (!player.isPulling()) {
					levelObject.toggleLevel();
					player.toggleCol();
				} else {
				// Otherwise just stop pulling the block
					player.togglePull();
				}
			} else if (levelObject.checkDoor(x, y)) {
				if (player.hasKey()) {
					level++;
					player.reset();
					if (levelFile[level*10][0] != null) {
						levelObject = new Level(width,height,cells,cells,this,getLevel(level),sprites);
					} else {
						gameMode = 2;
					}
				}
			} else {
				// Check if any surrounding blocks are movable
				char surrounding = levelObject.checkSurrounding(x, y);
				if (surrounding != 'x') {
					// If so, set the pull direction to the direction of the block
					player.togglePull();
					player.setPullDir(surrounding);
				}
			}
		}		
		// Each movement key follows the same structure, just with varied x/y numbers
		if (key == 'W' || key == 'w') {
			// If y is 0, the player is already at the top of the map so the move is invalid
			if (y > 0) {
				// If the tile above the player is a wall, the move is invalid
				if (!levelObject.checkWall(x, y-1)) {
					// If the tile above the player isn't movable and the player isn't pulling a block, move the player up.
					if (!levelObject.checkMovable(x, y-1)) {
						if (!player.isPulling()) {
							player.move('n');
							checkKeyTile();
						} else if (player.getPullDir() == 's') {
							// If the player is pulling a block, the move is only valid if the pulled block is below the player.
							player.move('n');
							levelObject.moveBlock(x, y+1, 'n');
							checkKeyTile();
						}
					} else {
						// If the tile is movable, check if the tile above that one is a valid space for the block to move to
						if (levelObject.checkValidMove(x, y-2)) {
							// Again, if the player isn't pulling a block, move the player up, but also move the block up
							if (!player.isPulling()) {
								player.move('n');
								levelObject.moveBlock(x, y-1, 'n');
								checkKeyTile();
							} else if (player.getPullDir() == 's') {
								// Again, if the player is pulling a block, the move is only valid if the pulled block is below the player.
								player.move('n');
								levelObject.moveBlock(x, y-1, 'n');
								levelObject.moveBlock(x, y+1, 'n');
								checkKeyTile();
							}
						}
					}
				}
			}
		}
		if (key == 'S' || key == 's') {
			if (y < cells - 1) {
				if (!levelObject.checkWall(x, y+1)) {
					if (!levelObject.checkMovable(x, y+1)) {
						if (!player.isPulling()) {
							player.move('s');
							checkKeyTile();
						} else if (player.getPullDir() == 'n') {
								player.move('s');
								levelObject.moveBlock(x, y-1, 's');
								checkKeyTile();
						}
					} else {
						if (levelObject.checkValidMove(x, y+2)) {
							if (!player.isPulling()) {
								player.move('s');
								levelObject.moveBlock(x, y+1, 's');
								checkKeyTile();
							} else if (player.getPullDir() == 'n') {
								player.move('s');
								levelObject.moveBlock(x, y-1, 's');
								levelObject.moveBlock(x, y+1, 's');
								checkKeyTile();
							}
						}
					}
				}
			}
		}
		if (key == 'A' || key == 'a') {
			if (x > 0) {
				if (!levelObject.checkWall(x-1, y)) {
					if (!levelObject.checkMovable(x-1, y)) {
						if (!player.isPulling()) {
							player.move('w');
							checkKeyTile();
						} else if (player.getPullDir() == 'e') {
								player.move('w');
								levelObject.moveBlock(x+1, y, 'w');
								checkKeyTile();
						}
					} else {
						if (levelObject.checkValidMove(x-2, y)) {
							if (!player.isPulling()) {
								player.move('w');
								levelObject.moveBlock(x-1, y, 'w');
								checkKeyTile();
							} else if (player.getPullDir() == 'e') {
								player.move('w');
								levelObject.moveBlock(x-1, y, 'w');
								levelObject.moveBlock(x+1, y, 'w');
								checkKeyTile();
							}
						}
					}
				}
			}
		}
		if (key == 'D' || key == 'd') {
			if (x < cells - 1) {
				if (!levelObject.checkWall(x+1, y)) {
					if (!levelObject.checkMovable(x+1, y)) {
						if (!player.isPulling()) {
							player.move('e');
							checkKeyTile();
						} else if (player.getPullDir() == 'w') {
								player.move('e');
								levelObject.moveBlock(x-1, y, 'e');
								checkKeyTile();
						}
					} else {
						if (levelObject.checkValidMove(x+2, y)) {
							if (!player.isPulling()) {
								player.move('e');
								levelObject.moveBlock(x+1, y, 'e');
								checkKeyTile();
							} else if (player.getPullDir() == 'w') {
								player.move('e');
								levelObject.moveBlock(x-1, y, 'e');
								levelObject.moveBlock(x+1, y, 'e');
								checkKeyTile();
							}
						}
					}
				}
			}
		}
		if (key == 'R' || key == 'r') {
			// Reset the levelObject to the same level array, and reset the player's data
			levelObject = new Level(width,height,cells,cells,this,getLevel(level),sprites);
			player.reset();
		}
	}
	
	public void mouseClicked() {
		if (gameMode == 0) {
			// If in the start menu, check if the mouse is inside the coordinates of the Begin button
			if (mouseX >= (width-500)/2 && mouseX <= (width+500)/2) {
				if (mouseY >= (height+250)/2 && mouseY <= (height+750)/2) {
					// If so, change the gameMode to 1
					gameMode = 1;
				}
			}
		} else if (gameMode == 2) {
			// If in the end menu, check if the mouse is inside the coordinates of the Quit button
			if (mouseX >= (width-500)/2 && mouseX <= (width+500)/2) {
				if (mouseY >= (height+250)/2 && mouseY <= (height+750)/2) {
					// If so, exit the game
					exit();
				}
			}
		}
	}
	
	// Function to read the .txt file containing the levels and compile the appropriate character sets into a 2D array
	public void readLevel(){
		try (BufferedReader br = new BufferedReader(new FileReader("levels.txt"))){;
			String line = br.readLine();
			int count = 0;
			// Loop continues while the file still has lines to read
			while (line != null) {
				// Ignore formatting of --- between each level
				if (!(line.equals("---"))) {
					// Ignore formatting of / for the key at the start
					if (!(line.charAt(0) == '/')) {
						String[] lineStr = line.split(" ");
						levelFile[count] = lineStr;
						count++;
					}
				}
				line = br.readLine();
			}
		} catch (Exception e) {
			
		}
	}
	
	// Testing function for printing the full level set to console
	public void printLevel() {
		for (int i = 0; i < levelFile.length; i++) {
			for (int j = 0; j < levelFile[i].length; j++) {
				System.out.print(levelFile[i][j]);
			}
			System.out.println("");
		}
	}
	
	// Returns a 10x10 char array of the level at the index of levelCount
	public char[][] getLevel(int levelCount) {
		char[][] levelMap = new char[cells][cells];
		for (int y = 0; y < cells; y++) {
			for (int x = 0; x < cells; x++) {
				levelMap[y][x] = levelFile[(levelCount*cells)+y][x].charAt(0);
			}
		}
		return levelMap;
	}
	
	// Checks if the tile under the player is a key by calling checkKey inside the levelObject
	public void checkKeyTile() {
		int x = player.getX();
		int y = player.getY();
		if (levelObject.checkKey(x, y)) {
			// If the tile has a key, give the player the key
			player.pickKey();
		}
	}

}
