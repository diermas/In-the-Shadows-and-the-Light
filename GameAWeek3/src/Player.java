import processing.core.PApplet;

public class Player {

	private int x;
	private int y;	
	private PApplet parent;
	private int cellHeight;
	private int cellWidth;
	
	public Player(int x, int y, PApplet parent, int cellHeight, int cellWidth) {
		this.x = x;
		this.y = y;
		this.parent = parent;
		this.cellHeight = cellHeight;
		this.cellWidth = cellWidth;
	}
	
	public void move() {
		
	}
	
	public void render() {
		parent.fill(255,0,0);
		parent.ellipse(x+cellWidth/2,y+cellHeight/2,cellWidth,cellHeight);
	}
	
	public void update() {
		move();
		render();
	}
}
