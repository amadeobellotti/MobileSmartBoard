package renderableObject;
import java.awt.Color;
import java.awt.Point;


public class Line {
	public Point start, end;
	public int thickness;
	public Color color;
	
	public Line(int x1,int y1, int x2,int y2, int thickness, Color c){
		start = new Point(x1,y1);
		end = new Point(x2,y2);
		this.thickness = thickness;
		color = c;
	}
	
}
