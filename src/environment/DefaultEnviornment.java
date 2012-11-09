package environment;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import renderableObject.RenderableObject;

public class DefaultEnviornment  extends JPanel {
	
	ArrayList<RenderableObject> objects;
	
	public void paint(Graphics g) {
		for(RenderableObject o: objects){
			o.draw(g);
		}
		
	}
}
