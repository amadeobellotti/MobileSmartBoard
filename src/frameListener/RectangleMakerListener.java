package frameListener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import renderableObject.Ellipse;
import renderableObject.Rectangle;
import frame.ShapeDrawer;
import frame.ShapeMaker;

public class RectangleMakerListener extends ShapeMakerListener {

	@Override
	public void mouseReleased(MouseEvent e) {
		end = e.getPoint();
		int thickness = ((ShapeMaker) frame).getThickness();
		Color c = ((ShapeMaker) frame).getColor();
		ShapeDrawer sdrawer = ((ShapeMaker) frame).getShapeDrawer();
		Rectangle rect = new Rectangle(start,end,thickness, c);
		sdrawer.setRenderableObject(rect);
	}
}
