package renderableObject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.PrintWriter;
import java.util.ArrayList;

import renderableObject.RenderableObject.ObjectType;

public class RenderableShape extends RenderableObject {
	private ArrayList<Line> lines;
	private int currentState = -1;

	public RenderableShape(Point location) {
		super(location);
		lines = new ArrayList<Line>();
	}

	public void add(Line line) {
		for (int i = currentState + 1; i < lines.size(); i++) {
			lines.remove(i);
		}
		lines.add(line);
		currentState++;
		setupBoundary();
		objectType = ObjectType.SHAPE;

	}

	public void save(PrintWriter f) {
		f.println("<Object>");
		f.println("<Type> Shape </Type>");
		f.println("<Location>" + location.x+","+location.y +"</Location>");
		for(Line l:lines){
			l.save(f);
		}
		f.println("</Object>");	}

	public void undo() {
		if (currentState != -1) {
			currentState--;
		}
		setupBoundary();

	}

	public void redo() {
		if (currentState < lines.size() - 1) {
			currentState++;
		}
		setupBoundary();
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		int x1, x2, y1, y2;
		// current state should always be the last thing drawn
		for (int i = 0; i <= currentState; i++) {
			g2.setColor(lines.get(i).color);
			g2.setStroke(new BasicStroke(lines.get(i).thickness));

			x1 = (int) (lines.get(i).start.getX() + location.getX());
			x2 = (int) (lines.get(i).end.getX() + location.getX());
			y1 = (int) (lines.get(i).start.getY() + location.getY());
			y2 = (int) (lines.get(i).end.getY() + location.getY());

			g2.drawLine(x1, y1, x2, y2);
		}

		if (selected) {
			drawBoundingBox(g);
		}
	}

	@Override
	public void setupBoundary() {
		int xMax = 0, yMax = 0;
		for (Line l : lines) {
			if (l.start.x > xMax)
				xMax = l.start.x;
			if (l.end.x > xMax)
				xMax = l.end.x;

			if (l.start.y > yMax)
				yMax = l.start.y;
			if (l.end.y > yMax)
				yMax = l.end.y;

		}

		dimension = new Dimension(xMax, yMax);

	}

	@Override
	public RenderableObject makeCopy() {
		RenderableShape copy = new RenderableShape(this.getLocation());
		for(Line l: lines){
			Point s, e;
			s= new Point(l.start.x,l.start.y);
			e = new Point(l.end.x,l.end.y);
			Line line = new Line(s,e,l.thickness,l.color);
			copy.add(line);
		}
		
		return copy;
	}
}
