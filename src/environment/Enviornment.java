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
		repaint();
	}
	
	public void remove(RenderableObject item){
		objects.remove(item);
		sort();
	}
	
	
	private void sort() {
		int n = objects.size();
		
		for (int pass=1; pass < n; pass++) {  // count how many times
	        // This next loop becomes shorter and shorter
	        for (int i=0; i < n-pass; i++) {
	            if (objects.get(i).getPriority() > objects.get(i+1).getPriority()) {
	                // exchange elements
	            	RenderableObject temp = objects.get(i);
	            	objects.set(i,objects.get(i+1));
	            	objects.set(i+1,temp);

	            }
	        }
	    }
		
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
	
	public ArrayList<RenderableObject> getObjects(){
		return objects;
	}
}
