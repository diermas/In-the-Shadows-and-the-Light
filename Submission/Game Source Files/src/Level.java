import processing.core.PImage;

public class Level {

	private char[][] tileMap;
	private int cellWidth;
	private int cellHeight;
	private LightDark parent;
	private PImage[][] sprites;
	private PImage current;
	// coveredTiles keeps track of what tile is underneath any movable block
	private char[][] coveredTiles;
	
	public Level(int width, int height, int cellsWidth, int cellsHeight, LightDark parent, char[][] tileMap, PImage[][] sprites) {
		this.tileMap = tileMap;
		cellWidth = width/cellsWidth;
		cellHeight = height/cellsHeight;
		this.parent = parent;
		this.sprites = sprites;
		coveredTiles = new char[10][10];
		// Any tiles that are not movable tiles in the level spawn are kept the same in coveredTiles, and movable tiles are always
		// shown to cover plain floor tiles
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				switch (tileMap[i][j]) {
				case 'I': case 'j': case 'K': case 'l':
					coveredTiles[i][j] = '1';
					break;
				case 'i': case 'J': case 'k': case 'L':
					coveredTiles[i][j] = '0';
					break;
				default:
					coveredTiles[i][j] = tileMap[i][j];
					break;
				}
			}
		}
	}
	
	// Switch case that has every tile possibility, and renders either the black or the white background plus any tile sprites
	public void render() {
		for (int y = 0; y < tileMap.length; y++) {
			for (int x = 0; x < tileMap[y].length; x++) {
				switch (tileMap[y][x]) {
				case '0': case '5': case 'b': case 'd': case 'f': case 'g': case 'k':
					parent.fill(0);
					parent.stroke(0);
					parent.rect(x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					break;
				case '1': case '3': case'a': case 'c': case 'e': case 'h': case 'l':
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
				case 'K':
					parent.fill(255);
					parent.stroke(255);
					parent.rect(x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					current = sprites[5][0];
					parent.image(current,x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					break;
				case 'L':
					parent.fill(0);
					parent.stroke(0);
					parent.rect(x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					current = sprites[5][1];
					parent.image(current,x*cellWidth,y*cellHeight,cellHeight,cellWidth);
					break;
				default:
					System.out.println("Invalid char");
					break;
				}
			}
		}
	}
	
	// Testing function to print the current level to console
	public void printLevel() {
		for (int y = 0; y < tileMap.length; y++) {
			String level = "";
			for (int x = 0; x < tileMap[y].length; x++) {
				level = level + tileMap[y][x] + " ";
			}
			System.out.println(level);
		}
	}
	
	// Toggle all of the tiles and covered tiles between light and dark
	public void toggleLevel() {
		toggleMap(tileMap);
		toggleMap(coveredTiles);
	}
	
	// Switch statement of what char to toggle each tile to in the given map
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
				case 'K':
					map[y][x] = 'k';
					break;
				case 'k':
					map[y][x] = 'K';
					break;
				case 'L':
					map[y][x] = 'l';
					break;
				case 'l':
					map[y][x] = 'L';
				default:
					break;
				}
			}
		}
	}
	
	// Return the tile at the given coordinates
	public char getTile(int x, int y) {
		return tileMap[y][x];
	}
	
	// Check if the tile at the given coordinates contains an active key, if so change the tile to a floor tile and return true
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
	
	// Check if the tile at the given coordinates is an active wall block
	public boolean checkWall(int x, int y) {
		char tile = tileMap[y][x];
		if (tile == 'C' || tile == 'D' || tile == '7' || tile == '9' || tile == 'E' || tile == 'F' || tile == 'i' || tile == 'j') {
			return true;
		}
		return false;
	}
	
	// Check if the tile at the given coordinates is an active button
	public boolean checkButton(int x, int y) {
		char tile = tileMap[y][x];
		if (tile == 'A' || tile == 'B' || tile == 'G' || tile == 'H') {
			return true;
		}
		return false;
	}
	
	
	// Check if the tile at the given coordinates is an active door
	public boolean checkDoor(int x, int y) {
		char tile = tileMap[y][x];
		if (tile == '6' || tile == '8') {
			return true;
		}
		return false;
	}
	
	// Check if the tile at the given coordinates is an active movable block
	public boolean checkMovable(int x, int y) {
		char tile = tileMap[y][x];
		if (tile == 'I' || tile == 'J' || tile == 'K' || tile == 'L') {
			return true;
		}
		return false;
	}
	
	// Check if the tile at the given coordinates is a valid space for a movable block to enter
	public boolean checkValidMove(int x, int y) {
		if (x < 0 || x > 9 || y < 0 || y > 9) {
			return false;
		}
		char tile = tileMap[y][x];
		if (tile == '0' || tile == '1' || tile == '3' || tile == '5' || tile == 'e' || tile == 'f' || tile == 'g' || tile == 'h' || tile == 'G' || tile == 'H' || tile == 'A' || tile == 'B') {
			return true;
		}
		return false;
	}
	
	// Move the block at the given coordinates in the direction specified, then replace the tile it was on with the coveredTiles entry
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
	
	// Check if any of the four cardinal tiles contains a movable block
	public char checkSurrounding(int x, int y) {
		if (checkMovable(x,y-1)) {
			return 'n';
		} else if (checkMovable(x-1,y)) {
			return 'w';
		} else if (checkMovable(x+1, y)) {
			return 'e';
		} else if (checkMovable(x,y+1)) {
			return 's';
		} else {
			return 'x';
		}
	}
}
