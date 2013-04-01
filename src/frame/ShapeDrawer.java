package frame;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import renderableObject.Line;
import renderableObject.RenderableObject;
import renderableObject.RenderableShape;

public class ShapeDrawer extends JPanel {

	private Color currentColor = Color.black;
	private RenderableObject currentShape = new RenderableShape(
			this.getLocation());

	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());

		currentShape.draw(g);
	}

	public void setRenderableObject(RenderableObject rs) {
		currentShape = rs;
		repaint();
	}

	public void addLine(Line line) {
		((RenderableShape) currentShape).add(line);
		repaint();
	}

	public RenderableObject getCurrentShape() {
		return currentShape;
	}

	public void setCurrentShape(RenderableShape cShape) {
		currentShape = cShape;
	}

	public Color getCurrentColor() {
		return currentColor;
	}

	public void setCurrentColor(Color currentColor) {
		this.currentColor = currentColor;
	}

	public void undo() {
		try {
			((RenderableShape) currentShape).undo();
			repaint();
		} catch (Exception e) {

		}
	}

	public void redo() {
		try {
			((RenderableShape) currentShape).redo();
			repaint();
		} catch (Exception e) {

		}
	}

}
