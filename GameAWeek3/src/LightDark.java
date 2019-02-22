import java.io.*;

import processing.core.PApplet;
import processing.core.PImage;

public class LightDark extends PApplet{

	private Level levelObject;
	private int height = 900;
	private int width = 900;
	private int cells = 10;
	private Player player;
	private StartMenu start;
	private int gameMode = 0;
	private String[][] levelFile;
	private PImage[][] sprites;
	private PImage spritesheet;
	private int level = 5;
	private GameCleared gameEnd;
	
	public static void main(String[] args) {
		PApplet.main("LightDark");
	}
	
	public void settings() {
		size(width,height);
		
	}

	public void setup() {
		spritesheet = loadImage("Data/Textures.png");
		sprites = new PImage[5][2];
		for (int i = 0; i < sprites.length; i++) {
			for (int j = 0; j < sprites[i].length; j++) {
				sprites[i][j] = spritesheet.get(j*90,i*90,90,90);
			}
		}
		levelFile = new String[1000][10];
		readLevel();
		surface.setTitle("In the Shadows and the Light");
		start = new StartMenu(this, height, width);
		player = new Player(cells, height/cells, width/cells, this, 1, 1, 1);
		levelObject = new Level(width,height,cells,cells,this,getLevel(level),sprites);
		gameEnd = new GameCleared(this, height, width);
	}
	
	public void draw() {
		switch (gameMode) {
		case 0:
			start.render();
			break;
		case 1:
			background(255);
			levelObject.render();
			player.render();
			break;
		case 2:
			gameEnd.render();
			break;
		}
	}
	
	public void keyPressed() {
		int x = player.getX();
		int y = player.getY();
		if (key == ' ') {
			if (levelObject.checkButton(x, y)) {
				levelObject.toggleLevel();
				player.toggleCol();
			}
			if (levelObject.checkDoor(x, y)) {
				if (player.hasKey()) {
					level++;
					player.reset();
					if (levelFile[level*10][0] != null) {
						levelObject = new Level(width,height,cells,cells,this,getLevel(level),sprites);
					} else {
						gameMode = 2;
					}
				}
			}
		}
		if (key == 'W' || key == 'w') {
			if (y > 0) {
				if (!levelObject.checkWall(x, y-1)) {
					if (!levelObject.checkMovable(x, y-1)) {
						player.move('n');
						checkKeyTile();
					} else {
						if (levelObject.checkValidMove(x, y-2)) {
							player.move('n');
							levelObject.moveBlock(x, y-1, 'n');
							checkKeyTile();
						}
					}
				}
			}
		}
		if (key == 'S' || key == 's') {
			if (y < cells - 1) {
				if (!levelObject.checkWall(x, y+1)) {
					if (!levelObject.checkMovable(x, y+1)) {
						player.move('s');
						checkKeyTile();
					} else {
						if (levelObject.checkValidMove(x, y+2)) {
							player.move('s');
							levelObject.moveBlock(x, y+1, 's');
							checkKeyTile();
						}
					}
				}
			}
		}
		if (key == 'A' || key == 'a') {
			if (x > 0) {
				if (!levelObject.checkWall(x-1, y)) {
					if (!levelObject.checkMovable(x-1, y)) {
						player.move('w');
						checkKeyTile();
					} else {
						if (levelObject.checkValidMove(x-2, y)) {
							player.move('w');
							levelObject.moveBlock(x-1, y, 'w');
							checkKeyTile();
						}
					}
				}
			}
		}
		if (key == 'D' || key == 'd') {
			if (x < cells - 1) {
				if (!levelObject.checkWall(x+1, y)) {
					if (!levelObject.checkMovable(x+1, y)) {
						player.move('e');
						checkKeyTile();
					} else {
						if (levelObject.checkValidMove(x+2, y)) {
							player.move('e');
							levelObject.moveBlock(x+1, y, 'e');
							checkKeyTile();
						}
					}
				}
			}
		}
	}
	
	public void mouseClicked() {
		if (gameMode == 0) {
			if (mouseX >= (width-500)/2 && mouseX <= (width+500)/2) {
				if (mouseY >= (height+250)/2 && mouseY <= (height+750)/2) {
					gameMode = 1;
				}
			}
		} else if (gameMode == 2) {
			if (mouseX >= (width-500)/2 && mouseX <= (width+500)/2) {
				if (mouseY >= (height+250)/2 && mouseY <= (height+750)/2) {
					exit();
				}
			}
		}
	}
	
	public void readLevel(){
		try (BufferedReader br = new BufferedReader(new FileReader("levels.txt"))){;
			String line = br.readLine();
			int count = 0;
			while (line != null) {
				if (!(line.equals("---"))) {
					if (!(line.charAt(0) == '/')) {
						String[] lineInt = line.split(" ");
						levelFile[count] = lineInt;
						count++;
					}
				}
				line = br.readLine();
			}
		} catch (Exception e) {
			
		}
	}
	
	public void printLevel() {
		for (int i = 0; i < levelFile.length; i++) {
			for (int j = 0; j < levelFile[i].length; j++) {
				System.out.print(levelFile[i][j]);
			}
			System.out.println("");
		}
	}
	
	public char[][] getLevel(int levelCount) {
		char[][] levelMap = new char[cells][cells];
		for (int y = 0; y < cells; y++) {
			for (int x = 0; x < cells; x++) {
				levelMap[y][x] = levelFile[(levelCount*cells)+y][x].charAt(0);
			}
		}
		return levelMap;
	}
	
	public void checkKeyTile() {
		int x = player.getX();
		int y = player.getY();
		if (levelObject.checkKey(x, y)) {
			player.pickKey();
		}
	}

}
