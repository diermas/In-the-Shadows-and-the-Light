import processing.core.PApplet;

public class Player {

	private int x;
	private int y;	
	private LightDark parent;
	private int cellHeight;
	private int cellWidth;
	private int cells;
	
	public Player(int cells, int cellHeight, int cellWidth, LightDark parent, int x, int y) {
		this.x = x;
		this.y = y;
		this.parent = parent;
		this.cellHeight = cellHeight;
		this.cellWidth = cellWidth;
		this.cells = cells;
	}
	
	public void move(char dir) {
		switch (dir) {
		case 'n':
			if (y > 0) {
				y--;
			}
			break;
		case 's':
			if (y < cells-1) {
				y++;
			}
			break;
		case 'w':
			if (x > 0) {
				x--;
			}
			break;
		case 'e':
			if (x < cells-1) {
				x++;
			}
			break;
		}
	}
	
	public void render() {
		System.out.println(parent.checkTile(x, y));
		if (parent.checkTile(x, y) == 0) {
			parent.fill(255);
			parent.stroke(255);
		} else {
			parent.fill(0);
			parent.stroke(0);
		}
		parent.ellipse((x*cellWidth)+cellWidth/2,(y*cellHeight)+cellHeight/2,cellWidth,cellHeight);
	}
}
