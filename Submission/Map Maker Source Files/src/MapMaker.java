import java.io.BufferedWriter;
import java.io.FileWriter;

import processing.core.PApplet;
import processing.core.PImage;

public class MapMaker extends PApplet{

	private char[][] tileMap;
	private PImage spritesheet;
	private PImage[][] sprites;
	private Level levelBuilding;
	private char selected;
	
	public static void main(String[] args) {
		PApplet.main("MapMaker");
	}
	
	public void settings() {
		size(1800,900);
	}
	
	public void setup() {
		// Load the spritesheet and split it into a 2D array of sprites
		spritesheet = loadImage("Data/Textures.png");
		sprites = new PImage[6][2];
		tileMap = new char[10][10];
		for (int i = 0; i < sprites.length; i++) {
			for (int j = 0; j < sprites[i].length; j++) {
				sprites[i][j] = spritesheet.get(j*90,i*90,90,90);
			}
		}
		// Set the default tileMap for the level builder to be walls surrounding plain floor
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (i == 0 || i == 9) {
					tileMap[i][j] = 'D';
				} else if (j == 0 || j == 9) {
					tileMap[i][j] = 'D';
				} else {
					tileMap[i][j] = '1';
				}
			}
		}
		// selected is the currently selected tile to be added to the map maker
		selected = '1';
		levelBuilding = new Level(tileMap, this, sprites);
	}
	
	public void draw() {
		// Draw the level as build
		background(255);
		levelBuilding.render();
		fill(255, 0);
		stroke(255, 0, 0);
		strokeWeight(3);
		// Draw red borders around each tile of the level display
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				rect(40+(i*60),100+(j*60),60,60);
			}
		}
		// Draw red borders around each button group on the tile select
		rect(650,100,60,120);
		rect(750,100,60,120);
		rect(850,100,60,120);
		for (int i = 0; i < 2; i++) {
			rect(950,100+i*120,60,120);
		}
		for (int i = 0; i < 2; i++) {
			rect(1050,100+i*120,60,120);
		}
		for (int i = 0; i < 2; i++) {
			rect(750,280+i*120,60,120);
		}
		for (int i = 0; i < 2; i++) {
			rect(850,280+i*120,60,120);
		}
		for (int i = 0; i < 2; i++) {
			rect(950,400+i*120,60,120);
		}
		for (int i = 0; i < 2; i++) {
			rect(1050,400+i*120,60,120);
		}
		rect(750,550,150,150);
		printLevel();
		// Display the buttons
		fill(255);
		stroke(0);
		rect(650,725,500,150);
		textSize(60);
		fill(0);
		text("SAVE LEVEL",750,825);
		fill(255);
		rect(150,725,400,150);
		fill(0);
		text("RESET",250,825);
	}
	
	// When the mouse is clicked, track the position of the click and act accordingly
	public void mouseClicked() {
		int x;
		int y;
		if (mouseX >= 150 && mouseX <= 550) {
			if (mouseY >= 725 && mouseY <= 875) {
				// If the click is inside the reset button, reset the level to default
				levelBuilding.reset();
			}
		}
		if (mouseX >= 40 && mouseX <= 640) {
			if (mouseY >= 100 && mouseY <= 700) {
				// If the click is inside the level display, find which tile was clicked and change it to the chosen tile
				x = (int) Math.floor((mouseX-40)/60);
				y = (int) Math.floor((mouseY-100)/60);
				levelBuilding.setTile(x, y, selected);
			}
		}
		if (mouseX >= 650 && mouseX <= 1150) {
			if (mouseY >= 725 && mouseY <= 875) {
				// If the click is inside the save level button, save the level to the text file
				saveLevel();
			}
		}
		// Check if a new tile has been selected
		selected = getSelected(mouseX, mouseY);
		// Set the selected tile inside levelBuilding
		levelBuilding.setSelection(selected);
	}
	
	// Series of if statements to track if any of the buttons from the tile picker has been clicked on, and if so return the appropriate tile
	// Otherwise, return the current selected
	public char getSelected(int x, int y) {
		if (x >= 650 && x <= 710) {
			if (y >= 100 && y <= 220) {
				return '1';
			}
		}
		if (x >= 750 && x <= 810) {
			if (y >= 100 && y <= 220) {
				return 'D';
			} else if (y >= 280 && y <= 400) {
				return 'K';
			} else if (y >= 400 && y <= 520) {
				return 'l';
			}
		} 
		if (x >= 850 && x <= 910) {
			if (y >= 100 && y <= 220) {
				return 'A';
			} else if (y >= 280 && y <= 400) {
				return 'I';
			} else if (y >= 400 && y <= 520) {
				return 'j';
			}
		} 
		if (x >= 950 && x <= 1010) {
			if (y >= 100 && y <= 220) {
				return '4';
			} else if (y >= 220 && y <= 340) {
				return '3';
			} else if (y >= 400 && y <= 520) {
				return 'G';
			} else if (y >= 520 && y <= 640) {
				return 'h';
			}
		} 
		if (x >= 1050 && x <= 1110) {
			if (y >= 100 && y <= 220) {
				return '8';
			} else if (y >= 220 && y <= 340) {
				return '7';
			} else if (y >= 400 && y <= 520) {
				return 'F';
			} else if (y >= 520 && y <= 640) {
				return 'e';
			}
		}
		return selected;
	}
	
	// Print the level to the text representation on the right of the window
	public void printLevel() {
		fill(255,0);
		stroke(0);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				rect(1150+i*60,100+j*60,60,60);
			}
		}
		textSize(30);
		fill(0);
		stroke(0);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				text(levelBuilding.getTile(i, j),1170+i*60,145+j*60);
			}
		}
	}
	
	// Write the text representation of the level to the .txt file, and append it with the formatting of ---
	public void saveLevel() {
		try (BufferedWriter br = new BufferedWriter(new FileWriter("levelEditor.txt"))){
			char[][] levelBuilt = levelBuilding.getTileMap();
			for (int i = 0; i < 10; i++) {
				String line = "";
				for (int j = 0; j < 10; j++) {
					line = line + levelBuilt[i][j] + " ";
				}
				line = line + "\n";
				br.write(line);
				br.newLine();
			}
			br.write("---");
			br.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
