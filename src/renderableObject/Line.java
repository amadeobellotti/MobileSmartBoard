package renderableObject;
import java.awt.Point;


public class Line {
	public Point start, end;
	public int thickness;
	
	public Line(int x1,int y1, int x2,int y2, int thickness){
		start = new Point(x1,y1);
		end = new Point(x2,y2);
		this.thickness = thickness;
	}
	
}
