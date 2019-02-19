import processing.core.PApplet;

public class LightDark extends PApplet{

	private Level[] levels;
	private int height = 1000;
	private int width = 1000;
	private Player player;
	
	public static void main(String[] args) {
		PApplet.main("LightDark");
	}
	
	public void settings() {
		size(width,height);
	}

	public void setup() {
		player = new Player(width/2,height/2,this,100,100);
		levels = new Level[1];
		levels[0] = new Level(width,height,10,10,this);
	}
	
	public void draw() {
		background(255);
		levels[0].render();
		player.update();
	}

}
