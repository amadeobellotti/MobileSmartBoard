package frame;

import java.awt.Dimension;

import javax.swing.JFrame;

import renderableObject.RenderableObject;

import frameListener.EllipseMakerListener;
import frameListener.RectangleMakerListener;
import frameListener.ShapeMakerListener;

public class RectangleMaker extends ShapeMaker {
	public RectangleMaker(MainFrame parent, RenderableObject ro) {
		this(parent);
		shapeDrawer.setCurrentShape(ro);
	}

	public RectangleMaker(MainFrame parent) {
		super(parent,new RectangleMakerListener());
		frameListener.setFrame(this);
		btnUndo.setEnabled(false);
		btnRedo.setEnabled(false);

	}
}
