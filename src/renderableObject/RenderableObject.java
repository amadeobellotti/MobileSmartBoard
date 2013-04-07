package renderableObject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
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
	protected int id;
	protected boolean identified;

	public static enum ObjectType {
		DEFAULT, SHAPE, RECTANGLE, ELLIPSE, TEXT, IMAGE, LATEX
	};

	protected ObjectType objectType;

	public RenderableObject(Point loc) {
		location = loc;
		setName();
		objectType = ObjectType.DEFAULT;
		priority = currentNumberOfObjects++;
		identified = false;
	}

	private void setName() {
		name = className + classCount / 2;
		classCount++;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
	
	public void setIdentified(boolean bool) {
		identified = bool;
	}
	
	protected void drawID(Graphics g) {
		if (identified) {
			g.setColor(Color.BLACK);
			Font f = new Font(null,Font.BOLD,22);
			AffineTransform at = new AffineTransform();
			FontRenderContext frc = new FontRenderContext(at,true,true);
			g.setFont(f);
			int textWidth = (int)(f.getStringBounds(Integer.toString(id),frc).getWidth());
			int textHeight = (int)(f.getStringBounds(Integer.toString(id), frc).getHeight());
			g.setColor(Color.WHITE);
			g.fillRect(location.x-(textWidth*1/4), location.y-(textHeight*3/4), (int)(textWidth*1.5), textHeight);
			g.setColor(Color.BLACK);
			g.drawRect(location.x-(textWidth*1/4), location.y-(textHeight*3/4), (int)(textWidth*1.5), textHeight);
			g.drawString(Integer.toString(id), location.x, location.y);
		}
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
	
	public Point getLocationCopy(){
		return new Point(location.x,location.y);
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
