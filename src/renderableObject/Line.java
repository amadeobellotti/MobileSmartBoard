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

		f.println("<Start>\n" + start.x + "\n" + start.y + "\n</Start>");
		f.println("<End>\n" + end.x + "\n" + end.y + "\n</End>");
		f.println("<Thickness>\n" + thickness + "\n</Thickness>");
		f.println("<Color>\n" + color.getRed() + "\n" + color.getGreen() + "\n"
				+ color.getBlue() + "\n</Color>");
		f.println("</Line>");

	}

}
