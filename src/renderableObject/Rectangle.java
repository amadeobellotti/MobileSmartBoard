package renderableObject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.PrintWriter;

import renderableObject.RenderableObject.ObjectType;

public class Rectangle extends RenderableObject {
	protected static String className = "Rectangle";
	protected int classCount = 0;	
	private Point start, end;
	private int thickness;
	private Color c;
	

	public Rectangle(Point start, Point end, int thickness, Color c) {
		super(new Point(0,0));
		this.start = start;
		this.end = end;
		this.thickness = thickness;
		this.c = c;
		setupBoundary();
		objectType = ObjectType.RECTANGLE;

	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(c);
		g2.setStroke(new BasicStroke(thickness));

		Rectangle2D.Double rect = new Rectangle2D.Double(location.x+thickness, location.y+thickness,
				Math.abs(start.x - end.x), Math.abs(start.y - end.y));
		g2.draw(rect);
		
		if(selected){
			drawBoundingBox(g);
		}
	}

	

	@Override
	public void save(PrintWriter f) {
		f.println("<Object>");
		f.println("<Type> Rectangle </Type>");
		f.println("<Location>" + location.x+","+location.y +"</Location>");
		f.println("<Start>" + start.x+","+start.y +"</Start>");
		f.println("<End>" + end.x+","+end.y +"</End>");
		f.println("<Thickness>" + thickness +"</Thickness>");
		f.println("<Color>" + c.getRed()+","+c.getGreen() +"," +c.getBlue() +"</Color>");
		f.println("</Object>");
	}

	@Override
	public void setupBoundary() {
		dimension = new Dimension(Math.abs(start.x - end.x)+thickness+2, Math.abs(start.y - end.y)+thickness+2);	
	}

	@Override
	public RenderableObject makeCopy() {
		Point s,e;
		s = new Point(start.x,start.y);
		e = new Point(end.x,end.y);
		Rectangle copy = new Rectangle(s, e, thickness, c);
		copy.setLocation(this.getLocation());
		return copy;
	}

}
