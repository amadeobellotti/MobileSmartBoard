package frame;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import renderableObject.Line;
import renderableObject.Shape;

public class ShapeDrawer extends JPanel{
	
	private Color currentColor = Color.black;
	private Shape currentShape = new Shape(this.getLocation());

	public void paint(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		currentShape.draw(g);		
	}
	
	public void addLine(Line line){
		currentShape.add(line);
		repaint();
	}
	
	public Shape getCurrentShape(){
		return currentShape;
	}
	
	public void setCurrentShape(Shape cShape){
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
