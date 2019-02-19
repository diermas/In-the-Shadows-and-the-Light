import processing.core.PApplet;

public class Level {

	private int[][] tileMap;
	private int cellWidth;
	private int cellHeight;
	private PApplet parent;
	
	public Level(int width, int height, int cellsWidth, int cellsHeight, PApplet parent, int[][] tileMap) {
		this.tileMap = tileMap;
		cellWidth = width/cellsWidth;
		cellHeight = height/cellsHeight;
		this.parent = parent;
	}
	
	public void render() {
		for (int y = 0; y < tileMap.length; y++) {
			for (int x = 0; x < tileMap[y].length; x++) {
				switch (tileMap[y][x]) {
				case 0:
					parent.fill(0);
					parent.stroke(0);
					break;
				case 1:
					parent.fill(255);
					parent.stroke(255);
					break;
				}
				parent.rect(x*cellWidth,y*cellHeight,cellHeight,cellWidth);
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
				if (tileMap[y][x] == 0) {
					tileMap[y][x] = 1;
				} else if (tileMap[y][x] == 1) {
					tileMap[y][x] = 0;
				}
			}
		}
	}
}
