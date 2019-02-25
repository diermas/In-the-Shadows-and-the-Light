import processing.core.PImage;

public class Level {

	private char[][] tileMap;
	private MapMaker parent;
	private int cellWidth = 60;
	private int cellHeight = 60;
	private PImage[][] sprites;
	private PImage current;
	private char selected;
	
	public Level(char[][] tileMap, MapMaker parent, PImage[][] sprites) {
		this.tileMap = tileMap;
		this.parent = parent;
		this.sprites = sprites;
		selected = '1';
	}
	
	// Render the tileMap in the level display, and each tile option in the menu spaces
	public void render() {
		for (int y = 0; y < tileMap.length; y++) {
			for (int x = 0; x < tileMap[y].length; x++) {
				chooseImage(40+x*cellWidth, 100+y*cellHeight, 60, 60, tileMap[y][x]);
			}
		}
		chooseImage(750,550,150,150,selected);
		chooseImage(650,100,60,60,'1');
		chooseImage(650,160,60,60,'0');
		chooseImage(750,100,60,60,'D');
		chooseImage(750,160,60,60,'C');
		chooseImage(850,100,60,60,'A');
		chooseImage(850,160,60,60,'B');
		chooseImage(950,100,60,60,'4');
		chooseImage(950,160,60,60,'5');
		chooseImage(950,220,60,60,'3');
		chooseImage(950,280,60,60,'2');
		chooseImage(1050,100,60,60,'8');
		chooseImage(1050,160,60,60,'9');
		chooseImage(1050,220,60,60,'7');
		chooseImage(1050,280,60,60,'6');
		chooseImage(750,280,60,60,'K');
		chooseImage(750,340,60,60,'k');
		chooseImage(750,400,60,60,'l');
		chooseImage(750,460,60,60,'L');
		chooseImage(850,280,60,60,'I');
		chooseImage(850,340,60,60,'i');
		chooseImage(850,400,60,60,'j');
		chooseImage(850,460,60,60,'J');
		chooseImage(950,400,60,60,'G');
		chooseImage(950,460,60,60,'g');
		chooseImage(950,520,60,60,'h');
		chooseImage(950,580,60,60,'H');
		chooseImage(1050,400,60,60,'F');
		chooseImage(1050,460,60,60,'f');
		chooseImage(1050,520,60,60,'e');
		chooseImage(1050,580,60,60,'E');
		
	}
	
	// Return the full tileMap when saving to .txt
	public char[][] getTileMap(){
		return tileMap;
	}
	
	// Set the tile at the given coordinates to the one specified
	public void setTile(int x, int y, char tile) {
		tileMap[y][x] = tile;
	}
	
	// Return the tile at the given coordinates
	public char getTile(int x, int y) {
		return tileMap[y][x];
	}
	
	// Set selected to the given tile type
	public void setSelection(char sel) {
		selected = sel;
	}
	
	// Render the correct tile in the given coordinates and size
	public void chooseImage(int x, int y, int width, int height, char tile) {
		switch (tile) { // The switch statement identifies which image to render based on tile
		case '0': case '5': case 'b': case 'd': case 'f': case 'g': case 'k':
			parent.fill(0);
			parent.stroke(0);
			parent.rect(x,y,width,height);
			break;
		case '1': case '3': case'a': case 'c': case 'e': case 'h': case 'l':
			parent.fill(255);
			parent.stroke(255);
			parent.rect(x,y,width,height);
			break;
		case '2':
			parent.fill(0);
			parent.stroke(0);
			parent.rect(x,y,width,height);
			current = sprites[0][1];
			parent.image(current,x,y,width,height);
			break;
		case '4':
			parent.fill(255);
			parent.stroke(255);
			parent.rect(x,y,width,height);
			current = sprites[0][0];
			parent.image(current,x,y,width,height);
			break;
		case '6':
			parent.fill(0);
			parent.stroke(0);
			parent.rect(x,y,width,height);
			current = sprites[1][1];
			parent.image(current,x,y,width,height);
			break;
		case '8':
			parent.fill(255);
			parent.stroke(255);
			parent.rect(x,y,width,height);
			current = sprites[1][0];
			parent.image(current,x,y,width,height);
			break;
		case 'A':
			parent.fill(255);
			parent.stroke(255);
			parent.rect(x,y,width,height);
			current = sprites[3][0];
			parent.image(current,x,y,width,height);
			break;
		case 'B':
			parent.fill(0);
			parent.stroke(0);
			parent.rect(x,y,width,height);
			current = sprites[3][1];
			parent.image(current,x,y,width,height);					
			break;
		case 'C': case '9': case 'i':
			parent.fill(255);
			parent.stroke(255);
			parent.rect(x,y,width,height);
			current = sprites[2][0];
			parent.image(current,x,y,width,height);
			break;
		case 'D': case '7': case 'j':
			parent.fill(0);
			parent.stroke(0);
			parent.rect(x,y,width,height);
			current = sprites[2][1];
			parent.image(current,x,y,width,height);
			break;
		case 'E':
			parent.fill(255);
			parent.stroke(255);
			parent.rect(x,y,width,height);
			current = sprites[2][0];
			parent.image(current,x,y,width,height);
			break;
		case 'F':
			parent.fill(0);
			parent.stroke(0);
			parent.rect(x,y,width,height);
			current = sprites[2][1];
			parent.image(current,x,y,width,height);
			break;
		case 'G':
			parent.fill(255);
			parent.stroke(255);
			parent.rect(x,y,width,height);
			current = sprites[3][0];
			parent.image(current,x,y,width,height);
			break;
		case 'H':
			parent.fill(0);
			parent.stroke(0);
			parent.rect(x,y,width,height);
			current = sprites[3][1];
			parent.image(current,x,y,width,height);
			break;
		case 'I':
			parent.fill(255);
			parent.stroke(255);
			parent.rect(x, y, width, height);
			current = sprites[4][0];
			parent.image(current,x,y,width,height);
			break;
		case 'J':
			parent.fill(0);
			parent.stroke(0);
			parent.rect(x, y, width, height);
			current = sprites[4][1];
			parent.image(current,x,y,width,height);
			break;
		case 'K':
			parent.fill(255);
			parent.stroke(255);
			parent.rect(x, y, width, height);
			current = sprites[5][0];
			parent.image(current,x,y,width,height);
			break;
		case 'L':
			parent.fill(0);
			parent.stroke(0);
			parent.rect(x,  y,  width, height);
			current = sprites[5][1];
			parent.image(current,x,y,width,height);
			break;
		default:
			break;
		}
		
	}
	
	// Reset the level to the default
	public void reset() {
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
	}
}
