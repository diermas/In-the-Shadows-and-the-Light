import processing.core.PImage;

public class Level {

	private char[][] tileMap;
	private int cellWidth;
	private int cellHeight;
	private LightDark parent;
	private PImage[][] sprites;
	private PImage current;
	private char[][] coveredTiles;
	
	public Level(int width, int height, int cellsWidth, int cellsHeight, LightDark parent, char[][] tileMap, PImage[][] sprites) {
		this.tileMap = tileMap;
		cellWidth = width/cellsWidth;
		cellHeight = height/cellsHeight;
		this.parent = parent;
		this.sprites = sprites;
		coveredTiles = new char[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (tileMap[i][j] == 'I') {
					coveredTiles[i][j] = '1';
				} else if (tileMap[i][j] == 'J') {
					coveredTiles[i][j] = '0';
				} else if (tileMap[i][j] == 'i') {
					coveredTiles[i][j] = '0';
				} else if (tileMap[i][j] == 'j') {
					coveredTiles[i][j] = '1';
				} else {
					coveredTiles[i][j] = tileMap[i][j];
				}
			}
		}
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
				case 'C': case '9': case 'i':
					parent.fill(255);
					parent.stroke(255);
					parent.rect(x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					current = sprites[2][0];
					parent.image(current,x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					break;
				case 'D': case '7': case 'j':
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
				case 'I':
					parent.fill(255);
					parent.stroke(255);
					parent.rect(x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					current = sprites[4][0];
					parent.image(current,x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					break;
				case 'J':
					parent.fill(0);
					parent.stroke(0);
					parent.rect(x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					current = sprites[4][1];
					parent.image(current,x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					break;
				default:
					System.out.println("Invalid char");
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
		toggleMap(tileMap);
		toggleMap(coveredTiles);
	}
	
	public void toggleMap(char[][] map) {
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map[y].length; x++) {
				switch (map[y][x]) {
				case '0': case '2': case '4': case '6': case '8':
					int tile = (int)map[y][x];
					tile++;
					map[y][x] = (char)tile;
					break;
				case '1': case '3': case '5': case '7': case '9':
					int tile1 = (int)map[y][x];
					tile1--;
					map[y][x] = (char)tile1;
					break;
				case 'A':
					map[y][x] = 'B';
					break;
				case 'B':
					map[y][x] = 'A';
					break;
				case 'C':
					map[y][x] = 'D';
					break;
				case 'D':
					map[y][x] = 'C';
					break;
				case 'E':
					map[y][x] = 'e';
					break;
				case 'e':
					map[y][x] = 'E';
					break;
				case 'F':
					map[y][x] = 'f';
					break;
				case 'f':
					map[y][x] = 'F';
					break;
				case 'G':
					map[y][x] = 'g';
					break;
				case 'g':
					map[y][x] = 'G';
					break;
				case 'H':
					map[y][x] = 'h';
					break;
				case 'h':
					map[y][x] = 'H';
					break;
				case 'I':
					map[y][x] = 'i';
					break;
				case 'i':
					map[y][x] = 'I';
					break;
				case 'J':
					map[y][x] = 'j';
					break;
				case 'j':
					map[y][x] = 'J';
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
		if (tile == 'C' || tile == 'D' || tile == '7' || tile == '9' || tile == 'E' || tile == 'F' || tile == 'i' || tile == 'j') {
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
	
	public boolean checkMovable(int x, int y) {
		char tile = tileMap[y][x];
		if (tile == 'I' || tile == 'J') {
			return true;
		}
		return false;
	}
	
	public boolean checkValidMove(int x, int y) {
		if (x < 0 || x > 9 || y < 0 || y > 9) {
			return false;
		}
		char tile = tileMap[y][x];
		if (tile == '0' || tile == '1' || tile == '3' || tile == '5' || tile == 'e' || tile == 'f' || tile == 'g' || tile == 'h') {
			return true;
		}
		return false;
	}
	
	public void moveBlock(int x, int y, char dir) {
		switch (dir) {
		case 'n':
			tileMap[y-1][x] = tileMap[y][x];
			tileMap[y][x] = coveredTiles[y][x];
			break;
		case 's':
			tileMap[y+1][x] = tileMap[y][x];
			tileMap[y][x] = coveredTiles[y][x];
			break;
		case 'w':
			tileMap[y][x-1] = tileMap[y][x];
			tileMap[y][x] = coveredTiles[y][x];
			break;
		case 'e':
			tileMap[y][x+1] = tileMap[y][x];
			tileMap[y][x] = coveredTiles[y][x];
			break;
		default:
			break;
		}
	}
}
