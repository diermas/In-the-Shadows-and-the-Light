import processing.core.PImage;

public class Level {

	private char[][] tileMap;
	private int cellWidth;
	private int cellHeight;
	private LightDark parent;
	private PImage[][] sprites;
	private PImage current;
	
	public Level(int width, int height, int cellsWidth, int cellsHeight, LightDark parent, char[][] tileMap, PImage[][] sprites) {
		this.tileMap = tileMap;
		cellWidth = width/cellsWidth;
		cellHeight = height/cellsHeight;
		this.parent = parent;
		this.sprites = sprites;
	}
	
	public void render() {
		for (int y = 0; y < tileMap.length; y++) {
			for (int x = 0; x < tileMap[y].length; x++) {
				switch (tileMap[y][x]) {
				case '0': case '5': case 'b': case 'd': case 'f': case 'g':
					parent.fill(0);
					parent.stroke(0);
					parent.rect(x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					break;
				case '1': case '3': case'a': case 'c': case 'e': case 'h':
					parent.fill(255);
					parent.stroke(255);
					parent.rect(x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					break;
				case '2':
					parent.fill(0);
					parent.stroke(0);
					parent.rect(x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					current = sprites[0][1];
					parent.image(current,x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					break;
				case '4':
					parent.fill(255);
					parent.stroke(255);
					parent.rect(x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					current = sprites[0][0];
					parent.image(current,x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					break;
				case '6':
					parent.fill(0);
					parent.stroke(0);
					parent.rect(x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					current = sprites[1][1];
					parent.image(current,x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					break;
				case '8':
					parent.fill(255);
					parent.stroke(255);
					parent.rect(x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					current = sprites[1][0];
					parent.image(current,x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					break;
				case 'A':
					parent.fill(255);
					parent.stroke(255);
					parent.rect(x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					current = sprites[3][0];
					parent.image(current,x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					break;
				case 'B':
					parent.fill(0);
					parent.stroke(0);
					parent.rect(x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					current = sprites[3][1];
					parent.image(current,x*cellWidth,y*cellHeight,cellHeight,cellWidth);					
					break;
				case 'C': case '9':
					parent.fill(255);
					parent.stroke(255);
					parent.rect(x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					current = sprites[2][0];
					parent.image(current,x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					break;
				case 'D': case '7':
					parent.fill(0);
					parent.stroke(0);
					parent.rect(x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					current = sprites[2][1];
					parent.image(current,x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					break;
				case 'E':
					parent.fill(255);
					parent.stroke(255);
					parent.rect(x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					current = sprites[2][0];
					parent.image(current,x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					break;
				case 'F':
					parent.fill(0);
					parent.stroke(0);
					parent.rect(x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					current = sprites[2][1];
					parent.image(current,x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					break;
				case 'G':
					parent.fill(255);
					parent.stroke(255);
					parent.rect(x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					current = sprites[3][0];
					parent.image(current,x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					break;
				case 'H':
					parent.fill(0);
					parent.stroke(0);
					parent.rect(x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					current = sprites[3][1];
					parent.image(current,x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					break;
				default:
					break;
				}
			}
		}
	}
	
	public void printLevel() {
		for (int y = 0; y < tileMap.length; y++) {
			String level = "";
			for (int x = 0; x < tileMap[y].length; x++) {
				level = level + tileMap[y][x] + " ";
			}
			System.out.println(level);
		}
	}
	
	public void toggleLevel() {
		for (int y = 0; y < tileMap.length; y++) {
			for (int x = 0; x < tileMap[y].length; x++) {
				switch (tileMap[y][x]) {
				case '0': case '2': case '4': case '6': case '8':
					int tile = (int)tileMap[y][x];
					tile++;
					tileMap[y][x] = (char)tile;
					break;
				case '1': case '3': case '5': case '7': case '9':
					int tile1 = (int)tileMap[y][x];
					tile1--;
					tileMap[y][x] = (char)tile1;
					break;
				case 'A':
					tileMap[y][x] = 'B';
					break;
				case 'B':
					tileMap[y][x] = 'A';
					break;
				case 'C':
					tileMap[y][x] = 'D';
					break;
				case 'D':
					tileMap[y][x] = 'C';
					break;
				case 'E':
					tileMap[y][x] = 'e';
					break;
				case 'e':
					tileMap[y][x] = 'E';
					break;
				case 'F':
					tileMap[y][x] = 'f';
					break;
				case 'f':
					tileMap[y][x] = 'F';
					break;
				case 'G':
					tileMap[y][x] = 'g';
					break;
				case 'g':
					tileMap[y][x] = 'G';
					break;
				case 'H':
					tileMap[y][x] = 'h';
					break;
				case 'h':
					tileMap[y][x] = 'H';
					break;
				default:
					break;
				}
			}
		}
	}
	
	public char getTile(int x, int y) {
		return tileMap[y][x];
	}
	
	public boolean checkKey(int x, int y) {
		char tile = tileMap[y][x];
		if (tile == '2') {
			tileMap[y][x] = '0';
			return true;
		} else if (tile == '4') {
			tileMap[y][x] = '1';
			return true;
		}
		return false;
	}
	
	public boolean checkWall(int x, int y) {
		char tile = tileMap[y][x];
		if (tile == 'C' || tile == 'D' || tile == '7' || tile == '9' || tile == 'E' || tile == 'F') {
			return true;
		}
		return false;
	}
	
	public boolean checkButton(int x, int y) {
		char tile = tileMap[y][x];
		if (tile == 'A' || tile == 'B' || tile == 'G' || tile == 'H') {
			return true;
		}
		return false;
	}
	
	public boolean checkDoor(int x, int y) {
		char tile = tileMap[y][x];
		if (tile == '6' || tile == '8') {
			return true;
		}
		return false;
	}
}
