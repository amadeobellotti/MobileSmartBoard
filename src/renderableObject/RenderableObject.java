package renderableObject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.io.PrintWriter;

public abstract class RenderableObject {

	private static int currentNumberOfObjects = 0;
	protected Dimension dimension;
	protected Point location;
	protected int priority;
	protected static String className = "RenderableObject";
	protected static int classCount = 0;
	private String name;
	protected boolean selected;

	public static enum ObjectType {
		DEFAULT, SHAPE, RECTANGLE, ELLIPSE, TEXT
	};

	protected ObjectType objectType;

	public RenderableObject(Point loc) {
		location = loc;
		setName();
		objectType = ObjectType.DEFAULT;
		priority = currentNumberOfObjects++;
	}

	private void setName() {
		name = className + classCount / 2;
		classCount++;
	}

	public abstract void setupBoundary();

	public abstract RenderableObject makeCopy();

	public abstract void draw(Graphics g);

	public abstract void save(PrintWriter f);

	public void move(int x, int y) {
		setLocation(new Point(x, y));
	}

	public void move(Point loc) {
		setLocation(loc);
	}

	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String toString() {
		return name;
	}

	public void setSelected(boolean b) {
		selected = b;
	}

	protected void drawBoundingBox(Graphics g) {
		g.setColor(Color.GRAY);
		g.drawRect(location.x, location.y, dimension.width, dimension.height);

	}

	public ObjectType getObjectType() {
		return objectType;
	}

}
