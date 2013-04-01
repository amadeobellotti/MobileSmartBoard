package frame;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import renderableObject.Line;
import renderableObject.RenderableShape;

public class ShapeDrawer extends JPanel{
	
	private Color currentColor = Color.black;
	private RenderableShape currentShape = new RenderableShape(this.getLocation());

	public void paint(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		currentShape.draw(g);		
	}
	
	public void addLine(Line line){
		currentShape.add(line);
		repaint();
	}
	
	public RenderableShape getCurrentShape(){
		return currentShape;
	}
	
	public void setCurrentShape(RenderableShape cShape){
		currentShape = cShape;
	}

	public Color getCurrentColor() {
		return currentColor;
	}

	public void setCurrentColor(Color currentColor) {
		this.currentColor = currentColor;
	}
	
	public void undo(){
		currentShape.undo();
		repaint();
	}
	
	public void redo(){
		currentShape.redo();
		repaint();
	}
	

}
