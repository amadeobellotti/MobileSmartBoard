package environment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import renderableObject.RenderableObject;

public abstract class Enviornment extends JPanel {

	
	protected ArrayList<RenderableObject> objects;
	protected Dimension dimension;
	
	public Enviornment(Dimension d){
		objects = new ArrayList<RenderableObject>();
		dimension = d;
		setSize(dimension);
	}
	

	
	public void add(RenderableObject item){
		item.setPriority(objects.size());
		objects.add(item);
		sort();
	}
	
	public void remove(RenderableObject item){
		objects.remove(item);
		sort();
	}
	
	
	private void sort() {
		// TODO create method that sorts objects by priority (higher is "better")
		
	}

	public void paint(Graphics g) {
		drawBackground(g);
		for(RenderableObject o: objects){
			o.draw(g);
		}
		
	}
	
	public void drawBackground(Graphics g){
		g.setColor(Color.white);
		g.drawRect(0, 0,(int) dimension.getWidth(),(int) dimension.getWidth());
		g.fillRect(0, 0,(int) dimension.getWidth(),(int) dimension.getWidth());

		
	}
}
