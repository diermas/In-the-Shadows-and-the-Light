
public class StartMenu {

	private LightDark parent;
	private int height;
	private int width;
	
	public StartMenu(LightDark parent, int height, int width) {
		this.parent = parent;
		this.height = height;
		this.width = width;
	}
	
	public void render() {
		parent.background(120);
		parent.fill(0);
		parent.stroke(0);
		parent.rect(0,0,width,height/2);
		parent.textSize(80);
		parent.fill(255);
		parent.stroke(255);
		parent.rect(0,height/2,width,height/2);
		parent.text("In the Shadows",width/2-250,height/2-25);
		parent.fill(0);
		parent.stroke(0);
		parent.text("And the Light",width/2-250,height/2+75);
		parent.rect((width-500)/2,(height+250)/2,500,250);
		parent.fill(255);
		parent.stroke(255);
		parent.text("BEGIN",width/2-100,height/2+275);
	}
}
