package renderableObject;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class Shape extends RenderableObject {
	private ArrayList<Line> lines;
	private int currentState = -1;

	public Shape(Point location) {
		super(location);
		lines = new ArrayList<Line>();
	}

	public void add(Line line) {
		for (int i = currentState + 1; i < lines.size(); i++) {
			lines.remove(i);
		}
		lines.add(line);
		currentState++;
	}

	public void save(String filename) {
		// TODO SAVE
	}

	public void undo() {
		if (currentState != -1) {
			currentState--;
		}
	}

	public void redo() {
		if (currentState < lines.size() - 1) {
			currentState++;
		}
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
	}
}
