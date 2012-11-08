import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;


public abstract class RenderableObject {
	protected Dimension dimension;
	protected Point location;
	protected int priority;
	
	public abstract void draw(Graphics g);
	
	public void move(int x,int y){
		setLocation(new Point(x,y));
	}
	public void move(Point loc){
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


	

}
