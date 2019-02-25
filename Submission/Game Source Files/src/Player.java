
public class Player {

	private int x;
	private int y;	
	private LightDark parent;
	private int cellHeight;
	private int cellWidth;
	private int cells;
	private int col;
	private boolean hasKey;
	private boolean pulling;
	// PullDir is the relative direction of the block from the player (i.e if the player is N of the block, pullDir is S)
	private char pullDir;
	
	public Player(int cells, int cellHeight, int cellWidth, LightDark parent, int x, int y, int col) {
		this.x = x;
		this.y = y;
		this.parent = parent;
		this.cellHeight = cellHeight;
		this.cellWidth = cellWidth;
		this.cells = cells;
		this.col= col;
		hasKey = false;
		pulling = false;
		pullDir = 'x';
	}
	
	// Check if the move is valid, and if so update the x or y coordinate
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
	
	// Render the player circle based on colour, and render the arms in the correct direction if the player is pulling a block
	public void render() {
		if (col == 0) {
			parent.fill(255);
			parent.stroke(255);
		} else {
			parent.fill(0);
			parent.stroke(0);
		}
		parent.ellipse((x*cellWidth)+cellWidth/2,(y*cellHeight)+cellHeight/2,(cellWidth/3)*2,(cellHeight/3)*2);
		if (pulling) {
			if (pullDir == 'n') {
				parent.ellipse((x*cellWidth)+cellWidth/4,(y*cellHeight)+cellHeight/4,cellWidth/4, cellHeight/4);
				parent.ellipse((x*cellWidth)+3*(cellWidth/4),(y*cellHeight)+cellHeight/4,cellWidth/4,cellHeight/4);
			}
			if (pullDir == 's') {
				parent.ellipse((x*cellWidth)+cellWidth/4,(y*cellHeight)+3*(cellHeight/4),cellWidth/4, cellHeight/4);
				parent.ellipse((x*cellWidth)+3*(cellWidth/4),(y*cellHeight)+3*(cellHeight/4),cellWidth/4,cellHeight/4);
			}
			if (pullDir == 'w') {
				parent.ellipse((x*cellWidth)+cellWidth/4,(y*cellHeight)+(cellHeight/4),cellWidth/4, cellHeight/4);
				parent.ellipse((x*cellWidth)+(cellWidth/4),(y*cellHeight)+3*(cellHeight/4),cellWidth/4,cellHeight/4);
			}
			if (pullDir == 'e') {
				parent.ellipse((x*cellWidth)+3*cellWidth/4,(y*cellHeight)+(cellHeight/4),cellWidth/4, cellHeight/4);
				parent.ellipse((x*cellWidth)+3*(cellWidth/4),(y*cellHeight)+3*(cellHeight/4),cellWidth/4,cellHeight/4);
			}
		}
	}
	
	// Toggle the player colour
	public void toggleCol() {
		if (col == 0) {
			col = 1;
		} else {
			col = 0;
		}
	}
	
	// Return the x coordinate of the player
	public int getX() {
		return x;
	}
	
	// Return the y coordinate of the player
	public int getY() {
		return y;
	}
	
	// Return whether or not the player has the key for the current level
	public boolean hasKey() {
		return hasKey;
	}
	
	// Set hasKey to true when the function is called from the Main class
	public void pickKey() {
		hasKey = true;
	}
	
	// Reset the player data including coordinates, key, colour and pulling
	public void reset() {
		x = 1;
		y = 1;
		hasKey = false;
		col = 1;
		pullDir = 'x';
		pulling = false;
	}
	
	// Toggle the player's pulling, and if it becomes false, set the direction to 'x' (specified as empty direction)
	public void togglePull() {
		pulling = !pulling;
		if (!pulling) {
			pullDir = 'x';
		}
	}
	
	// Set the pull direction to the given char
	public void setPullDir(char dir) {
		pullDir = dir;
	}
	
	// Return whether or not the player is pulling a block
	public boolean isPulling() {
		return pulling;
	}
	
	// Return the char representation of which direction the pulled block is from the player
	public char getPullDir() {
		return pullDir;
	}
}
