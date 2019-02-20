
public class GameCleared {
	
	private LightDark parent;
	private int height;
	private int width;
	
	public GameCleared(LightDark parent, int height, int width) {
		this.parent = parent;
		this.height = height;
		this.width = width;
	}
	
	public void render() {
		parent.fill(255);
		parent.stroke(255);
		parent.rect(0,0,width,height/2);
		parent.fill(0);
		parent.stroke(0);
		parent.rect(0,height/2,width,height/2);
		parent.textSize(80);
		parent.text("GAME",width/2-150,height/2-25);
		parent.fill(255);
		parent.stroke(255);
		parent.text("CLEARED",width/2-150,height/2+75);
		parent.rect((width-500)/2,(height+250)/2,500,250);
		parent.fill(0);
		parent.stroke(0);
		parent.text("QUIT",width/2-100,height/2+275);
	}
}
