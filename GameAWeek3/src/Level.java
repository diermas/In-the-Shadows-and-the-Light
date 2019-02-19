import processing.core.PApplet;

public class Level {

	private int[][] tileMap;
	private int cellWidth;
	private int cellHeight;
	private PApplet parent;
	
	public Level(int width, int height, int cellsWidth, int cellsHeight, PApplet parent) {
		tileMap = new int[cellsHeight][cellsWidth];
		cellWidth = width/cellsWidth;
		cellHeight = height/cellsHeight;
		this.parent = parent;
		for (int y = 0; y < tileMap.length; y++) {
			if (!(y == 0 || y == tileMap.length-1)) {
				for (int x = 0; x < tileMap[y].length; x++) {
					if (!(x == 0 || x == tileMap[y].length-1)) {
						tileMap[y][x] = 1;
					}
				}
			}
		}
	}
	
	public void render() {
		for (int y = 0; y < tileMap.length; y++) {
			for (int x = 0; x < tileMap[y].length; x++) {
				switch (tileMap[y][x]) {
				case 0:
					parent.fill(0);
					break;
				case 1:
					parent.fill(150);
					break;
				}
				parent.rect(y*cellHeight,x*cellWidth,cellHeight,cellWidth);
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
}
