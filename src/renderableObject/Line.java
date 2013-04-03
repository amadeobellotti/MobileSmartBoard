package renderableObject;

import java.awt.Color;
import java.awt.Point;
import java.io.PrintWriter;

public class Line {
	public Point start, end;
	public int thickness;
	public Color color;

	public Line(int x1, int y1, int x2, int y2, int thickness, Color c) {
		start = new Point(x1, y1);
		end = new Point(x2, y2);
		this.thickness = thickness;
		color = c;
	}

	public Line(Point s, Point e, int thickness, Color c) {
		start = s;
		end = e;
		this.thickness = thickness;
		color = c;
	}

	public void save(PrintWriter f) {
		f.println("<Line>");

		f.println("<Start>" + start.x + "," + start.y + "</Start>");
		f.println("<End>" + end.x + "," + end.y + "</End>");
		f.println("<Thickness>" + thickness + "</Thickness>");
		f.println("<Color>" + color.getRed() + "," + color.getGreen() + ","
				+ color.getBlue() + "</Color>");
		f.println("</Line>");

	}

}
