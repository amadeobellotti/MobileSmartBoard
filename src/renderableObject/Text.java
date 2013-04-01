package renderableObject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

public class Text extends RenderableObject {

	private String text;
	private Font font;
	private Color c;

	
	public Text(String text){
		this(text, new Font(null,15,Font.BOLD),Color.BLACK);
	}
	
	public Text(String text,Font font,Color c) {
		super(new Point(0,0));
		this.font = font;
		this.c =c;
		this.text = text;
	}

	@Override
	public void draw(Graphics g) {
		System.out.println(text);
		System.out.println(location);
		g.setColor(c);
		g.setFont(font);
		g.drawString(text, location.x, location.y);
	}

	@Override
	public void save(String filename) {
		// TODO Auto-generated method stub

	}

}
