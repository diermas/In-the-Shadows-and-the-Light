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
		spritesheet = loadImage("Data/Textures.png");
		sprites = new PImage[4][2];
		tileMap = new char[10][10];
		for (int i = 0; i < sprites.length; i++) {
			for (int j = 0; j < sprites[i].length; j++) {
				sprites[i][j] = spritesheet.get(j*90,i*90,90,90);
			}
		}
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
		selected = '1';
		levelBuilding = new Level(tileMap, this, sprites);
	}
	
	public void draw() {
		background(255);
		levelBuilding.render();
		fill(255, 0);
		stroke(255, 0, 0);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				rect(100+(i*60),100+(j*60),60,60);
			}
		}
		for (int i = 0; i < 2; i++) {
			rect(750,100+i*60,60,60);
		}
		for (int i = 0; i < 2; i++) {
			rect(850,100+i*60,60,60);
		}
		for (int i = 0; i < 4; i++) {
			rect(950,100+i*60,60,60);
		}
		for (int i = 0; i < 4; i++) {
			rect(1050,100+i*60,60,60);
		}
		for (int i = 0; i < 2; i++) {
			rect(850,400+i*60,60,60);
		}
		for (int i = 0; i < 4; i++) {
			rect(950,400+i*60,60,60);
		}
		for (int i = 0; i < 4; i++) {
			rect(1050,400+i*60,60,60);
		}
		rect(750,550,150,150);
		printLevel();
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
	
	public void mouseClicked() {
		int x;
		int y;
		if (mouseX >= 150 && mouseX <= 550) {
			if (mouseY >= 725 && mouseY <= 875) {
				levelBuilding.reset();
			}
		}
		if (mouseX >= 100 && mouseX <= 700) {
			if (mouseY >= 100 && mouseY <= 700) {
				x = (int) Math.floor((mouseX-100)/60);
				y = (int) Math.floor((mouseY-100)/60);
				levelBuilding.setTile(x, y, selected);
			}
		}
		if (mouseX >= 650 && mouseX <= 1150) {
			if (mouseY >= 725 && mouseY <= 875) {
				saveLevel();
			}
		}
		selected = getSelected(mouseX, mouseY);
		System.out.println(selected);
		levelBuilding.setSelection(selected);
	}
	
	public char getSelected(int x, int y) {
		if (x >= 750 && x <= 810) {
			if (y >= 100 && y <= 160) {
				return '0';
			} else if (y >= 160 && y <= 220) {
				return '1';
			}
		} else if (x >= 850 && x <= 910) {
			if (y >= 100 && y <= 160) {
				return 'D';
			} else if (y >= 160 && y <= 220) {
				return 'C';
			} else if (y >= 400 && y <= 460) {
				return 'A';
			} else if (y >= 460 && y <= 520) {
				return 'B';
			}
		} else if (x >= 950 && x <= 1010) {
			if (y >= 100 && y <= 160) {
				return '4';
			} else if (y >= 160 && y <= 220) {
				return '5';
			} else if (y >= 220 && y <= 280) {
				return '2';
			} else if (y >= 280 && y <= 340) {
				return '3';
			} else if (y >= 400 && y <= 460) {
				return 'G';
			} else if (y >= 460 && y <= 520) {
				return 'g';
			} else if (y >= 520 && y <= 580) {
				return 'H';
			} else if (y >= 580 && y <= 640) {
				return 'h';
			}
		} else if (x >= 1050 && x <= 1110) {
			if (y >= 100 && y <= 160) {
				return '8';
			} else if (y >= 160 && y <= 220) {
				return '9';
			} else if (y >= 220 && y <= 280) {
				return '6';
			} else if (y >= 280 && y <= 340) {
				return '7';
			} else if (y >= 400 && y <= 460) {
				return 'F';
			} else if (y >= 460 && y <= 520) {
				return 'f';
			} else if (y >= 520 && y <= 580) {
				return 'E';
			} else if (y >= 580 && y <= 640) {
				return 'e';
			}
		}
		return selected;
	}
	
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
	
	public void saveLevel() {
		try (BufferedWriter br = new BufferedWriter(new FileWriter("level.txt"))){
			br.write("---\n");
			char[][] levelBuilt = levelBuilding.getTileMap();
			for (int i = 0; i < 10; i++) {
				String line = "";
				for (int j = 0; j < 10; j++) {
					line = line + levelBuilt[i][j] + " ";
				}
				line = line + "\n";
				br.write(line);
			}
			br.write("---\n");
			br.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
