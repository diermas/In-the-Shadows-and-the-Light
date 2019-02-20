
public class Player {

	private int x;
	private int y;	
	private LightDark parent;
	private int cellHeight;
	private int cellWidth;
	private int cells;
	private int col;
	private boolean hasKey;
	
	public Player(int cells, int cellHeight, int cellWidth, LightDark parent, int x, int y, int col) {
		this.x = x;
		this.y = y;
		this.parent = parent;
		this.cellHeight = cellHeight;
		this.cellWidth = cellWidth;
		this.cells = cells;
		this.col= col;
		hasKey = false;
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
		if (col == 0) {
			parent.fill(255);
			parent.stroke(255);
		} else {
			parent.fill(0);
			parent.stroke(0);
		}
		parent.ellipse((x*cellWidth)+cellWidth/2,(y*cellHeight)+cellHeight/2,(cellWidth/3)*2,(cellHeight/3)*2);
	}
	
	public void toggleCol() {
		if (col == 0) {
			col = 1;
		} else {
			col = 0;
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean hasKey() {
		return hasKey;
	}
	
	public void pickKey() {
		hasKey = true;
	}
	
	public void reset() {
		x = 1;
		y = 1;
		hasKey = false;
		col = 1;
	}
}
