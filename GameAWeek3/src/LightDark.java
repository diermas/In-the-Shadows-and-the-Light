import processing.core.PApplet;

public class LightDark extends PApplet{

	private Level[] levels;
	private int height = 1000;
	private int width = 1000;
	private int cells = 10;
	private Player player;
	private int[][] testLevel = {{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1}};
	
	public static void main(String[] args) {
		PApplet.main("LightDark");
	}
	
	public void settings() {
		size(width,height);
	}

	public void setup() {
		player = new Player(cells, height/cells, width/cells, this, 5, 5);
		levels = new Level[1];
		levels[0] = new Level(width,height,10,10,this,testLevel);
		levels[0].printLevel();
	}
	
	public void draw() {
		background(255);
		levels[0].render();
		player.render();
	}
	
	public int checkTile(int x, int y) {
		return testLevel[y][x];
	}
	
	public void keyPressed() {
		if (key == 'W' || key == 'w') {
			player.move('n');
		}
		if (key == 'S' || key == 's') {
			player.move('s');
		}
		if (key == 'A' || key == 'a') {
			player.move('w');
		}
		if (key == 'D' || key == 'd') {
			player.move('e');
		}
		if (key == ' ') {
			levels[0].toggleLevel();
		}
	}

}
