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
	
	public void render() {
		for (int y = 0; y < tileMap.length; y++) {
			for (int x = 0; x < tileMap[y].length; x++) {
				chooseImage(100+x*cellWidth, 100+y*cellHeight, 60, 60, tileMap[y][x]);
			}
		}
		chooseImage(750,550,150,150,selected);
		chooseImage(750,100,60,60,'0');
		chooseImage(750,160,60,60,'1');
		chooseImage(850,100,60,60,'D');
		chooseImage(850,160,60,60,'C');
		chooseImage(950,100,60,60,'4');
		chooseImage(950,160,60,60,'5');
		chooseImage(950,220,60,60,'2');
		chooseImage(950,280,60,60,'3');
		chooseImage(1050,100,60,60,'8');
		chooseImage(1050,160,60,60,'9');
		chooseImage(1050,220,60,60,'6');
		chooseImage(1050,280,60,60,'7');
		chooseImage(750,400,60,60,'A');
		chooseImage(750,460,60,60,'B');
		chooseImage(850,280,60,60,'I');
		chooseImage(850,340,60,60,'i');
		chooseImage(850,400,60,60,'J');
		chooseImage(850,460,60,60,'j');
		chooseImage(950,400,60,60,'G');
		chooseImage(950,460,60,60,'g');
		chooseImage(950,520,60,60,'H');
		chooseImage(950,580,60,60,'h');
		chooseImage(1050,400,60,60,'F');
		chooseImage(1050,460,60,60,'f');
		chooseImage(1050,520,60,60,'E');
		chooseImage(1050,580,60,60,'e');
		
	}
	
	public char[][] getTileMap(){
		return tileMap;
	}
	
	public void setTile(int x, int y, char tile) {
		tileMap[y][x] = tile;
	}
	
	public char getTile(int x, int y) {
		return tileMap[y][x];
	}
	
	public void setSelection(char sel) {
		selected = sel;
	}
	
	public void chooseImage(int x, int y, int width, int height, char tile) {
		switch (tile) {
		case '0': case '5': case 'b': case 'd': case 'f': case 'g':
			parent.fill(0);
			parent.stroke(0);
			parent.rect(x,y,width,height);
			break;
		case '1': case '3': case'a': case 'c': case 'e': case 'h':
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
		case 'C': case '9': case 'j':
			parent.fill(255);
			parent.stroke(255);
			parent.rect(x,y,width,height);
			current = sprites[2][0];
			parent.image(current,x,y,width,height);
			break;
		case 'D': case '7': case 'i':
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
		default:
			break;
		}
		
	}
	
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
