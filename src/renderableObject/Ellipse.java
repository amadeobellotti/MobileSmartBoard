package renderableObject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Ellipse extends RenderableObject {
	private Point start, end;
	private int thickness;
	private Color c;

	public Ellipse(Point start, Point end, int thickness, Color c) {
		super(new Point(0, 0));
		this.start = start;
		this.end = end;
		this.thickness = thickness;
		this.c = c;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(c);
		g2.setStroke(new BasicStroke(thickness));

		Ellipse2D.Double ellipse = new Ellipse2D.Double(location.x+thickness, location.y+thickness,
				Math.abs(start.x - end.x), Math.abs(start.y - end.y));
		g2.draw(ellipse);

	}

	@Override
	public void save(String filename) {
		// TODO Auto-generated method stub

	}

}
